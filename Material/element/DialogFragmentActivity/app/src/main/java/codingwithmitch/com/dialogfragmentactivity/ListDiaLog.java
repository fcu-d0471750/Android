package codingwithmitch.com.dialogfragmentactivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;


public class ListDiaLog extends DialogFragment {


    //======================================================
    //宣告變數
    //======================================================
    //食物名稱
    ArrayList<String> Lunch = new ArrayList<String>();

    //======================================================
    //建構子
    //======================================================
    //無參數
    public ListDiaLog (){

    }

    //有參數
    public ListDiaLog newInstance(ArrayList<String> Lunch) {
        ListDiaLog f = new ListDiaLog();

        Bundle bundle = new Bundle(1);

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

        //取得要顯示的DiaLog編號
        Lunch = getArguments().getStringArrayList("Lunch");

        builder = ListDiaLog_Create(builder);

        // Create the AlertDialog object and return it
        return builder.create();
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
}
