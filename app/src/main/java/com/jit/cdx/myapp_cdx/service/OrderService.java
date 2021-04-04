package com.jit.cdx.myapp_cdx.service;

import com.jit.cdx.myapp_cdx.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2019/12/9.
 */

public interface OrderService {

    //根据uid 查找所有的订单
    public List<Order> findAllOrderByUid(int uid);

    //根据oid 查找具体订单
    public Order findOrederByOid(int oid);

    //根据uid 查找所有的订单 返回值为存储map的list
    public  List<Map<String,Object>> findAllNotesByUid2(int uid);

    //新加 根据order 找到该订单中的所有物品信息 返回值为存储map的list
    public List<Map<String,Object>> getOrderDetails(Order order);

    //传入新的order  修改订单中的信息
    public void ChangeOrder(Order order);

    //增加新的订单
    public void addOrder(Order order);
    //由oid 删除该订单
    public void deleteOrder(int oid);

}
