/*
*MVC架構之一
* Controller
* 用於告訴Model、View需要進行的動作
*
* */
package controlclasspackage;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import modelclasspackage.Model_Class;
import viewclasspackage.Rainer_item_Class;
import viewclasspackage.View_Class;

public class Control_Class {

    //===========================================================
    //宣告變數
    //===========================================================
    //儲存Model_Use的資料
    private ArrayList<Rainer_item_Class> Rainer_list = new ArrayList();

    //外部的Activity
    private Context context;

    //Model
    private Model_Class Model_Use;
    //View
    private View_Class View_Use;

    //===========================================================
    //建構子(外部的Activity)
    //===========================================================
    public Control_Class(Context context){
        this.context = context;
        Model_Use = new Model_Class(context);
        View_Use = new View_Class(context);
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
    //更新Model和View
    //===========================================================
    public ArrayList<Rainer_item_Class> Update_Model_View(){
        //取得新的資料
        Rainer_list = Model_Use.GetRainerData();
        Log.d("Con" , "" + Rainer_list.size());
        return Rainer_list;
    }



}//Control_Class
