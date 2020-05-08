package com.yahya.ramadanfood;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final long LENGTH = 2500;
    RecyclerView recyclerView;
    ImageView imageSplash;
    TextView textView;
    AdView adView;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list");


        MobileAds.initialize(this, "ca-app-pub-6046329842156236~2647427332");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6046329842156236/3289838430");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        imageSplash = findViewById(R.id.image_splash);
        textView = findViewById(R.id.text_app_name);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.animator);
                animator.setTarget(imageSplash);
                animator.start();
                Animator animator1 = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.animator);
                animator1.setTarget(textView);
                animator1.start();
            }
        }, LENGTH);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 10);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageSplash.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                adView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Wait 5 minutes, until the pictures upload", Toast.LENGTH_LONG).show();
                mInterstitialAd.show();
            }
        }, 5000);


        final ArrayList<Products> url = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                url.clear();
                for (DataSnapshot a : dataSnapshot.getChildren()) {
                    Products products = a.getValue(Products.class);
                    url.add(products);

                }
                MyAdapter myAdapter = new MyAdapter(url, MainActivity.this);

                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


    }
}