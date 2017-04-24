package com.qianwang.fresco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;

    private void initView(){
        mButton= (Button) findViewById(R.id.btn_base);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_base:
                Intent intent=new Intent(this,BaseUseActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }
}
