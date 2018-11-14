package com.simplism.iwillhealyou.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.simplism.iwillhealyou.Data.Debate;
import com.simplism.iwillhealyou.Make_Debate_Room_Activity;
import com.simplism.iwillhealyou.R;
import com.simplism.iwillhealyou.WaitingActivity;
import com.simplism.iwillhealyou.viewholder.PostViewHolder;  // 디렉토리 추가는 패키지 추가야

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MainFragment extends Fragment {

    //private FirebaseRecyclerAdapter<Recycler_debate_adapter, PostViewHolder> mAdapter;
    private DatabaseReference mDatabase;
    private LinearLayoutManager mManager;
    public String roomname;
    private String topic;


    private static final String TAG = "PostListFragment";

    private FirebaseRecyclerAdapter<Debate, PostViewHolder> mAdapter;
    private RecyclerView mRecycler;

    public MainFragment() {}

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecycler = rootView.findViewById(R.id.friends);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler.setHasFixedSize(true);


        // Set up FirebaseRecyclerAdapter with the Query

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query postsQuery = getQuery(mDatabase); // 여기서 getQuery함수랑 mDatabase의 차일드 경로를 지정 안해주는건 이 메인 프래그먼트를 프로토 타입으로 해서
                                                // 여러 프래그먼트에서 재사용 하려는 계획때문

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("posts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseRecyclerOptions<Debate> options = new FirebaseRecyclerOptions.Builder<Debate>()
                .setQuery(postsQuery, Debate.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Debate, PostViewHolder>(options) {
            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_item_debate, parent, false); // 레이아웃을 리사이클러뷰 안에 들어갈 하나하나의
                                                                                            // 아이디가 포함된, 즉 아이템 레이아웃으로 해야함.
                return new PostViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(PostViewHolder holder, int position, final Debate model) {

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        roomname = model.roomname;
                        topic = model.rm;
                        SharedPreferences msp = getActivity().getSharedPreferences("msp", Context.MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = msp.edit();
                        editor.putString("roomname", roomname);
                        editor.putString("topic", topic);
                        editor.apply();
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("posts").child(roomname);

                        if(!model.p1) {
                            ref.child("p1").setValue(true);
                            ref.child("p1uid").setValue(getUid());
                            editor.putString("player", "p1");
                            editor.apply();
                            Intent intent = new Intent(getActivity(), WaitingActivity.class);
                            startActivity(intent);
                        } else if(!model.p2) {
                            ref.child("p2").setValue(true);
                            ref.child("p2uid").setValue(getUid());
                            editor.putString("player", "p2");
                            editor.apply();
                            Intent intent = new Intent(getActivity(), WaitingActivity.class);
                            startActivity(intent);
                        } else if(!model.p3) {
                            ref.child("p3").setValue(true);
                            ref.child("p3uid").setValue(getUid());
                            editor.putString("player", "p3");
                            editor.apply();
                            Intent intent = new Intent(getActivity(), WaitingActivity.class);
                            startActivity(intent);
                        } else if(!model.p4) {
                            ref.child("p4").setValue(true);
                            ref.child("p4uid").setValue(getUid());
                            editor.putString("player", "p4");
                            editor.apply();
                            Intent intent = new Intent(getActivity(), WaitingActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "The room was already filled", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                holder.bindToPost(model);
                // Bind the Chat object to the ChatHolder
                // ...
            }
        };

        int a = mAdapter.getItemCount();

        Log.d(TAG, String.valueOf(a));
        Log.d(TAG, postsQuery.toString());
        mRecycler.setAdapter(mAdapter);

        // Set up Layout Manager, reverse layout

    }

    // [START post_stars_transaction]


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


    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

}
