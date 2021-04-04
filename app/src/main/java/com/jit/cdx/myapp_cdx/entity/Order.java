package com.jit.cdx.myapp_cdx.entity;

import java.io.Serializable;

/**
 * Created by 14032 on 2019/12/2.
 */

public class Order implements Serializable{
    private int oid;
    private int uid;
    private String date;
    private String sname;
    private String ophone;
    private String oaddress;
    private String oitems;
    private int cost;

    public Order() {
    }

    public Order(int oid, int uid, String date,String sname, String ophone, String oadress, String oitems, int cost) {
        this.oid = oid;
        this.uid = uid;
        this.date = date;
        this.sname=sname;
        this.ophone = ophone;
        this.oaddress = oadress;
        this.oitems = oitems;
        this.cost = cost;
    }

    //构造方法2 在实现添加order中用到 （oid为自增长  不需要传入数值）
    public Order(int uid, String date,String sname,String ophone, String oaddress, String oitems, int cost) {
        this.uid = uid;
        this.date = date;
        this.sname=sname;
        this.ophone=ophone;
        this.oaddress = oaddress;
        this.oitems = oitems;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", date='" + date + '\'' +
                ", sname='" + sname + '\'' +
                ", ophone='" + ophone + '\'' +
                ", oaddress='" + oaddress + '\'' +
                ", oitems='" + oitems + '\'' +
                ", cost=" + cost +
                '}';
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    public String getOadress() {
        return oaddress;
    }

    public void setOadress(String oadress) {
        this.oaddress = oadress;
    }

    public String getOitems() {
        return oitems;
    }

    public void setOitems(String oitems) {
        this.oitems = oitems;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
