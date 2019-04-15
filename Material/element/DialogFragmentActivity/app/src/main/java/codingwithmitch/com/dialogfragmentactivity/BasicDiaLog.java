/*
*BasicDiaLog功能
* */
package codingwithmitch.com.dialogfragmentactivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class BasicDiaLog extends DialogFragment {


    //======================================================
    //建構子
    //======================================================
    //無參數
    public BasicDiaLog (){

    }

    //======================================================
    //onCreateDialog
    //======================================================
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //設定DiaLog為BasicDiaLog
        builder = BasicDiaLog_Create(builder);

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

}
