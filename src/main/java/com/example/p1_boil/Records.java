package com.example.p1_boil;

public class Records {

    public String Nazwa;
    public Integer Czas;
    public String Po;
    public String Przed;

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

    public Records(String nazwa, Integer Czas, String po, String przed) {
        Nazwa = nazwa;
        this.Czas = Czas;
        Po = po;
        Przed = przed;
    }
}
