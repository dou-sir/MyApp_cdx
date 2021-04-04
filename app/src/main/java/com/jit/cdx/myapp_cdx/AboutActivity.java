package com.jit.cdx.myapp_cdx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        back=(Button)findViewById(R.id.back);
        System.out.println("");
    }

    public void back(View v){

        AboutActivity.this.finish();
    }
}
