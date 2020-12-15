package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.livechat.videocallguide.videocall.adshelper.NativeAds;
import com.skyfishjy.library.RippleBackground;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomActivity extends AppCompatActivity {

    @BindView(R.id.video_btn)
    RippleBackground videoBtn;

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
//    NativeAds nativeAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);

        videoBtn.startRippleAnimation();

//        nativeAds = new NativeAds(this, adContainer);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        nativeAds.destroyAds();
    }

    @OnClick(R.id.start_video_call)
    void onVideoBtnClick() {
        startActivity(new Intent(this,VideoCallActivity.class));
    }
}