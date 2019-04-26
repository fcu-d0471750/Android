/*
*Rainer包含的元件
*
* */
package viewclasspackage;

public class Rainer_item_Class {
    //==========================================================
    //宣告屬性
    //==========================================================
    //地名
    private String location_name;
    //天氣圖片ID
    private int wx_value;
    //天氣圖片名稱
    private String wx_name;
    //降雨機率
    private int pop_value;
    //最高溫度
    private int max_t;
    //最低溫度
    private int min_t;
    //舒適度
    private String ci;


    //==========================================================
    //建構子
    //==========================================================
    public Rainer_item_Class(String location_name, int wx_value, String wx_name, int pop_value, int max_t, int min_t, String ci) {
        this.location_name = location_name;
        this.wx_value = wx_value;
        this.wx_name = wx_name;
        this.pop_value = pop_value;
        this.max_t = max_t;
        this.min_t = min_t;
        this.ci = ci;
    }

    //==========================================================
    //地名
    //==========================================================
    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    //==========================================================
    //天氣圖片ID
    //==========================================================
    public int getWx_value() {
        return wx_value;
    }

    public void setWx_value(int wx_value) {
        this.wx_value = wx_value;
    }

    //==========================================================
    //天氣圖片名稱
    //==========================================================
    public String getWx_name() {
        return wx_name;
    }

    public void setWx_name(String wx_name) {
        this.wx_name = wx_name;
    }

    //==========================================================
    //降雨機率
    //==========================================================
    public int getPop_value() {
        return pop_value;
    }

    public void setPop_value(int pop_value) {
        this.pop_value = pop_value;
    }

    //==========================================================
    //最高溫度
    //==========================================================
    public int getMax_t() {
        return max_t;
    }

    public void setMax_t(int max_t) {
        this.max_t = max_t;
    }

    //==========================================================
    //最低溫度
    //==========================================================
    public int getMin_t() {
        return min_t;
    }

    public void setMin_t(int min_t) {
        this.min_t = min_t;
    }

    //==========================================================
    //舒適度
    //==========================================================
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }


}//Rainer_item_Class
