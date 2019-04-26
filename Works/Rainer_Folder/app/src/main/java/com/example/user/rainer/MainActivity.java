package com.example.user.rainer;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import controlclasspackage.*;
import modelclasspackage.*;
import viewclasspackage.*;

public class MainActivity extends AppCompatActivity {


    //=======================================================================
    //宣告變數
    //=======================================================================
    //自訂GalleryAdapter
    private GalleryAdapter mAdapter;
    //自訂news類別List
    private ArrayList<Rainer_item_Class> mDatas  = new ArrayList<Rainer_item_Class>();
    private Control_Class Con = new Control_Class(MainActivity.this);
    boolean check = false;
    //=======================================================================
    //宣告UI
    //=======================================================================
    //RecycleView
    private RecyclerView mRecyclerView;

    Button RE;
    //=======================================================================
    //onCreate
    //=======================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //螢幕保持直向
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.d("Main" , "AAAA");
        //Con = new Control_Class(MainActivity.this);



        //mDatas = new ArrayList<Rainer_item_Class>();
        mDatas = Con.Update_Model_View();

        //initDatas();

        //UI初始化
        UI_Inutial();

    }


    //=======================================================================
    //UI初始化
    //=======================================================================
    void UI_Inutial(){
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

    }

    //=======================================================================
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

    }





}//MainActivity
