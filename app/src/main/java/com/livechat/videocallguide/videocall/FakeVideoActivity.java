package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.otaliastudios.cameraview.CameraView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FakeVideoActivity extends AppCompatActivity {

    private static final String TAG = "FakeVideoActivity";
    @BindView(R.id.camera)
    CameraView camera;
    @BindView(R.id.videoView)
    VideoView videoView;

    String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_video);
        ButterKnife.bind(this);
        camera.setLifecycleOwner(this);
        playVideo();
    }

    void playVideo() {
        videoPath = getIntent().getStringExtra("video_path");


        Log.e(TAG,videoPath);
//        File imgFile = new File(videoPath.replaceAll("/raw//","/"));
//        if (imgFile.exists()) {
            videoView.setVideoURI(Uri.parse(videoPath.replaceAll("/raw//","/")));
//        }else{
//            Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
//        }


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                float screenRatio = videoView.getWidth() / (float)
                        videoView.getHeight();
                float scaleX = videoRatio / screenRatio;
                if (scaleX >= 1f) {
                    videoView.setScaleX(scaleX);
                } else {
                    videoView.setScaleY(1f / scaleX);
                }

            }
        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @OnClick(R.id.down_btn)
    void onClickDownBtn() {
        finish();
    }
}