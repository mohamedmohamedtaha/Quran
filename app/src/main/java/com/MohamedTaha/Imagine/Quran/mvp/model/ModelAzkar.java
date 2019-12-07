package com.MohamedTaha.Imagine.Quran.mvp.model;

public class ModelAzkar {
    public String getName_azkar() {
        return name_azkar;
    }

    public void setName_azkar(String name_azkar) {
        this.name_azkar = name_azkar;
    }

    private String name_azkar;

    private String describe_azkar;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDescribe_azkar() {
        return describe_azkar;
    }

    public void setDescribe_azkar(String describe_azkar) {
        this.describe_azkar = describe_azkar;
    }
}
