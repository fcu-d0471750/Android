/*
*MVC架構之一
* Model
* 用於處理Json資料讀取、儲存、使用
*
* */
package modelclasspackage;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import viewclasspackage.Rainer_item_Class;

public class Model_Class {


    //===========================================================
    //宣告變數
    //===========================================================
    //儲存Json_Volley_Class的資料
    private ArrayList<Rainer_item_Class> Rainer_list = new ArrayList();
    //外部的Activity
    private Context context;
    //Json_Volley_Class
    private Json_Volley_Class JVC;
    //自訂news類別List
    private List<Rainer_item_Class> mDatas;

    //===========================================================
    //建構子(外部的Activity)
    //===========================================================
    public Model_Class(Context context){
        this.context = context;
        //設定使用的Activity
        JVC = new Json_Volley_Class(context);
    }

   /*
      =========================================================================================================================
     內部處理
    ==========================================================================================================================
    */
    //===========================================================
    //取得JVC資料，依照使用者要取得的資料
    //===========================================================
    private void GetJVCData(){
        Rainer_list = JVC.GetJsonData();
    }


  /*
     =========================================================================================================================
    外部使用
   ==========================================================================================================================
   */
    public ArrayList<Rainer_item_Class> GetRainerData(){
        GetJVCData();
        Log.d("Mod" , "" + Rainer_list.size());
        return Rainer_list;
    }


}//Model_Class
