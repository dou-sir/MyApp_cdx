package com.jit.cdx.myapp_cdx.dao;

import com.jit.cdx.myapp_cdx.entity.Goods;

import java.util.List;

/**
 * Created by admin on 2019/12/9.
 */

public interface GoodsDao {
    /**
     * 查找所有商品
     */
    public List<Goods> findAllGoods();
    /**
     * 通过商品名查找商品
     */
    public List<Goods> findGoodsByGname(String gname);
    /**
     * 通过商家编号查询所有商品
     */
    public List<Goods> findGoodsBySid(int sid);
    /**
     *通过商品编号查询商品
     */
    public Goods findGoodsByGid(int gid);

    public Goods findAllGoods1();

    /**
     * 查找水果类商品
     */
    public List<Goods> findFruitGoods();
    /**
     * 查找蔬菜类商品
     */
    public List<Goods> findVegGoods();
    /**
     * 查找肉类商品
     */
    public List<Goods> findMeatGoods();
    /**
     * 查找饮品类商品
     */
    public List<Goods> findMilkGoods();
    /**
     * 查找酒水类商品
     */
    public List<Goods> findWineGoods();
    /**
     * 查找零食类商品
     */
    public List<Goods> findFoodGoods();
    /**
     * 查找面包类商品
     */
    public List<Goods> findBreadGoods();
    /**
     * 查找粮油类商品
     */
    public List<Goods> findOilGoods();

//    /**
//     * 添加新的商品
//     */
//    public void addGoods(Goods goods);
//
//    /**
//     * 修改商品信息
//     */
//    public void modifyGoods(Goods goods);
//
//    /**
//     * 通过商品编号删除商品
//     */
//    public void delGoodsByGid(int gid);

}
