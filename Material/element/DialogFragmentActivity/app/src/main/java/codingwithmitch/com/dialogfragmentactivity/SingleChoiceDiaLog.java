package codingwithmitch.com.dialogfragmentactivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


public class SingleChoiceDiaLog extends DialogFragment {


    //======================================================
    //給外部Active使用的Function(需實作)
    //======================================================
    public interface OnInputListener{
        void sendsingleChoiceIndex(int input);
    }

    //======================================================
    //宣告變數
    //======================================================
    //new一個OnInputListener介面，這樣就會依照Active override的方法去呼叫
    public OnInputListener mOnInputListener;

    //食物名稱
    ArrayList<String> Lunch = new ArrayList<String>();

    //單選式記錄編號
    private int singleChoiceIndex = 0;



    //======================================================
    //建構子
    //======================================================
    //無參數
    public SingleChoiceDiaLog (){

    }

    //有參數
    public SingleChoiceDiaLog newInstance(int singleChoiceIndex , ArrayList<String> Lunch) {
        SingleChoiceDiaLog f = new SingleChoiceDiaLog();

        //設定bundle要從Activity接受的變數數量
        Bundle bundle = new Bundle(2);
        //接收singleChoiceIndex
        bundle.putInt("singleChoiceIndex" , singleChoiceIndex);
        //接收Lunch
        bundle.putStringArrayList("Lunch" , Lunch);

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

        //取得要顯示的singleChoiceIndex編號
        singleChoiceIndex = getArguments().getInt("singleChoiceIndex");

        //取得要顯示的字串List
        Lunch = getArguments().getStringArrayList("Lunch");

        //設定DiaLog為SingleChoiceDiaLog
        builder = SingleChoiceDiaLog_Create(builder);

        // Create the AlertDialog object and return it
        return builder.create();
    }






    //======================================================
    //SingleChoiceDiaLog(單選式對話框)
    //======================================================
    AlertDialog.Builder SingleChoiceDiaLog_Create(AlertDialog.Builder builder ){
        //(標題)
        builder.setTitle(R.string.Title)//(字串List , 記錄的選項)
                .setSingleChoiceItems(Lunch.toArray(new String[Lunch.size()]), singleChoiceIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //開發者實作........................
                        //記錄選擇的項目編號
                        singleChoiceIndex = which;
                        //回傳給呼叫的Activity
                        mOnInputListener.sendsingleChoiceIndex(which);
                    }
                })

                // (確定)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //開發者實作............................
                        //取得選擇的項目
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
    //當Fragment被加到某個Activity畫面中時，會自動呼叫此方法
    //======================================================
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e("SingleChoiceDiaLog", "onAttach: ClassCastException: " + e.getMessage() );
        }
    }



}
