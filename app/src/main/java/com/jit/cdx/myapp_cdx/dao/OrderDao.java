package com.jit.cdx.myapp_cdx.dao;

import com.jit.cdx.myapp_cdx.entity.Order;

import java.util.List;

/**
 * Created by DELL on 2019/12/9.
 */

public interface OrderDao {

    //根据uid 查找所有的订单
    public List<Order> findAllOrderByUid(int uid);

    //根据oid 查找具体订单
    public Order findOrederByOid(int oid);

    //传入新的order  修改订单中的信息
    public void ChangeOrder(Order order);

    //增加新的订单
    public void addOrder(Order order);
    //由oid 删除该订单
    public void deleteOrder(int oid);

}
