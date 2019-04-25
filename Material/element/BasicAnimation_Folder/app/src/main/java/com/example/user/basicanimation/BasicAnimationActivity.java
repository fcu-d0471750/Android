package com.example.user.basicanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BasicAnimationActivity extends AppCompatActivity {

    private int Start_X = 0;

    private TextView tv;
    private Button Start_Button;
    private Button Cancel_Button;

    private ValueAnimator repeatAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_animation);

        //UI初始化
        UI_Initial();
        //Button初始化
        Button_Initial();
    }


    //===========================================
    //UI初始化
    //===========================================
    void UI_Initial(){
        tv = (TextView) findViewById(R.id.tv);
        Start_Button = (Button) findViewById(R.id.Start_Button);
        Cancel_Button = (Button) findViewById(R.id.Cancel_Button);
    }

    //===========================================
    //Button初始化
    //===========================================
    void Button_Initial(){
        Start_Button_Function();
        Cancel_Button_Function();
    }

    //===========================================
    //Start_Button功能
    //===========================================
    void Start_Button_Function(){
        Start_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeatAnimator = doAnimation();
            }
        });
    }

    //===========================================
    //Cancel_Button功能
    //===========================================
    void Cancel_Button_Function(){
        Cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeatAnimator.cancel();
            }
        });
    }

    //===========================================
    //doAnimation
    //===========================================
    private ValueAnimator  doAnimation(){
        //設定位移量
        ValueAnimator animator = ValueAnimator.ofInt(0,400);
        //動畫時間
        animator.setDuration(1000);
        //重複模式(倒轉)
        animator.setRepeatMode(ValueAnimator.REVERSE);
        //重複次數(無限)
        animator.setRepeatCount(ValueAnimator.INFINITE);

        //監聽動畫變化時的實時值
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int)animation.getAnimatedValue();
                tv.layout(tv.getLeft(),curValue,tv.getRight(),curValue+tv.getHeight());
            }
        });

        //監聽動畫變化時四個狀態
        animator.addListener(new Animator.AnimatorListener() {
            //開始
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("qijian","animation start");
            }

            //結束
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("qijian","animation end");
            }

            //取消
            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d("qijian","animation cancel");
            }

            //重複
            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d("qijian","animation repeat");
            }
        });

        animator.start();
        return animator;
    }
}
