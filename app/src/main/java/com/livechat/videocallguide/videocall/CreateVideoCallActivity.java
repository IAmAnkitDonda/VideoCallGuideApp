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

public class CreateVideoCallActivity extends AppCompatActivity {

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;

    InterstitialAds mInterstitialAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_video_call);
        ButterKnife.bind(this);

        nativeAds = new NativeAds(this, adContainer);
        mInterstitialAds = new InterstitialAds(this);

    }


    @OnClick(R.id.add_contact_btn)
    void addContactBtnClick() {
        startActivity(new Intent(this, AddContactActivity.class));
        mInterstitialAds.showAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }

    @OnClick(R.id.schedual_video_call_btn)
    void addSchedualVideoCallBtnClick() {
        Intent intent = new Intent(this,BlankActivity.class);
        intent.putExtra("title","Schedule Video Call");
        startActivity(intent);
        mInterstitialAds.showAd();

    }

    @OnClick(R.id.instant_video_call_btn)
    void addInstantVideoCallBtnClick() {
        Intent intent = new Intent(this,BlankActivity.class);
        intent.putExtra("title","Instant Call");
        startActivity(intent);
        mInterstitialAds.showAd();
    }
}