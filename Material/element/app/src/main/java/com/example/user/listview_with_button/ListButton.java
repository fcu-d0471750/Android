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
    //ListView控件
    private ListView mList;
    //ListView数据源
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_button);

        data = new ArrayList<>();
        mList = (ListView)findViewById(R.id.mList);
        for(int i = 0; i < 20; i ++){
            data.add("今天好手气" + i);
        }
        MyAdapter adapter = new MyAdapter(data);
        mList.setAdapter(adapter);

        //ListView item点击事件
        mList.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListButton.this,"我是item点击事件 i = " + i + "l = " + l,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
