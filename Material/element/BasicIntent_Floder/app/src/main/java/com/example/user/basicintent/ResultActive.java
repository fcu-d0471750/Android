package com.example.user.basicintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ResultActive extends AppCompatActivity {

    public Button Back_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_active);

        //UI初始化
        UI_Initial();
        //Button功能初始化
        Button_Initial();

    }



    //=======================================
    //UI初始化
    //=======================================
    void UI_Initial(){

        Back_Button = (Button) findViewById(R.id.Back_Button);
    }


    //=======================================
    //Button功能初始化
    //=======================================
    void Button_Initial(){
        Back_Button_Function();
    }

    //=======================================
    //Back_Button功能
    //=======================================
    void Back_Button_Function(){
        Back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultActive_To_MainActive();
            }
        });
    }

    //=======================================
    //切換頁面(ResultActive到MainActine)
    //=======================================
    void ResultActive_To_MainActive(){
        //Intent
        Intent intent = new Intent();

        //接收從MainActivity傳來的資料
        Bundle bundle = new Bundle();

        //放入計算後的資料
        bundle.putString("Hello" , "startActivityForResult");
        intent.putExtras(bundle);

        //設定回傳的頁面(resultCode(自身識別碼))
        ResultActive.this.setResult(2 , intent);

        //結束目前 Activity(如果沒有要完全結束ResultActive，則不用打這一行)
        ResultActive.this.finish();
    }

}//ResultActive
