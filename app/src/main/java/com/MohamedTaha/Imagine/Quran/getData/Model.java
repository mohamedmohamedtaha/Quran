package com.MohamedTaha.Imagine.Quran.getData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MANASATT on 23/08/17.
 */

public class Model implements Parcelable {
    String sora_name;
    String sora_link;

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
    protected Model(Parcel in) {
        this.sora_name = in.readString();
        this.sora_link = in.readString();

    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sora_name);
        dest.writeString(this.sora_link);
    }
}


























