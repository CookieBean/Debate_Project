package com.simplism.iwillhealyou;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simplism.iwillhealyou.Data.Mode;
import com.simplism.iwillhealyou.Data.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MakeRoomActivity extends AppCompatActivity {

    TextView tvmain, tvcont, tvmain2, tvcont2;
    Spinner topic, mode;
    DatabaseReference myref;
    Button btn;

    ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    String main, cont;

    private final String TAG = "MakeRoomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_room);

        tvmain = (TextView) findViewById(R.id.tvmain);
        tvcont = (TextView) findViewById(R.id.tvcont);
        tvmain2 = (TextView) findViewById(R.id.tvmain2);
        tvcont2 = (TextView) findViewById(R.id.tvcont2);

        btn = (Button) findViewById(R.id.btn1);

        topic = (Spinner) findViewById(R.id.topic);
        mode = (Spinner) findViewById(R.id.mode);

        myref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference listref = FirebaseDatabase.getInstance().getReference().child("topics");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("posts");

                String rm = mref.push().getKey();
                String tp = topic.getSelectedItem().toString();

                mref.child(rm).child("roomname").setValue(rm);
                mref.child(rm).child("rm").setValue(tp); // rm is topic
                mref.child(rm).child("p1").setValue(true);
                mref.child(rm).child("p2").setValue(false);
                mref.child(rm).child("p3").setValue(false);
                mref.child(rm).child("p4").setValue(false);

                SharedPreferences msp = getSharedPreferences("msp", Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = msp.edit();
                editor.putString("roomname", rm);
                editor.putString("topic", tp);
                editor.putString("player", "p1");
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), WaitingActivity.class);
                startActivity(intent);
            }
        });

        listref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final List<String> topics = new ArrayList<String>();
                for (DataSnapshot topicsnapshot : dataSnapshot.getChildren()) {
                    String topicname = topicsnapshot.child("topic").getValue(String.class);
                    topics.add(topicname);
                }
                ArrayAdapter<String> topicadapter = new ArrayAdapter<String>(MakeRoomActivity.this, android.R.layout.select_dialog_item, topics);
                topic.setAdapter(topicadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference moderef = FirebaseDatabase.getInstance().getReference().child("modes");

        moderef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final List<String> modes = new ArrayList<String>();
                for (DataSnapshot modesnapshot : dataSnapshot.getChildren()) {
                    String modename = modesnapshot.child("mode").getValue(String.class);
                    modes.add(modename);
                }
                ArrayAdapter<String> modeadapter = new ArrayAdapter<String>(MakeRoomActivity.this, android.R.layout.select_dialog_item, modes);
                mode.setAdapter(modeadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference topicref = FirebaseDatabase.getInstance().getReference().child("topics");

        topic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                topicref.child(item).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Topic topic1 = dataSnapshot.getValue(Topic.class);
                        tvmain.setText(topic1.topic);
                        tvcont.setText(topic1.cont);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                String item = topic.getSelectedItem().toString();
                topicref.child(item).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Topic topic1 = dataSnapshot.getValue(Topic.class);
                        tvmain.setText(topic1.topic);
                        tvcont.setText(topic1.cont);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        final DatabaseReference mdref = FirebaseDatabase.getInstance().getReference().child("modes");

        mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, item);
                mdref.child(item).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Mode mode1 = dataSnapshot.getValue(Mode.class);
                        tvmain2.setText(mode1.mode);
                        tvcont2.setText(mode1.cont);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                String item = mode.getSelectedItem().toString();
                moderef.child(item).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Mode mode1 = dataSnapshot.getValue(Mode.class);
                        tvmain2.setText(mode1.mode);
                        tvcont2.setText(mode1.cont);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
