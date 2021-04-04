package com.jit.cdx.myapp_cdx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.Impl.UserServiceImpl;
import com.jit.cdx.myapp_cdx.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentMine extends Fragment  implements AdapterView.OnItemClickListener{

    private ListView lv_mine;
    private ImageButton ib_logout;
    private TextView tv_mine;
    private LinearLayout ll_rebag,ll_coupon,ll_vip;

    private List<Map<String, Object>> data;
    private int[] icons = {R.drawable.wallet,R.drawable.my_info,R.drawable.modify_pwd,
            R.drawable.rewards, R.drawable.help,R.drawable.contact    };
    private String[] titles = {"我的钱包","个人信息", "修改密码","邀请有礼", "帮助与反馈","关于我们"};

    private UserService userService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(MainActivity.info.getUser().getUstate()!=0)
            return inflater.inflate(R.layout.fragment_mine, container, false);
        else
            return inflater.inflate(R.layout.fragment_mine_tologin, container, false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userService = new UserServiceImpl(getContext());
        if(MainActivity.info.getUser().getUstate()!=0)
            initFun();
        else
            initView();

    }

    public void initFun(){

        lv_mine = getActivity().findViewById(R.id.lv_mine);
        ib_logout = getActivity().findViewById(R.id.ib_logout);
        tv_mine = getActivity().findViewById(R.id.tv_mine);
        ll_rebag = getActivity().findViewById(R.id.ll_redbag);
        ll_coupon = getActivity().findViewById(R.id.ll_coupon);
        ll_vip = getActivity().findViewById(R.id.ll_vip);

        String s = "你好,"+MainActivity.info.getUser().getUname();
        if (s.length()>9){
            s = s.substring(0,9)+"...";
        }
        tv_mine.setText(s);

        ll_rebag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "您未获得红包", Toast.LENGTH_SHORT).show();
            }
        });

        ll_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "您未获得优惠券", Toast.LENGTH_SHORT).show();
            }
        });

        ll_vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "您还未成为会员", Toast.LENGTH_SHORT).show();
            }
        });

        data = getData();
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(), //当前activity
                data,//适配器需要解析的数据
                R.layout.lv_style_mine,//样式文件
                new String[]{"image", "title"},//data中Map的文字
                new int[]{R.id.iv_row, R.id.tv_row});//样式文件id
        lv_mine.setAdapter(adapter);

        //登出按钮
        ib_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("提示" )
                        .setMessage("是否登出?" )
                        .setPositiveButton("确定" ,   new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.info.getUser().setUstate(0);
                                User user=MainActivity.info.getUser();

                                userService.modifyUser(user);//todo

                                Intent intent = new Intent(getActivity(),LoginActivity.class);
                                intent.putExtra("uname",user.getUname());
                                startActivity(intent);
                                getActivity().finish();
                            }
                        });//TODO
                builder.setNegativeButton("取消",null);
                //创建对话框
                builder.create().show();
            }

        });

        //ListView监听
        lv_mine.setOnItemClickListener(this);

    }

    public void initView(){
        lv_mine = getActivity().findViewById(R.id.lv_mine);
        tv_mine = getActivity().findViewById(R.id.tv_mine);

        data = getData();
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(), //当前activity
                data,//适配器需要解析的数据
                R.layout.lv_style_mine,//样式文件
                new String[]{"image", "title"},//data中Map的文字
                new int[]{R.id.iv_row, R.id.tv_row});//样式文件id
        lv_mine.setAdapter(adapter);

        tv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        switch (position) {//todo
            case 0:
                Toast.makeText(getActivity(),
                "点击了"+titles[position], Toast.LENGTH_SHORT).show();
                break;
            case 1:
                intent.setClass(getActivity(),PersonInformationActivity.class);
//                intent.putExtra("uname",MainActivity.uname);
                startActivity(intent);//!!务必要启动activity
//                getActivity().finish();
                break;
            case 2:
                intent.setClass(getActivity(),ChangePasswordActivity.class);
                startActivity(intent);//!!务必要启动activity
                break;
            case 3:
                Toast.makeText(getActivity(),
                "点击了"+titles[position], Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getActivity(),
                        "点击了"+titles[position], Toast.LENGTH_SHORT).show();
                break;
            case 5:
                intent.setClass(getActivity(),AboutActivity.class);
                startActivity(intent);//!!务必要启动activity
                break;
            case 6:

                break;

        }

    }

    private List<Map<String,Object>> getData(){
        data = new ArrayList<Map<String, Object>>();
        for(int i=0;i<icons.length;i++){
            Map<String,Object> hm = new HashMap<String, Object>();
            hm.put("image",icons[i]);//从图标数组提取图片
            hm.put("title",titles[i]);//从标题数组中提取文字
            data.add(hm);//添加进data
        }
        return data;
    }


}
