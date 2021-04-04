package com.jit.cdx.myapp_cdx.entity;

/**
 * Created by 14032 on 2019/12/2.
 */

public class Goods {
    private int gid;
    private String gname;
    private int sid;//店铺
    private int price;
    private int gimg;//图片

    public Goods() {
    }

    public Goods(String gname, int sid, int price) {
        this.gname = gname;
        this.sid = sid;
        this.price = price;
    }

    public Goods(int gid, String gname, int sid, int price, int gimg) {

        this.gid = gid;
        this.gname = gname;
        this.sid = sid;
        this.price = price;
        this.gimg = gimg;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", sid=" + sid +
                ", price=" + price +
                ", gimg=" + gimg +
                '}';
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGimg() {
        return gimg;
    }

    public void setGimg(int gimg) {
        this.gimg = gimg;
    }
}
