/*
*ListView畫面
* */
package com.example.user.listview_with_button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListButton extends AppCompatActivity {
    //ListView元件
    private ListView mList;
    //ListView資料
    private List<String> data;

    //=========================================
    //onCreate
    //=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_button);

        data = new ArrayList<>();
        //綁定ListView
        mList = (ListView)findViewById(R.id.mList);

        //加入資料，傳給MyAdapter處理
        for(int i = 0; i < 20; i ++){
            data.add("今天好手氣" + i);
        }

        //使用自訂的MyAdapter
        MyAdapter adapter = new MyAdapter(data);
        //設定使用的MyAdapter
        mList.setAdapter(adapter);

        //ListView item點擊事件
        mList.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListButton.this,"我是item點擊事件 i = " + i + "l = " + l,Toast.LENGTH_SHORT).show();
            }
        });

    }

}//ListButton
