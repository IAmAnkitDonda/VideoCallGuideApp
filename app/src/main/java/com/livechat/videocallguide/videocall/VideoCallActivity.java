package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import android.widget.VideoView;

import com.otaliastudios.cameraview.CameraView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoCallActivity extends AppCompatActivity {


    @BindView(R.id.camera)
    CameraView camera;
    @BindView(R.id.videoView)
    VideoView videoView;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean isOpened = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);
        ButterKnife.bind(this);

        camera.setLifecycleOwner(this);

        sharedPreferences = getSharedPreferences("VIDEO_CALL_APP", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        isOpened = sharedPreferences.getBoolean("isPlayed", false);


//        if (!isOpened) {
        playVideo();
//        } else {
//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(VideoCallActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
//                    onBackPressed();
//                }
//            }, 4 * 1000);
//        }
    }

    @OnClick(R.id.down_btn)
    void onClickDownBtn() {
        finish();
    }


    void playVideo() {
        final int min = 1;
        final int max = 6;
        final int random = new Random().nextInt((max - min) + 1) + min;
        editor.putBoolean("isPlayed", true);
        editor.apply();
        String videoPath = "android.resource://" + getPackageName() + "/";


        if (random == 1) {
            videoPath = videoPath + R.raw.video_file_1;
        }

        if (random == 2) {
            videoPath = videoPath + R.raw.video_file_2;
        }

        if (random == 3) {
            videoPath = videoPath + R.raw.video_file_3;
        }

        if (random == 4) {
            videoPath = videoPath + R.raw.video_file_4;
        }

        if (random == 5) {
            videoPath = videoPath + R.raw.video_file_5;
        }

        if (random == 6) {
            videoPath = videoPath + R.raw.video_file_6;
        }


        videoView.setVideoURI(Uri.parse(videoPath));


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                float screenRatio = videoView.getWidth() / (float) videoView.getHeight();
                float scale = videoRatio / screenRatio;
                if (scale >= 1f) {
                    videoView.setScaleX(scale);
                } else {
                    videoView.setScaleY(1f / scale);
                }

            }
        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                onBackPressed();
            }
        });
    }
}