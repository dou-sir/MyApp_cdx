package com.jit.cdx.myapp_cdx.service;

import com.jit.cdx.myapp_cdx.entity.Goods;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/12/9.
 */

public interface GoodsService {

    /**
     * 通过商品名查找商品
     */
    public List<Goods> findGoodsByGname(String gname);
    /**
     * 通过商家编号查询所以商品
     */
    public List<Goods> findGoodsBySid(int sid);
    /**
     *通过商品编号查询商品
     */
    public Goods findGoodsByGid(int gid);

    public List<Map<String,Object>> getGoodsDetails(Goods goods);

    public List<Map<String,Object>> getSelectGoods(String gname);

    public List<Map<String,Object>> getFruitGoods();

    public List<Map<String,Object>> getVegGoods();

    public List<Map<String,Object>> getMeatGoods();

    public List<Map<String,Object>> getMilkGoods();

    public List<Map<String,Object>> getWineGoods();

    public List<Map<String,Object>> getFoodGoods();

    public List<Map<String,Object>> getBreadGoods();

    public List<Map<String,Object>> getOilGoods();

    public List<Map<String,Object>> getGoodsDetails();

    public Goods findAllGoods();

}
