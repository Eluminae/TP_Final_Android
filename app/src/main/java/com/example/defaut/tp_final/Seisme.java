package com.example.defaut.tp_final;


import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * Created by p1406206 on 22/03/2016.
 */
public class Seisme {
    Float mag;
    String place;
    String time;
    Long updated;
    String url;
    String detail;
    Integer tsunami;
    Double longitude;
    Double latitude;
    Double depth;


    public String toString(){
        //return "mag:"+mag+" place:"+place+" time:"+time+" updated:"+updated+" updated:"+updated+" url:"+url+" detail:"+detail+" tsunami:"+tsunami;

        return "magnitude : " + mag + " \nlieu : " + place + " \nDate : " + time + " :: " + longitude + latitude + depth;
    }


    public void setLocation (String str){

        String longitudeStr = str;
        String latitudeStr = str;
        String depthStr = str;


        longitudeStr = longitudeStr.substring(longitudeStr.indexOf("[") + 1);
        longitudeStr = longitudeStr.substring(0, longitudeStr.indexOf(","));

        latitudeStr = latitudeStr.substring(latitudeStr.indexOf(",") + 1);
        latitudeStr = latitudeStr.substring(0, latitudeStr.indexOf(","));

        depthStr = depthStr.substring(depthStr.indexOf(",") + 1);
        depthStr = depthStr.substring(depthStr.indexOf(",") + 1);
        depthStr = depthStr.substring(0, depthStr.indexOf("]"));


        longitude = new Double(longitudeStr);
        latitude = new Double(latitudeStr);
        depth = new Double(depthStr);

    }


    public Float getMag() {
        return mag;
    }

    public void setMag(Float mag) {
        this.mag = mag;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(Long time) {this.time = new SimpleDateFormat("dd/MM/yyyy H:m:s").format(time);}

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getTsunami() {
        return tsunami;
    }

    public void setTsunami(Integer tsunami) {
        this.tsunami = tsunami;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }
}
