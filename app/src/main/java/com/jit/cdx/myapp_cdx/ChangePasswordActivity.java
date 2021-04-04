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

public class ChangePasswordActivity extends AppCompatActivity {
    private Button sure,reset,back;
    private EditText oripassword,afterpassword,surepassword;

    private UserService userService = new UserServiceImpl(ChangePasswordActivity.this);
    private UserDao userDao=new UserDaoImpl(this);
    private User user;
    private MyApplicationInfo info;
    private boolean upwdFlag,sureFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        info = (MyApplicationInfo)getApplication();
        user = info.getUser();
        initComponent();

    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getSupportActionBar().hide();
    }

    private void initComponent(){
        sure=(Button)findViewById(R.id.sure);
        reset=(Button)findViewById(R.id.reset);
        back=(Button)findViewById(R.id.back);
        oripassword=(EditText)findViewById(R.id.oripassword);
        afterpassword=(EditText)findViewById(R.id.afterpassword);
        surepassword=(EditText)findViewById(R.id.surepassword);

    }


    public void back(View v){
//        Intent intent=new Intent(ChangePasswordActivity.this,MainActivity.class);
//        startActivity(intent);
        ChangePasswordActivity.this.finish();
    }
    public void reset(View v){
        oripassword.setText("");
        afterpassword.setText("");
        surepassword.setText("");
    }

    public void sure(View v){
        User findUser=userService.findUserByUname(user.getUname());
        String upwd=findUser.getUpwd();
        //System.out.println(upwd);
        if(oripassword.getText().toString().equals(upwd)){
            upwdFlag=true;
            //System.out.println("tttttt");
        }else{
            upwdFlag=false;
            //System.out.println("ffffff");
        }

        if(afterpassword.getText().toString().equals(surepassword.getText().toString())){
            sureFlag=true;
        }else{
            sureFlag=false;
        }
        if(upwdFlag==true&&sureFlag==true){
            User findUser1=userService.findUserByUname(user.getUname());
            user = findUser1;
            user.setUpwd(afterpassword.getText().toString());
            userService.modifyUser(user);

//            System.out.println(user);
            info.setUser(user);

            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(ChangePasswordActivity.this,MainActivity.class);
//            startActivity(intent);
            ChangePasswordActivity.this.finish();
        }else if(upwdFlag==false&&sureFlag==true){
            Toast.makeText(this,"原密码错误",Toast.LENGTH_SHORT).show();

        }else if(upwdFlag==true&&sureFlag==false){
            Toast.makeText(this,"前后两次密码不一致",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"原密码错误",Toast.LENGTH_SHORT).show();
        }

    }
}
