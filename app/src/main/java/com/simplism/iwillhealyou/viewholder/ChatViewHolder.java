package com.simplism.iwillhealyou.viewholder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.simplism.iwillhealyou.Chat_activity;
import com.simplism.iwillhealyou.Data.Chat;
import com.simplism.iwillhealyou.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    public TextView cont;
    public TextView rp;
    public TextView lp;

    public LinearLayout layout;
    public View lv;
    public View rv;

    public SharedPreferences msp;
    private String p;

    final String TAG = "ChatViewHolder";

    public ChatViewHolder(View itemview, Context ctx) {
        super(itemview);

        cont = (TextView) itemview.findViewById(R.id.text);
        layout = (LinearLayout) itemview.findViewById(R.id.layout);
        lv = (View) itemview.findViewById(R.id.imageViewleft);
        rv = (View) itemview.findViewById(R.id.imageViewright);

        rp = (TextView) itemview.findViewById(R.id.pright);
        lp = (TextView) itemview.findViewById(R.id.pleft);

        msp = ctx.getSharedPreferences("msp", Context.MODE_PRIVATE);
        p = msp.getString("player", null);

    }

    public void bindToChat(Chat chat) {

        Log.d(TAG, "start binding");
        cont.setText(chat.cont);

        if(chat.person.equals(p)) {
            layout.setGravity(Gravity.END);
            cont.setBackgroundResource(R.drawable.chat_bubble_right);
            rv.setVisibility(View.GONE);
            lv.setVisibility(View.GONE);
            rp.setVisibility(View.GONE);
            lp.setVisibility(View.GONE); 
        } else {
            switch (chat.person) {
                case "p1":
                    layout.setGravity(Gravity.START);
                    cont.setBackgroundResource(R.drawable.chat_bubble_left);
                    lp.setText("p1");
                    rv.setVisibility(View.GONE);
                    lv.setVisibility(View.GONE);
                    rp.setVisibility(View.GONE);
                    lp.setVisibility(View.VISIBLE);
                    break;
                case "p2":
                    layout.setGravity(Gravity.START);
                    cont.setBackgroundResource(R.drawable.chat_bubble_left);
                    lp.setText("p2");
                    rv.setVisibility(View.GONE);
                    lv.setVisibility(View.GONE);
                    rp.setVisibility(View.GONE);
                    lp.setVisibility(View.VISIBLE);
                    break;
                case "p3":
                    layout.setGravity(Gravity.START);
                    cont.setBackgroundResource(R.drawable.chat_bubble_left);
                    lp.setText("p3");
                    rv.setVisibility(View.GONE);
                    lv.setVisibility(View.GONE);
                    rp.setVisibility(View.GONE);
                    lp.setVisibility(View.VISIBLE);
                    break;
                case "p4":
                    layout.setGravity(Gravity.START);
                    cont.setBackgroundResource(R.drawable.chat_bubble_left);
                    lp.setText("p4");
                    rv.setVisibility(View.GONE);
                    lv.setVisibility(View.GONE);
                    rp.setVisibility(View.GONE);
                    lp.setVisibility(View.VISIBLE);
                    break;
            }
        }

    }
}
