/*
* AlterDialog基本用法
*
* */
package com.example.user.dialogelement;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DiaLogActivity extends AppCompatActivity {


    //========================================
    //宣告元件
    //========================================
    //一般式
    public Button Basic_Button;
    //條列式
    public Button List_Button;
    //單選式
    public Button SingleChoice_Button;
    //多選式
    public Button MultiChoice_Button;
    //自訂
    public Button User_Button;


    //自訂格式
    //final Dialog dialog = new Dialog(DiaLogActivity.this);

    //========================================
    //宣告變數
    //========================================
    //食物名稱
    ArrayList<String> Lunch = new ArrayList<String>();

    //單選式記錄編號
    private int singleChoiceIndex = 0;

    //多選式記錄編號
    List<Boolean> checkedStatusList = new ArrayList<>();

    //多選式項目記錄
    boolean[] checkedboolArray;

    //========================================
    //onCreate
    //========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_log);

        // Lunch String初始化
        Lunch_String_Initial();
        //checkedStatusList初始化
        checkedStatusList_Initial();

        //UI初始化
        UI_Initial();

        //Button功能初始化
        Button_Function_Initial();

    }


    //================================
    //UI初始化
    //================================
    void UI_Initial(){
        Basic_Button = (Button) findViewById(R.id.Basic_Button);
        List_Button = (Button) findViewById(R.id.List_Button);
        SingleChoice_Button = (Button) findViewById(R.id.SingleChoice_Button);
        MultiChoice_Button = (Button) findViewById(R.id.MultiChoice_Button);
        User_Button = (Button) findViewById(R.id.User_Button);
    }

    //================================
    //Button功能初始化
    //================================
    void Button_Function_Initial(){
        //Basic_Button功能
        Basic_Button_Function();
        //Line_Button功能
        List_Button_Function();
        //SingleChoice_Button功能
        SingleChoice_Button_Function();
        //MultiChoice_Button功能
        MultiChoice_Button_Function();
        //User_Button功能
        User_Button_Function();
    }

    //================================
    //Lunch String初始化
    //================================
    void Lunch_String_Initial(){
        Lunch.add(getString(R.string.lunch_1));
        Lunch.add(getString(R.string.lunch_2));
        Lunch.add(getString(R.string.lunch_3));
        Lunch.add(getString(R.string.lunch_4));
        Lunch.add(getString(R.string.lunch_5));
    }

    //================================
    //Lunch String初始化
    //================================
    void checkedStatusList_Initial(){
        //依照Lunch String的長度，設定checkedboolArray的數量
        checkedboolArray = new boolean[Lunch.size()];
        //預設為false，表未選擇
        for(int i=0; i<Lunch.size(); i++){
            checkedStatusList.add(false);
            checkedboolArray[i] = false;
        }
    }

    //================================
    //Basic_Button功能(一般對話框)
    //================================
    void Basic_Button_Function(){
        Basic_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DiaLogActivity.this)
                        //標題
                        .setTitle(R.string.lunch_time)
                        //內容
                        .setMessage(R.string.want_to_eat)

                        // (確定)
                        .setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //開發者實作............................
                                Toast.makeText(getApplicationContext(), R.string.gogo, Toast.LENGTH_SHORT).show();
                            }
                        })

                        //(拒絕)
                        .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //開發者實作............................
                                Toast.makeText(getApplicationContext(), R.string.i_am_not_hungry, Toast.LENGTH_SHORT).show();
                            }
                        })

                        //(中立)
                        .setNeutralButton(R.string.Wait, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //開發者實作............................
                                Toast.makeText(getApplicationContext(), R.string.diet, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }//onClick
        });//setOnClickListener
    }

    //================================
    //List_Button功能(條列式對話框)
    //================================
    void List_Button_Function(){
        List_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DiaLogActivity.this)
                        .setItems(Lunch.toArray(new String[Lunch.size()]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //開發者實作............................
                                String name = Lunch.get(which);
                                Toast.makeText(getApplicationContext(), getString(R.string.Eat) + name, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }//onClick
        });//setOnClickListener
    }

    //================================
    //SingleChoice_Button功能(單選式對話框)
    //================================
    void SingleChoice_Button_Function(){

        SingleChoice_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DiaLogActivity.this)
                        //(字串資料 , 上次選擇的項目編號 , 功能)
                        .setSingleChoiceItems(Lunch.toArray(new String[Lunch.size()]), singleChoiceIndex,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //開發者實作........................
                                        //記錄選擇的項目編號
                                        singleChoiceIndex = which;
                                    }
                                })
                        // (確定)
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //開發者實作..................
                                String name = Lunch.get(singleChoiceIndex);
                                Toast.makeText(getApplicationContext(),getString(R.string.Eat) + name, Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }//onClick
        });//setOnClickListener
    }

    //================================
    //MultiChoice_Button功能(多選式對話框)
    //================================
    void MultiChoice_Button_Function(){

        MultiChoice_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(DiaLogActivity.this)
                        //(字串資料 , 預設勾選或者不勾選 , 功能)
                        .setMultiChoiceItems(Lunch.toArray(new String[Lunch.size()]), checkedboolArray,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        //開發者實作..................
                                        //改變勾選或不勾選的畫面
                                        checkedStatusList.set(which, isChecked);
                                        //記錄選擇的項目
                                        checkedboolArray[which] = isChecked;
                                    }
                                })
                        // (確定)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //開發者實作..................
                                //用於儲存選擇的項目
                                StringBuilder sb = new StringBuilder();
                                //用於是否沒有選擇項目
                                boolean Isempty = true;

                                //如果有勾選，則儲存到sb裡
                                for(int i = 0; i < checkedStatusList.size(); i++){
                                    if(checkedStatusList.get(i) == true){
                                        sb.append(Lunch.get(i));
                                        sb.append(" ");
                                        //有選擇項目
                                        Isempty = false;
                                    }
                                }//for

                                //有選擇項目
                                if(Isempty == false)Toast.makeText(DiaLogActivity.this, "你選擇的是" + sb.toString(), Toast.LENGTH_SHORT).show();
                                //沒有選擇項目
                                else Toast.makeText(DiaLogActivity.this, "請選擇項目" , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }//onClick
        });//setOnClickListener
    }

    //================================
    //User_Button功能(自訂對話框)
    //================================
    void User_Button_Function(){

        User_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //自訂的AlertLog
                final View item = LayoutInflater.from(DiaLogActivity.this).inflate(R.layout.alertdialog_user, null);

                new AlertDialog.Builder(DiaLogActivity.this)
                        //標題
                        .setTitle(R.string.Input_user_name)
                        //設定自訂的AlertLog
                        .setView(item)

                        // (確定)
                        .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //開發者實作..................
                                //綁定自訂Layout上的EditText
                                EditText editText = (EditText) item.findViewById(R.id.edit_text);
                                //將EditText的輸入轉成字串
                                String name = editText.getText().toString();

                                //有輸入
                                if(TextUtils.isEmpty(name)==false){
                                    Toast.makeText(getApplicationContext(), getString(R.string.Hi)+ name , Toast.LENGTH_SHORT).show();
                                }
                                //沒有輸入
                                else {
                                    Toast.makeText(getApplicationContext(),  R.string.Input_user_name, Toast.LENGTH_SHORT).show();
                                }
                            }//onClick
                        })//setPositiveButton
                        .show();
            }//onClick
        });//setOnClickListener
    }

}//DiaLogActivity
