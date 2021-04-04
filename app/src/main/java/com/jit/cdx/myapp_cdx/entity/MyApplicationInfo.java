package com.jit.cdx.myapp_cdx.entity;

import android.app.Application;

/**
 * Created by 14032 on 2019/12/10.
 */

public class MyApplicationInfo extends Application{

    public User user = new User("-1","-1");
    public String cart="";
    public String track="";
    public int sid;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }
}
