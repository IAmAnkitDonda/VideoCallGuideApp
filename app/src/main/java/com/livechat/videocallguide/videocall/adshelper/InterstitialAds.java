package com.livechat.videocallguide.videocall.adshelper;

import android.content.Context;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.livechat.videocallguide.videocall.R;


public class InterstitialAds {

    public static final String TAG = "InterstitialAds";
    Context mContext;
    public static boolean AdLoadedFlag = false;
    public static boolean AdRequestFlag = false;
    public boolean isAdShow = false;
    public static InterstitialAd AmInterstitialAd;
    public static com.facebook.ads.InterstitialAd FbInterstitialAd;
    String FbAdId;


    public InterstitialAds(Context context) {
        this.mContext = context;
        AdRequestFlag = true;
        loadFbInterstitialAd();
    }

    public void loadFbInterstitialAd() {
        FbInterstitialAd = new com.facebook.ads.InterstitialAd(mContext, mContext.getResources().getString(R.string.fb_inter));
        FbInterstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                isAdShow= true;
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                Log.e(TAG,"Fb Inter Dismiss");
                /*FbInterstitialAd.destroy();*/
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                AdLoadedFlag = false;
                ad.destroy();
                loadAmInterstitialAd();
                Log.e(TAG, "Fb Inter Not Loaded : " + adError.getErrorMessage()+" :"+FbAdId);

            }

            @Override
            public void onAdLoaded(Ad ad) {
                AdLoadedFlag = true;
                AmInterstitialAd= null;
                Log.e(TAG, "Fb Inter Load Success :" + FbAdId);

            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        FbInterstitialAd.loadAd();
    }

    public void loadAmInterstitialAd() {
        AdRequestFlag=true;
        AmInterstitialAd = new InterstitialAd(mContext);
        AmInterstitialAd.setAdUnitId(mContext.getResources().getString(R.string.am_inter));
        AmInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e(TAG, "Ad Inter Not Loaded" + i+" :");

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                AdLoadedFlag = true;
                FbInterstitialAd.destroy();
                Log.e(TAG, "Ad Inter Load Success");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                isAdShow = true;
            }
        });

        AmInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void showAd() {
        if (AdLoadedFlag&&AdLoadedFlag){
            try {
                if ( FbInterstitialAd.isAdLoaded()&&FbInterstitialAd!=null) {
                    Log.e(TAG,"Fb Inter Ready To Show");
                    FbInterstitialAd.show();
                    Log.e("Interstitial", "Fb Inter Show ");
                }

                else if (AmInterstitialAd!=null&&AmInterstitialAd.isLoaded()){
                    Log.e(TAG,"Am Inter Ready To Show");
                    AmInterstitialAd.show();
                    Log.e("Interstitial","Am Inter Show ");
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());

            }
        }
    }

    public Boolean isAdShow(){
        if (AdLoadedFlag){
            return isAdShow;
        }
        return false;
    }

    public void destroyAds() {
        AdLoadedFlag=false;
        AdRequestFlag=false;
        isAdShow = false;
        if (FbInterstitialAd != null) {
            FbInterstitialAd.destroy();
            Log.e("Interstitial","FbInter Destroy");
        }


    }
}
