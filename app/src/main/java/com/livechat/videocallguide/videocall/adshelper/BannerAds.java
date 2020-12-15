package com.livechat.videocallguide.videocall.adshelper;

import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.livechat.videocallguide.videocall.R;

public class BannerAds {
    public static AdView fbBannerView;
    public static com.google.android.gms.ads.AdView amBannerView;
    public static RelativeLayout relativeLayout;
    public static Activity activity;

    public BannerAds(Activity activity, RelativeLayout relativeLayout) {
        this.activity = activity;
        this.relativeLayout = relativeLayout;
        FbBannerAd();
    }

    public void FbBannerAd(){
        fbBannerView = new AdView(activity, activity.getString(R.string.fb_native), AdSize.BANNER_HEIGHT_50);
        fbBannerView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.e("FbBanner","Fb Banner Not Load : "+adError.getErrorMessage());
                AmBannerAd();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                relativeLayout.addView(fbBannerView);
                Log.e("FbBanner","Fb Load Success");
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        fbBannerView.loadAd();

    }

    public static void AmBannerAd(){
        amBannerView = new com.google.android.gms.ads.AdView(activity);
        amBannerView.setAdUnitId(activity.getString(R.string.am_banner));
        amBannerView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
        amBannerView.loadAd(new AdRequest.Builder().build());
        amBannerView.setAdListener(new com.google.android.gms.ads.AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                relativeLayout.addView(amBannerView);
                Log.e("AmBanner","Am Banner Load Success");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e("AmBanner","Am Banner Not Load");
            }
        });


    }

    public void destroyAds(){
        if (fbBannerView!=null){
            fbBannerView.destroy();
            relativeLayout.removeAllViews();
        }
        if (amBannerView!=null){
            amBannerView.destroy();
            relativeLayout.removeAllViews();
        }
    }
}
