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

import com.jit.cdx.myapp_cdx.entity.MyApplicationInfo;
import com.jit.cdx.myapp_cdx.entity.User;
import com.jit.cdx.myapp_cdx.service.Impl.UserServiceImpl;
import com.jit.cdx.myapp_cdx.service.UserService;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private Button back,signup,login,forgetPassword;
    private EditText username,codes,password;
    private  String strCode;
    private String tempCode;
    private String uname = "";
    private TextView showCode;
    private boolean unameFlag,upwdFlag,checkCodeFlag;

    private User user;
    private UserService userService=new UserServiceImpl(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        if(intent.getStringExtra("uname")!=null)
            uname = intent.getStringExtra("uname");
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
        login=(Button)findViewById(R.id.login);
        username=(EditText)findViewById(R.id.username);
        codes=(EditText)findViewById(R.id.codes);
        password=(EditText)findViewById(R.id.password);
        signup=(Button) findViewById(R.id.signup);
        showCode=(TextView)findViewById(R.id.showCode);
        forgetPassword=(Button)findViewById(R.id.forgetPassword);

    }
    private void init(){
        if (!uname.equals(""))
            username.setText(uname);

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
    //跳转到主界面
    public void exitToHome(View v){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);

        startActivity(intent);
        LoginActivity.this.finish();

    }
    //跳转到注册界面
    public void signup(View v){
        Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    public String checkCode()
    {
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
    //验证登录
    public void login(View v){
        User inUser =new User();
        inUser.setUname(username.getText().toString());
        inUser.setUpwd(password.getText().toString());
        String result=userService.checkLogin(inUser);
        if(result.equals("success")&&checkCodeFlag==true){
            //登录
            user = userService.findUserByUname(inUser.getUname());
            user.setUstate(1);
            userService.modifyUser(user);
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);//TODO
            intent.putExtra("user",user);

            startActivity(intent);
            LoginActivity.this.finish();
            //Toast.makeText(this,"用户"+et_login_uname.getText().toString()+"登录成功！",Toast.LENGTH_SHORT).show();

        }else if(result.equals("success")&&checkCodeFlag==false){
            Toast.makeText(this,"验证码错误",Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("uPwdErr")&&checkCodeFlag==true){
            Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();

        }else if(result.equals("uPwdErr")&&checkCodeFlag==false){
            Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
        }else if(result.equals("uNameErr")&&checkCodeFlag==false){
            Toast.makeText(this,"用户名错误",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"用户名错误",Toast.LENGTH_SHORT).show();
        }

    }
    //忘记密码操作
    public void forget(View v){
        Toast.makeText(this,"忘记了密码",Toast.LENGTH_SHORT).show();
    }

}
