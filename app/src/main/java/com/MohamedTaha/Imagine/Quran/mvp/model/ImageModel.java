package com.MohamedTaha.Imagine.Quran.mvp.model;

public class ImageModel {
    private String name_sora;
    private int url_image;
    private String type_sora;
    String sora_link;
    private String name_shekh;
    private int position;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageModel(String name_shekh, int url_image, String type_sora, int position) {
        this.name_shekh = name_shekh;
        this.url_image = url_image;
        this.type_sora = type_sora;
        this.position = position;
    }

    public ImageModel() {
    }

    public ImageModel(String name_sora, int position) {
        this.name_sora = name_sora;
        this.position = position;
    }

    public String getSora_link() {
        return sora_link;
    }

    public void setSora_link(String sora_link) {
        this.sora_link = sora_link;
    }

    public String getName_shekh() {
        return name_shekh;
    }

    public void setName_shekh(String name_shekh) {
        this.name_shekh = name_shekh;
    }

    public String getType_sora() {
        return type_sora;
    }

    public String getName_sora() {
        return name_sora;
    }

    public void setName_sora(String name_sora) {
        this.name_sora = name_sora;
    }

    public int getUrl_image() {
        return url_image;
    }

    public void setUrl_image(int url_image) {
        this.url_image = url_image;
    }
}