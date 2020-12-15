package com.livechat.videocallguide.videocall;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.livechat.videocallguide.videocall.adshelper.InterstitialAds;
import com.livechat.videocallguide.videocall.adshelper.NativeAds;
import com.livechat.videocallguide.videocall.helper.DatabaseHelper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddContactActivity extends AppCompatActivity {

    public static final String TAG = "AddContactActivity";

    @BindView(R.id.profile_img)
    CircleImageView profileImg;
    @BindView(R.id.video_img)
    ImageView videoImg;
    @BindView(R.id.name_text)
    EditText name;
    @BindView(R.id.number_text)
    EditText number;

    @BindView(R.id.ad_container)
    RelativeLayout adContainer;
    NativeAds nativeAds;

    String photo_path = "";
    String video_path = "";

    DatabaseHelper databaseHelper;

    InterstitialAds mInterstitialAds;
    static int SELECT_IMAGE = 1, SELECT_VIDEO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);

        nativeAds = new NativeAds(this, adContainer);
        mInterstitialAds = new InterstitialAds(this);
        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nativeAds.destroyAds();
    }


    @OnClick(R.id.profile_img)
    void onClickProfileImg() {
        pickImageFromGallery();
    }

    @OnClick(R.id.video_img)
    void onClickVideoImg() {
        pickVideoFromGallery();
    }

    private void pickVideoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("video/*");
        startActivityForResult(galleryIntent.createChooser(galleryIntent, "Select File"), SELECT_VIDEO);
    }

    @OnClick(R.id.save_btn)
    void onClickSaveBtn() {
        if (name.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (number.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please Enter Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (photo_path.trim().isEmpty()) {
            Toast.makeText(this, "Please Select Photo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (video_path.trim().isEmpty()) {
            Toast.makeText(this, "Please Select Video", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseHelper.addDataInFavTable(photo_path, video_path, name.getText().toString(), number.getText().toString());
        Toast.makeText(this, "Contact Saved !", Toast.LENGTH_SHORT).show();
        onBackPressed();
        mInterstitialAds.showAd();

    }

    private void pickImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent.createChooser(galleryIntent, "Select File"), SELECT_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            assert data != null;
            Uri coverImgUri = data.getData();
            if (requestCode == SELECT_IMAGE) {
                photo_path = coverImgUri.getPath();
                Glide.with(this).load(coverImgUri).centerCrop().into(profileImg);

            } else {
                video_path = getRealPathFromURI(coverImgUri);
                Log.e(TAG,"getPath()"+coverImgUri.getPath());
                Log.e(TAG,"getEncodedPath()"+coverImgUri.getEncodedPath());
                Log.e(TAG,"getRealPathFromURI() : "+getRealPathFromURI(coverImgUri));
                Glide.with(this).load(coverImgUri).centerCrop().into(videoImg);
            }
        }

    }


    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}