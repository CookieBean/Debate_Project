package com.simplism.iwillhealyou;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simplism.iwillhealyou.Data.Debate;

public class WaitingActivity extends AppCompatActivity {

    String roomname, uid, player, topic;
    SharedPreferences msp;
    TextView tv;
    ImageView ivp1, ivp2, ivp3, ivp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        msp = getSharedPreferences("msp", MODE_PRIVATE);

        tv = (TextView) findViewById(R.id.tv);

        ivp1 = (ImageView) findViewById(R.id.p1);
        ivp2 = (ImageView) findViewById(R.id.p2);
        ivp3 = (ImageView) findViewById(R.id.p3);
        ivp4 = (ImageView) findViewById(R.id.p4);

        roomname = msp.getString("roomname", null);
        player = msp.getString("player", null);
        topic = msp.getString("topic", null);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("posts").child(roomname);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Debate debate = dataSnapshot.getValue(Debate.class);
                boolean p1 = debate.p1;
                boolean p2 = debate.p2;
                boolean p3 = debate.p3;
                boolean p4 = debate.p4;
                if(p1 && p2 && p3 && p4) {
                    Toast.makeText(getApplicationContext(), "All people entered the room. Starting Debate...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Chat_activity.class);
                    startActivity(intent);
                }
                if(p1) ivp1.setImageResource(R.drawable.ic_person_black_24dp);
                if(p2) ivp2.setImageResource(R.drawable.ic_person_black_24dp);
                if(p3) ivp3.setImageResource(R.drawable.ic_person_black_24dp);
                if(p4) ivp4.setImageResource(R.drawable.ic_person_black_24dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tv.setText("You're waiting for " + topic + " as " + player);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = msp.edit();
        editor.remove("roomname");
        editor.remove("player");

        Toast.makeText(this, "You're quiting the Current Waiting Room", Toast.LENGTH_SHORT).show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("posts").child(roomname);
        switch (player) {
            case "p1":
                ref.child("p1").setValue(false);
                ref.child("p1uid").setValue("null");
                break;
            case "p2":
                ref.child("p2").setValue(false);
                ref.child("p2uid").setValue("null");
                break;
            case "p3":
                ref.child("p2").setValue(false);
                ref.child("p2uid").setValue("null");
                break;
            case "p4":
                ref.child("p2").setValue(false);
                ref.child("p2uid").setValue("null");
                break;
        }
    }
}
