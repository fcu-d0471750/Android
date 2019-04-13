/*
* 基礎Toast使用、客製Toast使用
*
* */
package com.example.user.basictoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ToastActivity extends AppCompatActivity {

    //=====================================================
    //宣告變數
    //=====================================================
    //Toast顯示位置(0:上 1:下 2:左 3:右 4:中)
    private int Toast_Direction = 0;

    //=====================================================
    //宣告UI
    //=====================================================
    //基本Toast按紐
    public Button BasicToast_Button;

    //客製化Toast按紐
    public Button UserToast_Button;

    //多選一RadioGroup
    public RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        //UI初始化
        UI_Initial();
        //Button初始化
        Button_Initial();

    }



    //==============================
    //UI初始化
    //==============================
    void UI_Initial(){
        BasicToast_Button = (Button) findViewById(R.id.BasicToast_Button);
        UserToast_Button = (Button) findViewById(R.id.UserToast_Button);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

    }

    //==============================
    //Button初始化
    //==============================
    void Button_Initial(){
        radioGroup_Function();
        BasicToast_Button_Function();
        UserToast_Button_Function();
    }


    //==============================
    //BasicToast_Button功能
    //==============================
    void BasicToast_Button_Function(){
        BasicToast_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasicToast();
            }
        });
    }

    //==============================
    //UserToast_Button_Button功能
    //==============================
    void UserToast_Button_Function(){
        UserToast_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserToast();
            }
        });
    }


    //==============================
    //radioGroup功能
    //==============================
    void radioGroup_Function(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //依照RadioButton的ID設定Toast位置
                switch(i){
                    //上
                    case R.id.radioButton_up:
                        Toast_Direction = 0;
                        break;
                    //下
                    case R.id.radioButton_down:
                        Toast_Direction = 1;
                        break;
                    //左
                    case R.id.radioButton_left:
                        Toast_Direction = 2;
                        break;
                    //右
                    case R.id.radioButton_right:
                        Toast_Direction = 3;
                        break;
                    //中
                    case R.id.radioButton_mid:
                        Toast_Direction = 4;
                        break;
                    //預設
                    default:
                        Toast_Direction = 0;
                        break;

                }//switch

            }//onCheckedChanged
        });
    }

    //==============================
    //Toast位置
    //==============================
    Toast setGravityToast(Toast toast){

        switch(Toast_Direction){
            //上
            case 0:
                toast.setGravity(Gravity.TOP,0,0);
                break;
            //下
            case 1:
                toast.setGravity(Gravity.BOTTOM,0,0);
                break;
            //左
            case 2:
                toast.setGravity(Gravity.LEFT   ,0,0);
                break;
            //右
            case 3:
                toast.setGravity(Gravity.RIGHT,0,0);
                break;
            //中
            case 4:
                toast.setGravity(Gravity.CENTER,0,0);
                break;
            //預設
            default:
                toast.setGravity(Gravity.TOP,0,0);
                break;
        }

        return toast;
    }

    //==============================
    //BasicToast
    //==============================
    void BasicToast(){
        //new一個Toast
        Toast toast = Toast.makeText(this, "Toast置中顯示", Toast.LENGTH_LONG);

        //(Toast起始位置 , X座標篇移值 (正數靠右、負數靠左)  , Y座標篇移值 (正數靠下、負數靠上))
        toast = setGravityToast(toast);

        //如果不需要RadioButton控制Toast位置，只需固定位置，則刪除toast = setGravityToast(toast)，改用下面一行
        //toast.setGravity(Gravity.BOTTOM,0,0);

        //顯示Toast
        toast.show();
    }

    //==============================
    //UserToast
    //==============================
    void UserToast(){
        //把xml的資源轉成view
        LayoutInflater inflater = getLayoutInflater();

        //R.layout.toast_view XML名稱
        //R.id.toast_layoutt XML裡面Layout ID
        View layout = inflater.inflate(R.layout.toast_user, (ViewGroup) findViewById(R.id.Toast_User_RelativeLayOut));

        //透過 inflater跟View方式來取得元件的控制權
        TextView text = (TextView) layout.findViewById(R.id.Toast_User_TextView);
        text.setText("Toast客製化");

        //new一個Toast
        Toast toast = Toast.makeText(this, text.getText(), Toast.LENGTH_LONG);

        //(Toast起始位置 , X座標篇移值 (正數靠右、負數靠左)  , Y座標篇移值 (正數靠下、負數靠上))
        toast = setGravityToast(toast);

        //如果不需要RadioButton控制Toast位置，只需固定位置，則刪除toast = setGravityToast(toast)，改用下面一行
        //toast.setGravity(Gravity.BOTTOM,0,0);

        //設定Toast外觀
        toast.setView(layout);
        //顯示Toast
        toast.show();
    }



}//ToastActivity
