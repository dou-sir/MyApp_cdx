package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jit.cdx.myapp_cdx.entity.Order;
import com.jit.cdx.myapp_cdx.entity.Shop;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.Impl.OrderServiceImpl;
import com.jit.cdx.myapp_cdx.service.Impl.UserServiceImpl;
import com.jit.cdx.myapp_cdx.service.OrderService;
import com.jit.cdx.myapp_cdx.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ConfirmOrderActivity extends AppCompatActivity {

    private EditText ed_makesure_address,ed_makesure_tel,ed_makesure_name;
    private TextView tv_arrive_time,tv_makesure_cost,tv_makesure_shopname;
    private ListView lv_makesure_itemlist;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> orderList;
    private OrderService orderService;
    private UserService userService;
    private Order order;                            //上个界面传递过来的order 有oid uid oitems cost oitems sname
    private String uname;                           //上个界面传递过来的uname

    private int zhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        initConponent();

    }

    //初始化组件
    public void initConponent(){
        ed_makesure_address=findViewById(R.id.ed_makesure_address);
        ed_makesure_tel=findViewById(R.id.ed_makesure_tel);
        tv_arrive_time=findViewById(R.id.tv_arrive_time);
        lv_makesure_itemlist=findViewById(R.id.lv_makesure_itemlist);
        tv_makesure_cost=findViewById(R.id.tv_makesure_cost);
        tv_makesure_shopname=findViewById(R.id.tv_makesure_shopname);
        ed_makesure_name=findViewById(R.id.ed_makesure_name);

        orderService=new OrderServiceImpl(this);
        userService=new UserServiceImpl(this);

        //TODO 获取上个界面传递过来的order及uname
        Intent intent=getIntent();
        if(intent.getStringExtra("uname")!=null){
            uname=intent.getStringExtra("uname");
            order=(Order) intent.getSerializableExtra("order");
            if (order.getDate()!=null)
                zhi=1;

        }else {
            order=new Order(0,"2019/08/08"," 光头烧烤","119","CWNhome","0,3",108);
            uname="cxk";
        }


        //通过传递来的order来获得存放订单物品详情的orderList
        try {
            orderList=orderService.getOrderDetails(order);
        }catch (Exception e){

        }

        //orderList与适配器相适配
        simpleAdapter=new SimpleAdapter(this,                                      //当前Activity
                orderList,                                                                   //数据  格式List<Map<String,Object>>
                R.layout.lv_style_orderdetail,                                              //需要使用的样式页面
                new String[]{"gname","gnumber"},                                            //Map的Key
                new int[]{R.id.tv_style_good,R.id.tv_style_number});                        //样式文件的id
        lv_makesure_itemlist.setAdapter(simpleAdapter);                                     //将适配器放入页面的ListView

        //通过uname得到user 并且获得其地址和电话
        User user=userService.findUserByUname(uname);
        String address=user.getUaddress();
        String tel=user.getUphone();

        //设置各个textview中的文字
        ed_makesure_address.setText(address);
        tv_makesure_shopname.setText(order.getSname());
        ed_makesure_name.setText(uname);
        ed_makesure_tel.setText(tel);
        tv_makesure_cost.setText("合约"+order.getCost()+"元");

        //对时间数据改造
        long etime1=System.currentTimeMillis()+20*60*1000;                                   // 获取当前系统时间          延时函数，单位毫秒，这里是延时了20分钟
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");                        //数据改造 将获得的时间改造为 时：分
        String strNow=sdf.format(new Date(etime1));                                                      //将日期类型转换为String
        tv_arrive_time.setText("大约"+strNow+"送达");

    }

    public void CancelOrder(View view){
        if (zhi!=1){
            Intent intent = new Intent(ConfirmOrderActivity.this, ShopActivity.class);
            startActivity(intent);
            this.finish();
        }else{
            Intent intent = new Intent(ConfirmOrderActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void ConfirmOrder(View view){
        //设置更改后的地址、电话信息
        order.setOaddress(ed_makesure_address.getText().toString());
        order.setOphone(ed_makesure_tel.getText().toString());
        //设置下单的时间
        Date now =new Date();       //获取当前系统时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String strNow=sdf.format(now);      //将日期类型转换为String 方便存入数据库
        order.setDate(strNow);

        System.out.println(order);
        //向数据库添加此order
        orderService.addOrder(order);

        Toast.makeText(this,"下单成功！",Toast.LENGTH_SHORT).show();

        //跳转至主页页面   将order传递下去
        Intent intent=new Intent(this,MainActivity.class);
//        User user=userService.findUserByUname(uname);
//        intent.putExtra("user",user);
        startActivity(intent);
        this.finish();


    }
}
