package com.example.user.basicintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BasicIntentActivity extends AppCompatActivity {

    public TextView BasicText;

    public Button BasicBack_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_intent);

        //UI初始化
        UI_Initial();
        //Button功能初始化
        Button_Initial();

        //接收資料(BadicIntentActive到MainActine)
        Receice_MainActive();
    }

    //=======================================
    //UI初始化
    //=======================================
    void UI_Initial(){

        BasicText = (TextView) findViewById(R.id.BasicText);

        BasicBack_Button = (Button) findViewById(R.id.BasicBack_Button);

    }


    //=======================================
    //Button功能初始化
    //=======================================
    void Button_Initial(){
        BasicBack_Button_Function();
    }

    //=======================================
    //BasicBack_Button功能
    //=======================================
    void BasicBack_Button_Function(){
        BasicBack_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasicIntentActive_To_MainActive();
            }
        });
    }



    //=======================================
    //接收資料(BadicIntentActive到MainActine)
    //=======================================
    void Receice_MainActive(){

        //new一個Bundle物件，並將傳遞的資料儲存
        Bundle bundle = getIntent().getExtras();
        //儲存String
        String name = bundle.getString("name");
        //儲存int
        int age = bundle.getInt("age");
        //設定TextView
        BasicText.setText("" + name);
    }

    //=======================================
    //從BasicIntentActive切換到MainActive
    //=======================================
    void BasicIntentActive_To_MainActive(){
        BasicBack_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new一個Intent，用於要切換Activity
                Intent intent = new Intent();

                //設定要切換的Activity
                intent.setClass(BasicIntentActivity.this , MainActivity.class);

                //切換Activity
                startActivity(intent);
            }
        });
    }

}//BasicIntentActivity
