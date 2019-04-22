/*
* FireBase基本功能
*選擇、上傳、下載、刪除
* */
package com.example.user.picture_firebase_small;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
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


public class FireBase_Basic_Class {

    //FireBase
    public StorageReference mStorageRef;
    //使用者要使用的FireBase的資料位置 或 本機資料位置
    public StorageReference riversRef;


    //====================================================================
    //FireBase_Basic_Class建構子(無參數)
    //====================================================================
    FireBase_Basic_Class(){
        initData();
    }


    //====================================================================
    //資料庫連接初始化: 取得連接ID
    //====================================================================
    private void initData() {
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }


    //====================================================================
    //功能: 刪除圖片(圖片FireBase的位置，顯示的頁面)
    //====================================================================
    public void deleteImg(final StorageReference ref , final Context context){
        //刪除圖片的位置，就跟下載圖片的位置一樣，也可以指定
        //ref = mStorageRef.child("AAA/op.png");

        //如果沒有圖片路徑，表示沒有上傳圖片
        if(ref == null){
            //顯示 請上傳圖片 訊息
            Toast.makeText(context, R.string.plz_upload_img, Toast.LENGTH_SHORT).show();
            return;
        }

        //如果有圖片路徑，表示有上傳圖片
        //(上傳成功)
        ref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //顯示 刪除成功 訊息
                Toast.makeText(context, R.string.delete_success, Toast.LENGTH_SHORT).show();
            }
        })
                //(上傳失敗)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        //顯示 刪除失敗 訊息
                        Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //====================================================================
    //功能: 下載圖片(圖片FireBase的位置，顯示的頁面，下載的圖片，下載TextView)
    //====================================================================
    public void downloadImg(final StorageReference ref , final Context context , final ImageView downloadImg , final TextView downloadInfoText){

        //如果沒有FireBase的圖片路徑，表示沒有上傳圖片
        if(ref == null){
            //顯示 請上傳圖片 訊息
            Toast.makeText(context, R.string.plz_upload_img, Toast.LENGTH_SHORT).show();
            return;
        }

        //如果要指定路徑下載，可使用下面一行指定路徑，(但由於副程式開頭宣告ref是設為final不能在這裡修改)
        //riversRef = mStorageRef.child("AAA/op.png");

        //(下載成功)，(如果要取得下載圖片的真正網址(Http)開頭，就只能在下面new OnSuccessListener<Uri>裡面的uri來獲得)
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                //(using:使用FirebaseImageLoader , load: 載入指定路徑的圖片 ,  into: 放入downloadImg顯示)
                Glide.with(context)
                        .using(new FirebaseImageLoader())
                        .load(ref)
                        .into(downloadImg);
                //顯示 下載成功 訊息
                downloadInfoText.setText(R.string.download_success);
            }
        })
                //(下載失敗)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        //顯示 下載失敗 訊息
                        downloadInfoText.setText(exception.getMessage());
                    }
                });
    }

    //====================================================================
    //功能: 上傳圖片(本機圖片路徑，進度條UI，上傳TextView)
    //====================================================================
    public void uploadImg(String path , final ProgressBar imgUploadProgress , final TextView uploadInfoText){
        //file要上傳的圖片的本機路徑
        Uri file = Uri.fromFile(new File(path));

        //StorageMetadata用於給於圖片額外資訊，例如名稱、時間、地點
        StorageMetadata metadata = new StorageMetadata.Builder()
                //
                .setContentDisposition("universe")
                //檔案類型
                .setContentType("image/jpg")
                .build();

        //riversRef設定為上傳到FireBase之後的Uri，將file儲存圖片路徑轉換成String
        //(如果要指定上傳路徑，則在file.getLastPathSegment()前面加上最後的資料夾位置，例"AAA/"+file.getLastPathSegment() 、 "AAA/BBB/" +file.getLastPathSegment() )
        //.child會自動補上開發者FireBase的網址，開發者只需填上要儲存的位置
        //.getLastPathSegment是取得選擇的圖片位置最後的名稱，也就是圖片名稱
        riversRef = mStorageRef.child(file.getLastPathSegment());

        //將riversRef放入uploadTask，預備上傳putFile(檔案，額外資訊)
        UploadTask uploadTask = riversRef.putFile(file, metadata);
        //(上傳失敗)
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                uploadInfoText.setText(exception.getMessage());
            }
        })
                //(上傳成功)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        uploadInfoText.setText(R.string.upload_success);

                    }
                })
                //(上傳中)
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        //取得上傳進度，並轉成int給使用者看
                        int progress = (int)((100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount());
                        //依照進度設定進度條
                        imgUploadProgress.setProgress(progress);
                        //如果完成，則隱藏進度條(View.VISIBLE: 顯示 View.GONE:隱藏)
                        if(progress >= 100){
                            imgUploadProgress.setVisibility(View.GONE);
                        }
                    }
                });


    }




}//FireBase_Basic_Class
