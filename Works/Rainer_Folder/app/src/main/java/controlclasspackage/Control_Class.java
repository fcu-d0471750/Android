/*
*MVC架構之一
* Controller
* 用於告訴Model、View需要進行的動作
*
* */
package controlclasspackage;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.rainer.MainActivity;
import com.example.user.rainer.R;

import java.util.ArrayList;

import modelclasspackage.Model_Class;
import viewclasspackage.GalleryAdapter;
import viewclasspackage.Rainer_item_Class;
import viewclasspackage.View_Class;

public class Control_Class {

    //===========================================================
    //宣告變數
    //===========================================================
    //儲存Model_Use的資料
    private ArrayList<Rainer_item_Class> mDatas  = new ArrayList<Rainer_item_Class>();

    //外部的Activity
    private Context context;
    //
    private Activity activity;

    //Model
    private Model_Class Model_Use;
    //View
    private View_Class View_Use;


    //=======================================================================
    //宣告UI
    //=======================================================================

    //===========================================================
    //建構子(外部的Activity)
    //===========================================================
    public Control_Class(Context context , Activity activity){
        this.context = context;
        this.activity = activity;
        Model_Use = new Model_Class(context);
        View_Use = new View_Class(context, activity,this);
    }

    /*
      =========================================================================================================================
     內部處理
    ==========================================================================================================================
    */
    //===========================================================
    //取得Model資料
    //===========================================================
    private ArrayList<Rainer_item_Class> GetModelData(){
        return Model_Use.GetRainerData();
    }



  /*
     =========================================================================================================================
    外部使用
   ==========================================================================================================================
   */

    //===========================================================
    //Rainer初始化
    //===========================================================
    public void Rainer_Initial(){
        //取得新的資料
        mDatas = Model_Use.GetRainerData();
        View_Use.UI_DO();
    }

    //===========================================================
    //呼叫Model資料
    //===========================================================
    public ArrayList<Rainer_item_Class> Get_Model_Data(){
        //取得新的資料
        mDatas = Model_Use.GetRainerData();
        Log.d("Con" , "" + mDatas.size());
        return mDatas;
    }

    //===========================================================
    //更新Model和View
    //===========================================================
    public void Con_DO(){
        View_Use.View_Update();
    }

}//Control_Class
