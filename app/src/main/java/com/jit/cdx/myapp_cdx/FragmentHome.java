package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;

import com.jit.cdx.myapp_cdx.entity.Shop;
import com.jit.cdx.myapp_cdx.service.Impl.ShopServiceImpl;
import com.jit.cdx.myapp_cdx.service.ShopService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentHome extends Fragment {

    private ViewFlipper mFlipper;
    private LinearLayout ll_search;
    private LinearLayout ll_barbecue,ll_firedrice,ll_kfc,ll_milktea;
    private ListView lv_home;
    private List<Map<String,Object>> allshops;

    private SimpleAdapter simpleAdapter;
    private ShopService shopService;
    private Shop shop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        shopService = new ShopServiceImpl(getActivity());
        shop = new Shop();
        shop.setSid(MainActivity.info.sid);
        init();

    }

    private void init(){
        ll_barbecue = getActivity().findViewById(R.id.ll_barbecue);
        ll_firedrice = getActivity().findViewById(R.id.ll_firedrice);
        ll_kfc = getActivity().findViewById(R.id.ll_kfc);
        ll_milktea = getActivity().findViewById(R.id.ll_milktea);
        lv_home = getActivity().findViewById(R.id.lv_home);
        ll_search = getActivity().findViewById(R.id.ll_search);

        mFlipper = (ViewFlipper) getActivity().findViewById(R.id.flipper);
        mFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.push_left_in));
        mFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.push_left_out));
        mFlipper.startFlipping();
        mFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AboutActivity.class);
                startActivity(intent);
            }
        });

        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchShopActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        ll_barbecue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShopActivity.class);
                MainActivity.info.setSid(0);
                startActivity(intent);
                getActivity().finish();
            }
        });
        ll_firedrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShopActivity.class);
                MainActivity.info.setSid(5);
                startActivity(intent);
                getActivity().finish();
            }
        });

        ll_kfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShopActivity.class);
//                //todo
//                int sid = 1;
//                sid = shop.getSid();
                MainActivity.info.setSid(3);
                startActivity(intent);
                getActivity().finish();
            }
        });

        ll_milktea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ShopActivity.class);
                MainActivity.info.setSid(6);
                startActivity(intent);
                getActivity().finish();
            }
        });

        allshops = getShops();
        simpleAdapter=new SimpleAdapter(getActivity(),               //当前Activity
                allshops,                                    //数据  格式List<Map<String,Object>>
                R.layout.lv_style_shop,                             //需要使用的样式页面
                new String[]{"sname","simg"},              //Map的Key
                new int[]{R.id.item_title,R.id.item_img});          //样式文件的id
        lv_home.setAdapter(simpleAdapter);                          //将适配器放入页面的ListView

        lv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int sid = (Integer)allshops.get(position).get("sid");
                MainActivity.info.setSid(sid);

                Intent intent = new Intent(getActivity(),ShopActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private List<Map<String,Object>> getShops(){
        List<Shop> shops = shopService.findAllShop();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for(int i=0;i<shops.size();i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("sid",shops.get(i).getSid());
            map.put("sname",shops.get(i).getSname());
            map.put("sphone",shops.get(i).getSphone());
            map.put("saddress",shops.get(i).getSaddress());

            int img = 0;
            switch (shops.get(i).getSimg()){
                case 0: img = R.drawable.barbecue; break;
                case 1: img = R.drawable.neobbq; break;
                case 2: img = R.drawable.hotpot; break;
                case 3: img = R.drawable.kfc; break;
                case 4: img = R.drawable.soursoup; break;
                case 5: img = R.drawable.firedrice; break;
                case 6: img = R.drawable.milktea; break;
                default:img = R.drawable.apple;
            }
            map.put("simg",img);

            data.add(map);//添加进data
        }
        return data;
    }

}
