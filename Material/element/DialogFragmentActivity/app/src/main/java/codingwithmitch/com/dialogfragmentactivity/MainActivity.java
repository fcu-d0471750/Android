/*
* 主要執行區域
*不同按扭會呼叫不同的DialogFragment執行，可依照需要載入需要的DialogFragment並自訂。
* */

package codingwithmitch.com.dialogfragmentactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static javax.xml.datatype.DatatypeFactory.newInstance;

public class MainActivity extends AppCompatActivity implements MyCustomDialog.OnInputListener , SingleChoiceDiaLog.OnInputListener , MultiChoiceDiaLog.OnInputListener , DateDiaLog.OnInputListener{

    //private static final String TAG = "MainActivity";


    //======================================================
    //宣告UI
    //======================================================
    //BasicDiaLog按扭(基本)
    public Button BasicDiaLog_Button;
    //ListDiaLog按扭(條列式)
    public Button ListDiaLog_Button;
    //SingleDiaLog按扭(單選式)
    public Button SingleDiaLog_Button;
    //MultiDiaLog按扭(多選式)
    public Button MultiDiaLog_Button;
    //DateDiaLog按扭(行事曆)
    public Button DateDiaLog_Button;
    //開啟自訂Dialog按鈕(客製化)
    private Button UserDialog_Button;
    //輸入的TextView
    public TextView mInputDisplay;


    //======================================================
    //宣告變數
    //======================================================

    //輸入字串
    public String mInput;

    //食物名稱
    ArrayList<String> Lunch = new ArrayList<String>();

    //單選式記錄編號
    private int singleChoiceIndex = 0;

    //多選式項目記錄
    private boolean[] checkedboolArray;

    //行事曆年、月、日
    private int mYear = 1990, mMonth = 1, mDay = 1;

    //======================================================
    //onCreate
    //======================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI初始化
        UI_Initial();
        //Lunch String初始化
        Lunch_String_Initial();
        //checkedStatusList初始化
        checkedStatusList_Initial();

        //Button功能初始化
        Button_Initial();

    }//onCreate


    //======================================================
    //UI初始化
    //======================================================
    void UI_Initial(){
        //BasicDialog功能(基本)
        BasicDiaLog_Button = (Button) findViewById(R.id.Basic_dialog_Button);
        //ListDialog功能(條列式)
        ListDiaLog_Button = (Button) findViewById(R.id.List_dialog_Button);
        //SingleDialog功能(單選式)
        SingleDiaLog_Button = (Button) findViewById(R.id.Single_dialog_Button);
        //MultiDialog功能(多選式)
        MultiDiaLog_Button = (Button) findViewById(R.id.Multi_dialog_Button);
        //DateDialog功能(行事曆)
        DateDiaLog_Button = (Button) findViewById(R.id.Date_dialog_Button);
        //UserDialog功能(客製化)
        UserDialog_Button = (Button) findViewById(R.id.UserDialog_Button);
        //TextView
        mInputDisplay =(TextView) findViewById(R.id.input_display);
    }


    //======================================================
    //Button功能初始化
    //======================================================
    void Button_Initial(){
        //BasicDialog功能
        BasicDialog_Function();
        //ListDialog功能
        ListDialog_Function();
        //SingleDialog功能
        SingleDialog_Function();
        //MultiDialog功能
        MultiDialog_Function();
        //DateDialog功能
        DateDialog_Function();
        //UserDialog功能(客製化)
        mOpenDialog_Function();
    }



    //======================================================
    //BasicDialog功能
    //======================================================
    void BasicDialog_Function(){
        BasicDiaLog_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個BasicDiaLog
                BasicDiaLog dialog = new BasicDiaLog();
                //顯示自製的DiaLog
                dialog.show(getFragmentManager(), "BasicDiaLog");

            }
        });//setOnClickListener
    }

    //======================================================
    //ListDialog功能
    //======================================================
    void ListDialog_Function(){
        ListDiaLog_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個ListDiaLog
                ListDiaLog dialog = new ListDiaLog().newInstance(Lunch);
                //顯示自製的DiaLog
                dialog.show(getFragmentManager(), "ListDiaLog");

            }
        });//setOnClickListener
    }

    //======================================================
    //SingleDialog功能
    //======================================================
    void SingleDialog_Function(){
        SingleDiaLog_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個SingleChoiceDiaLog
                SingleChoiceDiaLog dialog = new SingleChoiceDiaLog().newInstance(singleChoiceIndex , Lunch);
                //顯示自製的DiaLog
                dialog.show(getFragmentManager(), "SingleDiaLog");

            }
        });//setOnClickListener
    }

    //======================================================
    //MultiDialog功能
    //======================================================
    void MultiDialog_Function(){
        MultiDiaLog_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個MultiChoiceDiaLog
                MultiChoiceDiaLog dialog = new MultiChoiceDiaLog().newInstance(checkedboolArray , Lunch);
                //顯示自製的DiaLog
                dialog.show(getFragmentManager(), "MultiDiaLog");

            }
        });//setOnClickListener
    }

    //======================================================
    //DateDialog功能
    //======================================================
    void DateDialog_Function(){
        DateDiaLog_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個自製的DiaLog
                DateDiaLog dialog = new DateDiaLog().newInstance(mYear , mMonth , mDay);
                //顯示自製的DiaLog
                dialog.show(getFragmentManager(), "DateDiaLog");

            }
        });//setOnClickListener
    }


    //======================================================
    //UserDialog功能
    //======================================================
    void mOpenDialog_Function(){
        UserDialog_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new一個自製的DiaLog
                MyCustomDialog dialog = new MyCustomDialog();
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

    //======================================================
    //(override)副程式:依照使用者選擇的項目，覆寫SingleChoiceDiaLog的sendsingleChoiceIndex()
    //======================================================
    @Override
    public void sendsingleChoiceIndex(int input) {
        singleChoiceIndex = input;
    }

    //======================================================
    //(override)副程式:依照使用者選擇的項目，覆寫MultiChoiceDiaLog的sendMultiChoiceIndex()
    //======================================================
    @Override
    public void sendMultiChoiceIndex(boolean[] checkedboolArray) {
        this.checkedboolArray = checkedboolArray;
    }

    //======================================================
    //(override)副程式:依照使用者選擇的項目，覆寫DateDiaLog的sendDate()
    //======================================================
    @Override
    public void sendDate(int Year, int Month, int Day) {
        mYear = Year;
        mMonth = Month;
        mDay = Day;
        mInputDisplay.setText(mYear + "-" + mMonth + "-" + mDay);
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
            checkedboolArray[i] = false;
        }
    }



}//MainActivity















