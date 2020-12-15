package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.Contract;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DialerActivity extends AppCompatActivity {

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView nameView;
    @BindView(R.id.number)
    TextView numberView;
    @BindView(R.id.pickup_btn)
    ImageView pickupBtn;
    @BindView(R.id.down_btn)
    ImageView downBtn;

    public MediaPlayer player;

    String name, number, photo_path, video_path = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
        ButterKnife.bind(this);

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();

        photo_path = getIntent().getStringExtra("photo_path");
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
        video_path = getIntent().getStringExtra("video_path");


        File imgFile = new File(photo_path.replaceAll("/raw//",""));
        if (imgFile.exists()) {
            Glide.with(this).load(imgFile).centerCrop().into(avatar);
        }

        nameView.setText(name);
        numberView.setText(number);
    }


    @OnClick(R.id.pickup_btn)
    void onClickPickUpBtn() {
        player.stop();
        Intent intent = new Intent(this, FakeVideoActivity.class);
        intent.putExtra("video_path", video_path);
        startActivity(intent);
        finish();
    }


    @OnClick(R.id.down_btn)
    void onClickDownBtn() {
        player.stop();
        onBackPressed();
    }


    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}