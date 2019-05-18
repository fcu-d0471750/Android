/*
* 基本動畫
*
*
* Property Animation故名思議就是通過動畫的方式改變對象的屬性了，我們首先需要了解幾個屬性：
* Duration動畫的持續時間，默認300ms。
* Time interpolation：時間差值，乍一看不知道是什麼，但是我說LinearInterpolator、AccelerateDecelerateInterpolator，大家一定知道是乾嘛的了，定義動畫的變化率。
* Repeat count and behavior：重複次數、以及重複模式；可以定義重複多少次；重複時從頭開始，還是反向。
* Animator sets: 動畫集合，你可以定義一組動畫，一起執行或者順序執行。
* Frame refresh delay：幀刷新延遲，對於你的動畫，多久刷新一次幀；
* 默認為10ms，但最終依賴系統的當前狀態；基本不用管。
*
*
*ObjectAnimator 動畫的執行類
* ValueAnimator 動畫的執行類
* AnimatorSet 用於控制一組動畫的執行：線性，一起，每個動畫的先後執行等。
* AnimatorInflater 用戶加載屬性動畫的xml文件
* TypeEvaluator 類型估值，主要用於設置動畫操作屬性的值。
*
*
*ValueAnimator
*
* ValueAnimator是整個屬性動畫機制當中最核心的一個類，屬性動畫的運行機制是通過不斷地對值進行操作來實現的，而初始值和結束值之間的動畫過渡就是由ValueAnimator這個類來負責計算的。
* 它的內部使用一種時間循環的機制來計算值與值之間的動畫過渡，我們只需要將初始值和結束值提供給ValueAnimator，並且告訴它動畫所需運行的時長，那麼ValueAnimator就會自動幫我們完成從初始值平滑地過渡到結束值這樣的效果。
* 除此之外，ValueAnimator還負責管理動畫的播放次數、播放模式、以及對動畫設置監聽器等，確實是一個非常重要的類。
*
*
* ObjectAnimator
*
*相比於ValueAnimator，ObjectAnimator可能才是我們最常接觸到的類，因為ValueAnimator只不過是對值進行了一個平滑的動畫過渡，但我們實際使用到這種功能的場景好像並不多。
* 而ObjectAnimator則就不同了，它是可以直接對任意對象的任意屬性進行動畫操作的，比如說View的alpha屬性。
* 不過雖說ObjectAnimator會更加常用一些，但是它其實是繼承自ValueAnimator的，底層的動畫實現機制也是基於ValueAnimator來完成的，因此ValueAnimator仍然是整個屬性動畫當中最核心的一個類。
* 那麼既然是繼承關係，說明ValueAnimator中可以使用的方法在ObjectAnimator中也是可以正常使用的
* */
package com.example.user.basicanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class BasicAnimationActivity extends AppCompatActivity {

    private int Start_X = 0;

    private TextView tv;
    private Button Start_Button;
    private Button Cancel_Button;

    //ValueAnimator
    private ValueAnimator valueAnimator;
    //ObjectAnimator
    private ObjectAnimator objectAnimator;

    //組合動畫
    private AnimatorSet animatorSet;

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
                //ValueAnimator
                //valueAnimator = DoValueAnimation();

                //ObjectAnimator
                //objectAnimator = DoObjectAnimation();

                //AnimSet
                animatorSet = DotAnimationSet();
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
                //ValueAnimator
                //valueAnimator.cancel();

                //ObjectAnimator
                //objectAnimator.cancel();

                //AnimSet
                animatorSet.cancel();
            }
        });
    }



    //===========================================
    //ObjectAnimator(建議使用)
    //===========================================
    private ObjectAnimator  DoObjectAnimation(){
        //改變透明度
        ObjectAnimator obAnimator = ObjectAnimator.ofFloat(tv, "alpha", 0f, 1f);

        //旋轉
        //ObjectAnimator obAnimator = ObjectAnimator.ofFloat(tv, "rotation", 0f, 360f);

        //向左移動(水平)
        //float x = tv.getTranslationX();
        //ObjectAnimator obAnimator = ObjectAnimator.ofFloat(tv, "translationX", x, -500f);

        //向右移動(水平)
        //float x = tv.getTranslationX();
        //ObjectAnimator obAnimator = ObjectAnimator.ofFloat(tv, "translationX", x, 500f);

        //縮放(水平)
        //ObjectAnimator animator = ObjectAnimator.ofFloat(tv, "scaleX", 1f, 3f, 1f);

        //動畫時間
        obAnimator.setDuration(5000);
        //重複次數(無限)
        obAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        //重複模式(倒轉)
        obAnimator.setRepeatMode(ObjectAnimator.REVERSE);

        //播放動畫
        obAnimator.start();

        //監聽動畫變化時的實時值
        obAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("ObjectAnimator","animation Update");
            }
        });

        //監聽動畫變化時四個狀態(四個狀態不一定要Override)
        obAnimator.addListener(new AnimatorListenerAdapter() {
            //開始
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("ObjectAnimator","animation start");
            }

            //結束
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("ObjectAnimator","animation end");
            }

            //取消
            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d("ObjectAnimator","animation cancel");
            }

            //重複
            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d("ObjectAnimator","animation repeat");
            }

        });

        return obAnimator;
    }

    //===========================================
    //DoValueAnimation
    //===========================================
    private ValueAnimator  DoValueAnimation(){
        //設定位移量
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);
        //動畫時間
        valueAnimator.setDuration(1000);
        //重複模式(倒轉)
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //重複次數(無限)
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        //播放動畫
        valueAnimator.start();

        //監聽動畫變化時的實時值(四個狀態不一定要Override)
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int)animation.getAnimatedValue();
                tv.layout(tv.getLeft(),curValue,tv.getRight(),curValue+tv.getHeight());
            }
        });

        //監聽動畫變化時四個狀態
        valueAnimator.addListener(new Animator.AnimatorListener() {
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


        return valueAnimator;
    }


    //===========================================
    /*組合動畫
            after(Animator anim) 將現有動畫插入到傳入的動畫之後執行
            after(long delay) 將現有動畫延遲指定毫秒後執行
            before(Animator anim) 將現有動畫插入到傳入的動畫之前執行
            with(Animator anim) 將現有動畫和傳入的動畫同時執行
        */
    //===========================================
    private AnimatorSet  DotAnimationSet(){
        //向右移動(水平)
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(tv, "translationX", -700f, 0f);
        //旋轉
        ObjectAnimator retate = ObjectAnimator.ofFloat(tv, "rotation", 0f, 360f);
        //淡出
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(tv, "alpha", 1f, 0f, 1f);

        //設定AnimatorSet
        AnimatorSet animSet = new AnimatorSet();
        //設定不同動畫的播放順序
        animSet.play(retate).with(fadeInOut).after(moveIn);
        //播放時間，是指每個動畫的播放時間
        animSet.setDuration(5000);
        //播放動畫
        animSet.start();

        /*
                //如果要監聽動畫，則要將每個動畫分別寫Listener，像是上面的例子一樣
                */

        return animSet;
    }

}//BasicAnimationActivity
