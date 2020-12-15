package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.livechat.videocallguide.videocall.adshelper.InterstitialAds;
import com.livechat.videocallguide.videocall.adshelper.NativeAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectGenderActivity extends AppCompatActivity {

    @BindView(R.id.name_text)
    EditText name;
    @BindView(R.id.male_btn)
    ImageView maleBtn;
    @BindView(R.id.female_btn)
    ImageView femaleBtn;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;

    InterstitialAds mInterstitialAds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gender);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("VIDEO_CALL_APP", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        nativeAds = new NativeAds(this, adContainer);

        mInterstitialAds = new InterstitialAds(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }



    @OnClick(R.id.go_btn)
    void onGoBtnClick() {
        if (!name.getText().toString().trim().isEmpty()) {
            editor.putBoolean("isOpened", true);
            editor.apply();
            startActivity(new Intent(this, RoomListActivity.class));
            mInterstitialAds.showAd();
        } else {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.male_btn)
    void onMaleBtnClick() {
        femaleBtn.setImageResource(R.drawable.female_selected);
        maleBtn.setImageResource(R.drawable.male);
    }

    @OnClick(R.id.female_btn)
    void onFemaleBtnClick() {
        femaleBtn.setImageResource(R.drawable.female);
        maleBtn.setImageResource(R.drawable.male_selected);
    }

}