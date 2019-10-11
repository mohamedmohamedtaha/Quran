package com.MohamedTaha.Imagine.Quran.model;

public class ImageModel {
    private String name_sora;
    private int url_image;
    private String type_sora;
    String sora_link;
    private String data;
    private String album;
    private String artist;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAlbum() {
        return album;
    }

    public ImageModel(String name_sora, int position) {
        this.name_sora = name_sora;
        this.position = position;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSora_link() {
        return sora_link;
    }

    public void setSora_link(String sora_link) {
        this.sora_link = sora_link;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName_shekh() {
        return name_shekh;
    }

    public void setName_shekh(String name_shekh) {
        this.name_shekh = name_shekh;
    }

    private String title;
    private String name_shekh;
    public String getType_sora() {
        return type_sora;
    }

    public void setType_sora(String type_sora) {
        this.type_sora = type_sora;
    }

    public ImageModel(String name_shekh, int url_image, String type_sora,int position) {
        this.name_shekh = name_shekh;
        this.url_image = url_image;
        this.type_sora = type_sora;
        this.position = position;
    }

    public ImageModel() {
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
