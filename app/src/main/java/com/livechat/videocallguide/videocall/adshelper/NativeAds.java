package com.livechat.videocallguide.videocall.adshelper;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.RelativeLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.livechat.videocallguide.videocall.R;

public class NativeAds {

    public static final String TAG = "NativeAds";
    public static AdView adView;
    public static NativeAd mNativeAd;


    public static Activity activity;
    public static RelativeLayout relativeLayout;


    public NativeAds(Activity activity, RelativeLayout relativeLayout) {
        this.activity = activity;
        this.relativeLayout = relativeLayout;
        FbNaviveAd();
    }


    public void FbNaviveAd() {
        mNativeAd = new NativeAd(activity, activity.getString(R.string.fb_native));
        mNativeAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("NativeAd ad failed to load: ");
                stringBuilder.append(adError.getErrorMessage());
                Log.e("FBNative", stringBuilder.toString());
                NativeAds.AmNativeLoad();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                relativeLayout.addView(NativeAdView.render(activity, NativeAds.mNativeAd, NativeAdView.Type.HEIGHT_300));
                Log.e("FBNative", "Fb Native Load Success");

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        mNativeAd.loadAd();
    }

    public static void AmNativeLoad() {
       /* AdLoader adLoader = new AdLoader.Builder(activity, activity.getString(R.string.am_native))
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();

                        TemplateView template = new TemplateView(activity);
                        template.setStyles(styles);
                        template.setNativeAd(unifiedNativeAd);
                        template.setLayerType();

                        relativeLayout.addView(template);

                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());*/


        adView = new AdView(activity);
        adView.setAdUnitId(activity.getString(R.string.am_banner));
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);

        adView.loadAd(new AdRequest.Builder().build());

        adView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e("AMNative","AM Native Not Load" );
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                relativeLayout.addView(adView);
                Log.e("AMNative","AM Native Load Success");
            }

        });
    }

    public void destroyAds() {
        if (mNativeAd != null) {
            mNativeAd.destroy();
            Log.e("FBNative", "Fb Native Destroy");
        }
        if (adView != null) {
            adView.destroy();
        }
    }


}
