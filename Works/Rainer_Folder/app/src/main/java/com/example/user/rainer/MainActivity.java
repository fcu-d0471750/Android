package com.example.user.rainer;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controlclasspackage.*;
import dialogpackage.BasicDiaLog;
import modelclasspackage.*;
import viewclasspackage.*;

public class MainActivity extends AppCompatActivity {


    //=======================================================================
    //宣告變數
    //=======================================================================
    //Control
    private Control_Class Con = new Control_Class(MainActivity.this , MainActivity.this);
    //Thread(用於更新UI)
    private Thread_Class thread_class = new Thread_Class(MainActivity.this , Con , 0);

    //=======================================================================
    //宣告UI
    //=======================================================================

    //=======================================================================
    //onCreate
    //=======================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //螢幕保持直向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Rainer初始化
        Con.Rainer_Initial();

    }

    //=======================================================================
    //onResume
    //=======================================================================
    @Override
    protected void onResume() {
        super.onResume();

        //使用Thread
        thread_class.Thread_Use();
    }

    //=======================================================================
    //onPause
    //=======================================================================
    @Override
    protected void onPause() {
        super.onPause();

        //停用Thread
        thread_class.Thread_Close();
    }


}//MainActivity
