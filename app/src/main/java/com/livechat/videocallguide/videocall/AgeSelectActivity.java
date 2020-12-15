package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.livechat.videocallguide.videocall.adshelper.InterstitialAds;
import com.livechat.videocallguide.videocall.adshelper.NativeAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgeSelectActivity extends AppCompatActivity {


    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;

    InterstitialAds mInterstitialAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_select);
        ButterKnife.bind(this);

        nativeAds = new NativeAds(this, adContainer);

        mInterstitialAds = new InterstitialAds(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }

    @OnClick(R.id.next_btn)
    void onNextBtnClick(){
        startActivity(new Intent(this, SelectGenderActivity.class));
        mInterstitialAds.showAd();
    }
}