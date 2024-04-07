package com.example.p1_boil;

public class Records {

    public String Nazwa;
    public Integer Czas;
    public String Po;
    public String Przed;
    public Integer id;

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public Integer getCzas() {
        return Czas;
    }

    public void setCzas(Integer czas) {
        this.Czas = czas;
    }

    public String getPo() {
        return Po;
    }

    public void setPo(String po) {
        Po = po;
    }

    public String getPrzed() {
        return Przed;
    }

    public void setPrzed(String przed) {
        Przed = przed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Records(String nazwa, Integer czas, String po, String przed, Integer id) {
        Nazwa = nazwa;
        Czas = czas;
        Po = po;
        Przed = przed;
        this.id = id;
    }
}
