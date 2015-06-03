package com.nordberg.android.happytoday;

import android.media.Image;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Marcus on 2015-05-30.
 */
public class HappyMoment {
    private static final String LOG_TAG = HappyMoment.class.getSimpleName();
    private String desc;
    private Date date;
    private Image image;

    public HappyMoment(String desc) {
        this.desc = desc;
        this.date = new Date();
    }

    public HappyMoment(String desc, String date) {
        this.desc = desc;
        try {
            this.date = DateFormat.getDateInstance().parse(date);
        } catch (ParseException e) {
            Log.d(LOG_TAG, "Failed to parse date " + date);
            e.printStackTrace();
        }
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
