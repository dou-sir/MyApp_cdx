package com.jit.cdx.myapp_cdx.service.Impl;

import android.content.Context;

import com.jit.cdx.myapp_cdx.dao.Impl.OrderDaoImpl;
import com.jit.cdx.myapp_cdx.dao.OrderDao;
import com.jit.cdx.myapp_cdx.entity.Order;
import com.jit.cdx.myapp_cdx.service.GoodsService;
import com.jit.cdx.myapp_cdx.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2019/12/9.
 */

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private Context mcontext;

    private GoodsService goodsService;

    public OrderServiceImpl(Context context){
        orderDao=new OrderDaoImpl(context);
        mcontext = context;
    }


    @Override
    public List<Order> findAllOrderByUid(int uid) {
        return orderDao.findAllOrderByUid(uid);
    }

    @Override
    public Order findOrederByOid(int oid) {
        return orderDao.findOrederByOid(oid);
    }

    @Override
    public void ChangeOrder(Order order) {
        try{
            orderDao.ChangeOrder(order);
            System.out.println("更改成功");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public List<Map<String, Object>> findAllNotesByUid2(int uid) {
        List<Map<String,Object>> orders=new ArrayList<Map<String,Object>>();
        //数据改造
        List<Order> orderslist=orderDao.findAllOrderByUid(uid);

        for(int i=0;i<orderslist.size();i++){

            Map<String,Object> map=new HashMap<String, Object>();
            map.put("oid",orderslist.get(i).getOid());
            map.put("uid",orderslist.get(i).getUid());
            map.put("date",orderslist.get(i).getDate());
            map.put("sname",orderslist.get(i).getSname());
            map.put("ophone",orderslist.get(i).getOphone());
            map.put("oaddress",orderslist.get(i).getOaddress());
            map.put("oitems",orderslist.get(i).getOitems());
            map.put("cost",orderslist.get(i).getCost());

            //在查看历史订单时 显示第一个商品名称
            String a=map.get("oitems").toString();
            String b []  = a.split(",");
            int num=Integer.parseInt(b[0]);

            goodsService=new GoodsServiceImpl(mcontext);
            map.put("oitemname",goodsService.findGoodsByGid(num).getGname()+"等商品");


            orders.add(map);
        }
        return orders;
    }

    @Override
    public List<Map<String, Object>> getOrderDetails(Order order) {
        List<Map<String,Object>> itemdetails=new ArrayList<Map<String, Object>>();

        //获取order 的item中的信息 用，来分割字符串  返回一个数组
        String a=order.getOitems();
        String b []  = a.split(",");
        //将数组里的东西取出来，存储到list<String>中
        List<String>  items = new ArrayList<>();
        for(int i=0;i<b.length;i++){
            items.add(b[i]);
        }


        //数据改造
        for(int i=0;i<items.size();i=i+2){

            Map<String,Object> map=new HashMap<String, Object>();

            //重新定义一个goodsService并且调用其中的方法
            goodsService=new GoodsServiceImpl(mcontext);
            //将items 中的gid 从String 型 改为int 型
            int gid=Integer.parseInt(items.get(i));
            map.put("gname",goodsService.findGoodsByGid(gid).getGname());
            map.put("gnumber",items.get(i+1));

            itemdetails.add(map);
        }

        return itemdetails;
    }
    @Override
    public void addOrder(Order order) {
        try{
            orderDao.addOrder(order);
            System.out.println("添加order成功");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteOrder(int oid) {
        try {
            orderDao.deleteOrder(oid);
            System.out.println("删除成功");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
