package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.livechat.videocallguide.videocall.adshelper.NativeAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAdviceActivity extends AppCompatActivity {

    String title, text;
    @BindView(R.id.appbar)
    TextView appBarTitle;
    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.text)
    TextView textView;

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_advice);
        ButterKnife.bind(this);
        title = getIntent().getStringExtra(VideoCallAdviceActivity.TITLE);
        text = getIntent().getStringExtra(VideoCallAdviceActivity.TEXT);

        appBarTitle.setText(title);
        titleView.setText(title);
        textView.setText(text);

        nativeAds = new NativeAds(this, adContainer);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }
}