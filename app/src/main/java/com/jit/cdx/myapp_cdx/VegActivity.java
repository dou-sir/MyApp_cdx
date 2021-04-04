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

public class VegActivity extends AppCompatActivity {
    private ListView lv_veg;
    private ImageView back;
    private GoodsService goodsService;
    private SimpleAdapter simpleAdapter;
    private GoodsDao goodsDao;
    private List<Map<String, Object>> veg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg);
        initComponent();
        init();
    }

    private void initComponent(){
        lv_veg=(ListView)findViewById(R.id.lv_veg);
        back=(ImageView)findViewById(R.id.back);
    }
    private void init(){
        GoodsService goodsService=new GoodsServiceImpl(this);
        veg=goodsService.getVegGoods();
        simpleAdapter=new SimpleAdapter(this,               //当前Activity
                veg,                                    //数据  格式List<Map<String,Object>>
                R.layout.lv_style_supermarket,                             //需要使用的样式页面
                new String[]{"gimg","gname","price"},              //Map的Key
                new int[]{R.id.iv_image,R.id.item_name,R.id.item_cost});          //样式文件的id
        lv_veg.setAdapter(simpleAdapter);

    }
    public void back(View v){
        VegActivity.this.finish();
    }
}
