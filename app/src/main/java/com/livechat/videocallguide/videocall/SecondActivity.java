package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.livechat.videocallguide.videocall.adshelper.NativeAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    boolean isOpened = false;

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("VIDEO_CALL_APP", Context.MODE_PRIVATE);
        isOpened = sharedPreferences.getBoolean("isOpened", false);

        nativeAds = new NativeAds(this, adContainer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }

    @OnClick(R.id.fake_call_btn)
    void onFakeCallBtnClick() {
        startActivity(new Intent(this, FakeAllCallChatActivity.class));
    }

    @OnClick(R.id.direct_call_btn)
    void onDirectCallBtnClick() {
        if (isOpened) {
            startActivity(new Intent(this, RoomListActivity.class));
        } else {
            startActivity(new Intent(this, AgeSelectActivity.class));
        }
    }

}