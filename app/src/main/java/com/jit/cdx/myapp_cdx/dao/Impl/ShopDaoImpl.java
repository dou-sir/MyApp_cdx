package com.jit.cdx.myapp_cdx.dao.Impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.cdx.myapp_cdx.dao.ShopDao;
import com.jit.cdx.myapp_cdx.entity.Shop;
import com.jit.cdx.myapp_cdx.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14032 on 2019/12/9.
 */

public class ShopDaoImpl implements ShopDao {

    private DataBaseHelper dbHelper;

    public ShopDaoImpl(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    @Override
    public Shop findShopBySid(int sid) {
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "Select * from tb_shop where sid=?";//尽量不要用*。 where uid=? and nid=?
        //new String[] { }对应sql语句的'?'  有几个'?'就需要几个参数
        Cursor cursor = db.rawQuery(sql,new String[] {sid+""});
        Shop shop = new Shop();
//将Cursor中的内容取出
        while (cursor.moveToNext()){
            shop = new Shop(
                    cursor.getInt(cursor.getColumnIndex("sid")),//顺序与user构造一致
                    cursor.getString(cursor.getColumnIndex("sname")),
                    cursor.getString(cursor.getColumnIndex("sphone")),
                    cursor.getString(cursor.getColumnIndex("saddress")),
                    cursor.getInt(cursor.getColumnIndex("simg"))
            );
        }
        //关闭连接
        db.close();
        cursor.close();
        return shop;
    }

    @Override
    public List<Shop> findAllShop() {
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "Select * from tb_shop ";//尽量不要用*
        Cursor cursor = db.rawQuery(sql,null);
        List<Shop> shops = new ArrayList<Shop>();

        while (cursor.moveToNext()){

            shops.add(new Shop(
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getString(cursor.getColumnIndex("sname")),
                    cursor.getString(cursor.getColumnIndex("sphone")),
                    cursor.getString(cursor.getColumnIndex("saddress")),
                    cursor.getInt(cursor.getColumnIndex("simg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();
        return shops;
    }

    @Override
    public List<Shop> findShopBySname(String sname) {
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "Select * from tb_shop where sname like '%"+sname+"%'";//尽量不要用*
        Cursor cursor = db.rawQuery(sql,null);
        List<Shop> shops = new ArrayList<Shop>();

        while (cursor.moveToNext()){

            shops.add(new Shop(
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getString(cursor.getColumnIndex("sname")),
                    cursor.getString(cursor.getColumnIndex("sphone")),
                    cursor.getString(cursor.getColumnIndex("saddress")),
                    cursor.getInt(cursor.getColumnIndex("simg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();
        return shops;
    }
}
