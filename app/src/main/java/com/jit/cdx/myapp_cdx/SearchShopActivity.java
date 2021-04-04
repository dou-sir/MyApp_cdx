package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jit.cdx.myapp_cdx.entity.Shop;
import com.jit.cdx.myapp_cdx.service.Impl.ShopServiceImpl;
import com.jit.cdx.myapp_cdx.service.ShopService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchShopActivity extends AppCompatActivity {

    private ImageView iv_searchshop;
    private EditText ed_searchcontext;
    private ListView lv_shoplist;
    private SimpleAdapter simpleAdapter;
    private ShopService shopService;
    private List<Map<String,Object>> allshops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shop);

        initComponent();

        //点击事件     进入详情
        lv_shoplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<Shop> shops = shopService.findShopBySname(ed_searchcontext.getText().toString());
                Shop shop= (Shop)shops.get(position);
                MainActivity.info.setSid(shop.getSid());
                //TODO 传入下一个Activity
                Intent intent=new Intent(SearchShopActivity.this,ShopActivity.class);

                startActivity(intent);
            }
        });


    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getSupportActionBar().hide();
    }

    public void initComponent(){
        iv_searchshop=findViewById(R.id.iv_searchshop);
        ed_searchcontext=findViewById(R.id.ed_searchcontext);
        lv_shoplist=findViewById(R.id.lv_shoplist);


    }

    public void SearchShop(View view){
        //TODO 点击搜索后显示list view
        allshops = getShops();
        simpleAdapter=new SimpleAdapter(this,               //当前Activity
                allshops,                                    //数据  格式List<Map<String,Object>>
                R.layout.lv_style_shop,                             //需要使用的样式页面
                new String[]{"sname","simg"},              //Map的Key
                new int[]{R.id.item_title,R.id.item_img});          //样式文件的id
        lv_shoplist.setAdapter(simpleAdapter);
    }

    public void Back(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }


    private List<Map<String,Object>> getShops(){

        shopService=new ShopServiceImpl(this);
        List<Shop> shops = shopService.findShopBySname(ed_searchcontext.getText().toString());
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
