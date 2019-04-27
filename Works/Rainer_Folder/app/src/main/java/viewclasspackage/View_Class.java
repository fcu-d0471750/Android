/*
*MVC架構之一
* View
* 用於顯示使用者看到的介面
*
* */
package viewclasspackage;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.rainer.MainActivity;
import com.example.user.rainer.R;

import java.util.ArrayList;

import controlclasspackage.Control_Class;

public class View_Class {

    //===========================================================
    //宣告變數
    //===========================================================
    //儲存Model_Use的資料
    private ArrayList<Rainer_item_Class> mDatas  = new ArrayList<Rainer_item_Class>();

    //外部的Activity
    private Context context;
    //外部的Activity(用於綁定UI)
    private Activity activity;

    private Control_Class Con;

    boolean check = false;
    //=======================================================================
    //宣告UI
    //=======================================================================
    //自訂GalleryAdapter
    private GalleryAdapter mAdapter;

    //RecycleView
    private RecyclerView mRecyclerView;

    Button RE;
    //===========================================================
    //建構子(外部的Activity)
    //===========================================================
    public View_Class(Context context , Activity activity , Control_Class Con){
        this.context = context;
        this.activity = activity;
        this.Con = Con;
    }

    /*
      =========================================================================================================================
     內部處理
    ==========================================================================================================================
    */
    //=======================================================================
    //UI初始化
    //=======================================================================
    void UI_Initial(){
        //綁定RecyclerView
        mRecyclerView = (RecyclerView) activity.findViewById(R.id.id_recyclerview_horizontal);
        //設置佈局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        //橫向排列
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //設定方向
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RE = (Button) activity.findViewById(R.id.rere);
        RE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatas = Con.Get_Model_Data();
                //如果讀取完資料
                if(check==false) {
                    //new一個Adapter
                    mAdapter = new GalleryAdapter(context, mDatas);
                    //設置自訂Adapter
                    mRecyclerView.setAdapter(mAdapter);
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

  /*
     =========================================================================================================================
    外部使用
   ==========================================================================================================================
   */
  //=======================================================================
  //View初始化
  //=======================================================================
    public void UI_DO(){
        UI_Initial();
    }

   //=======================================================================
   //View更新
   //=======================================================================
    public void View_Update(){
        mDatas = Con.Get_Model_Data();
        //如果讀取完資料
        if(check==false) {
            //new一個Adapter
            mAdapter = new GalleryAdapter(context, mDatas);
            //設置自訂Adapter
            mRecyclerView.setAdapter(mAdapter);
            check = true;
        }
        //RecycleView刷新
        else{
            mDatas.set(0, new Rainer_item_Class("AAAA" , 15 , "AAAA" , 20 , 30 , 40 , "AAAAA"));
            mAdapter.ViewUpdate(mDatas);

        }
    }

}//View_Class
