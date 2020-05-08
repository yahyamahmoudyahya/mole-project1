package com.yahya.ramadanfood;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import androidx.appcompat.app.AppCompatActivity;

public class PhotoDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_demo);
        String url = getIntent().getStringExtra("url");

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        Glide.with(this).load(url).into(photoView);

    }
}
