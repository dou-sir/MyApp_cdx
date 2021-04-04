package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jit.cdx.myapp_cdx.dao.Impl.UserDaoImpl;
import com.jit.cdx.myapp_cdx.dao.UserDao;
import com.jit.cdx.myapp_cdx.entity.MyApplicationInfo;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.Impl.UserServiceImpl;
import com.jit.cdx.myapp_cdx.service.UserService;

public class PersonInformationActivity extends AppCompatActivity {
    private Button back,modify;
    private EditText et_uname,et_uphone,et_ubirth,et_uaddress;

    private UserService userService = new UserServiceImpl(PersonInformationActivity.this);
    private UserDao userDao=new UserDaoImpl(this);
    private User user;
    private MyApplicationInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_information);
        info = (MyApplicationInfo)getApplication();
        user = info.getUser();

        initComponent();
        init();
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getSupportActionBar().hide();
    }

    private void initComponent(){
        back=(Button)findViewById(R.id.back);
        et_uname=(EditText)findViewById(R.id.et_uname);
        et_uphone=(EditText)findViewById(R.id.et_uphone);
        et_ubirth=(EditText)findViewById(R.id.et_ubirth);
        et_uaddress=(EditText)findViewById(R.id.et_uaddress);
        modify=(Button)findViewById(R.id.modify);

    }

    private void init(){
        User findUser=userService.findUserByUname(user.getUname());
        et_uname.setText(findUser.getUname());
        et_ubirth.setText(findUser.getUbirth());
        et_uphone.setText(findUser.getUphone());
        et_uaddress.setText(findUser.getUaddress());


    }
    public void back(View v){
//        Intent intent=new Intent(PersonInformationActivity.this,MainActivity.class);
//        startActivity(intent);
        PersonInformationActivity.this.finish();
    }

    public void modify(View v){
        Intent intent=new Intent(PersonInformationActivity.this,ChangeInformationActivity.class);
        startActivity(intent);
        PersonInformationActivity.this.finish();
    }
}