package com.jit.cdx.myapp_cdx.entity;

import java.io.Serializable;

/**
 * Created by 14032 on 2019/12/2.
 */

public class User implements Serializable{
    private int uid;
    private String uname;
    private String upwd;
    private String ubirth;
    private int usex;
    private String uphone;
    private String uaddress;
    private int ustate = 0;

    public User() {
    }

    public User(int uid, String uname, String upwd, String ubirth, int usex, String uphone, String uaddress, int ustate) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.ubirth = ubirth;
        this.usex = usex;
        this.uphone = uphone;
        this.uaddress = uaddress;
        this.ustate = ustate;
    }


    public User(String uname, String upwd) {
        this.uname = uname;
        this.upwd = upwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", ubirth='" + ubirth + '\'' +
                ", usex=" + usex +
                ", uphone='" + uphone + '\'' +
                ", uaddress='" + uaddress + '\'' +
                ", ustate=" + ustate +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUbirth() {
        return ubirth;
    }

    public void setUbirth(String ubirth) {
        this.ubirth = ubirth;
    }

    public int getUsex() {
        return usex;
    }

    public void setUsex(int usex) {
        this.usex = usex;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public int getUstate() {
        return ustate;
    }

    public void setUstate(int ustate) {
        this.ustate = ustate;
    }
}
