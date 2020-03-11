package com.app.picturechoose;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.choose.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PictureSelector.with(this).openImageGallery().singleMode().forResult(new OnResultCallbackListener(){
            @Override
            public void onResult(List<LocalMedia> result){

            }

            @Override
            public void onCancel(){

            }
        });
    }
}
