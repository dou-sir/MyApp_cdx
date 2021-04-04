package com.jit.cdx.myapp_cdx.dao.Impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.cdx.myapp_cdx.dao.UserDao;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.util.DataBaseHelper;


/**
 * Created by 14032 on 2019/12/2.
 */

public class UserDaoImpl implements UserDao {

    private DataBaseHelper dbHelper;

    public UserDaoImpl(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    @Override
    public User findUserByUstate() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "Select * from tb_user where ustate=1";
        Cursor cursor = db.rawQuery(sql,null);
        User user = new User();
        while (cursor.moveToNext()){
            user = new User(
                    cursor.getInt(cursor.getColumnIndex("uid")),//顺序与user构造一致
                    cursor.getString(cursor.getColumnIndex("uname")),
                    cursor.getString(cursor.getColumnIndex("upwd")),
                    cursor.getString(cursor.getColumnIndex("ubirth")),
                    cursor.getInt(cursor.getColumnIndex("usex")),
                    cursor.getString(cursor.getColumnIndex("uphone")),
                    cursor.getString(cursor.getColumnIndex("uaddress")),
                    cursor.getInt(cursor.getColumnIndex("ustate"))
            );
        }
        //关闭连接
        db.close();
        cursor.close();
        return user;
    }

    @Override
    public User findUserByUname(String uname) {
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "Select * from tb_user where uname=?";//尽量不要用*。 where uid=? and nid=?
        //new String[] { }对应sql语句的'?'  有几个'?'就需要几个参数
        Cursor cursor = db.rawQuery(sql,new String[] {uname});
        User user = new User();
//将Cursor中的内容取出
        while (cursor.moveToNext()){
            user = new User(
                    cursor.getInt(cursor.getColumnIndex("uid")),//顺序与user构造一致
                    cursor.getString(cursor.getColumnIndex("uname")),
                    cursor.getString(cursor.getColumnIndex("upwd")),
                    cursor.getString(cursor.getColumnIndex("ubirth")),
                    cursor.getInt(cursor.getColumnIndex("usex")),
                    cursor.getString(cursor.getColumnIndex("uphone")),
                    cursor.getString(cursor.getColumnIndex("uaddress")),
                    cursor.getInt(cursor.getColumnIndex("ustate"))
            );
        }
        //关闭连接
        db.close();
        cursor.close();
        return user;
    }

    @Override
    public User addUser(User user) {

        String sql= "insert into tb_user (uname,upwd,usex,ubirth,uphone,uaddress)values(?,?,?,?,?,?)";
        //获取可写入数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        new String[] { }对应sql语句的'?'  有几个'?'就需要几个参数
        db.execSQL(sql,new Object[]{user.getUname(),user.getUpwd(),user.getUsex(),
                user.getUbirth(),user.getUphone(),user.getUaddress()});
        db.close();

        return user;
    }

    @Override
    public void modifyUser(User user) {
        String sql= "update tb_user set upwd=?,usex=?,ubirth=?,uphone=?,uaddress=?,ustate=? where uid=?";
        //获取可写入数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        new String[] { }对应sql语句的'?'  有几个'?'就需要几个参数
        db.execSQL(sql,new Object[]{user.getUpwd(),user.getUsex(),
                user.getUbirth(),user.getUphone(),user.getUaddress(),user.getUstate(),user.getUid()});
        db.close();
    }
}
