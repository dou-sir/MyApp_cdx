package com.jit.cdx.myapp_cdx.service;

import com.jit.cdx.myapp_cdx.entity.Shop;

import java.util.List;
import java.util.Map;

/**
 * Created by 14032 on 2019/12/9.
 */

public interface ShopService {
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


}
