package codingwithmitch.com.dialogfragmentactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyCustomDialog.OnInputListener {

    //private static final String TAG = "MainActivity";


    //======================================================
    //宣告UI
    //======================================================
    //開啟Dialog按鈕
    private Button mOpenDialog;
    //輸入的TextView
    public TextView mInputDisplay;


    //======================================================
    //宣告變數
    //======================================================

    //輸入字串
    public String mInput;


    //======================================================
    //onCreate
    //======================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI初始化
        UI_Initial();
        //Button功能初始化
        Button_Initial();

    }//onCreate


    //======================================================
    //UI初始化
    //======================================================
    void UI_Initial(){
        mOpenDialog = (Button) findViewById(R.id.open_dialog);
        mInputDisplay =(TextView) findViewById(R.id.input_display);
    }


    //======================================================
    //Button功能初始化
    //======================================================
    void Button_Initial(){
        mOpenDialog_Function();
    }

    //======================================================
    //mOpenDialog功能
    //======================================================
    void mOpenDialog_Function(){
        mOpenDialog.setOnClickListener(new View.OnClickListener() {
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

}//MainActivity















