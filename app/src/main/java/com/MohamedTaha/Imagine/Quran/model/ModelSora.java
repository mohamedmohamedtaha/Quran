package com.MohamedTaha.Imagine.Quran.model;

public class ModelSora {

    private String name_sora;
    private int position;

    public ModelSora() {
    }

    public ModelSora(String name_sora, int position) {
        this.name_sora = name_sora;
        this.position = position;
    }

    public String getName_sora() {
        return name_sora;
    }

    public void setName_sora(String name_sora) {
        this.name_sora = name_sora;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
