package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.livechat.videocallguide.videocall.adapter.ContactAdapter;
import com.livechat.videocallguide.videocall.helper.DatabaseHelper;
import com.livechat.videocallguide.videocall.model.ContactModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlankActivity extends AppCompatActivity {

    @BindView(R.id.appbar)
    TextView appBar;
    @BindView(R.id.no_data)
    TextView noData;
    DatabaseHelper databaseHelper;

    @BindView(R.id.contact_list_view)
    RecyclerView contactListView;

    ContactAdapter contactAdapter;
    ArrayList<ContactModel> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        ButterKnife.bind(this);

        appBar.setText(getIntent().getStringExtra("title"));

        databaseHelper = new DatabaseHelper(this);

        contactListView.setLayoutManager(new LinearLayoutManager(this));
        contactListView.setHasFixedSize(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavData();
    }

    private void loadFavData() {
        contactList = new ArrayList<>();
        Cursor data = databaseHelper.getFavData(DatabaseHelper.TBNAME);
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String photo_path = data.getString(1);
            String video_path = data.getString(2);
            String name = data.getString(3);
            String number = data.getString(4);
            contactList.add(new ContactModel(id, photo_path, video_path, name, number));
        }
        if (contactList.size() > 0) {
            noData.setVisibility(View.GONE);
        } else {
            noData.setVisibility(View.VISIBLE);
        }

        contactAdapter = new ContactAdapter(this, contactList);
        contactListView.setAdapter(contactAdapter);

        Log.e("BlankActivity", contactList.size() + "");
    }
}