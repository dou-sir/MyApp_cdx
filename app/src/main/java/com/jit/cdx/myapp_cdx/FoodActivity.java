package com.jit.cdx.myapp_cdx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jit.cdx.myapp_cdx.dao.GoodsDao;
import com.jit.cdx.myapp_cdx.service.GoodsService;
import com.jit.cdx.myapp_cdx.service.Impl.GoodsServiceImpl;

import java.util.List;
import java.util.Map;

public class FoodActivity extends AppCompatActivity {
    private ListView lv_food;
    private ImageView back;
    private GoodsService goodsService;
    private SimpleAdapter simpleAdapter;
    private GoodsDao goodsDao;
    private List<Map<String, Object>> food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        initComponent();
        init();
    }
    private void initComponent(){
        lv_food=(ListView)findViewById(R.id.lv_food);
        back=(ImageView)findViewById(R.id.back);
    }
    private void init(){
        GoodsService goodsService=new GoodsServiceImpl(this);
        food=goodsService.getFoodGoods();
        simpleAdapter=new SimpleAdapter(this,               //当前Activity
                food,                                    //数据  格式List<Map<String,Object>>
                R.layout.lv_style_supermarket,                             //需要使用的样式页面
                new String[]{"gimg","gname","price"},              //Map的Key
                new int[]{R.id.iv_image,R.id.item_name,R.id.item_cost});          //样式文件的id
        lv_food.setAdapter(simpleAdapter);

    }
    public void back(View v){
        FoodActivity.this.finish();
    }
}
