package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.livechat.videocallguide.videocall.adshelper.NativeAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoCallAdviceActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String TEXT = "text";

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_advice);
        ButterKnife.bind(this);

        nativeAds = new NativeAds(this, adContainer);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }

    @OnClick(R.id.girl_1_btn)
    void onGirl1BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Video Chat");
        intent.putExtra(TEXT, getResources().getString(R.string.text1));
        startActivity(intent);
    }

    @OnClick(R.id.girl_2_btn)
    void onGirl2BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Lots of Girls");
        intent.putExtra(TEXT, getResources().getString(R.string.text2));
        startActivity(intent);

    }

    @OnClick(R.id.girl_3_btn)
    void onGirl3BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Hot Video Chat");
        intent.putExtra(TEXT, getResources().getString(R.string.text3));
        startActivity(intent);
    }

    @OnClick(R.id.girl_4_btn)
    void onGirl4BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Video Call");
        intent.putExtra(TEXT, getResources().getString(R.string.text4));
        startActivity(intent);
    }

    @OnClick(R.id.girl_5_btn)
    void onGirl5BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Live Girl");
        intent.putExtra(TEXT, getResources().getString(R.string.text5));
        startActivity(intent);
    }

    @OnClick(R.id.girl_6_btn)
    void onGirl6BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Chat With Girl");
        intent.putExtra(TEXT, getResources().getString(R.string.text6));
        startActivity(intent);
    }

    @OnClick(R.id.girl_7_btn)
    void onGirl7BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Sexy Girl Call");
        intent.putExtra(TEXT, getResources().getString(R.string.text7));
        startActivity(intent);
    }

    @OnClick(R.id.girl_8_btn)
    void onGirl8BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Hot Video Call");
        intent.putExtra(TEXT, getResources().getString(R.string.text5));
        startActivity(intent);
    }

    @OnClick(R.id.girl_9_btn)
    void onGirl9BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Hotty Girl");
        intent.putExtra(TEXT, getResources().getString(R.string.text4));
        startActivity(intent);
    }

    @OnClick(R.id.girl_10_btn)
    void onGirl10BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Live Stream");
        intent.putExtra(TEXT, getResources().getString(R.string.text3));
        startActivity(intent);
    }

    @OnClick(R.id.girl_11_btn)
    void onGirl11BtnClick() {
        Intent intent = new Intent(this, ViewAdviceActivity.class);
        intent.putExtra(TITLE, "Beautiful Girl");
        intent.putExtra(TEXT, getResources().getString(R.string.text7));
        startActivity(intent);
    }
}