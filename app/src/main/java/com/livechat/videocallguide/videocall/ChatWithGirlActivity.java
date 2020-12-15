package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.livechat.videocallguide.videocall.adapter.GirlAdapter;
import com.livechat.videocallguide.videocall.model.GirlModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatWithGirlActivity extends AppCompatActivity {

    ArrayList<GirlModel> girlList = new ArrayList<>();

    @BindView(R.id.girl_list_view)
    RecyclerView girlListView;

    GirlAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_girl);
        ButterKnife.bind(this);

        girlList.add(new GirlModel("Rashmi", R.drawable.chat_1));
        girlList.add(new GirlModel("Hinal", R.drawable.chat_2));
        girlList.add(new GirlModel("Denny", R.drawable.chat_3));
        girlList.add(new GirlModel("Aliana", R.drawable.chat_4));
        girlList.add(new GirlModel("Krishna", R.drawable.chat_5));
        girlList.add(new GirlModel("Janvi", R.drawable.chat_6));
        girlList.add(new GirlModel("Hetvi", R.drawable.chat_7));
        girlList.add(new GirlModel("Khushi", R.drawable.chat_8));
        girlList.add(new GirlModel("Tanvi", R.drawable.chat_9));
        girlList.add(new GirlModel("Keshvi", R.drawable.chat_10));

        girlListView.setLayoutManager(new GridLayoutManager(this, 2));
        girlListView.setHasFixedSize(true);


        adapter = new GirlAdapter(this, girlList);
        girlListView.setAdapter(adapter);


    }
}