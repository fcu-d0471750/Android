/*
* MultiChoiceDialog功能
* */
package codingwithmitch.com.dialogfragmentactivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MultiChoiceDiaLog extends DialogFragment {

    //======================================================
    //給外部Active使用的Function(需實作)
    //======================================================
    public interface OnInputListener{
        void sendMultiChoiceIndex(boolean[] checkedboolArray);
    }

    //======================================================
    //宣告變數
    //======================================================
    //new一個OnInputListener介面，這樣就會依照Active override的方法去呼叫
    public MultiChoiceDiaLog.OnInputListener mOnInputListener;

    //食物名稱
    ArrayList<String> Lunch = new ArrayList<String>();

    //多選式記錄編號
    private List<Boolean> checkedStatusList = new ArrayList<>();

    //多選式項目記錄
    private boolean[] checkedboolArray;



    //======================================================
    //建構子
    //======================================================
    //無參數
    public MultiChoiceDiaLog (){

    }

    //有參數(原本的選擇項目 ， 字串List)
    public MultiChoiceDiaLog newInstance(boolean[] checkedboolArray , ArrayList<String> Lunch) {
        MultiChoiceDiaLog f = new MultiChoiceDiaLog();
        //設定bundle要從Activity接受的變數數量
        Bundle bundle = new Bundle(2);
        //接收checkedboolArray
        bundle.putBooleanArray("checkedboolArray" , checkedboolArray);
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

        //取得要顯示的字串List
        Lunch = getArguments().getStringArrayList("Lunch");

        //依照Lunch String的長度，設定checkedboolArray的數量
        checkedboolArray = new boolean[Lunch.size()];

        //取得要顯示的checkedboolArray編號
        checkedboolArray = getArguments().getBooleanArray("checkedboolArray");

        //依照接收的checkedboolArray設定以選擇的項目
        checkedStatusList_Initial();

        //設定DiaLog為 MultiChoiceDiaLog
        builder = MultiChoiceDiaLog_Create(builder);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    //======================================================
    //multiChoiceDiaLog(多選式對話框)
    //======================================================
    AlertDialog.Builder MultiChoiceDiaLog_Create(AlertDialog.Builder builder ){
        //(標題)
        builder.setTitle(R.string.Title)//(字串List，記錄的選項)
                .setMultiChoiceItems(Lunch.toArray(new String[Lunch.size()]), checkedboolArray, new DialogInterface.OnMultiChoiceClickListener() {
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
                        if(Isempty == false) Toast.makeText(getActivity(), "你選擇的是" + sb.toString(), Toast.LENGTH_SHORT).show();
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

    //================================
    //checkedStatusList設定
    //================================
    void checkedStatusList_Initial(){
        //依照原本使用者選擇的項目進行設定
        for(int i=0; i<Lunch.size(); i++){
            if(checkedboolArray[i]==true) {
                checkedStatusList.add(true);
                checkedboolArray[i] = true;
            }
            else{
                checkedStatusList.add(false);
                checkedboolArray[i] = false;
            }
        }//for
    }

}
