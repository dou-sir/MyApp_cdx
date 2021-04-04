package com.jit.cdx.myapp_cdx.service.Impl;


import android.app.AlertDialog;
import android.content.Context;

import com.jit.cdx.myapp_cdx.dao.Impl.UserDaoImpl;
import com.jit.cdx.myapp_cdx.dao.UserDao;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.UserService;
import com.jit.cdx.myapp_cdx.util.DataBaseHelper;

/**
 * Created by 14032 on 2019/12/2.
 */

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(Context context) {
        userDao = new UserDaoImpl(context);
    }

    @Override
    public User findUserByUname(String uname) {
        return userDao.findUserByUname(uname);
    }

    @Override
    public User findUserByUstate() {
        return userDao.findUserByUstate();//todo
//        if (userDao.findUserByUstate().getUid()!=0)
//            return userDao.findUserByUstate();
//        else
//            return new User("-1","-1");

    }

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean modifyUser(User user) {
        try {
            userDao.modifyUser(user);
            return true;
        }catch (Exception e){
            //
            return false;
        }
    }

    @Override
    public String checkLogin(User user) {
        User findUser=userDao.findUserByUname(user.getUname());
        //2.可能还需要对数据再次进行判断之类的操作
        //findUser 对象判断
        if(null!=findUser){
            //user中密码与findUser中密码匹配
            if(user.getUpwd().equals(findUser.getUpwd())){


                return "success";
            }
            return "uPwdErr";
        }
        return "uNameErr";
    }
}
