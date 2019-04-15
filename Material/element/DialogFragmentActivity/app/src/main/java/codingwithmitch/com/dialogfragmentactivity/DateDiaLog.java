/*
* DateDialog功能
* */
package codingwithmitch.com.dialogfragmentactivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class DateDiaLog extends DialogFragment  implements DatePickerDialog.OnDateSetListener{
    //======================================================
    //給外部Active使用的Function(需實作)
    //======================================================
    public interface OnInputListener{
        void sendDate(int Year , int Month ,int Day);
    }

    //======================================================
    //宣告變數
    //======================================================
    //new一個OnInputListener介面，這樣就會依照Active override的方法去呼叫
    public OnInputListener mOnInputListener;
    //行事曆年、月、日
    private int mYear, mMonth, mDay;

    //======================================================
    //建構子
    //======================================================
    //無參數
    public DateDiaLog (){

    }

    //有參數(年，月，日)
    public DateDiaLog newInstance(int Year , int Month , int Day) {
        DateDiaLog f = new DateDiaLog();
        //設定bundle要從Activity接受的變數數量
        Bundle bundle = new Bundle(3);
        //接收年
        bundle.putInt("Year" , Year);
        //接收月
        bundle.putInt("Month" , Month);
        //接收日
        bundle.putInt("Day" , Day);

        f.setArguments(bundle);

        return f;
    }

    //======================================================
    //onCreateDialog
    //======================================================
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //取得年
        mYear = getArguments().getInt("Year");
        //取得月
        mMonth = getArguments().getInt("Month");
        //取得日
        mDay = getArguments().getInt("Day");


        // Create the AlertDialog object and return it(開啟DiaLog時的預設數字)
        return new DatePickerDialog(getActivity(), this, mYear, mMonth-1, mDay);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        //記錄現在的年月日
        mYear = year;
        mMonth = month + 1;
        mDay = day;

        //將Calendar的年月日轉換成String
        String format = getString(R.string.Set_date) + setDateFormat(mYear,mMonth,mDay);

        //設定行事曆日期(如果是要使用者輸入日期，可將Date_TextView改成EditTextView)
        //呼叫Active 實作的sendInput這個方法
        mOnInputListener.sendDate(mYear,mMonth,mDay);

        Toast.makeText(getActivity(), format, Toast.LENGTH_SHORT).show();
    }

    //================================
    //將Calendar的年月日轉換成String
    //================================
    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear) + "-"
                + String.valueOf(dayOfMonth);
    }


    //======================================================
    //當Fragment被加到某個Activity畫面中時，會自動呼叫此方法
    //======================================================
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e("DateDialog", "onAttach: ClassCastException: " + e.getMessage() );
        }
    }


}
