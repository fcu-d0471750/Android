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
    private Context context;
    private List<String> data;
    public MyAdapter(List<String> data){
        this.data = data;
    }
    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }
    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(context == null)
            context = viewGroup.getContext();
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_user,null);
            viewHolder = new ViewHolder();
            viewHolder.mTv = (TextView)view.findViewById(R.id.mTv);
            viewHolder.mBtn = (Button)view.findViewById(R.id.mBtn);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)view.getTag();
        //设置tag标记
        viewHolder.mBtn.setTag(R.id.btn,i);//添加此代码
        viewHolder.mBtn.setText("点我点我"+ i);
        viewHolder.mBtn.setOnClickListener(this);
        viewHolder.mTv.setText(data.get(i));
        //设置tag标记
        viewHolder.mTv.setTag(R.id.tv,i);//添加此代码
        viewHolder.mTv.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mBtn:
                int b = (int) view.getTag(R.id.btn);
                Toast.makeText(context,"我是按钮 " + b,Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTv:
                int t = (int)view.getTag(R.id.tv);
                Toast.makeText(context,"我是文本" + t,Toast.LENGTH_SHORT).show();
                break;
        }
    }
    static class ViewHolder{
        TextView mTv;
        Button mBtn;
    }
}

