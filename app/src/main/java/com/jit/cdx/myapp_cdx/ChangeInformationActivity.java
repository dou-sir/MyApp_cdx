package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jit.cdx.myapp_cdx.dao.Impl.UserDaoImpl;
import com.jit.cdx.myapp_cdx.dao.UserDao;
import com.jit.cdx.myapp_cdx.entity.MyApplicationInfo;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.Impl.UserServiceImpl;
import com.jit.cdx.myapp_cdx.service.UserService;

public class ChangeInformationActivity extends AppCompatActivity {
    private Button back,sure,cancel;
    private EditText et_uname,et_uphone,et_uaddress,et_ubirth;

    private UserService userService = new UserServiceImpl(ChangeInformationActivity.this);
    private UserDao userDao=new UserDaoImpl(this);
    private User user;
    private MyApplicationInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);
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
        sure=(Button)findViewById(R.id.sure);
        et_uname=(EditText)findViewById(R.id.et_uname);
        et_uphone=(EditText)findViewById(R.id.et_uphone);
        et_uaddress=(EditText)findViewById(R.id.et_uaddress);
        et_ubirth=(EditText)findViewById(R.id.et_ubirth);
        cancel=(Button)findViewById(R.id.cancel);

    }

    private void init(){
        User findUser = user;
        et_uname.setText(findUser.getUname());
        et_ubirth.setText(findUser.getUbirth());
        et_uphone.setText(findUser.getUphone());
        et_uaddress.setText(findUser.getUaddress());

    }

    public void back(View v){
//        Intent intent=new Intent(ChangeInformationActivity.this,PersonInformationActivity.class);
//        startActivity(intent);
        ChangeInformationActivity.this.finish();
    }

    public void sure(View v){
        User findUser1 = userService.findUserByUname(user.getUname());

        user = findUser1;

        user.setUphone(et_uphone.getText().toString());
        //TODO 性别
        user.setUbirth(et_ubirth.getText().toString());
        user.setUaddress(et_uaddress.getText().toString());
        userService.modifyUser(user);


        info.setUser(user);

        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();

//        Intent intent=new Intent(ChangeInformationActivity.this,PersonInformationActivity.class);
//        startActivity(intent);
        ChangeInformationActivity.this.finish();





    }
    public void cancel(View v){

//        Intent intent=new Intent(ChangeInformationActivity.this,PersonInformationActivity.class);
//        startActivity(intent);
        ChangeInformationActivity.this.finish();
    }


}
