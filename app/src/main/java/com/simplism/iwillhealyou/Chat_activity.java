package com.simplism.iwillhealyou;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.simplism.iwillhealyou.Data.Chat;
import com.simplism.iwillhealyou.viewholder.ChatViewHolder;

public class Chat_activity extends BaseActivity{

    private DatabaseReference mDatabase;
    private LinearLayoutManager mManager;
    private FirebaseRecyclerAdapter<Chat, ChatViewHolder> mAdapter;

    private String roomname;
    private String topic;
    private String person;

    final String TAG = "Chat_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);

        TextView tv = (TextView) findViewById(R.id.tv);
        final RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        Button btn = (Button) findViewById(R.id.btn);
        final EditText et = (EditText) findViewById(R.id.et);

        SharedPreferences msp = getSharedPreferences("msp", MODE_PRIVATE);
        roomname = msp.getString("roomname", null);
        topic = msp.getString("topic", null);
        person = msp.getString("player", null);

        mManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(mManager);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        recycler.setHasFixedSize(true);

        Query chatquery = mDatabase.child("chats").child(roomname)
                .limitToFirst(100);

        FirebaseRecyclerOptions<Chat> options = new FirebaseRecyclerOptions.Builder<Chat>()
                .setQuery(chatquery, Chat.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Chat, ChatViewHolder>(options) {
            @NonNull
            @Override
            public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chat_recycler_item, parent, false); // 아직 채팅에서 사용자 이름이랑 그런거 구현 안했으니까 뷰 홀더랑
                // 아이디가 포함된, 즉 아이템 레이아웃으로 해야함.                                 // 아이템 뷰 들어가서 만들기
                return new ChatViewHolder(view, getApplicationContext());
            }

            @Override
            protected void onBindViewHolder(ChatViewHolder holder, int position, final Chat model) {
                Log.d(TAG, "start binding");
                holder.bindToChat(model);
                Log.d(TAG, "sucessfully binded");
                // Bind the Chat object to the ChatHolder
                // ...

            }
        };
        recycler.setAdapter(mAdapter);

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mAdapter.getItemCount();
                Log.d(TAG, "onItemRangeInserted");
                int lastVisiblePosition = mManager.findLastCompletelyVisibleItemPosition();

                // If the recycler view is initially being loaded or the user is at the bottom of the list, scroll
                // to the bottom of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) && lastVisiblePosition == (positionStart - 1))) {
                    recycler.scrollToPosition(positionStart);
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cont = et.getText().toString();
                String key = mDatabase.child("chats").child(roomname).push().getKey();
                mDatabase.child("chats").child(roomname).child(key).child("person").setValue(person);
                mDatabase.child("chats").child(roomname).child(key).child("cont").setValue(cont);
                Toast.makeText(getApplicationContext(), "Successfully sended the text", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    public void cdtimer(int fin, final TextView tv) {
        final int[] value = {10};
        CountDownTimer timer = new CountDownTimer(fin*1000, 1000) {

            @Override
            public void onFinish() {
                //TODO : 카운트다운타이머 종료시 처리
                finish();
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                //TODO : 카운트다운타이머 onTick구현
                value[0]--;
                tv.setText(String.format("남은시간 %d", value[0]));
            }
        };
    }

}
