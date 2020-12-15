package com.livechat.videocallguide.videocall.helper;

import android.content.Context;
import android.content.Intent;

import com.livechat.videocallguide.videocall.BuildConfig;
import com.livechat.videocallguide.videocall.R;

public class ShareHelper {

    Context mContext;

    public ShareHelper(Context mContext) {
        this.mContext = mContext;
    }



    public void sendAppShare(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.app_name));
        String shareMessage = "Let me recommend you this application";
        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID ;
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        mContext.startActivity(Intent.createChooser(shareIntent, "choose one"));

    }
}
