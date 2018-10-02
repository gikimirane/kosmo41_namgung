package com.study.android.ex43_telephonyex;

import android.graphics.Bitmap;

public class AddressItem {

    private String name;
    private String telNum;
    private Bitmap photo;

    public AddressItem(String name, String telNum, Bitmap photo) {
        this.name = name;
        this.telNum = telNum;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
