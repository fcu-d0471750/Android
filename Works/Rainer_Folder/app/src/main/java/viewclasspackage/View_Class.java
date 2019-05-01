/*
*MVC架構之一
* View
* 用於顯示使用者看到的介面
*
* */
package viewclasspackage;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.rainer.MainActivity;
import com.example.user.rainer.R;

import java.util.ArrayList;

import controlclasspackage.Control_Class;
import dialogpackage.BasicDiaLog;

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

    //接收Control的呼叫
    private Control_Class Con;
    //是否為第1次載入資料，true:是 false:不是
    boolean check = false;
    //=======================================================================
    //宣告UI
    //=======================================================================
    //自訂GalleryAdapter
    private GalleryAdapter mAdapter;

    //RecycleView
    private RecyclerView mRecyclerView;

    //資料來源按扭
    private Button DataSourse_Button;

    //製作方按扭
    private Button Maker_Button;
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
        //綁定資料來源按扭
        DataSourse_Button = (Button) activity.findViewById(R.id.DataSourse_Button);
        Maker_Button = (Button) activity.findViewById(R.id.Maker_Button);
    }

    //======================================================
    //Button初始化
    //======================================================
    void Button_FunctionI_Initial(){
        DataSourse_Button_Function();
        Maker_Button_Function();
    }

    //======================================================
    //DataSourse_Button功能
    //======================================================
    void DataSourse_Button_Function(){
        DataSourse_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個BasicDiaLog
                BasicDiaLog dialog = new BasicDiaLog().newInstance("資料來源" ,   "政府資料開放平台\n\n一般天氣預報-今明36小時天氣預報\n\n提供機關: 交通部中央氣象局\n\n授權方式: 政府資料開放授權條款-第1版\n\n計費方式: 免費\n");
                //顯示資料來源的DiaLog
                dialog.show(activity.getFragmentManager() , "DataSource");

            }
        });//setOnClickListener
    }

    //======================================================
    //Maker_Button功能
    //======================================================
    void Maker_Button_Function(){
        Maker_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個BasicDiaLog
                BasicDiaLog dialog = new BasicDiaLog().newInstance("製作方" , "逢甲大學資訊工程學系 大學部\n");
                //顯示資料來源的DiaLog
                dialog.show(activity.getFragmentManager() , "Maker");

            }
        });//setOnClickListener
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
        //UI初始化
        UI_Initial();
        //Button初始化
        Button_FunctionI_Initial();
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
            //mDatas.set(0, new Rainer_item_Class("AAAA" , 15 , "AAAA" , 80 , 30 , 40 , "AAAAA"));
            mAdapter.ViewUpdate(mDatas);

        }
    }

}//View_Class
