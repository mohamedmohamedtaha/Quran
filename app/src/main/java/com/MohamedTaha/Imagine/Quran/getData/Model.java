package com.MohamedTaha.Imagine.Quran.getData;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by MANASATT on 23/08/17.
 */

public class Model implements Serializable {
     String sora_name;
     String sora_link;
    private String data;
    private String title;
    private String name_shekh;
    long duration;
    long albumId;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    String composer;

    public String getName_shekh() {
        return name_shekh;
    }

    public void setName_shekh(String name_shekh) {
        this.name_shekh = name_shekh;
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

    public String getAlbum() {
        return album;
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

    private String album;
    private String artist;

    public String getSora_name() {
        return sora_name;
    }

    public void setSora_name(String sora_name) {
        this.sora_name = sora_name;
    }

    public String getSora_link() {
        return sora_link;
    }

    public void setSora_link(String sora_link) {
        this.sora_link = sora_link;
    }

    public Model() {

    }

    public Model(String sora_name, String data, String title, String album, String artist) {
        this.sora_name = sora_name;
        this.data = data;
        this.title = title;
        this.album = album;
        this.artist = artist;
    }

    protected Model(Parcel in) {
        this.sora_name = in.readString();
        this.sora_link = in.readString();

    }
}



