
package com.MohamedTaha.Imagine.Quran.model.azan;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hijri {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("weekday")
    @Expose
    private Weekday_ weekday;
    @SerializedName("month")
    @Expose
    private Month_ month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("designation")
    @Expose
    private Designation_ designation;
    @SerializedName("holidays")
    @Expose
    private List<String> holidays = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Weekday_ getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday_ weekday) {
        this.weekday = weekday;
    }

    public Month_ getMonth() {
        return month;
    }

    public void setMonth(Month_ month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation_ getDesignation() {
        return designation;
    }

    public void setDesignation(Designation_ designation) {
        this.designation = designation;
    }

    public List<String> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<String> holidays) {
        this.holidays = holidays;
    }

}
