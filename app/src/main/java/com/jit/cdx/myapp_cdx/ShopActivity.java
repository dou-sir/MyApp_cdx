package com.jit.cdx.myapp_cdx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jit.cdx.myapp_cdx.Bean.LeftBean;
import com.jit.cdx.myapp_cdx.Bean.RightBean;
import com.jit.cdx.myapp_cdx.adapter.LeftAdapter;
import com.jit.cdx.myapp_cdx.adapter.RightAdapter;
import com.jit.cdx.myapp_cdx.entity.Goods;
import com.jit.cdx.myapp_cdx.entity.MyApplicationInfo;
import com.jit.cdx.myapp_cdx.entity.Order;
import com.jit.cdx.myapp_cdx.entity.Shop;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.GoodsService;
import com.jit.cdx.myapp_cdx.service.Impl.GoodsServiceImpl;
import com.jit.cdx.myapp_cdx.service.Impl.ShopServiceImpl;
import com.jit.cdx.myapp_cdx.service.ShopService;
import com.jit.cdx.myapp_cdx.service.UserService;

import java.util.Arrays;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ShopActivity extends AppCompatActivity {

    private ListView lv_left;
    private StickyListHeadersListView lv_right;
    private int currentLeftItem;
    private TextView tv_cost,tv_sname;
    private TextView tv_tel,tv_adress;
    private Button btn_buy;
    private ImageButton ib_back;

    private User user;
    private Shop shop;
    private Order order = new Order();
    private int Gid[] = {0};
    private int Gnum[] = {0};

    private ShopService shopService;
    private GoodsService goodsService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shopService = new ShopServiceImpl(this);
        goodsService = new GoodsServiceImpl(this);

        MyApplicationInfo info = (MyApplicationInfo) getApplication();
        try{
            if(info.getUser().getUstate()==0){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示" )
                        .setMessage("请登录" )
                        .setPositiveButton("登录" ,   new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ShopActivity.this,LoginActivity.class);
                                startActivity(intent);
                                ShopActivity.this.finish();
                            }
                        });//TODO
                builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ShopActivity.this,MainActivity.class);
                        startActivity(intent);
                        ShopActivity.this.finish();
                    }
                });
                //创建对话框
                builder.create().show();
            }
            user = info.getUser();
        }catch (Exception e){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示" )
                    .setMessage("请登录" )
                    .setPositiveButton("登录" ,   new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ShopActivity.this,LoginActivity.class);
                            startActivity(intent);
                            ShopActivity.this.finish();
                        }
                    });//TODO
            builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ShopActivity.this,MainActivity.class);
                    startActivity(intent);
                    ShopActivity.this.finish();
                }
            });
            //创建对话框
            builder.create().show();
        }

        info = (MyApplicationInfo) getApplication();
        shop = new Shop();
        try{
            shop = shopService.findShopBySid(info.sid);
        }catch (Exception e){

            shop = shopService.findShopBySid(0);
        }


        initView();
        initData();
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getSupportActionBar().hide();
    }

    //初始化控件
    private void initView() {
        //初始化控件
        lv_left = findViewById(R.id.lv_left);
        lv_right = findViewById(R.id.lv_right);
        tv_cost = findViewById(R.id.tv_cost);
        btn_buy = findViewById(R.id.btn_buy);
        tv_sname = findViewById(R.id.tv_sname);
        tv_tel = findViewById(R.id.tv_tel);
        tv_adress = findViewById(R.id.tv_address);
        ib_back = findViewById(R.id.ib_back);

    }

    //设置适配器
    private void initData() {
        //店铺名
        tv_sname.setText(shop.getSname());
        tv_tel.setText("联系方式:"+shop.getSphone());
        tv_adress.setText("地址:"+shop.getSaddress());
        //todo 写入data


        order.setOitems("");
        List<Goods> goods= goodsService.findGoodsBySid(shop.getSid());
        final String gname[]=new String[goods.size()];
        int price[]=new int[goods.size()];
        final int gid[]=new int[goods.size()];
        for (int i=0;i<goods.size();i++){
            gname[i] = goods.get(i).getGname();
            price[i] = goods.get(i).getPrice();
            gid[i] = goods.get(i).getGid();
        }
        //TODO gnum【】
        Data data = new Data(gname,price,gid);

        //创建适配器
        final LeftAdapter leftAdapter = new LeftAdapter(data.getLeftData());
        //获取左侧数据
        final List<LeftBean> leftData = data.getLeftData();
        //获取右侧数据
        final List<RightBean> rightData = data.getRightData(leftData);
        RightAdapter rightAdapter = new RightAdapter(leftData, rightData, this, leftAdapter);

        //为左侧布局设置适配器
        lv_left.setAdapter(leftAdapter);
        lv_right.setAdapter(rightAdapter);

        rightAdapter.setOnButtonClick(new RightAdapter.onButtonClick() {
            @Override
            public void myButtonClick(int position) {

                //加入购物车
                addToCart(goodsService.findGoodsByGid(rightData.get(position).gid),rightData.get(position).gnum);

            }
        });

        //为左侧条目设置点击事件
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //当左侧条目被点击时,记录下被点击条目的type
                int type = leftData.get(position).type;
                //遍历右侧条目,并获取右侧条目的typeId,与刚刚获取的type对比,是否一致
                for (int i = 0; i < rightData.size(); i++) {
                    if (type == rightData.get(i).typeId) {
                        //如果找到对应的条目,那就将右侧条目滚动至对应条目,并跳出循环
                        lv_right.smoothScrollToPosition(i);
                        currentLeftItem = i;
                        //设置当前被选中的左侧条目
                        leftAdapter.setCurrentSelect(currentLeftItem);
                        //刷新数据适配器
                        leftAdapter.notifyDataSetChanged();
                        break;
                    }
                }

            }
        });

        //为右侧条目设置点击事件
        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ShopActivity.this, rightData.get(position).gname, Toast.LENGTH_SHORT).show();
