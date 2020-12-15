package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.livechat.videocallguide.videocall.adshelper.BannerAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomListActivity extends AppCompatActivity {

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    BannerAds bannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        ButterKnife.bind(this);

        bannerAd = new BannerAds(this,adContainer);
    }

    @OnClick({R.id.room1_btn, R.id.room2_btn, R.id.room3_btn, R.id.room4_btn})
    void onRoomBtnClick() {
        startActivity(new Intent(this, RoomActivity.class));

    }


}