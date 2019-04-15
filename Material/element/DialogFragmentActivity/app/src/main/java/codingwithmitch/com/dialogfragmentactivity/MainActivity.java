package codingwithmitch.com.dialogfragmentactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static javax.xml.datatype.DatatypeFactory.newInstance;

public class MainActivity extends AppCompatActivity implements MyCustomDialog.OnInputListener {

    //private static final String TAG = "MainActivity";


    //======================================================
    //宣告UI
    //======================================================
    //BasicDiaLog按扭
    public Button BasicDiaLog_Button;
    //ListDiaLog按扭
    public Button ListDiaLog_Button;
    //開啟自訂Dialog按鈕
    private Button mOpenDialog;
    //輸入的TextView
    public TextView mInputDisplay;


    //======================================================
    //宣告變數
    //======================================================

    //輸入字串
    public String mInput;

    //食物名稱
    ArrayList<String> Lunch = new ArrayList<String>();


    //======================================================
    //onCreate
    //======================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI初始化
        UI_Initial();

        Lunch_String_Initial();

        //Button功能初始化
        Button_Initial();

    }//onCreate


    //======================================================
    //UI初始化
    //======================================================
    void UI_Initial(){
        BasicDiaLog_Button = (Button) findViewById(R.id.Basic_dialog_Button);
        ListDiaLog_Button = (Button) findViewById(R.id.List_dialog_Button);
        mOpenDialog = (Button) findViewById(R.id.open_dialog);
        mInputDisplay =(TextView) findViewById(R.id.input_display);
    }


    //======================================================
    //Button功能初始化
    //======================================================
    void Button_Initial(){
        mOpenDialog_Function();
        BasicDialog_Function();
        ListDialog_Function();
    }

    //======================================================
    //ListDialog功能
    //======================================================
    void ListDialog_Function(){
        ListDiaLog_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個自製的DiaLog
                ListDiaLog dialog = new ListDiaLog().newInstance(Lunch);
                //顯示自製的DiaLog
                dialog.show(getFragmentManager(), "BasicDiaLog");

            }
        });//setOnClickListener
    }

    //======================================================
    //BasicDialog功能
    //======================================================
    void BasicDialog_Function(){
        BasicDiaLog_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個自製的DiaLog
                BasicDiaLog dialog = new BasicDiaLog();
                //顯示自製的DiaLog
                dialog.show(getFragmentManager(), "BasicDiaLog");

            }
        });//setOnClickListener
    }

    //======================================================
    //mOpenDialog功能
    //======================================================
    void mOpenDialog_Function(){
        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個自製的DiaLog
                MyCustomDialog dialog = new MyCustomDialog().newInstance(4);
                //顯示自製的DiaLog
                dialog.show(getFragmentManager(), "MyCustomDialog");

            }
        });//setOnClickListener
    }

    //======================================================
    //(override)副程式:將mInputDisplay設定成輸入的字串，覆寫MyCustomDialog的sendInput()
    //======================================================
    @Override
    public void sendInput(String input) {
        mInputDisplay.setText(input);
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

}//MainActivity















