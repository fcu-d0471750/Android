/*
* 自製DialogFragment功能
* */
package codingwithmitch.com.dialogfragmentactivity;

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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomDialog extends DialogFragment {

    //private static final String TAG = "MyCustomDialog";

    //======================================================
    //宣告UI
    //======================================================
    //輸入EditText
    private EditText mInput;
    //確認、取消按扭
    private Button mActionOk, mActionCancel;


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

        //BasicDiaLog
        if(number==0)builder = BasicDiaLog_Create(builder);

        //UserDiaLog
        else if(number==1)builder = UserDiaLog_Create(builder);

        // Create the AlertDialog object and return it
        return builder.create();
    }


    //======================================================
    //BasicDiaLog
    //======================================================
    AlertDialog.Builder BasicDiaLog_Create(AlertDialog.Builder builder){
        builder.setMessage(R.string.Title)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        return builder;
    }

    //======================================================
    //UserDiaLog
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



