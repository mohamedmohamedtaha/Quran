
package com.MohamedTaha.Imagine.Quran.model.azan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offset {

    @SerializedName("Imsak")
    @Expose
    private Integer imsak;
    @SerializedName("Fajr")
    @Expose
    private Integer fajr;
    @SerializedName("Sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("Dhuhr")
    @Expose
    private Integer dhuhr;
    @SerializedName("Asr")
    @Expose
    private Integer asr;
    @SerializedName("Maghrib")
    @Expose
    private Integer maghrib;
    @SerializedName("Sunset")
    @Expose
    private Integer sunset;
    @SerializedName("Isha")
    @Expose
    private Integer isha;
    @SerializedName("Midnight")
    @Expose
    private Integer midnight;

    public Integer getImsak() {
        return imsak;
    }

    public void setImsak(Integer imsak) {
        this.imsak = imsak;
    }

    public Integer getFajr() {
        return fajr;
    }

    public void setFajr(Integer fajr) {
        this.fajr = fajr;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(Integer dhuhr) {
        this.dhuhr = dhuhr;
    }

    public Integer getAsr() {
        return asr;
    }

    public void setAsr(Integer asr) {
        this.asr = asr;
    }

    public Integer getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(Integer maghrib) {
        this.maghrib = maghrib;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public Integer getIsha() {
        return isha;
    }

    public void setIsha(Integer isha) {
        this.isha = isha;
    }

    public Integer getMidnight() {
        return midnight;
    }

    public void setMidnight(Integer midnight) {
        this.midnight = midnight;
    }

}
