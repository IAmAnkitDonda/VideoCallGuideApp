package com.livechat.videocallguide.videocall.model;

public class ChatModel {
    public String msg;
    public boolean isMe;

    public ChatModel(String msg, boolean isMe) {
        this.msg = msg;
        this.isMe = isMe;
    }
}
