package com.example.user.basicintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView Show_Text;

    //單純切換Activity的Button
    public Button BasicIntent_Button;

    //從切換Activity獲得資料的Button
    public Button Intent_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI初始化
        UI_Initial();

        //Button功能初始化
        Button_Initial();

    }


    //=======================================
    //UI初始化
    //=======================================
    void UI_Initial(){

        Show_Text = (TextView) findViewById(R.id.Show_TextView);

        BasicIntent_Button = (Button) findViewById(R.id.BasicIntent_Button);

        Intent_Button = (Button) findViewById(R.id.Intent_Button);

    }


    //=======================================
    //Button功能初始化
    //=======================================
    void Button_Initial(){

        BasicIntent_Button_Function();
        Intent_Button_Function();
    }

    //=======================================
    //BasicIntent_Button功能
    //=======================================
    void BasicIntent_Button_Function(){
        BasicIntent_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActive_To_BasicIntentActivity();
            }
        });
    }

    //=======================================
    //Intent_Button功能
    //=======================================
    void Intent_Button_Function(){
        Intent_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActive_To_ResultActive(2);
            }
        });
    }



    //=======================================
    //切換頁面(MainActine到BasicIntentActive)
    //=======================================
    void MainActive_To_BasicIntentActivity(){
        //MainActivity.class(傳送資料)
        int age = 18;
        String name = "Xavier";

        //new一個Intent，用於要切換Activity
        Intent intent = new Intent();
        //設定要切換的Activity
        intent.setClass(MainActivity.this , BasicIntentActivity.class);

        //new一個Bundle物件，並將要傳遞的資料傳入
        Bundle bundle = new Bundle();
        //傳遞int
        bundle.putInt("age", age);
        //傳遞String
        bundle.putString("name", name);

        //將Bundle物件傳給intent
        intent.putExtras(bundle);

        //切換Activity
        startActivity(intent);
    }


    //=====================================================================================================================================================================================================
    //分隔線(上: 單純切換 ， 下: 從 A 跳轉到 B，再從 B 傳參數回去 )
    //=====================================================================================================================================================================================================

    //=======================================
    //切換頁面(MainActine到ResultActive)
    //=======================================
    void MainActive_To_ResultActive(int i){
        //Intent
        Intent intent = new Intent();

        //設定切換頁面
        if(i==2)intent.setClass(MainActivity.this , ResultActive.class);

        //啟動切換(requestCode(傳送出去的Activity的識別碼))
        startActivityForResult(intent, 1);


        //結束目前 Activity(如果沒有要完全結束MainActivity，則不用打這一行)
        //MainActivity.this.finish();
    }

    //=================================================
    //用於接收startActivityForResult回傳的資料(傳送出去的Activity ， 切換過去的Activity)
    //=================================================
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //確認是從哪個Activity回傳
        if(resultCode == 2)
        {
            //確認ResultActive傳給MainActivity的動作
            if(requestCode == 1)
            {
                String N = data.getExtras().getString("Hello");
                Show_Text.setText("" + N);
            }
        }
        else if(resultCode == 3){
            String N = data.getExtras().getString("Hello");
            Show_Text.setText("" + N);
        }
        else if(resultCode==4){
            Show_Text.setText("ListView");
        }

    }

}//MainActivity
