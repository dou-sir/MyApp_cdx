package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.Impl.UserServiceImpl;
import com.jit.cdx.myapp_cdx.service.UserService;

import java.util.Random;

public class RegistActivity extends AppCompatActivity {
    private Button back;
    private EditText username,password,regphone,codes;
    private TextView showCode;

    private  String strCode;
    private String tempCode;
    private boolean uphoneFlag,upwdFlag,checkCodeFlag;

    private UserService userService = new UserServiceImpl(RegistActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
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
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        regphone = findViewById(R.id.regphone);
        showCode = findViewById(R.id.showCode);
        codes = findViewById(R.id.codes);
    }

    private void init(){
        tempCode=checkCode();//只执行一次  生成验证码
        showCode.setText(tempCode);
        codes.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            // private String[] strs;
            //private  String strCode;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //1.解析  由于为了美观 在验证码生成的时候添加了空格 这里需要进行对比 我们需要还原验证码
                //String[] strs=tempCode.split(" ");
                //String srtCode="";
                //for(int i=0;i<strs.length;i++) {
                //   strCode += strs[i];
                //}
                //System.out.println("验证码："+strCode);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp=s;

            }

            @Override
            public void afterTextChanged(Editable s) {


                if(strCode.equalsIgnoreCase(temp.toString())){

                    checkCodeFlag=true;
                }else{

                    checkCodeFlag=false;
                }


            }
        });

    }

    //返回主界面
    public void back(View v){
        Intent intent=new Intent(RegistActivity.this,MainActivity.class);
        startActivity(intent);
        RegistActivity.this.finish();
    }

    public String checkCode(){
        //规定验证码范围
        char[] chars="ABCDEFGHIJKLMNOPQRSTUVWXYZqwertyuioplkjhgfdsazxcvbnm1234567890".toCharArray();
        //2.从范围中随机取内容
        Random r=new Random();
        StringBuilder sb=new StringBuilder();
        //3.四位验证码
        strCode="";//初始值清空  否则是null
        for(int i=0;i<4;i++){
            int n=r.nextInt(chars.length);//根据chars长度  随机选取下标
            sb.append(chars[n]+" ");//根据下标将内容   放入到 sb对象
            strCode+=chars[n];
        }
        return sb.toString();
    }
    //点击验证码切换
    public void codeChange(View v){
        tempCode=checkCode();//重新  生成验证码
        showCode.setText(tempCode);

    }
    //确认注册
    public void register(View v){
        User findUser=userService.findUserByUname(username.getText().toString());
        int a=findUser.getUid();
        if(null!=findUser.getUname())
            a++;
        if(password.getText().toString().equals("")){
            upwdFlag=false;
        }else {
            upwdFlag=true;
        }

        if(regphone.getText().toString().equals("")){
            uphoneFlag=false;
        }else{
            uphoneFlag=true;
        }


        if(a==0&&checkCodeFlag==true&&upwdFlag==true&&uphoneFlag==true){


            User user=new User();
            user.setUname(username.getText().toString());
            user.setUpwd(password.getText().toString());
            user.setUphone(regphone.getText().toString());
            userService.addUser(user);

            Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
            intent.putExtra("uname",username.getText().toString());
            startActivity(intent);
            RegistActivity.this.finish();

        }
        else if(a!=0){
            Toast.makeText(RegistActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
        }else if(a==0&&checkCodeFlag==false&&upwdFlag==true&&uphoneFlag==true){
            Toast.makeText(RegistActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
        }else if(a==0&&checkCodeFlag==true&&upwdFlag==false&&uphoneFlag==true){
            Toast.makeText(RegistActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
        }else if(a==0&&checkCodeFlag==true&&upwdFlag==true&&uphoneFlag==false){
            Toast.makeText(RegistActivity.this,"手机号码不能为空",Toast.LENGTH_SHORT).show();
        }
    }
}