//todo 加入购物车 rightData.get(position).gname
//改了位置在adapter
//                addToCart(goodsService.findGoodsByGid(rightData.get(position).gid));
//                //当右侧条目被点击时,获取被点击条目的typeId
//                int typeId = rightData.get(position).typeId;
//                //遍历左侧条目
//                for (int i = 0; i < leftData.size(); i++) {
//                    //获取左侧条目的type,与右侧条目的typeId对比是否一致
//                    if (typeId == leftData.get(i).type) {
//                        //说明找到了对应条目,跳出循环,设置当前被选中的条目
//                        currentLeftItem = i;
//                        //设置当前被选中的左侧条目
//                        leftAdapter.setCurrentSelect(currentLeftItem);
//                        //刷新数据适配器
//                        leftAdapter.notifyDataSetChanged();
//                        break;
//                    }
//
//                }
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setUid(user.getUid());
                order.setSname(shop.getSname());
                for(int i=0;i<Gnum.length;i++){
                    if(Gnum[i]!=0){
                        order.setOitems(order.getOitems()+Gid[i]+","+Gnum[i]+",");

                    }
                }//todo
                String s = order.getOitems();
                order.setOitems(s.substring(0,s.length()-1));
                Intent intent = new Intent(ShopActivity.this,ConfirmOrderActivity.class);
                intent.putExtra("order",order);
                System.out.println(order);

                intent.putExtra("uname",user.getUname());

                startActivity(intent);
                ShopActivity.this.finish();
            }
        });
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShopActivity.this,MainActivity.class);
                startActivity(intent);
                ShopActivity.this.finish();
            }
        });
    }

    private void addToCart(Goods goods,int inum){

        if(order.getCost()==0) {
            btn_buy.setBackgroundColor(Color.GREEN);
            btn_buy.setText("去下单");
            btn_buy.setTextColor(Color.WHITE);
            btn_buy.setTextSize(20);
            btn_buy.setEnabled(true);
        }//todo 修改加法规则
        int flag = -1;
        for (int i=0;i<Gid.length;i++){
            if(Gid[i] == goods.getGid())
                flag = i;
        }

        if(flag<0){//不在cart时
//            String gname = goods.getGname();
            int id[] = new int[Gid.length+1];
            int num[] = new  int[id.length];
            for (int i=0;i<Gid.length;i++){
                id[i] = Gid[i];
                num[i] = Gnum[i];
            }
            id[Gid.length] = goods.getGid();
            num[Gid.length] = 1;

            Gid = id;
            Gnum = num;

            int n=0;
            for (int i=0;i<Gid.length;i++){
                n+=num[i];
            }
          //  String oitems = order.getOitems()+goods.getGid()+",";//todo
//            order.setOitems(order.getOitems()+goods.getGid()+",");

            order.setCost(order.getCost()+goods.getPrice());
            tv_cost.setText(n+"件商品\n共"+order.getCost()+"元");

        }else {

            order.setCost(order.getCost()+(goods.getPrice()*(inum-Gnum[flag])));
            Gnum[flag] = inum;
            int n=0;
            for (int i=0;i<Gid.length;i++) {
                n += Gnum[i];
                tv_cost.setText(n + "件商品\n共" + order.getCost() + "元");
            }

            if(order.getCost()==0) {
                btn_buy.setBackgroundColor(Color.WHITE);
                btn_buy.setText("请选择商品");
                btn_buy.setTextColor(Color.GRAY);
                btn_buy.setTextSize(16);
                btn_buy.setEnabled(false);
            }
        }
    }

}
