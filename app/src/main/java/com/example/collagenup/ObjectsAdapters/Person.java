package com.example.collagenup.ObjectsAdapters;

public class Person {
    private String nazwisko;
    private String icoone_ilosc;
    private String icoone_suma;
    private String pierwszy_icoone;
    private String pierwszy_ost_pakiet;
    private String ilosc_pakietow;
    private String ilosc_gratisow;

    public Person(String nazwisko, String icoone_ilosc, String icoone_suma, String pierwszy_icoone, String pierwszy_ost_pakiet, String ilosc_pakietow, String ilosc_gratisow) {
        this.nazwisko = nazwisko;
        this.icoone_ilosc = icoone_ilosc;
        this.icoone_suma = icoone_suma;
        this.pierwszy_icoone = pierwszy_icoone;
        this.pierwszy_ost_pakiet = pierwszy_ost_pakiet;
        this.ilosc_pakietow = ilosc_pakietow;
        this.ilosc_gratisow = ilosc_gratisow;
    }

    public String getNazwiskonazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getIcoone_ilosc() {
        return icoone_ilosc;
    }

    public void setIcoone_ilosc(String icoone_ilosc) {
        this.icoone_ilosc = icoone_ilosc;
    }

    public String getIcoone_suma() {
        return icoone_suma;
    }

    public void setIcoone_suma(String icoone_suma) {
        this.icoone_suma = icoone_suma;
    }

    public String getPierwszy_icoone() {
        return pierwszy_icoone;
    }

    public void setPierwszy_icoone(String pierwszy_icoone) {
        this.pierwszy_icoone = pierwszy_icoone;
    }

    public String getPierwszy_ost_pakiet() {
        return pierwszy_ost_pakiet;
    }

    public void setPierwszy_ost_pakiet(String pierwszy_ost_pakiet) {
        this.pierwszy_ost_pakiet = pierwszy_ost_pakiet;
    }

    public String getIlosc_pakietow() {
        return ilosc_pakietow;
    }

    public void setIlosc_pakietow(String ilosc_pakietow) {
        this.ilosc_pakietow = ilosc_pakietow;

    }

    public String getIlosc_gratisow() {
        return ilosc_gratisow;
    }

    public void setIlosc_gratisow(String ilosc_gratisow) {
        this.ilosc_gratisow = ilosc_gratisow;

    }
}