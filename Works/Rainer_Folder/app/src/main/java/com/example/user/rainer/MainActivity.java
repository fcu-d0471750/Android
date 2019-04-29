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



    /*
    *Thread更新View
    *sendMessage給Handle，在handleMessage
    *
    * 在android中系统不允許在非Main Thread更新UI。當我們在非Main Thread做了耗時操作後，需要去更新UI的時候，我們就需要使用Handler來執行更新操作。
    *
    * 參數名稱    型別         用途
    * what        int               標記識別符號
    * obj         Object           物件, 必須Parcelable
    * data        Bundle          Bundle物件
    * callback   Runnable     實作Runnable的callback
    *
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            do{
                try {
                    //5秒執行1次
                    Thread.sleep(5000);
                    //new一個Message要求Thread執行
                    Message msg = new Message();
                    //設定要求Thread的編號，表示哪一個Thread做哪份工作
                    msg.what = msgKey1;
                    //寄送要求
                    mHandler.sendMessage(msg);

                }
                //例外錯誤處理
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    };

    //處理Message寄來的要求
    private  Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //依照Message設定的編號，來設定要處理的工作
            switch (msg.what){
                case msgKey1:
                        //更新View
                        Con.Con_DO();
                    break;
                default:
                    break;
            }//switch
        }
    };
*/

    //=======================================================================
    //UI初始化
    //=======================================================================
    /*void UI_Inutial(){
        //綁定RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        //設置佈局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //橫向排列
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //設定方向
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RE = (Button) findViewById(R.id.rere);
        RE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatas = Con.Update_Model_View();
                //如果讀取完資料
                if(check==false) {
                    //new一個Adapter
                    mAdapter = new GalleryAdapter(MainActivity.this, mDatas);
                    //設置自訂Adapter
                    mRecyclerView.setAdapter(mAdapter);
                    //mAdapter.notifyItemRangeChanged(0,mDatas.size()-1);
                    check = true;
                }
                //RecycleView刷新
                else{
                    mDatas.set(0, new Rainer_item_Class("AAAA" , 15 , "AAAA" , 20 , 30 , 40 , "AAAAA"));

                    mAdapter.ViewUpdate(mDatas);

                }

            }
        });

    }*/

    /*//=======================================================================
    //資料初始化
    //=======================================================================
    private void initDatas()
    {
        //mDatas = new ArrayList<>(Arrays.asList(R.drawable.bread_01, R.drawable.bread_02, R.drawable.bread_03, R.drawable.bread_01, R.drawable.bread_02, R.drawable.bread_02, R.drawable.bread_01, R.drawable.bread_03, R.drawable.bread_03));
        mDatas = new ArrayList<Rainer_item_Class>();
        mDatas.add(new Rainer_item_Class("AAAA" , 15 , "AAAA" , 20 , 30 , 40 , "AAAAA") );
        mDatas.add(new Rainer_item_Class("BBBB" , 15 , "BBBB" , 20 , 30 , 40 , "BBBB") );
        mDatas.add(new Rainer_item_Class("CCCC" , 15 , "CCCC" , 20 , 30 , 40 , "CCCC") );
        mDatas.add(new Rainer_item_Class("DDDD" , 15 , "DDDD" , 20 , 30 , 40 , "DDDD") );
        mDatas.add(new Rainer_item_Class("EEEE" , 15 , "EEEE" , 20 , 30 , 40 , "EEEE") );

    }*/





}//MainActivity
