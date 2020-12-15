package com.livechat.videocallguide.videocall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.livechat.videocallguide.videocall.ChatActivity;
import com.livechat.videocallguide.videocall.R;
import com.livechat.videocallguide.videocall.ViewContactActivity;
import com.livechat.videocallguide.videocall.model.ContactModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    Context context;
    ArrayList<ContactModel> contactList;

    public ContactAdapter(Context context, ArrayList<ContactModel> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contact, int position) {
        contact.name.setText(contactList.get(position).name);
        contact.number.setText(contactList.get(position).number);
        contact.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewContactActivity.class);
                intent.putExtra("id", contactList.get(position).id);
                intent.putExtra("photo_path", contactList.get(position).photo_path);
                intent.putExtra("video_path", contactList.get(position).video_path);
                intent.putExtra("name", contactList.get(position).name);
                intent.putExtra("number", contactList.get(position).number);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card)
        CardView card;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.number)
        TextView number;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
