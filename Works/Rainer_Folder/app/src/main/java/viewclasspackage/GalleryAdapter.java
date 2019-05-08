package viewclasspackage;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
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
    //外部的Activity
    //private Context context;

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

        //天氣圖片
        ImageView mImg;
        //地名
        TextView mlocation_name;
        //降雨機率
        TextView mpop_value;
        //最高溫度標題
        TextView mmax_t_tilte;
        //最高溫度
        TextView mmax_t;
        //最低溫度標題
        TextView mmin_t_tilte;
        //最低溫度
        TextView mmin_t;
        //舒適度
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
        //地名
        viewHolder.mlocation_name = (TextView) view.findViewById(R.id.location_name_textview);
        //降雨機率
        viewHolder.mpop_value = (TextView) view.findViewById(R.id.pop_value_textview);
        //最高溫度標題
        viewHolder.mmax_t_tilte = (TextView) view.findViewById(R.id.maxt_textview_title);
        //最高溫度
        viewHolder.mmax_t = (TextView) view.findViewById(R.id.maxt_textview);
        //最低溫度標題
        viewHolder.mmin_t_tilte = (TextView) view.findViewById(R.id.mint_textview_title);
        //最低溫度
        viewHolder.mmin_t = (TextView) view.findViewById(R.id.mint_textview);
        //舒適度
        viewHolder.mci = (TextView) view.findViewById(R.id.ci_textview);

        return viewHolder;
    }

    //=======================================================================
    //設定自訂的UI的功能(所有的View元件，位置)
    //=======================================================================
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {

        //設定天氣圖片
        //viewHolder.mImg.setImageResource(R.drawable.bread_01);
        Set_mImg_Color(viewHolder , mDatas.get(i).getWx_value());
        //縮放動畫
        viewHolder.mImg.startAnimation(AnimationUtils.loadAnimation(mInflater.getContext() , R.anim.scale));

        //設定文字
        viewHolder.mlocation_name.setText(mDatas.get(i).getLocation_name());
        viewHolder.mpop_value.setText(""+mDatas.get(i).getPop_value() + " % ");
        viewHolder.mmax_t.setText(""+mDatas.get(i).getMax_t() + " ℃");
        viewHolder.mmin_t.setText(""+mDatas.get(i).getMin_t() + " ℃");
        viewHolder.mci.setText(mDatas.get(i).getCi());

        //設定文字顏色
        Set_pop_value_Color(viewHolder , mDatas.get(i).getPop_value());
        Set_mmax_t_Color(viewHolder , mDatas.get(i).getMax_t());
        Set_mmin_t_Color(viewHolder , mDatas.get(i).getMin_t());

        //設定文字字型
        Set_Font(viewHolder , "fonts/circle.otf");

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

  /*
    *設定UI外觀
    *
    * setTextColor(透明度、Red、Green、Blue)
    * */
  //======================================================================================
  //設定文字字型(FontPath:文字路徑(fonts/....))
  //======================================================================================
  private void Set_Font(final ViewHolder viewHolder , String FontPath){
      Typeface face = Typeface.createFromAsset(mInflater.getContext().getAssets(), FontPath);
      //地名
      viewHolder.mlocation_name.setTypeface(face);
      //降雨機率
      viewHolder.mpop_value.setTypeface(face);
      //最高溫度標題
      viewHolder.mmax_t_tilte.setTypeface(face);
      //最高溫度
      viewHolder.mmax_t.setTypeface(face);
      //最低溫度標題
      viewHolder.mmin_t_tilte.setTypeface(face);
      //最低溫度
      viewHolder.mmin_t.setTypeface(face);
      //舒適度
      viewHolder.mci.setTypeface(face);
  }


  //======================================================================================
    //設定降雨機率顏色
  //======================================================================================
    private void Set_pop_value_Color(final ViewHolder viewHolder , int Value){
        if(Value >= 70){
            viewHolder.mpop_value.setTextColor(0xff003d79);
        }
        else if(Value > 30 && Value < 70){
            viewHolder.mpop_value.setTextColor(0xff2894ff);
        }
        else{
            viewHolder.mpop_value.setTextColor(0xff97cbff);
        }
    }

    //======================================================================================
    //設定最高溫度顏色
    //======================================================================================
    private void Set_mmax_t_Color(final ViewHolder viewHolder , int Value){
            viewHolder.mmax_t.setTextColor(0xffad5a5a);
    }

    //======================================================================================
    //設定最低溫度顏色
    //======================================================================================
    private void Set_mmin_t_Color(final ViewHolder viewHolder , int Value){
        viewHolder.mmin_t.setTextColor(0xff8080c0);
    }

    //======================================================================================
    //設定天氣圖片
    //======================================================================================
    private void Set_mImg_Color(final ViewHolder viewHolder , int Value){
        switch (Value){
            case 1:
                viewHolder.mImg.setImageResource(R.drawable.sun);
                break;
            case 2:
                viewHolder.mImg.setImageResource(R.drawable.mostlyclear);
                break;
            case 3:
                viewHolder.mImg.setImageResource(R.drawable.partlyclear);
                break;
            default:
                viewHolder.mImg.setImageResource(R.drawable.sun);
                break;
        }//switch
    }



}//GalleryAdapter
