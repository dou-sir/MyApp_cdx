package com.jit.cdx.myapp_cdx.entity;

import java.io.Serializable;

/**
 * Created by 14032 on 2019/12/9.
 */

public class Shop implements Serializable{

    private int sid;
    private String sname;
    private String sphone;
    private String saddress;
    private int simg;

    public Shop() {
    }

    public Shop(int sid, String sname, String sphone, String saddress, int simg) {
        this.sid = sid;
        this.sname = sname;
        this.sphone = sphone;
        this.saddress = saddress;
        this.simg = simg;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sphone='" + sphone + '\'' +
                ", saddress='" + saddress + '\'' +
                ", simg=" + simg +
                '}';
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public int getSimg() {
        return simg;
    }

    public void setSimg(int simg) {
        this.simg = simg;
    }
}
