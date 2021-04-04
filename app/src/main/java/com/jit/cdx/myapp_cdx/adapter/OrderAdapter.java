package com.jit.cdx.myapp_cdx.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jit.cdx.myapp_cdx.ConfirmOrderActivity;
import com.jit.cdx.myapp_cdx.MainActivity;
import com.jit.cdx.myapp_cdx.OrderDetailActivity;
import com.jit.cdx.myapp_cdx.R;
import com.jit.cdx.myapp_cdx.entity.Goods;
import com.jit.cdx.myapp_cdx.entity.Order;
import com.jit.cdx.myapp_cdx.service.GoodsService;
import com.jit.cdx.myapp_cdx.service.Impl.GoodsServiceImpl;

import java.util.List;

/**
 * Created by DELL on 2019/12/16.
 */

public class OrderAdapter extends BaseAdapter {

    private TextView item_title,item_time,item_good_details,item_good_cost;
    private Button btn_details,btn_onceagain;
    private GoodsService goodsService;
    private ImageView iv_image,item_good_img;


    private List<Order> myorder;
    private Context context;

    public OrderAdapter(List<Order> myorder, Context context){
        this.myorder=myorder;
        this.context=context;
    }

    @Override
    public int getCount() {
        return myorder.size();
    }

    @Override
    public Object getItem(int position) {
        return myorder.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(context, R.layout.lv_style_order,null);
        Order nowOrder=myorder.get(position);

        item_title=convertView.findViewById(R.id.item_title);
        item_time=convertView.findViewById(R.id.item_time);
        item_good_details=convertView.findViewById(R.id.item_good_details);
        item_good_cost=convertView.findViewById(R.id.item_good_cost);
        btn_details=convertView.findViewById(R.id.btn_details);
        btn_onceagain=convertView.findViewById(R.id.btn_onceagain);
        iv_image=convertView.findViewById(R.id.iv_image);
        item_good_img=convertView.findViewById(R.id.item_good_img);

        item_title.setText(nowOrder.getSname());
        item_time.setText(nowOrder.getDate());

        //数据改造 将items中的数值 改为“XX等商品”
        goodsService=new GoodsServiceImpl(context);

        String str=nowOrder.getOitems().toString();
        String b []  = str.split(",");
        int num=Integer.parseInt(b[0]);

        Goods goods=(Goods)goodsService.findGoodsByGid(num);
        item_good_details.setText(goods.getGname()+"等商品");
        item_good_cost.setText(nowOrder.getCost()+"");
        int img = goods.getSid();
        switch (img){
            //TODO 改商家、商品的图片
            case 0: iv_image.setImageResource(R.drawable.barbecue);
                item_good_img.setImageResource(R.drawable.barbecue);
                break;
            case 1: iv_image.setImageResource( R.drawable.neobbq);
                item_good_img.setImageResource(R.drawable.neobbq);
                break;
            case 2: iv_image.setImageResource(R.drawable.hotpot);
                item_good_img.setImageResource(R.drawable.hotpot);
                break;
            case 3: iv_image.setImageResource(R.drawable.kfc);
                item_good_img.setImageResource(R.drawable.kfc);
                break;
            case 4:iv_image.setImageResource(R.drawable.soursoup);
                item_good_img.setImageResource(R.drawable.soursoup);
                break;
            case 5: iv_image.setImageResource(R.drawable.firedrice);
                item_good_img.setImageResource(R.drawable.firedrice);
                break;
            case 6: iv_image.setImageResource(R.drawable.milktea);
                item_good_img.setImageResource(R.drawable.milktea);
                break;
            default:iv_image.setImageResource(R.drawable.apple);
                item_good_img.setImageResource(R.drawable.apple);
        }



        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=position;

                Intent intent=new Intent(context,OrderDetailActivity.class);
                int oid=myorder.get(position).getOid();
                intent.putExtra("oid",oid);
                context.startActivity(intent);

            }
        });

        btn_onceagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context,ConfirmOrderActivity.class);
                Order order=myorder.get(position);
                intent.putExtra("order",order);
                intent.putExtra("uname", MainActivity.info.getUser().getUname());
                context.startActivity(intent);
                ((MainActivity)context).finish();
            }
        });




        return convertView;
    }
}
