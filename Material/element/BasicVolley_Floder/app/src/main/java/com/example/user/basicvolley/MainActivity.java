/*
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
package com.example.user.basicvolley;

import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //===========================================================
    //宣告UI
    //===========================================================
    //Button
    protected Button jsonbtnobj;
    //ListView
    protected ListView jsonlvobj;

    //===========================================================
    //onCreate
    //===========================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //螢幕保持直向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //UI初始化
        UI_Initial();

        //Button初始化
        Button_Initial();
    }

    //============================================================
    //UI初始化
    //============================================================
    void UI_Initial(){
        jsonbtnobj = (Button) findViewById(R.id.jsonbtn);
        jsonlvobj = (ListView) findViewById(R.id.jsonlv);
    }

    //============================================================
    //Button初始化
    //============================================================
    void Button_Initial(){
        jsonbtnobj_Function();
    }


    //============================================================
    //jsonbtnobj功能
    //============================================================
    void jsonbtnobj_Function(){
        jsonbtnobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData();
            }
        });
    }

    protected void GetData() {

        //資料來源政府開放平台 —「臺北捷運轉乘停車場座標」
        String urlParkingArea = "https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=a880adf3-d574-430a-8e29-3192a41897a5";

        //使用JsonObjectRequest類別要求JSON資料。(此方法僅能取得 JsonObject，若回傳資料內容為 JsonArray 請改用 JsonArrayRequest，否則會執行onErrorResponse。)
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                urlParkingArea,
                new Response.Listener<JSONObject>() {
                    //Velloy採非同步作業，匿名類別 Response.Listener  監聽回應
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.d("CORRECT"," OK");
                        try {
                            parserJson(response);
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

        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

    protected void parserJson(JSONObject jsonObject) throws JSONException {

        ArrayList<String> list = new ArrayList();

        //取得停車場資訊主體資料，放到JSONArray
        JSONArray data = jsonObject.getJSONObject("result").getJSONArray("results");
        //逐筆取得停車場地點
        for (int i = 0; i < data.length(); i++) {
            JSONObject o = data.getJSONObject(i);
            String str ="_id : "+o.getString("_id")+"\n"+
                    "項次 : "+o.getString("項次")+"\n"+
                    "停車場名稱 : "+o.getString("停車場名稱")+"\n"+
                    "經度(WGS84) : "+o.getString("經度(WGS84)")+"\n"+
                    "緯度(WGS84) : "+o.getString("緯度(WGS84)");

            //將資料放到list
            list.add(str);
        }

        //設定ListView
        jsonlvobj.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list));

    }


}//MainActivity
