/*
* 自訂Adapter
* */
package com.example.user.basicrecycleview;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{
    //轉換器
    private LayoutInflater mInflater;
    //資料
    private List<news> mDatas;

    //=======================================================================
    //建構子
    //=======================================================================
    public GalleryAdapter(Context context, List<news> datats)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    //=======================================================================
    //ViewHolder，這邊的ViewHolder同樣是繼承RecyclerView裡的ViewHolder class，需要留意的是，這個子類constructor必需呼叫super constructor。
    // 接下來，你可以在ViewHolder內定義每個list cell上會有的view components，並在待會的onCreateViewHolder中初始化這些view components。
    //=======================================================================
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt;
    }

    //=======================================================================
    //回傳清單數量
    //=======================================================================
    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    //=======================================================================
    //綁定自訂的UI元件
    //在 onCreateViewHolder 中指定 item 所使用的 view 佈局，並將該 view 轉換成 MyAdapter.ViewHolder 之物件回傳
    // 如此一來，我們就可以使用在前面自訂的 ViewHolder 類別中，所定義的每個 item 的介面。
    //=======================================================================
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        //綁定View
        View view = mInflater.inflate(R.layout.news_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        //綁定ImageView
        viewHolder.mImg = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
        //綁定TextView
        viewHolder.mTxt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);

        return viewHolder;
    }

    //=======================================================================
    //設定自訂的UI的功能(所有的View元件，位置)
    //=======================================================================
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
        //設定圖片
        viewHolder.mImg.setImageResource(mDatas.get(i).getPic());

        //設定文字
        viewHolder.mTxt.setText(mDatas.get(i).getTitle());

        //點擊
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mInflater.getContext(), "Item "  + i +  " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        //長按
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mInflater.getContext(), "Item "  + i +  " is long clicked.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

}//GalleryAdapter