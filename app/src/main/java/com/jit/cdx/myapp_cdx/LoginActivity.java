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

        tempCode=checkCode();//???????????????  ???????????????
        showCode.setText(tempCode);
        codes.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            // private String[] strs;
            //private  String strCode;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //1.??????  ?????????????????? ?????????????????????????????????????????? ???????????????????????? ???????????????????????????
                //String[] strs=tempCode.split(" ");
                //String srtCode="";
                //for(int i=0;i<strs.length;i++) {
                //   strCode += strs[i];
                //}
                //System.out.println("????????????"+strCode);

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
    //??????????????????
    public void exitToHome(View v){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);

        startActivity(intent);
        LoginActivity.this.finish();

    }
    //?????????????????????
    public void signup(View v){
        Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    public String checkCode()
    {
        //?????????????????????
        char[] chars="ABCDEFGHIJKLMNOPQRSTUVWXYZqwertyuioplkjhgfdsazxcvbnm1234567890".toCharArray();
        //2.???????????????????????????
        Random r=new Random();
        StringBuilder sb=new StringBuilder();
        //3.???????????????
        strCode="";//???????????????  ?????????null
        for(int i=0;i<4;i++){
            int n=r.nextInt(chars.length);//??????chars??????  ??????????????????
            sb.append(chars[n]+" ");//?????????????????????   ????????? sb??????
            strCode+=chars[n];
        }
        return sb.toString();
    }
    //?????????????????????
    public void codeChange(View v){
        tempCode=checkCode();//??????  ???????????????
        showCode.setText(tempCode);

    }
    //????????????
    public void login(View v){
        User inUser =new User();
        inUser.setUname(username.getText().toString());
        inUser.setUpwd(password.getText().toString());
        String result=userService.checkLogin(inUser);
        if(result.equals("success")&&checkCodeFlag==true){
            //??????
            user = userService.findUserByUname(inUser.getUname());
            user.setUstate(1);
            userService.modifyUser(user);
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);//TODO
            intent.putExtra("user",user);

            startActivity(intent);
            LoginActivity.this.finish();
            //Toast.makeText(this,"??????"+et_login_uname.getText().toString()+"???????????????",Toast.LENGTH_SHORT).show();

        }else if(result.equals("success")&&checkCodeFlag==false){
            Toast.makeText(this,"???????????????",Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("uPwdErr")&&checkCodeFlag==true){
            Toast.makeText(this,"????????????",Toast.LENGTH_SHORT).show();

        }else if(result.equals("uPwdErr")&&checkCodeFlag==false){
            Toast.makeText(this,"????????????",Toast.LENGTH_SHORT).show();
        }else if(result.equals("uNameErr")&&checkCodeFlag==false){
            Toast.makeText(this,"???????????????",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"???????????????",Toast.LENGTH_SHORT).show();
        }

    }
    //??????????????????
    public void forget(View v){
        Toast.makeText(this,"???????????????",Toast.LENGTH_SHORT).show();
    }

}
