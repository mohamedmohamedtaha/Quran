package com.MohamedTaha.Imagine.Quran.getData;

import java.io.Serializable;

/**
 * Created by MANASATT on 25/06/17.
 */

public class Parts implements Serializable {
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;
    //The variable for Name Sora
    private String nameSora;

    public String getTimeNzol() {
        return timeNzol;
    }


    private String timeNzol;

    //The variable for Number Page
    private  String numberPageOrLink;

    public String getNameSora() {
        return nameSora;
    }

    public String getNumberPageOrLink() {
        return numberPageOrLink;
    }

    public Parts(String nameSora, String numberPageOrLink) {
        this.nameSora = nameSora;
        this.numberPageOrLink = numberPageOrLink;
    }
    public Parts(String nameSora, String numberPageOrLink,String data,String timeNzol) {
        this.nameSora = nameSora;
        this.numberPageOrLink = numberPageOrLink;
        this.data = data;
        this.timeNzol = timeNzol;
    }
    public Parts( int name , String numberPageOrLink) {
        this.numberPageOrLink = numberPageOrLink;
        this.numberPageOrLin = name;
    }
    int  numberPageOrLin;
    public Parts() {

    }
}

