package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.stefanodp91.android.circularseekbar.CircularSeekBar;
import com.github.stefanodp91.android.circularseekbar.OnCircularSeekBarChangeListener;
import com.livechat.videocallguide.videocall.adshelper.NativeAds;
import com.livechat.videocallguide.videocall.helper.DatabaseHelper;
import com.livechat.videocallguide.videocall.model.ChatModel;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ViewContactActivity extends AppCompatActivity {

    public static final String TAG = "ViewContactActivity";

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView nameView;
    @BindView(R.id.number)
    TextView numberView;
    @BindView(R.id.time)
    CircularSeekBar time;

    int seconds = 0;

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;


    String name, number, photo_path, video_path = "";
    int id;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        ButterKnife.bind(this);

        nativeAds = new NativeAds(this, adContainer);

        id = getIntent().getIntExtra("id", 0);
        photo_path = getIntent().getStringExtra("photo_path");
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
        video_path = getIntent().getStringExtra("video_path");

        databaseHelper = new DatabaseHelper(this);

        Log.e(TAG, photo_path);
//        Log.e(TAG, photo_path.replaceAll("/raw//","/")));

//        File imgFile = new File(photo_path);
        File imgFile = new File(photo_path.replaceAll("/raw//","/"));
        if (imgFile.exists()) {
            Glide.with(this).load(imgFile).centerCrop().into(avatar);
        }

        nameView.setText("Name : " + name);
        numberView.setText("Number : " + number);

        time.setOnRoundedSeekChangeListener(new OnCircularSeekBarChangeListener() {
            /**
             * Progress change
             * @param CircularSeekBar
             * @param progress the progress
             */
            @Override
            public void onProgressChange(CircularSeekBar CircularSeekBar, float progress) {
                Log.d("progress", "" + progress);

                seconds = (int) progress;
            }

            /**
             * Indicator touched
             * @param CircularSeekBar
             */
            @Override
            public void onStartTrackingTouch(CircularSeekBar CircularSeekBar) {

            }

            /**
             * Indicator released
             * @param CircularSeekBar
             */
            @Override
            public void onStopTrackingTouch(CircularSeekBar CircularSeekBar) {

            }
        });


    }

    @OnClick(R.id.delete)
    void onClickDeleteBtn() {
        databaseHelper.deleteFavData(id);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }

    @OnClick(R.id.check)
    void onClickCheckBtn() {
        if (seconds == 0) {
            seconds = 1;
        } else {
            Toast.makeText(this, "Wait " + seconds + " Seconds", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, DialerActivity.class);
        intent.putExtra("photo_path", photo_path);
        intent.putExtra("video_path", video_path);
        intent.putExtra("name", name);
        intent.putExtra("number", number);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, seconds * 1000);


    }
}