package com.jit.cdx.myapp_cdx.service.Impl;

import android.content.Context;

import com.jit.cdx.myapp_cdx.dao.Impl.ShopDaoImpl;
import com.jit.cdx.myapp_cdx.dao.ShopDao;
import com.jit.cdx.myapp_cdx.entity.Shop;
import com.jit.cdx.myapp_cdx.service.ShopService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 14032 on 2019/12/9.
 */

public class ShopServiceImpl implements ShopService {

    private ShopDao shopDao;
    public ShopServiceImpl(Context context) {
        shopDao = new ShopDaoImpl(context);
    }

    @Override
    public Shop findShopBySid(int sid) {
        return shopDao.findShopBySid(sid);
    }

    @Override
    public List<Shop> findAllShop() {
//        List<Map<String,Object>> allshop = new ArrayList<Map<String,Object>>();
        //数据改造
        List<Shop> shops = shopDao.findAllShop();
//        for(int i=0;i<shops.size();i++){
//            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("sid",shops.get(i).getSid());
//            map.put("sname",shops.get(i).getSname());
//            map.put("sphone",shops.get(i).getSphone());
//            map.put("saddress",shops.get(i).getSaddress());
//            map.put("simg",shops.get(i).getSimg());
//            // 会将所有内容全显示，需改造：使显示简略信息
//            String content = notesList.get(i).getContent();
//            if(content.length()>10){
//                content = content.substring(0,11)+"...";
//            }
//            map.put("content",content);
//            allshop.add(map);
//        }
        return shops;
    }

    @Override
    public List<Shop> findShopBySname(String sname) {
        return shopDao.findShopBySname(sname);
    }
}


