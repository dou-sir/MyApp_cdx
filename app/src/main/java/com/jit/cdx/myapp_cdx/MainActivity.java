package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jit.cdx.myapp_cdx.entity.MyApplicationInfo;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.GoodsService;
import com.jit.cdx.myapp_cdx.service.Impl.GoodsServiceImpl;
import com.jit.cdx.myapp_cdx.service.Impl.OrderServiceImpl;
import com.jit.cdx.myapp_cdx.service.Impl.ShopServiceImpl;
import com.jit.cdx.myapp_cdx.service.Impl.UserServiceImpl;
import com.jit.cdx.myapp_cdx.service.OrderService;
import com.jit.cdx.myapp_cdx.service.ShopService;
import com.jit.cdx.myapp_cdx.service.UserService;
import com.jit.cdx.myapp_cdx.util.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private UserService userService;
    private ShopService shopService;
    private GoodsService goodsService;
    private OrderService orderService;

    public static MyApplicationInfo info = new MyApplicationInfo();

    //定义TabHost用于操作该页面的底部点击内容
    private FragmentTabHost mTabHost;
    //    定义布局 用于为换页面
    private LayoutInflater layoutInflater;
    //    定义一个数组用于存放不同的fragment页面
    private Class fragmentArray[] = {FragmentHome.class,FragmentSupermarket.class,FragmentOrder.class,FragmentMine.class};
    //    用一个数组存放每个功能键（TabHost中的每个点击按钮）的图片资源
    private  int mImageViewArray[] = {R.drawable.home,R.drawable.supermarket,R.drawable.order,R.drawable.mine2};
    //    为每个按钮添加文字说明
    private  String mTextViewArray[] = {"  首 页","  超 市","  订 单","  我 的"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();//获取可写入数据库

        userService = new UserServiceImpl(MainActivity.this);
        shopService = new ShopServiceImpl(MainActivity.this);
        goodsService = new GoodsServiceImpl(MainActivity.this);
        orderService = new OrderServiceImpl(MainActivity.this);

        info = (MyApplicationInfo)getApplication();
        Intent intent =getIntent();
        if(intent.getSerializableExtra("user")!=null){

            info.setUser((User) intent.getSerializableExtra("user"));
            System.out.println(info.getUser());
        }else {
            info.setUser(userService.findUserByUstate());
//            info.getUser().setUstate(0);
//            userService.modifyUser(info.getUser());
        }

        initView();

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getSupportActionBar().hide();
    }

    //初始化页面
    private void initView(){
        //当前Activity 和 布局页面绑定
        layoutInflater = LayoutInflater.from(this);
        //处理Tabhost 使用android.R 内的
        mTabHost = findViewById(android.R.id.tabhost);
        //将Tabhost 和自定义Tabhost 中的content绑定
        mTabHost.setup(this,getSupportFragmentManager(),R.id.main_show);
        //获取需要现实的Fragment数量
        int count = fragmentArray.length;
        //页面绑定
        for(int i=0;i<count;i++){
            //适配tabhost
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextViewArray[i])
                    .setIndicator(getTabItemView(i));
            //将拼装好的TabSpec 放入mTabHost
            mTabHost.addTab(tabSpec,fragmentArray[i],null);
            //给mTabHost 设置背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.bg2);
        }
    }
    //    初始化底部tabhost 将mTextViewArray和mImageViewArray结合 并放入到tab_item_wiew.xml文件中
    private View getTabItemView(int index){
        //将视图进行绑定 将视图和我们创建的底部样式文件进行绑定
        View view = layoutInflater.inflate(R.layout.tab_item_view,null);
        //获取tab_item_wiew.xml图片的ImageView组件
        ImageView imageView = view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);//显示具体图片
        //文字绑定
        TextView textView = view.findViewById(R.id.textview);
        textView.setText(mTextViewArray[index]);
        //内容组装完毕，返回视图
        return view;
    }
}
