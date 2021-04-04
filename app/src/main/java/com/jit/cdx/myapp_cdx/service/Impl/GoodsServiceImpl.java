package com.jit.cdx.myapp_cdx.service.Impl;

import android.content.Context;

import com.jit.cdx.myapp_cdx.dao.GoodsDao;
import com.jit.cdx.myapp_cdx.dao.Impl.GoodsDaoImpl;
import com.jit.cdx.myapp_cdx.entity.Goods;
import com.jit.cdx.myapp_cdx.service.GoodsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/12/9.
 */

public class GoodsServiceImpl implements GoodsService {
   private GoodsDao goodsDao;
    public GoodsServiceImpl(Context context){
        goodsDao=new GoodsDaoImpl(context);
    }

    @Override
    public List<Goods> findGoodsByGname(String gname) {
        return goodsDao.findGoodsByGname(gname);
    }

    @Override
    public List<Goods> findGoodsBySid(int sid) {
        return goodsDao.findGoodsBySid(sid);
    }

    @Override
    public Goods findGoodsByGid(int gid) {
        return goodsDao.findGoodsByGid(gid);
    }

    @Override
    public List<Map<String, Object>> getGoodsDetails(Goods goods) {
        List<Map<String,Object>>goodsdetails=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findAllGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String,Object>map=new HashMap<String, Object>();
            map.put("gid",goodsList.get(i).getGid());
            map.put("gname",goodsList.get(i).getGname());
            map.put("sid",goodsList.get(i).getSid());
            map.put("price",goodsList.get(i).getPrice());
            map.put("gimg",goodsList.get(i).getGimg());

            goodsdetails.add(map);
        }

        return goodsdetails;
    }

    public List<Map<String, Object>> getSelectGoods(String gname) {
        List<Map<String,Object>> selectgoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findGoodsByGname(gname);
        for(int i=0;i<goodsList.size();i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            selectgoods.add(map);
        }


        return selectgoods;
    }

    @Override
    public Goods findAllGoods() {
        return goodsDao.findAllGoods1();
    }

    @Override
    public List<Map<String, Object>> getFruitGoods() {
        List<Map<String,Object>> fruitgoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findFruitGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            fruitgoods.add(map);
        }
        return fruitgoods;
    }

    @Override
    public List<Map<String, Object>> getVegGoods() {
        List<Map<String,Object>> veggoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findVegGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            veggoods.add(map);
        }
        return veggoods;
    }

    @Override
    public List<Map<String, Object>> getMeatGoods() {
        List<Map<String,Object>> meatgoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findMeatGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            meatgoods.add(map);
        }
        return meatgoods;
    }

    @Override
    public List<Map<String, Object>> getMilkGoods() {
        List<Map<String,Object>> milkgoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findMilkGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            milkgoods.add(map);
        }
        return milkgoods;
    }

    @Override
    public List<Map<String, Object>> getWineGoods() {
        List<Map<String,Object>> winegoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findWineGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            winegoods.add(map);
        }
        return winegoods;
    }

    @Override
    public List<Map<String, Object>> getFoodGoods() {
        List<Map<String,Object>> foodgoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findFoodGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            foodgoods.add(map);
        }
        return foodgoods;
    }

    @Override
    public List<Map<String, Object>> getBreadGoods() {
        List<Map<String,Object>> breadgoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findBreadGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            breadgoods.add(map);
        }
        return breadgoods;
    }

    @Override
    public List<Map<String, Object>> getOilGoods() {
        List<Map<String,Object>> oilgoods=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findOilGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", goodsList.get(i).getGid());
            map.put("gname", goodsList.get(i).getGname());
            map.put("sid", goodsList.get(i).getSid());
            map.put("price", goodsList.get(i).getPrice());
            map.put("gimg", goodsList.get(i).getGimg());

            oilgoods.add(map);
        }
        return oilgoods;
    }
    @Override
    public List<Map<String, Object>> getGoodsDetails() {
        List<Map<String,Object>>goodsdetails=new ArrayList<Map<String,Object>>();
        List<Goods> goodsList=goodsDao.findAllGoods();
        for(int i=0;i<goodsList.size();i++){
            Map<String,Object>map=new HashMap<String, Object>();
            map.put("gid",goodsList.get(i).getGid());
            map.put("gname",goodsList.get(i).getGname());
            map.put("sid",goodsList.get(i).getSid());
            map.put("price",goodsList.get(i).getPrice());
            map.put("gimg",goodsList.get(i).getGimg());

            goodsdetails.add(map);
        }

        return goodsdetails;
    }
}
