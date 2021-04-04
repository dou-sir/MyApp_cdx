package com.jit.cdx.myapp_cdx.dao.Impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.cdx.myapp_cdx.dao.GoodsDao;
import com.jit.cdx.myapp_cdx.entity.Goods;
import com.jit.cdx.myapp_cdx.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/12/9.
 */

public class GoodsDaoImpl implements GoodsDao {
    private DataBaseHelper dbHelper;
    public GoodsDaoImpl(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    @Override
    public List<Goods> findGoodsByGname(String gname) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where gname like '%"+gname+"%'";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数
        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findGoodsBySid(int sid1) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=?";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数
        String sid=toString().valueOf(sid1);
        Cursor cursor=db.rawQuery(sql,new String[]{sid});
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;

    }

    @Override
    public Goods findGoodsByGid(int gid1){

        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where gid=?";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数
        String gid=toString().valueOf(gid1);
        Cursor cursor=db.rawQuery(sql,new String[]{gid});
        Goods goods=new Goods();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods=new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            );
        }
        //关闭连接
        db.close();
        cursor.close();
        return goods;
    }

    @Override
    public List<Goods> findAllGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数
        Cursor cursor=db.rawQuery(sql,null);
        List<Goods>goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){
            Integer gid=cursor.getInt(cursor.getColumnIndex("gid"));
            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    public Goods findAllGoods1() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数
        Cursor cursor=db.rawQuery(sql,null);
        Goods goods=new Goods();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods=new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            );
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findFruitGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=7";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数

        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findVegGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=8";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数

        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findMeatGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=9";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数

        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findMilkGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=10";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数

        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findWineGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=11";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数

        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findFoodGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=12";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数

        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findBreadGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=13";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数

        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }

    @Override
    public List<Goods> findOilGoods() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String sql="select * from tb_goods where sid=14";//尽量不要用* 请使用具体列名
        //new String[]{uid}   对应sql语句的？  有几个？就需要几个参数

        Cursor cursor=db.rawQuery(sql,null);
        List<Goods> goods=new ArrayList<Goods>();
        //将Cursor中的内容取出
        while(cursor.moveToNext()){

            goods.add(new Goods(
                    cursor.getInt(cursor.getColumnIndex("gid")),
                    cursor.getString(cursor.getColumnIndex("gname")),
                    cursor.getInt(cursor.getColumnIndex("sid")),
                    cursor.getInt(cursor.getColumnIndex("price")),
                    cursor.getInt(cursor.getColumnIndex("gimg"))
            ));
        }
        //关闭连接
        db.close();
        cursor.close();

        return goods;
    }
//    @Override
//    public void addGoods(Goods goods) {
//        String sql="insert into tb_goods (gname,sid,price,gimg)values(?,?,?,?)";
//        //获取可写入数据库
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
////        new String[] { }对应sql语句的'?'  有几个'?'就需要几个参数
//        db.execSQL(sql,new Object[]{goods.getGname(),goods.getSid(),goods.getPrice(),goods.getGimg()});
//        db.close();
//
//
//
//    }
//
//    @Override
//    public void modifyGoods(Goods goods) {
//        String sql= "update tb_goods set gname=?,sid=?,price=?,gimg=? where gid=?";
//        //获取可写入数据库
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
////        new String[] { }对应sql语句的'?'  有几个'?'就需要几个参数
//        db.execSQL(sql,new Object[]{goods.getGname(),goods.getSid(),goods.getPrice(),goods.getGimg(),goods.getGid()});
//        db.close();
//
//    }
//
//    @Override
//    public void delGoodsByGid(int gid) {
//        String sql="delete from tb_goods where gid=?";
//        //获取可写入数据库
//        SQLiteDatabase db=dbHelper.getWritableDatabase();
//
//        //如果有多个？  需要传入多个参数
//        db.execSQL(sql,new Object[]{gid});
//        db.close();
//
//    }

}
