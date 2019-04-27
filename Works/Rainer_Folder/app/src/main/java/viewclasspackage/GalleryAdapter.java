package viewclasspackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.rainer.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    //轉換器
    private LayoutInflater mInflater;
    //資料
    private ArrayList<Rainer_item_Class> mDatas;

    //=======================================================================
    //建構子
    //=======================================================================
    public GalleryAdapter(Context context, ArrayList<Rainer_item_Class> datats)
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
        TextView mlocation_name;
        TextView mpop_value;
        TextView mmax_t;
        TextView mmin_t;
        TextView mci;
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
        View view = mInflater.inflate(R.layout.rainer_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        //綁定ImageView
        viewHolder.mImg = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
        //綁定TextView
        viewHolder.mlocation_name = (TextView) view.findViewById(R.id.location_name_textview);
        viewHolder.mpop_value = (TextView) view.findViewById(R.id.pop_value_textview);
        viewHolder.mmax_t = (TextView) view.findViewById(R.id.maxt_textview);
        viewHolder.mmin_t = (TextView) view.findViewById(R.id.mint_textview);
        viewHolder.mci = (TextView) view.findViewById(R.id.ci_textview);

        return viewHolder;
    }

    //=======================================================================
    //設定自訂的UI的功能(所有的View元件，位置)
    //=======================================================================
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {

        //設定圖片
        viewHolder.mImg.setImageResource(R.drawable.bread_01);

        //設定文字
        viewHolder.mlocation_name.setText(mDatas.get(i).getLocation_name());
        viewHolder.mpop_value.setText(""+mDatas.get(i).getPop_value());
        viewHolder.mmax_t.setText(""+mDatas.get(i).getMax_t());
        viewHolder.mmin_t.setText(""+mDatas.get(i).getMin_t());
        viewHolder.mci.setText(mDatas.get(i).getCi());

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

    //======================================================================================
    //刷新RecycleView
    //======================================================================================
    public void ViewUpdate(ArrayList<Rainer_item_Class> list ){
        mDatas = list;
        notifyDataSetChanged();
    }


}//GalleryAdapter