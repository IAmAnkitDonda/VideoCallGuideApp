package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.stefanodp91.android.circularseekbar.CircularSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactListActivity extends AppCompatActivity {

    public static final String TAG = "ContactListActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

    }



}