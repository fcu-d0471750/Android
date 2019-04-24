package com.example.user.basicrecycleview;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //=======================================================================
    //宣告變數
    //=======================================================================
    //自訂GalleryAdapter
    private GalleryAdapter mAdapter;
    //自訂news類別List
    private List<news> mDatas;
    //=======================================================================
    //宣告UI
    //=======================================================================
    //RecycleView
    private RecyclerView mRecyclerView;


    //=======================================================================
    //onCreate
    //=======================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //螢幕保持直向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //資料初始化
        initDatas();
        //UI初始化
        UI_Inutial();


        //new一個Adapter
        mAdapter = new GalleryAdapter(this, mDatas);
        //設置自訂Adapter
        mRecyclerView.setAdapter(mAdapter);
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
    }

    //=======================================================================
    //資料初始化
    //=======================================================================
    private void initDatas()
    {
        //mDatas = new ArrayList<>(Arrays.asList(R.drawable.bread_01, R.drawable.bread_02, R.drawable.bread_03, R.drawable.bread_01, R.drawable.bread_02, R.drawable.bread_02, R.drawable.bread_01, R.drawable.bread_03, R.drawable.bread_03));
        mDatas = new ArrayList<news>();
        mDatas.add(new news("A" , R.drawable.bread_01)  );
        mDatas.add( new news("B" , R.drawable.bread_02) );
        mDatas.add( new news("C" , R.drawable.bread_03) );
        mDatas.add( new news("D" , R.drawable.bread_03) );
        mDatas.add( new news("E" , R.drawable.bread_02) );
        mDatas.add( new news("F" , R.drawable.bread_01) );
    }


}//MainActivity
