package com.livechat.videocallguide.videocall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.livechat.videocallguide.videocall.R;
import com.livechat.videocallguide.videocall.model.ChatModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {


    public static final int MSG_TYPE_LEFT = 0;      //  For Receiver
    public static final int MSG_TYPE_RIGHT = 1;

    private Context context;
    private ArrayList<ChatModel> msgList;
    private Integer img;

//    public ChatAdapter(Context context, ArrayList<ChatModel> msgList) {
//        this.context = context;
//        this.msgList = msgList;
//
//    }


    public ChatAdapter(Context context, ArrayList<ChatModel> msgList, Integer img) {
        this.context = context;
        this.msgList = msgList;
        this.img = img;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_RIGHT) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_chat_right, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_chat_left, parent, false);
        }
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder chat, int position) {
        chat.text.setText(msgList.get(position).msg);
        chat.avatar.setImageResource(img);
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_item_text)
        TextView text;
        @BindView(R.id.chat_item_avatar)
        CircleImageView avatar;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (msgList.get(position).isMe) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}
