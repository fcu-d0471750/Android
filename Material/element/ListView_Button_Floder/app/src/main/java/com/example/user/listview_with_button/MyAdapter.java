/*
* ListView自訂Adapter
* */
package com.example.user.listview_with_button;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MyAdapter extends BaseAdapter implements View.OnClickListener {
    //======================================
    //宣告變數
    //======================================
    //來自哪個Activity
    private Context context;
    //用於儲存context傳來的字串
    private List<String> data;

    //儲存從context傳來的字串
    public MyAdapter(List<String> data){
        this.data = data;
    }
    //data的資料數量
    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }
    //取得data的資料
    @Override
    public Object getItem(int i) {
        return data.get(i);
    }
    //取得data的編號
    @Override
    public long getItemId(int i) {
        return i;
    }

    //======================================
    //設定ListView(ListView 針對List中每個item，要求 adapter 給一個view，系統會自動將所有傳入的資料都設定ListView，只需處理傳入的資料市要放在哪個元件上)
    //======================================
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //宣告ViewHolder類別(未使用)
        ViewHolder viewHolder = null;

        //如果context還沒得到從Activity傳來的資料，則去抓取資料
        if(context == null)
            context = viewGroup.getContext();

        //如果還未設定List的元件，則去設定
        if(view == null){
            //依照listview_user設定的外觀，設定view的外觀
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_user,null);
            //建立ViewHolder類別(開始使用)
            viewHolder = new ViewHolder();
            //綁定TextView
            viewHolder.mTv = (TextView)view.findViewById(R.id.mTv);
            //綁定Button
            viewHolder.mBtn = (Button)view.findViewById(R.id.mBtn);
            //設定View外觀
            view.setTag(viewHolder);
        }

        //取得所有資料id，也就是編號
        viewHolder = (ViewHolder)view.getTag();

        //設置List元件
        viewHolder.mBtn.setTag(R.id.btn,i);
        viewHolder.mBtn.setText("點我"+ i);
        viewHolder.mBtn.setOnClickListener(this);
        viewHolder.mTv.setText(data.get(i));

        //設置tag編號
        viewHolder.mTv.setTag(R.id.tv,i);
        viewHolder.mTv.setOnClickListener(this);

        return view;
    }

    //======================================
    //按下ListView或Button的事件
    //======================================
    @Override
    public void onClick(View view) {
        //依照Id來判斷是ListView或Button
        switch (view.getId()){
            case R.id.mBtn:
                int b = (int) view.getTag(R.id.btn);
                Toast.makeText(context,"我是按紐 " + b,Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTv:
                int t = (int)view.getTag(R.id.tv);
                Toast.makeText(context,"我是文本" + t,Toast.LENGTH_SHORT).show();
                break;
        }//switch
    }

    //======================================
    //每個List擁有的元件(一個TextView、一個Button)
    //======================================
    static class ViewHolder{
        TextView mTv;
        Button mBtn;
    }

}//MyAdapter

