package com.livechat.videocallguide.videocall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.livechat.videocallguide.videocall.adapter.ChatAdapter;
import com.livechat.videocallguide.videocall.model.ChatModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.chat_list_view)
    RecyclerView chatListView;
    @BindView(R.id.chat_send_text)
    TextView chatText;

    String name;
    Integer photo;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.title)
    TextView title;


    private ArrayList<ChatModel> chatList = new ArrayList<>();
    private ChatAdapter chatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        name = getIntent().getStringExtra("name");
        photo = getIntent().getIntExtra("photo", R.drawable.chat_1);

        title.setText(name);
        avatar.setImageResource(photo);

        chatAdapter = new ChatAdapter(this, chatList, photo);
        chatListView.setLayoutManager(new LinearLayoutManager(this));
        chatListView.setHasFixedSize(true);
        chatListView.setAdapter(chatAdapter);


    }

    private Handler uiHandler = new Handler(Looper.getMainLooper());

    int autoMessageIndex = 0;

    @OnClick(R.id.chat_send_btn)
    void onSendBtnClick() {
        if (!chatText.getText().toString().trim().isEmpty()) {
            chatList.add(new ChatModel(chatText.getText().toString().trim(), true));
            chatAdapter.notifyDataSetChanged();
            chatListView.scrollToPosition(chatList.size() - 1);

            chatText.setText("");
            hideKeyboard(this);


            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    chatList.add(new ChatModel(getMessage(autoMessageIndex), false));
                    chatAdapter.notifyDataSetChanged();
                    chatListView.scrollToPosition(chatList.size() - 1);
                    autoMessageIndex++;
                }
            }, 2 * 1000);

        } else {
            Toast.makeText(this, "Please Enter Message", Toast.LENGTH_SHORT).show();
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    String getMessage(int autoMessageIndex) {
        switch (autoMessageIndex) {
            case 0:
                return "Hello";
            case 1:
                return "How are you?";
            case 2:
                return "I'm fine";
            case 3:
                return "And you?";
            case 4:
                return "Thanks";
            default:
                return "Good!";
        }
    }

}