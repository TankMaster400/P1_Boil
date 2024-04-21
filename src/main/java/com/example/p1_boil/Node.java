package com.example.p1_boil;

import java.util.ArrayList;

public class Node {
    String name;
    Integer time_pk = 0;
    Integer time_kp = 0;
    Integer time_l =0;
    Integer pom_int = 0;
    Node sk = null;
    ArrayList<Czyn> next= new ArrayList<Czyn>();
    ArrayList<Czyn> prev = new ArrayList<Czyn>();

    Node(String nazwa){
        name = nazwa;
    }

}
