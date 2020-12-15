package com.livechat.videocallguide.videocall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.livechat.videocallguide.videocall.ChatActivity;
import com.livechat.videocallguide.videocall.R;
import com.livechat.videocallguide.videocall.ViewAdviceActivity;
import com.livechat.videocallguide.videocall.adshelper.InterstitialAds;
import com.livechat.videocallguide.videocall.model.GirlModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder> {

    Context context;
    ArrayList<GirlModel> girlList;

    InterstitialAds mInterstitialAds;

    public GirlAdapter(Context context, ArrayList<GirlModel> girlList) {
        this.context = context;
        this.girlList = girlList;
        mInterstitialAds = new InterstitialAds(context);
    }

    @NonNull
    @Override
    public GirlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_girl, parent, false);
        return new GirlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GirlViewHolder girl, int position) {
        girl.avatar.setImageResource(girlList.get(position).photo);
        girl.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("name", girlList.get(position).name);
                intent.putExtra("photo", girlList.get(position).photo);
                context.startActivity(intent);
                mInterstitialAds.showAd();
            }
        });
    }

    @Override
    public int getItemCount() {
        return girlList.size();
    }

    public static class GirlViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;

        public GirlViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
