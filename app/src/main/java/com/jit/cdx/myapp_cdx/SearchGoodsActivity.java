package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jit.cdx.myapp_cdx.dao.GoodsDao;
import com.jit.cdx.myapp_cdx.entity.Goods;
import com.jit.cdx.myapp_cdx.service.GoodsService;
import com.jit.cdx.myapp_cdx.service.Impl.GoodsServiceImpl;

import java.util.List;
import java.util.Map;

public class SearchGoodsActivity extends AppCompatActivity {
    private ListView lv_search_goods;
    private ImageView iv_back;
    private GoodsService goodsService;
    private SimpleAdapter simpleAdapter;
    private GoodsDao goodsDao;
    private String str;
    private List<Map<String, Object>> goods1;
    private Goods goods=new Goods(0,"烧鸡",0,36,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        str=bundle.getString("str");
        System.out.println(str);

        initComponent();
        init();

    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getSupportActionBar().hide();
    }

    private void initComponent(){
        lv_search_goods=(ListView)findViewById(R.id.lv_search_goods);
        iv_back=(ImageView)findViewById(R.id.iv_back);
    }

    private void init(){
        GoodsService goodsService=new GoodsServiceImpl(this);
        goods1=goodsService.getSelectGoods(str);
        simpleAdapter=new SimpleAdapter(this,               //当前Activity
                goods1,                                    //数据  格式List<Map<String,Object>>
                R.layout.lv_style_supermarket,                             //需要使用的样式页面
                new String[]{"gimg","gname","price"},              //Map的Key
                new int[]{R.id.iv_image,R.id.item_name,R.id.item_cost});          //样式文件的id
        lv_search_goods.setAdapter(simpleAdapter);

    }

    public void back(View v){
//        Intent intent=new Intent(SearchGoodsActivity.this,MainActivity.class);
//        startActivity(intent);
        SearchGoodsActivity.this.finish();
    }
}

