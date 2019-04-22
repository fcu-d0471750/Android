package com.example.user.picture_firebase_small;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //====================================================================
    //宣告變數
    //====================================================================
    //MainActivity的識別碼
    private static final int PICKER = 100;
    //識別使用者權限的識別碼
    private static final int REQUEST_EXTERNAL_STORAGE = 200;

    //FireBase
    FireBase_Basic_Class FBC;

    //====================================================================
    //宣告UI
    //====================================================================
    //上傳資訊TextView
    private TextView uploadInfoText;
    //下載資訊TextView
    private TextView downloadInfoText;


    //選擇圖片Button
    private Button pickImgButton;
    //上傳圖片Button
    private Button uploadImgButton;
    //下載圖片Button
    private Button downloadImgButton;
    //刪除圖片Button
    private Button deleteImgButton;

    //選擇的圖片ImageView
    private ImageView pickImg;
    //下載的圖片ImageView
    private ImageView downloadImg;

    //將圖片位置轉成String儲存的String
    private String imgPath;
    //進度條
    private ProgressBar imgUploadProgress;


    //====================================================================
    //宣告動態新增元件
    //====================================================================
    //用於綁定要哪個Layout下的RelativeLayout
    RelativeLayout relativeLayout;
    //新增圖片1
    ImageView tv1;
    //新增圖片2
    ImageView tv2;

    //====================================================================
    //onCreate
    //====================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FireBase初始化
        FBC = new FireBase_Basic_Class();

        //UI初始化
        initView();
    }



    //====================================================================
    //UI初始化
    //====================================================================
    private void initView() {
        //上傳資訊TextView
        uploadInfoText = (TextView) findViewById(R.id.upload_info_text);
        //下載資訊TextView
        downloadInfoText = (TextView) findViewById(R.id.download_info_text);

        //選擇圖片Button
        pickImgButton = (Button) findViewById(R.id.pick_button);
        //上傳圖片Button
        uploadImgButton = (Button) findViewById(R.id.upload_button);
        //下載圖片Button
        downloadImgButton = (Button) findViewById(R.id.download_button);
        //刪除圖片Button
        deleteImgButton = (Button) findViewById(R.id.delete_button);

        //選擇的圖片ImageView
        pickImg = (ImageView) findViewById(R.id.pick_img);
        //下載的圖片ImageView
        downloadImg = (ImageView) findViewById(R.id.download_img);

        //進度條
        imgUploadProgress = (ProgressBar) findViewById(R.id.upload_progress);


        //選取圖片功能
        pickImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //檢查權限
                checkPermission();
            }
        });

        //上傳圖片功能
        uploadImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果有選取圖片
                if(!TextUtils.isEmpty(imgPath)) {
                    //顯示進度條
                    imgUploadProgress.setVisibility(View.VISIBLE);
                    //上傳圖片
                    FBC.uploadImg(imgPath , imgUploadProgress , uploadInfoText);
                }
                //如果沒有選取圖片
                else{
                    //顯示 請選擇圖片 訊息
                    Toast.makeText(MainActivity.this, R.string.plz_pick_img, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //下載圖片功能
        downloadImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FBC.downloadImg(FBC.riversRef , MainActivity.this , downloadImg , downloadInfoText);
            }
        });

        //刪除圖片功能
        deleteImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FBC.deleteImg(FBC.riversRef , MainActivity.this);
            }
        });

    }

    //====================================================================
    //判斷使用者是否有授予"讀檔權限"給 App 使用
    //====================================================================
    private void checkPermission(){
        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

        //沒有就開啟對話框請求權限
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權限
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
        }
        //有就直接選擇圖片
        else {
            getLocalImg();
        }
    }


    //====================================================================
    //功能: 取得圖片
    //====================================================================
    private void getLocalImg(){
        //開啟圖片庫
        Intent picker = new Intent(Intent.ACTION_GET_CONTENT);
        picker.setType("image/*");
        picker.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        Intent destIntent = Intent.createChooser(picker, null);
        startActivityForResult(destIntent, PICKER);
    }

    //====================================================================
    //功能: 判斷使用者是否允許權限(當執行checkPermission()，就會呼叫這個功能)
    //====================================================================
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                //如果允許則可以進行選取圖片的操作
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocalImg();
                }
                //如果不允許則顯示 無權限無法做事 訊息
                else {
                    Toast.makeText(MainActivity.this, R.string.do_nothing, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //====================================================================
    //功能: 選完照片以後，透過覆寫 onActivityResult 來判斷從選取器回來的相簿資料接著使用 Glide 去呈現選取完的相簿資料
    //====================================================================
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKER) {
            if (resultCode == Activity.RESULT_OK) {
                //儲存選擇的圖片路徑
                Uri uri = data.getData();
                //uri將儲存成String
                imgPath = getPath(MainActivity.this, uri);
                //如果imgPath是有路徑
                if(!TextUtils.isEmpty(imgPath)) {
                    //顯示 圖片路徑 訊息
                    Toast.makeText(MainActivity.this, imgPath, Toast.LENGTH_SHORT).show();
                    //依照圖片路徑，讀取圖片
                    Glide.with(MainActivity.this).load(imgPath).into(pickImg);
                }
                //如果imgPath沒有路徑
                else{
                    //顯示 讀取圖片失敗 訊息
                    Toast.makeText(MainActivity.this, R.string.load_img_fail, Toast.LENGTH_SHORT).show();
                }
            }//if (resultCode == Activity.RESULT_OK)
        }//if (requestCode == PICKER)
    }


    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     *由於原本提供的getpath()並不能完全載入路徑
     *所以要開發者如有需要，則可以自己寫getpath()這項功能
     *
     * Android在4.4之後的版本(包括4.4)中，從相冊中選取圖片返回Uri進行了改動。
     * 所以我們無法通過該Uri來取得文件路徑，從而解碼圖片，將其顯示出來。
     *
     * 在4.3或以下可以直接用Intent.ACTION_GET_CONTENT打開相簿。
     * 在4.4或以上,官方建議用ACTION_OPEN_DOCUMENT打開相簿。
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @author paulburke
     */

    //====================================================================
    //功能: 獲得使用者選擇的圖片在本機的位置路徑
    //====================================================================
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        //判斷本機的SDK版本是否高於4.4版本，true:高於4.4版本 false:低於4.4版本(確保功能可以使用)
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        //首先對Uri的authority進行判斷。// 如果是document類型的 uri, 則通過document id來進行處理
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider(外部檔案)
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider(下載檔案)
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider(多媒體)
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)// 如果是 content 類型的 Uri
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File(如果是普通類型，則直接使用getPath()就好了)
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */

    //獲取資料庫表中的 _data 列，即返回Uri對應的文件路徑
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    //判斷是否為外部檔案
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    //判斷是否為下載檔案
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    //判斷是否為多媒體檔案
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    //====================================================================
    //動態載入圖片
    //====================================================================
    void DynamicAddImage_1(){

        //新增第1張Image
        tv1 = new ImageView(this);

        // 這一行是你想要讓你的圖片呈現原始大小
        //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //這一行是你想要讓你的圖片大小是多大，我想要的是100*100
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 100);

        //與父容器的左側對齊
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        //與父容器的上側對齊
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        //用於設定新增圖片的ID
        int a = 1;
        //設定新增圖片ID
        tv1.setId(a);
        //設定圖片位置
        tv1.setLayoutParams(lp);
        //加入新的圖片
        relativeLayout.addView(tv1,lp);

        //設定新的圖片來源
        FBC.riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                //(using:使用FirebaseImageLoader , load: 載入指定路徑的圖片 ,  into: 放入downloadImg顯示)
                Glide.with(MainActivity.this)
                        .using(new FirebaseImageLoader())
                        .load(FBC.riversRef)
                        .into(tv1);
            }
        });

        //====================================================================
        //(分隔線)
        //====================================================================

        //新增第2張Image
        tv2 = new ImageView(this);

        //這一行是你想要讓你的圖片大小是多大，我想要的是100*100
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(100, 100);

        //設定圖片在ID為1的元件下方(位置，ID)
        lp2.addRule(RelativeLayout.BELOW, 1);
        //設定圖片的左邊對齊ID為1的元件左方(位置，ID)
        lp2.addRule(RelativeLayout.ALIGN_LEFT, 1);

        //用於設定新增圖片的ID
        int b = 2;
        //設定新增圖片ID
        tv2.setId(b);
        //設定圖片位置
        tv2.setLayoutParams(lp2);
        //加入新的圖片
        relativeLayout.addView(tv2,lp2);

        //設定新的圖片來源
        FBC.riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                //(using:使用FirebaseImageLoader , load: 載入指定路徑的圖片 ,  into: 放入downloadImg顯示)
                Glide.with(MainActivity.this)
                        .using(new FirebaseImageLoader())
                        .load(FBC.riversRef)
                        .into(tv2);
            }
        });

    }





}//MainActivity
