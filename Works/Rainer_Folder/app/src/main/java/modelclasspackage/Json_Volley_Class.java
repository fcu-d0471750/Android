/*
* 附屬於Model
*
* 讀取一般天氣預報-今明36小時天氣預報的API
* 基本Volley使用
*
*下面是Volley的異常列表:
* AuthFailureError —如果你想做Http基本身份驗證,這個錯誤是最有可能的。
NetworkError — Socket斷開,伺服器不能訪問,DNS問題,可能導致這個錯誤。
NoConnectionError — 與NetworkError相似,當裝置沒有網際網路連線,應用的錯誤處理邏輯可以將clubNetworkError與NoConnectionError放在一起,同樣的處理。
ParseError — 在使用JsonObjectRequest或者JsonArrayRequest時,如果收到將的JSON格式是錯誤的,那麼出現這個異常。
ServerError — 伺服器回覆了一個錯誤,很可能與4 xx或5 xx HTTP狀態程式碼。
TimeoutError —Socket超時,伺服器太忙,來不及處理請求或網路延遲問題。預設Volley超時請求時間是2.5秒,使用一個RetryPolicy如果一直出現這個錯誤。
* */

package modelclasspackage;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.rainer.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import viewclasspackage.Rainer_item_Class;

public class Json_Volley_Class {

    //===========================================================
    //宣告變數
    //===========================================================
    //外部的Activity
    private Context context;
    //自訂news類別List
    private ArrayList<Rainer_item_Class> Outsidelist;

    //===========================================================
    //建構子(外部的Activity)
    //===========================================================
    public Json_Volley_Class(Context context){
        this.context = context;
        Outsidelist = new ArrayList<Rainer_item_Class>();
    }


   /*
      =========================================================================================================================
     內部處理
    ==========================================================================================================================
    */

    //===========================================================
    //獲得天氣預報資料
    //===========================================================
    private void GetData() {

        //資料來源政府開放平台 —「一般天氣預報-今明36小時天氣預報」
        String urlParkingArea = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=rdec-key-123-45678-011121314";

        //使用JsonObjectRequest類別要求JSON資料。(此方法僅能取得 JsonObject，若回傳資料內容為 JsonArray 請改用 JsonArrayRequest，否則會執行onErrorResponse。)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                urlParkingArea,
                new Response.Listener<JSONObject>() {
                    //Velloy採非同步作業，匿名類別 Response.Listener  監聽回應
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.d("CORRECT"," OK");
                        try {
                            Outsidelist = parserJson(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    //匿名類別Response.ErrorListener 監聽錯誤
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //處理錯誤.....
                        Log.d("ERROR"," Happen");
                    }
                }
        );

        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }

    //===========================================================
    //處理讀取的資料
    //===========================================================
    private ArrayList<Rainer_item_Class> parserJson(JSONObject jsonObject) throws JSONException {

        ArrayList<Rainer_item_Class> list = new ArrayList();
        //地名
        String location_name;
        //天氣圖片ID
        int wx_value;
        //天氣圖片名稱
        String wx_name;
        //降雨機率
        int pop_value;
        //最高溫度
        int max_t;
        //最低溫度
        int min_t;
        //舒適度
        String ci;


        //取得天氣預報資訊主體資料，放到JSONArray
        JSONArray data = jsonObject.getJSONObject("records").getJSONArray("location");

        //逐筆取得天氣預報資料
        for (int i = 0; i < data.length(); i++) {
            //地名
            JSONObject o = data.getJSONObject(i);

            //天氣圖片ID
            JSONObject ow = o.getJSONArray("weatherElement").getJSONObject(0).getJSONArray("time").getJSONObject(0).getJSONObject("parameter");
            //降雨機率
            JSONObject op = o.getJSONArray("weatherElement").getJSONObject(1).getJSONArray("time").getJSONObject(0).getJSONObject("parameter");

            //最高溫
            JSONObject omn = o.getJSONArray("weatherElement").getJSONObject(2).getJSONArray("time").getJSONObject(0).getJSONObject("parameter");
            //最低溫
            JSONObject omx = o.getJSONArray("weatherElement").getJSONObject(4).getJSONArray("time").getJSONObject(0).getJSONObject("parameter");

            //舒適度
            JSONObject oc = o.getJSONArray("weatherElement").getJSONObject(3).getJSONArray("time").getJSONObject(0).getJSONObject("parameter");


            location_name = o.getString("locationName");

            wx_value = Integer.valueOf(ow.getString("parameterValue")).intValue();
            wx_name = ow.getString("parameterName");

            pop_value =  Integer.valueOf(op.getString("parameterName")).intValue();

            max_t = Integer.valueOf(omx.getString("parameterName")).intValue();
            min_t = Integer.valueOf(omn.getString("parameterName")).intValue();

            ci = oc.getString("parameterName");

            //將資料放到list
            list.add(new Rainer_item_Class(location_name , wx_value , wx_name , pop_value , max_t , min_t , ci));
        }

        return list;
    }

  /*
     =========================================================================================================================
    外部使用
   ==========================================================================================================================
   */
    //回傳給外部天氣預報資料
    public ArrayList<Rainer_item_Class> GetJsonData(){
        GetData();
        return Outsidelist;
    }

}
