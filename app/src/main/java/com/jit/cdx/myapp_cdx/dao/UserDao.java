package com.jit.cdx.myapp_cdx.dao;

import com.jit.cdx.myapp_cdx.entity.User;


/**
 * Created by 14032 on 2019/12/2.
 */

public interface UserDao {

    /**
     * 查询单个用户
     */
    public User findUserByUname(String uname) ;

    public User findUserByUstate() ;

    /**
     * 添加用户
     * @return
     */
    public User addUser(User user) ;

    /**
     * 修改用户
     * @return
     */
    public void modifyUser(User user) ;
}
