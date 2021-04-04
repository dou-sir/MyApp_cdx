package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jit.cdx.myapp_cdx.entity.MyApplicationInfo;
import com.jit.cdx.myapp_cdx.entity.Order;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.Impl.OrderServiceImpl;
import com.jit.cdx.myapp_cdx.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderDetailActivity extends AppCompatActivity {

    private ListView lv_order_detail_list;
    private TextView tv_shopname, tv_user_address, tv_order_time, tv_order_cost;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> itemList;
    private OrderService orderService;
    private Order order;
    private User user;                 //TODO 用于返回主界面


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        orderService = new OrderServiceImpl(this);
        initComponent();
        init();
    }

    //组件绑定
    public void initComponent() {
        lv_order_detail_list = findViewById(R.id.lv_order_detail_list);
        tv_shopname = findViewById(R.id.tv_shopname);
        tv_user_address = findViewById(R.id.tv_user_address);
        tv_order_time = findViewById(R.id.tv_order_time);
        tv_order_cost = findViewById(R.id.tv_order_cost);
    }

    public void init() {
        //TODO 获取order 并且显示需要的信息 （以下的order oid设置为0）
        //获取上一个界面传过来的order并且在textview中显示其内容
        Intent intent = getIntent();


        int oid = intent.getIntExtra("oid", 0);
        order = orderService.findOrederByOid(oid);
        //显示需要的信息
        tv_shopname.setText(order.getSname());
        tv_user_address.setText("配送地址：" + order.getOaddress() + "\n收件人联系方式：" + order.getOphone());
        tv_order_time.setText(order.getDate());

        //将order中cost 从int型转换String型
        tv_order_cost.setText(order.getCost() + "元");

        //获取存放订单物品的列表 并且与适配器适配 显示

        itemList = orderService.getOrderDetails(order);

        simpleAdapter = new SimpleAdapter(this,                                      //当前Activity
                itemList,                                                                   //数据  格式List<Map<String,Object>>
                R.layout.lv_style_orderdetail,                                              //需要使用的样式页面
                new String[]{"gname", "gnumber"},                                            //Map的Key
                new int[]{R.id.tv_style_good, R.id.tv_style_number});                        //样式文件的id
        lv_order_detail_list.setAdapter(simpleAdapter);                                     //将适配器放入页面的ListView
    }

    public void OneMoreOrder(View view) {
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
        intent.putExtra("order", order);
        MyApplicationInfo info = (MyApplicationInfo)getApplication();
        intent.putExtra("uname",info.getUser().getUname());
        startActivity(intent);
        this.finish();
    }

    public void Back(View view) {
        //TODO 返回mainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
