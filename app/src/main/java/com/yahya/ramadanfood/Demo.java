package com.yahya.ramadanfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Demo extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String image ;
    InterstitialAd mInterstitialAd;
    AdView adView;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        MobileAds.initialize(this, "ca-app-pub-6046329842156236~2647427332");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6046329842156236/3289838430");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        String title = getIntent().getStringExtra("title") ;
        image = getIntent().getStringExtra("url") ;
        String des = getIntent().getStringExtra("des") ;

        ImageView imageView = findViewById(R.id.image_view1);
        Glide.with(this).load(image).into(imageView);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(title);

        TextView textView = findViewById(R.id.text_title);
        textView.setText(title);

        TextView textView1 = findViewById(R.id.text_des1);
        textView1.setText(des);



    }

    public void demoClick(View view) {
        Intent intent = new Intent(this,PhotoDemo.class);
        intent.putExtra("url",image);
        this.startActivity(intent);
    }
}
