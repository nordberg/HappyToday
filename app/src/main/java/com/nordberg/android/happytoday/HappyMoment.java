package com.nordberg.android.happytoday;

import android.media.Image;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Marcus on 2015-05-30.
 */
public class HappyMoment {
    private String desc;
    private Date date;
    private Image image;

    public HappyMoment(String desc) {
        this.desc = desc;
        this.date = new Date();
    }

    public HappyMoment(String desc, Image image) {
        this.desc = desc;
        this.date = new Date();
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return DateFormat.getDateInstance().format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
