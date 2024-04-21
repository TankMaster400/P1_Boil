package com.example.p1_boil;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Data {

    ArrayList<Node> nodes = new ArrayList<Node>() ;
    ArrayList<Czyn> czyns = new ArrayList<Czyn>() ;

    void licz(){
//        addRecord("A", 3, "1", "2");
//        addRecord("B", 4, "2", "3");
//        addRecord("C", 6, "2", "4");
//        addRecord("D", 7, "3", "5");
//        addRecord("E", 1, "5", "7");
//        addRecord("F", 2, "4", "7");
//        addRecord("G", 3, "4", "6");
//        addRecord("H", 4, "6", "7");
//        addRecord("I", 1, "7", "8");
//        addRecord("J", 2, "8", "9");

        //SZUKANIE PUNKTU STARTOWEGO I KOŃCOWEGO
        int start = -1;
        int end = -1;
        for(int i=0; i<nodes.size();i++){
            //startowy
            if(nodes.get(i).prev.size()==0){
                if(start!=-1){
                    System.out.println("Nie może być więcej niż jeden punkt startowy!"); //Obsłużyć!!!
                }else{
                    start = i;
                    System.out.println("START: " + i);
                }
            }
            //końcowy
            if(nodes.get(i).next.size()==0){
                if(end!=-1){
                    System.out.println("Nie może być więcej niż jeden punkt końcowy!"); //Obsłużyć!!!
                }else{
                    end = i;
                    System.out.println("KONIEC: "+i);
                }
            }
        }

        //LICZENIE PK
        time_pk(nodes.get(start));

        //LICZENIE KP
        for(int i=0; i<nodes.size();i++) {
            nodes.get(i).time_kp = nodes.get(end).time_pk;
            nodes.get(i).pom_int = 0;
        }
        time_kp(nodes.get(end));

        //LICZENIE L
        for(int i=0; i<nodes.size();i++) {
            nodes.get(i).time_l = nodes.get(i).time_kp - nodes.get(i).time_pk;
        }

        //wyświetlanie wyników w terminalu
        for(int i=0; i<nodes.size();i++){
            System.out.println("Node "+nodes.get(i).name+" | PK: "+nodes.get(i).time_pk+" | KP: "+nodes.get(i).time_kp+" | L: "+nodes.get(i).time_l);
        }

        findSK(nodes.get(end));
    }
    void time_pk(Node oper){
        for(int i=0;i<oper.next.size();i++){
            if(oper.time_pk + oper.next.get(i).time>oper.next.get(i).next.time_pk){
                oper.next.get(i).next.time_pk = oper.time_pk + oper.next.get(i).time;
            }

            oper.next.get(i).next.pom_int++;

            if(oper.next.get(i).next.prev.size()==1){
                time_pk(oper.next.get(i).next);
            } else if(oper.next.get(i).next.pom_int == oper.next.get(i).next.prev.size()){
                time_pk(oper.next.get(i).next);
            }
        }
    }
    void time_kp(Node oper){
        for(int i=0;i<oper.prev.size();i++){
            if(oper.time_kp - oper.prev.get(i).time<oper.prev.get(i).prev.time_kp){
                oper.prev.get(i).prev.time_kp = oper.time_kp - oper.prev.get(i).time;
            }

            oper.prev.get(i).prev.pom_int++;

            if(oper.prev.get(i).prev.next.size()==1){
                time_kp(oper.prev.get(i).prev);
            } else if(oper.prev.get(i).prev.pom_int == oper.prev.get(i).prev.next.size()){
                time_kp(oper.prev.get(i).prev);
            }
        }
    }

    void addRecord(String nazwa_czyn, Integer czas_czyn, String OD, String DO){
        //UWAGA DANE WPISYWANE NA SZTYWNO W LICZ()
        Integer od_int = -1;
        Integer do_int = -1;

        //szukanie OD
        boolean find = false;
        Integer i=0;
        while(find!=true&&i<=nodes.size()){
            if(i==nodes.size()){
                nodes.add(new Node(OD));
                od_int = i;
                find = true;
            }else{
                //System.out.println((nodes.get(i).name+" "+OD+" "+(Objects.equals(nodes.get(i).name, OD))));
                if(Objects.equals(nodes.get(i).name, OD)){
                    od_int = i;
                    find = true;
                }
            }
            i++;
        }

        //szukanie DO
        find = false;
        i=0;
        while(find!=true&&i<=nodes.size()){
            if(i==nodes.size()){
                nodes.add(new Node(DO));
                do_int = i;
                find = true;
            }else{
                if(Objects.equals(nodes.get(i).name, DO)){
                    do_int = i;
                    find = true;
                }
            }
            i++;
        }

        //dodanie czynności razem ze wszystkimi relacjami
        czyns.add(new Czyn(nazwa_czyn, czas_czyn));

        nodes.get(od_int).next.add(czyns.get(czyns.size()-1));
        nodes.get(do_int).prev.add(czyns.get(czyns.size()-1));
        czyns.get(czyns.size()-1).next = nodes.get(do_int);
        czyns.get(czyns.size()-1).prev = nodes.get(od_int);
    }

    void findSK(Node looking){
        //System.out.println(looking.name);
        int i = 0;
        while(looking.prev.get(i).prev.time_pk != looking.time_pk - looking.prev.get(i).time){
            i++;
        }
        looking.prev.get(i).prev.sk = looking;
        if(looking.prev.get(i).prev.prev.size() >0){
            findSK(looking.prev.get(i).prev);
        }
    }
}
