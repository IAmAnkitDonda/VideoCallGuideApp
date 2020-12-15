package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.livechat.videocallguide.videocall.adshelper.InterstitialAds;
import com.livechat.videocallguide.videocall.adshelper.NativeAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FakeAllCallChatActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    boolean isOpened = false;

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;

    InterstitialAds mInterstitialAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_all_call_chat);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("VIDEO_CALL_APP", Context.MODE_PRIVATE);
        isOpened = sharedPreferences.getBoolean("isOpened", false);

        mInterstitialAds = new InterstitialAds(this);
        nativeAds = new NativeAds(this, adContainer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick(R.id.video_call_advice_btn)
    void onVideoCallAdviceBtnClick() {
        startActivity(new Intent(this, VideoCallAdviceActivity.class));
        mInterstitialAds.showAd();
    }

    @OnClick(R.id.random_video_call_btn)
    void onRandomVideoCallBtnClick() {
        if (isOpened) {
            startActivity(new Intent(this, RoomListActivity.class));
            mInterstitialAds.showAd();
        } else {
            startActivity(new Intent(this, AgeSelectActivity.class));
            mInterstitialAds.showAd();
        }
    }

    @OnClick(R.id.create_fake_video_call_btn)
    void onCreateFakeVideoCallBtnClick() {
        startActivity(new Intent(this, CreateVideoCallActivity.class));
        mInterstitialAds.showAd();
    }

    @OnClick(R.id.chating_with_girl_btn)
    void onChatingWithGirlBtnClick() {
        startActivity(new Intent(this, ChatWithGirlActivity.class));
        mInterstitialAds.showAd();
    }

}