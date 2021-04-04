package com.jit.cdx.myapp_cdx.dao;

import com.jit.cdx.myapp_cdx.entity.Shop;

import java.util.List;

/**
 * Created by 14032 on 2019/12/9.
 */

public interface ShopDao {
    /**
     * 查询单个商家
     */
    public Shop findShopBySid(int sid) ;

    /**
     * 查询all商家
     */
    public List<Shop> findAllShop() ;

    /**
     * 查询某些商家
     */
    public List<Shop> findShopBySname(String sname) ;

//    /**
//     * 添加商家
//     * @return
//     */
//    public Shop addShop(Shop shop) ;
//
//    /**
//     * 修改商家
//     * @return
//     */
//    public void modifyShop(Shop shop) ;
}
