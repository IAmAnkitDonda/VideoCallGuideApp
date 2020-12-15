package com.livechat.videocallguide.videocall.model;

public class ContactModel {
    public int id;
    public String photo_path;
    public String video_path;
    public String name;
    public String number;


    public ContactModel(int id, String photo_path, String video_path, String name, String number) {
        this.id = id;
        this.photo_path = photo_path;
        this.video_path = video_path;
        this.name = name;
        this.number = number;
    }
}
