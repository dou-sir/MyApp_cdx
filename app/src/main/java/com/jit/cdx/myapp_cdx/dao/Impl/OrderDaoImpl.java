package com.jit.cdx.myapp_cdx.dao.Impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.cdx.myapp_cdx.dao.OrderDao;
import com.jit.cdx.myapp_cdx.entity.Order;
import com.jit.cdx.myapp_cdx.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/12/9.
 */

public class OrderDaoImpl implements OrderDao {

    private DataBaseHelper dbHelper;

    //改构造方法   能够从Service读取到对应的Activity
    public OrderDaoImpl(Context context) {

        dbHelper = new DataBaseHelper(context);
    }

    //根据uid 查找所有的订单 具体实现
    public List<Order> findAllOrderByUid(int uid) {
        //获取可读数据库
        SQLiteDatabase db= dbHelper.getReadableDatabase();

        String sql="select * from tb_order where uid=?";     //尽量不要用*   请使用具体别名
        //new String[]{uid}    对应sql语句的 ？    有几个 ？  就需要几个参数
        Cursor cursor=db.rawQuery(sql,new String[]{uid+""});
        List<Order> orders=new ArrayList<Order>();

        while (cursor.moveToNext()){

            orders.add(new Order(
                    cursor.getInt(cursor.getColumnIndex("oid")),            //订单编号
                    cursor.getInt(cursor.getColumnIndex("uid")),            //用户编号
                    cursor.getString(cursor.getColumnIndex("date")),        //订单日期
                    cursor.getString(cursor.getColumnIndex("sname")),       //商家名称
                    cursor.getString(cursor.getColumnIndex("ophone")),      //收件人电话
                    cursor.getString(cursor.getColumnIndex("oaddress")),    //送货地址
                    cursor.getString(cursor.getColumnIndex("oitems")),      //订单物品详情
                    cursor.getInt(cursor.getColumnIndex("cost"))         //订单价格
            ));
        }
        //关闭连接
        db.close();
        cursor.close();
        return orders;
    }

    //根据oid 查找具体订单 具体实现
    public Order findOrederByOid(int oid) {
        //获取可读数据库
        SQLiteDatabase db= dbHelper.getReadableDatabase();

        String sql="select * from tb_order where oid=?";     //尽量不要用*   请使用具体别名
        //new String[]{uid}    对应sql语句的 ？    有几个 ？  就需要几个参数
        Cursor cursor=db.rawQuery(sql,new String[]{oid+""});
        Order order=new Order();
        // 将Cursor中的内容取出
        while (cursor.moveToNext()){

            order=new Order(
                    cursor.getInt(cursor.getColumnIndex("oid")),            //订单编号
                    cursor.getInt(cursor.getColumnIndex("uid")),            //用户编号
                    cursor.getString(cursor.getColumnIndex("date")),        //订单日期
                    cursor.getString(cursor.getColumnIndex("sname")),       //商家名称
                    cursor.getString(cursor.getColumnIndex("ophone")),      //收件人电话
                    cursor.getString(cursor.getColumnIndex("oaddress")),    //送货地址
                    cursor.getString(cursor.getColumnIndex("oitems")),      //订单物品详情
                    cursor.getInt(cursor.getColumnIndex("cost"))         //订单价格
            );
        }
        //关闭连接
        db.close();
        cursor.close();
        return order;
    }

    //  修改订单中的信息
    public void ChangeOrder(Order order) {
        int n=order.getOid();
        String sql="update tb_order set ophone=?,oaddress=?,oitems=? where oid=?";
        //获取可写入数据库
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        //如果有多个问号       需要传入多个参数
        db.execSQL(sql,new Object[]{order.getOphone(),order.getOaddress(),order.getOitems(),n});
        db.close();
    }

    //添加order方法 具体实现
    public void addOrder(Order order) {
        String sql = "insert into tb_order (uid,date,sname,ophone,oaddress,oitems,cost) values(?,?,?,?,?,?,?)";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{order.getUid(), order.getDate(),order.getSname(),order.getOphone(),order.getOaddress(),order.getOitems(),order.getCost()});
        db.close();
    }

    //由oid 删除该订单
    public void deleteOrder(int oid) {
        String sql="delete from tb_order where oid=?";
        //获取可写入数据库
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //execSQL 方法无返回值    new Object[]{nid}   nid的值会赋值给delete from tb_note where nid=?的问号
        //如果有多个问号       需要传入多个参数
        db.execSQL(sql,new Object[]{oid});
        db.close();
    }
}
