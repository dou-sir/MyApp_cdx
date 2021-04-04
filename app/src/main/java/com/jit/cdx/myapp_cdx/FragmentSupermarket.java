package com.jit.cdx.myapp_cdx;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;

import com.jit.cdx.myapp_cdx.dao.GoodsDao;
import com.jit.cdx.myapp_cdx.entity.Goods;
import com.jit.cdx.myapp_cdx.service.GoodsService;
import com.jit.cdx.myapp_cdx.service.Impl.GoodsServiceImpl;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FragmentSupermarket extends Fragment {
    private ListView lv_supermarket;
    private ImageView iv_search,iv_shuiguo,iv_shucai,iv_meat,iv_milk,iv_wine,iv_food,iv_bread,iv_oil;
    private EditText et_gname;
    private SimpleAdapter simpleAdapter;
    private GoodsService goodsService;
    private GoodsDao goodsDao;
    private ViewFlipper mFlipper;
    private List<Map<String, Object>> goods1;
    //private Goods goods;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_supermarket,null);
        iv_search=(ImageView) view.findViewById(R.id.iv_search);
        et_gname=(EditText) view.findViewById(R.id.et_gname);
        iv_shuiguo=(ImageView) view.findViewById(R.id.iv_shuiguo);
        iv_shucai=(ImageView) view.findViewById(R.id.iv_shucai);
        iv_meat=(ImageView) view.findViewById(R.id.iv_meat);
        iv_milk=(ImageView) view.findViewById(R.id.iv_milk);
        iv_wine=(ImageView) view.findViewById(R.id.iv_wine);
        iv_food=(ImageView) view.findViewById(R.id.iv_food);
        iv_bread=(ImageView) view.findViewById(R.id.iv_bread);
        iv_oil=(ImageView) view.findViewById(R.id.iv_oil);


        mFlipper = (ViewFlipper) view. findViewById(R.id.flipper);
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

        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        goodsService = new GoodsServiceImpl(getContext());
        try{

            goods1=goodsService.getGoodsDetails();
        }catch (Exception e){

        }
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchGoodsActivity.class);
                intent.putExtra("str",et_gname.getText().toString());
                startActivity(intent);
            }
        });

        iv_shuiguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FruitActivity.class);

                startActivity(intent);
            }
        });

        iv_shucai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),VegActivity.class);

                startActivity(intent);

            }
        });

        iv_meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MeatActivity.class);

                startActivity(intent);

            }
        });

        iv_milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MilkActivity.class);

                startActivity(intent);

            }
        });

        iv_wine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WineActivity.class);

                startActivity(intent);

            }
        });

        iv_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FoodActivity.class);

                startActivity(intent);

            }
        });

        iv_bread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BreadActivity.class);

                startActivity(intent);

            }
        });

        iv_oil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),OilActivity.class);

                startActivity(intent);

            }
        });

        init();


    }



    private void init(){

        lv_supermarket=getActivity().findViewById(R.id.lv_supermarket);
        simpleAdapter=new SimpleAdapter(getActivity(),               //当前Activity
                goods1,                                    //数据  格式List<Map<String,Object>>
                R.layout.lv_style_supermarket,                             //需要使用的样式页面
                new String[]{"gimg","gname","price"},              //Map的Key
                new int[]{R.id.iv_image,R.id.item_name,R.id.item_cost});          //样式文件的id
        lv_supermarket.setAdapter(simpleAdapter);
    }

}
