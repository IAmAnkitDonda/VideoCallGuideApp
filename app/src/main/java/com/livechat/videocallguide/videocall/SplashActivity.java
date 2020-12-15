package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    int waitTime = 1 * 1000; // second *1000

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize Facebook Ads
        AudienceNetworkAds.initialize(this);
        AudienceNetworkAds.isInAdsProcess(this);
        // Initialize Google Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        getAppPermissionHere();
    }


    void getAppPermissionHere() {
        Dexter.withContext(SplashActivity.this).withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
        ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(waitTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                                SplashActivity.this.finish();
                            }
                        }
                    };
                    timer.start();
                } else {
//                    Toast.makeText(SplashActivity.this, "You have to give us those permission", Toast.LENGTH_SHORT).show();
                    getAppPermissionHere();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
}