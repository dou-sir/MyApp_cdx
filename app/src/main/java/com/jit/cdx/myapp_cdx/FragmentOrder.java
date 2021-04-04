package com.jit.cdx.myapp_cdx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jit.cdx.myapp_cdx.adapter.OrderAdapter;
import com.jit.cdx.myapp_cdx.entity.Order;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.Impl.OrderServiceImpl;
import com.jit.cdx.myapp_cdx.service.OrderService;

import java.util.List;
import java.util.Map;

//TODO xml 设置 样式文件的xml 适配器
public class FragmentOrder extends Fragment {

    private ListView lv_orderlist;
    private LinearLayout tologin;
    private OrderService orderService;
    private List<Map<String,Object>> itemlist;
    private List<Order> orders;
    private User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(MainActivity.info.getUser().getUstate()!=0)
            return inflater.inflate(R.layout.fragment_order, container, false);
        else
            return inflater.inflate(R.layout.fragment_order_tologin, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_fragment_order_list, null);
        orderService=new OrderServiceImpl(getActivity());
        //TODO  需要获取uid在listview显示该名用户的历史订单
//        Intent intent=getIntent();
//        Intent intent=new Intent();
//        int uid=intent.getIntExtra("uid",1);
        user = MainActivity.info.getUser();
        try{
            orders=orderService.findAllOrderByUid(user.getUid());
        }catch (Exception e){
            System.out.println("failed");
        }

        if(MainActivity.info.getUser().getUstate()!=0)
            init();
        else
            initView();
    }

    private void initView(){
        tologin = getActivity().findViewById(R.id.tologin);

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    //初始化方法
    private void init(){
        lv_orderlist = getActivity().findViewById(R.id.lv_orderlist);


        //用适配器适配
        final OrderAdapter orderAdapter=new OrderAdapter(orders,getActivity());

        lv_orderlist.setAdapter(orderAdapter);                          //将适配器放入页面的ListView


        //点击事件      进入详情
        lv_orderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int i=position;
                //通过点击的位置position获取到这个订单
                //TODO 得到订单后 将order对象 传入下一个Activity    Intent intent=new Intent(getActivity(),？？？.class);
                //TODO 传入下一层的数值
                //TODO intent.putExtra("order",order);
                //TODO startActivity(intent);
                Intent intent=new Intent(getActivity(),OrderDetailActivity.class);
                int oid=(Integer)orders.get(position).getOid();
                intent.putExtra("oid",oid);
                startActivity(intent);
                getActivity().finish();
            }
        });



        //长按事件    删除
        lv_orderlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //弹出对话框
                //定义一个AltertDialog.Builder  用于弹出确认删除对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("确认删除？");
                builder.setTitle("提示");
                //弹出对话框  按钮设置
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //页面删除数据之前      先获取到每次点击的订单编号
                        int oid=(Integer)orders.get(position).getOid();
                        //ListView删除
                        //1.操作页面中显示的数据删除    position是长按事件的位置
                        if(orders.remove(position)!=null){
                            //2.调用删除方法进行删除
                            orderService.deleteOrder(oid);
                            System.out.println("删除成功");
                        }else{
                            System.out.println("删除失败");
                        }

                        //适配器重新适配
                        orderAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(),"删除成功!",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"取消!",Toast.LENGTH_SHORT).show();
                    }
                });
                //创建对话框
                builder.create().show();
                return false;
            }
        });


    }

}