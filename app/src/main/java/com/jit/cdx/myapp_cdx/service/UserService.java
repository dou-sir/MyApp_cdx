package com.jit.cdx.myapp_cdx.service;

import com.jit.cdx.myapp_cdx.entity.User;

/**
 * Created by 14032 on 2019/12/2.
 */

public interface UserService {

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
    public boolean modifyUser(User user) ;

    /**
     *
     * 检查用户登录
     */
    public String checkLogin(User user);

}
