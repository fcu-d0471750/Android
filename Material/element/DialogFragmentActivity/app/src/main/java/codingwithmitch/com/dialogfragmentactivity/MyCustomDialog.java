/*
* 自製DialogFragment功能
* */
package codingwithmitch.com.dialogfragmentactivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyCustomDialog extends DialogFragment implements
        DatePickerDialog.OnDateSetListener{

    //private static final String TAG = "MyCustomDialog";

    //======================================================
    //宣告UI
    //======================================================
    //輸入EditText
    private EditText mInput;
    //確認、取消按扭
    private Button mActionOk, mActionCancel;

    @Override//(year month day為使用者選擇的數字)
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        //獲得現在的年月日
        Calendar c = Calendar.getInstance();
        //記錄現在的年月日
        mYear = year;
        mMonth =month;
        mDay = day;

                //將Calendar的年月日轉換成String
                String format = getString(R.string.Set_date) + setDateFormat(mYear,mMonth,mDay);

                //設定行事曆日期(如果是要使用者輸入日期，可將Date_TextView改成EditTextView)
                //呼叫Active 實作的sendInput這個方法
                mOnInputListener.sendInput(format);
                Toast.makeText(getActivity(), "BasicAAAA!", Toast.LENGTH_SHORT).show();

    }


    //======================================================
    //宣告介面
    //======================================================
    //======================================================
    //給外部Active使用的Function(需實作)
    //======================================================
    public interface OnInputListener{
        void sendInput(String input);
    }

    //======================================================
    //宣告變數
    //======================================================
    //new一個OnInputListener介面，這樣就會依照Active override的方法去呼叫
    public OnInputListener mOnInputListener;

    //食物名稱
    ArrayList<String> Lunch = new ArrayList<String>();

    //呼叫的DiaLog
    private int number = 0;

    //單選式記錄編號
    private int singleChoiceIndex = 0;

    //多選式項目記錄
    private boolean[] checkedboolArray;


    //多選式記錄編號
    List<Boolean> checkedStatusList = new ArrayList<>();

    //行事曆年、月、日
    private int mYear, mMonth, mDay;
    //======================================================
    //建構子
    //======================================================
    //有參數
    public MyCustomDialog (){
        number = 0;
    }

    //有參數
    public MyCustomDialog newInstance(int i) {
        MyCustomDialog f = new MyCustomDialog();

        Bundle bundle = new Bundle(1);

        bundle.putInt("number" , i);

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

        //取得要顯示的DiaLog編號
        number = getArguments().getInt("number");

        //Lunch String初始化
        Lunch_String_Initial();

        checkedStatusList_Initial();

        switch(number) {
            //BasicDiaLog
            case 0:
                builder = BasicDiaLog_Create(builder);
                break;

            //ListDiaLog
            case 1:
                builder = ListDiaLog_Create(builder);
                break;

            //SingleChoiceDiaLog
            case 2:
                builder = SingleChoiceDiaLog_Create(builder);
                break;

            //MultiChoiceDiaLog
            case 3:
                builder = MultiChoiceDiaLog_Create(builder);
                break;

            //DateDiaLog(開啟DiaLog時的預設數字)
            case 4:
                return new DatePickerDialog(getActivity(), this, 1995, 5, 5);


            //UserDiaLog
            case 5:
                builder = UserDiaLog_Create(builder);
                break;
        }
        // Create the AlertDialog object and return it
        return builder.create();
    }


    //======================================================
    //BasicDiaLog(一般對話框)
    //======================================================
    AlertDialog.Builder BasicDiaLog_Create(AlertDialog.Builder builder){
        //(標題)
        builder.setTitle(R.string.Title)
                //(內容)
                .setMessage(R.string.Message)
                // (確定)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //開發者實作............................
                        Toast.makeText(getActivity(), "Basic確認!", Toast.LENGTH_SHORT).show();
                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                })

                //(中立)
                .setNeutralButton(R.string.Wait, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //開發者實作............................
                        Toast.makeText(getActivity(), "Basic考慮!", Toast.LENGTH_SHORT).show();
                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                })

                //(拒絕)
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //開發者實作............................
                        Toast.makeText(getActivity(), "Basic取消!", Toast.LENGTH_SHORT).show();
                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                });

        return builder;
    }

    //======================================================
    //ListDiaLog(條列式對話框)
    //======================================================
    AlertDialog.Builder ListDiaLog_Create(AlertDialog.Builder builder){
        //(標題)
        builder.setTitle(R.string.Title)
                .setItems(Lunch.toArray(new String[Lunch.size()]), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //開發者實作............................
                        String name = Lunch.get(which);
                        Toast.makeText(getActivity(), getString(R.string.Eat) + name, Toast.LENGTH_SHORT).show();
                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                })

                //(拒絕)
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //開發者實作............................
                        Toast.makeText(getActivity(), "Basic取消!", Toast.LENGTH_SHORT).show();
                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                });

        return builder;
    }

    //======================================================
    //SingleChoiceDiaLog(單選式對話框)
    //======================================================
    AlertDialog.Builder SingleChoiceDiaLog_Create(AlertDialog.Builder builder ){
        //(標題)
        builder.setTitle(R.string.Title)
                .setSingleChoiceItems(Lunch.toArray(new String[Lunch.size()]), singleChoiceIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //開發者實作........................
                        //記錄選擇的項目編號
                        singleChoiceIndex = which;
                    }
                })

                // (確定)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //開發者實作............................
                        String name = Lunch.get(singleChoiceIndex);
                        Toast.makeText(getActivity(), getString(R.string.Eat) + name, Toast.LENGTH_SHORT).show();
                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                })

                //(拒絕)
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //開發者實作............................
                        Toast.makeText(getActivity(), "Basic取消!", Toast.LENGTH_SHORT).show();
                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                });

        return builder;
    }


    //======================================================
    //multiChoiceDiaLog(多選式對話框)
    //======================================================
    AlertDialog.Builder MultiChoiceDiaLog_Create(AlertDialog.Builder builder ){
        //(標題)
        builder.setTitle(R.string.Title)
                .setMultiChoiceItems(Lunch.toArray(new String[Lunch.size()]), null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        //開發者實作..................
                        //改變勾選或不勾選的畫面
                        checkedStatusList.set(which, isChecked);
                        //記錄選擇的項目
                        checkedboolArray[which] = isChecked;
                    }
                })

                // (確定)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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
                        if(Isempty == false)Toast.makeText(getActivity(), "你選擇的是" + sb.toString(), Toast.LENGTH_SHORT).show();
                            //沒有選擇項目
                        else Toast.makeText(getActivity(), "請選擇項目" , Toast.LENGTH_SHORT).show();
                    }
                })

                //(拒絕)
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //開發者實作............................
                        Toast.makeText(getActivity(), "Basic取消!", Toast.LENGTH_SHORT).show();
                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                });

        return builder;
    }

    //======================================================
    ///DateDiaLog(行事曆對話框)
    //======================================================
     void DateDiaLog_Create(){
        //獲得現在的年月日
        Calendar c = Calendar.getInstance();
        //記錄現在的年月日
         mYear = c.get(Calendar.YEAR);
         mMonth = c.get(Calendar.MONTH);
         mDay = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                //將Calendar的年月日轉換成String
                String format = getString(R.string.Set_date) + setDateFormat(year,month,day);
                //記錄使用者輸入的年月日
                mYear = year;
                mMonth = month;
                mDay = day;
                //設定行事曆日期(如果是要使用者輸入日期，可將Date_TextView改成EditTextView)
                //呼叫Active 實作的sendInput這個方法
                //mOnInputListener.sendInput(format);
                Toast.makeText(getActivity(), "BasicAAAA!", Toast.LENGTH_SHORT).show();

            }
            //下方mYear,mMonth, mDay為使用者開啟行事曆預設的日期
        }, mYear,mMonth, mDay).show();


    }

    //================================
    //將Calendar的年月日轉換成String
    //================================
    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }


    //======================================================
    //UserDiaLog(客製化)
    //======================================================
    AlertDialog.Builder UserDiaLog_Create(AlertDialog.Builder builder){

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_my_custom, null);

        //View_UI初始化
        UI_Initial(view);

        //Button功能初始化
        Button_Initial();

        builder.setView(view);

        return builder;
    }

   /* //======================================================
    //onCreateView
    //======================================================
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //new一個View，設定成自製的LayOut
        View view = inflater.inflate(R.layout.dialog_my_custom, container, false);

        //View_UI初始化
        UI_Initial(view);

        //Button功能初始化
        Button_Initial();

        return view;
    }
*/
    //======================================================
    //View_UI初始化
    //======================================================
    void UI_Initial(View view){
        mActionCancel =  view.findViewById(R.id.action_cancel);
        mActionOk =  view.findViewById(R.id.action_ok);
        mInput = view.findViewById(R.id.input);
    }


    //======================================================
    //Button功能初始化
    //======================================================
    void Button_Initial(){
        mActionOk_Function();
        mActionCancel_Function();
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
    //checkedStatusList初始化
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
    //======================================================
    //mActionOk功能
    //======================================================
    void mActionOk_Function(){
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //將輸入的Text轉成字串
                String input = mInput.getText().toString();

                //呼叫Active 實作的sendInput這個方法
                mOnInputListener.sendInput(input);

                //關閉DiaLog
                getDialog().dismiss();
            }
        });
    }

    //======================================================
    //mActionCancel功能
    //======================================================
    void mActionCancel_Function(){
        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //關閉DiaLog
                getDialog().dismiss();
            }
        });
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
            Log.e("MyCustomDialog", "onAttach: ClassCastException: " + e.getMessage() );
        }
    }

}//MyCustomDialog



