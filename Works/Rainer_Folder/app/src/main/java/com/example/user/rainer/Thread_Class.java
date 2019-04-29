/*
* Thread Class
*用於分擔M、V二者的運算要求
*
* */

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
*/

package com.example.user.rainer;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import controlclasspackage.Control_Class;

public class Thread_Class extends Thread{

    //===========================================================
    //宣告變數
    //===========================================================
    //使用Thead的Activity
    private Context context;
    //使用Thead的類別
    private Control_Class Con;
    //工作編號
    private int WorkNumber;

    //Thread
    private Thread t;
    //Thread是否執行 true:執行 false:不執行
    private boolean Run_able = true;

    //===========================================================
    //建構子(外部的Activity，使用Thead的類別，工作編號)
    //===========================================================
    public Thread_Class(Context context , Control_Class Con , int WorkNumber){
        super();

        this.context = context;
        this.Con = Con;
        this.WorkNumber = WorkNumber;
    }


   /*
      =========================================================================================================================
     內部處理
    ==========================================================================================================================
    */

   //Runnable 為被處理的事件
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
                    msg.what = WorkNumber;
                    //寄送要求
                    mHandler.sendMessage(msg);
                }
                //例外錯誤處理
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (Run_able);
        }
    };

    //處理Runnable寄來的要求
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //依照Message設定的編號，來設定要處理的工作
            switch (msg.what){
                case 0:
                    //更新View
                    Con.Con_DO();
                    break;
                default:
                    break;
            }//switch
        }
    };

   /*
     =========================================================================================================================
    外部使用
   ==========================================================================================================================
   */

   //使用Thread
   public void Thread_Use(){
       //產生新的Thread
       t = new Thread(runnable);
       //設定Thread可以執行
       Run_able = true;
       //使用Thread
       t.start();

   }

   //關閉Thread
   public void Thread_Close(){
       //移除mHandler指派的工作
       mHandler.removeCallbacks(runnable);
       //設定Thread不可以執行
       Run_able = false;
       //中斷Thread
       t.interrupt();
       //清空Thread
       t = null;

   }

}//Thread_Class
