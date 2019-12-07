package com.MohamedTaha.Imagine.Quran.mvp.model;

public class ModelSora {
    private String name_sora;
    private int position;
    private String nzol_elsora;
    private String name_part;

    public String getName_part() {
        return name_part;
    }

    public void setName_part(String name_part) {
        this.name_part = name_part;
    }

    public String getNzol_elsora() {
        return nzol_elsora;
    }

    public void setNzol_elsora(String nsol_elsora) {
        this.nzol_elsora = nsol_elsora;
    }


    public ModelSora() {
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
