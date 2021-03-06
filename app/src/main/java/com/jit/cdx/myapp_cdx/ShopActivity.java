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
                builder.setTitle("??????" )
                        .setMessage("?????????" )
                        .setPositiveButton("??????" ,   new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ShopActivity.this,LoginActivity.class);
                                startActivity(intent);
                                ShopActivity.this.finish();
                            }
                        });//TODO
                builder.setNegativeButton("??????",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ShopActivity.this,MainActivity.class);
                        startActivity(intent);
                        ShopActivity.this.finish();
                    }
                });
                //???????????????
                builder.create().show();
            }
            user = info.getUser();
        }catch (Exception e){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("??????" )
                    .setMessage("?????????" )
                    .setPositiveButton("??????" ,   new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ShopActivity.this,LoginActivity.class);
                            startActivity(intent);
                            ShopActivity.this.finish();
                        }
                    });//TODO
            builder.setNegativeButton("??????",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ShopActivity.this,MainActivity.class);
                    startActivity(intent);
                    ShopActivity.this.finish();
                }
            });
            //???????????????
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

    //???????????????
    private void initView() {
        //???????????????
        lv_left = findViewById(R.id.lv_left);
        lv_right = findViewById(R.id.lv_right);
        tv_cost = findViewById(R.id.tv_cost);
        btn_buy = findViewById(R.id.btn_buy);
        tv_sname = findViewById(R.id.tv_sname);
        tv_tel = findViewById(R.id.tv_tel);
        tv_adress = findViewById(R.id.tv_address);
        ib_back = findViewById(R.id.ib_back);

    }

    //???????????????
    private void initData() {
        //?????????
        tv_sname.setText(shop.getSname());
        tv_tel.setText("????????????:"+shop.getSphone());
        tv_adress.setText("??????:"+shop.getSaddress());
        //todo ??????data


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
        //TODO gnum??????
        Data data = new Data(gname,price,gid);

        //???????????????
        final LeftAdapter leftAdapter = new LeftAdapter(data.getLeftData());
        //??????????????????
        final List<LeftBean> leftData = data.getLeftData();
        //??????????????????
        final List<RightBean> rightData = data.getRightData(leftData);
        RightAdapter rightAdapter = new RightAdapter(leftData, rightData, this, leftAdapter);

        //??????????????????????????????
        lv_left.setAdapter(leftAdapter);
        lv_right.setAdapter(rightAdapter);

        rightAdapter.setOnButtonClick(new RightAdapter.onButtonClick() {
            @Override
            public void myButtonClick(int position) {

                //???????????????
                addToCart(goodsService.findGoodsByGid(rightData.get(position).gid),rightData.get(position).gnum);

            }
        });

        //?????????????????????????????????
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //???????????????????????????,???????????????????????????type
                int type = leftData.get(position).type;
                //??????????????????,????????????????????????typeId,??????????????????type??????,????????????
                for (int i = 0; i < rightData.size(); i++) {
                    if (type == rightData.get(i).typeId) {
                        //???????????????????????????,??????????????????????????????????????????,???????????????
                        lv_right.smoothScrollToPosition(i);
                        currentLeftItem = i;
                        //????????????????????????????????????
                        leftAdapter.setCurrentSelect(currentLeftItem);
                        //?????????????????????
                        leftAdapter.notifyDataSetChanged();
                        break;
                    }
                }

            }
        });

        //?????????????????????????????????
        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ShopActivity.this, rightData.get(position).gname, Toast.LENGTH_SHORT).show();
//todo ??????????????? rightData.get(position).gname
//???????????????adapter
//                addToCart(goodsService.findGoodsByGid(rightData.get(position).gid));
//                //???????????????????????????,????????????????????????typeId
//                int typeId = rightData.get(position).typeId;
//                //??????????????????
//                for (int i = 0; i < leftData.size(); i++) {
//                    //?????????????????????type,??????????????????typeId??????????????????
//                    if (typeId == leftData.get(i).type) {
//                        //???????????????????????????,????????????,??????????????????????????????
//                        currentLeftItem = i;
//                        //????????????????????????????????????
//                        leftAdapter.setCurrentSelect(currentLeftItem);
//                        //?????????????????????
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
            btn_buy.setText("?????????");
            btn_buy.setTextColor(Color.WHITE);
            btn_buy.setTextSize(20);
            btn_buy.setEnabled(true);
        }//todo ??????????????????
        int flag = -1;
        for (int i=0;i<Gid.length;i++){
            if(Gid[i] == goods.getGid())
                flag = i;
        }

        if(flag<0){//??????cart???
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
            tv_cost.setText(n+"?????????\n???"+order.getCost()+"???");

        }else {

            order.setCost(order.getCost()+(goods.getPrice()*(inum-Gnum[flag])));
            Gnum[flag] = inum;
            int n=0;
            for (int i=0;i<Gid.length;i++) {
                n += Gnum[i];
                tv_cost.setText(n + "?????????\n???" + order.getCost() + "???");
            }

            if(order.getCost()==0) {
                btn_buy.setBackgroundColor(Color.WHITE);
                btn_buy.setText("???????????????");
                btn_buy.setTextColor(Color.GRAY);
                btn_buy.setTextSize(16);
                btn_buy.setEnabled(false);
            }
        }
    }

}
