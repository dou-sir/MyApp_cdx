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

public class WineActivity extends AppCompatActivity {
    private ListView lv_wine;
    private ImageView back;
    private GoodsService goodsService;
    private SimpleAdapter simpleAdapter;
    private GoodsDao goodsDao;
    private List<Map<String, Object>> wine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);
        initComponent();
        init();
    }
    private void initComponent(){
        lv_wine=(ListView)findViewById(R.id.lv_wine);
        back=(ImageView)findViewById(R.id.back);
    }
    private void init(){
        GoodsService goodsService=new GoodsServiceImpl(this);
        wine=goodsService.getWineGoods();
        simpleAdapter=new SimpleAdapter(this,               //当前Activity
                wine,                                    //数据  格式List<Map<String,Object>>
                R.layout.lv_style_supermarket,                             //需要使用的样式页面
                new String[]{"gimg","gname","price"},              //Map的Key
                new int[]{R.id.iv_image,R.id.item_name,R.id.item_cost});          //样式文件的id
        lv_wine.setAdapter(simpleAdapter);

    }
    public void back(View v){
        WineActivity.this.finish();
    }
}

