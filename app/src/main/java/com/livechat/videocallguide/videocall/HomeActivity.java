package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fuzzproductions.ratingbar.RatingBar;
import com.livechat.videocallguide.videocall.adshelper.InterstitialAds;
import com.livechat.videocallguide.videocall.helper.ShareHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
//    NativeAds nativeAds;

    InterstitialAds mInterstitialAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

//        nativeAds = new NativeAds(this, adContainer);
        mInterstitialAds = new InterstitialAds(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick(R.id.start_btn)
    void onStartBtnClick() {
        startActivity(new Intent(this, SecondActivity.class));
        mInterstitialAds.showAd();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        nativeAds.destroyAds();
    }


//    @OnClick(R.id.rate_btn)
//    void onClickRateBtn() {
//        Dialog dialog;
//        dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_rating);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//
//
//        TextView submitBtn = dialog.findViewById(R.id.submit_btn);
//        RatingBar ratingBar = dialog.findViewById(R.id.rating);
//        ImageView closeBtn = dialog.findViewById(R.id.close_btn);
//
//
//        closeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                if ((int) ratingBar.getRating() > 4) {
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID));
//                    startActivity(browserIntent);
//                } else {
//                    onClickFeedbackBtn();
//                }
//            }
//        });
//
//        dialog.getWindow().setAttributes(lp);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dialog.setCancelable(true);
//
//
//        dialog.show();
//    }


    @OnClick(R.id.privacy_btn)
    void onClickPrivacyBtn() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://videocallswithchat.blogspot.com/2020/12/privacy-policy.html"));
        startActivity(browserIntent);
    }

    @OnClick(R.id.more_btn)
    void onClickMoreBtn() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=meet.meindiangirlchating"));
        startActivity(browserIntent);
    }

    @OnClick(R.id.feedback_btn)
    void onClickFeedbackBtn() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "skmfreeapps@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        startActivity(Intent.createChooser(emailIntent, null));
    }

    @OnClick(R.id.share_btn)
    void onClickShareBtn() {
        ShareHelper shareHelper = new ShareHelper(this);
        shareHelper.sendAppShare();
    }
}