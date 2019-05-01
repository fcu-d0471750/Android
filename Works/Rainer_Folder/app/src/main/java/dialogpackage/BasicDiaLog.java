/**
 * BasicDiaLog功能
 *
 * 顯示資料來源、製作方的基本資料
 */

package dialogpackage;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.user.rainer.R;

import java.util.ArrayList;


public class BasicDiaLog extends DialogFragment {

    //======================================================
    //宣告變數
    //======================================================
    //Title
    private String Title;
    //Message
    private String Message;

    //======================================================
    //建構子
    //======================================================
    //無參數
    public BasicDiaLog (){

    }

    //有參數
    public BasicDiaLog newInstance(String Title , String Message) {
        BasicDiaLog f = new BasicDiaLog();

        Bundle bundle = new Bundle(2);

        //Title
        bundle.putString("Title" , Title);
        //Message
        bundle.putString("Message" , Message);

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

        //取得Title
        Title = getArguments().getString("Title");

        //取得Message
        Message = getArguments().getString("Message");

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
        builder.setTitle(Title)
                //(內容)
                .setMessage(Message)
                // (確定)
                .setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //開發者實作............................

                        //關閉DiaLog
                        getDialog().dismiss();
                    }
                });


        return builder;
    }
}
