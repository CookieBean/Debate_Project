package com.simplism.iwillhealyou.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.simplism.iwillhealyou.Data.Chat;
import com.simplism.iwillhealyou.Data.Debate;
import com.simplism.iwillhealyou.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    public TextView cont;

    public ChatViewHolder(View itemview) {
        super(itemview);

        cont = (TextView) itemview.findViewById(R.id.text);

    }

    public void bindToPost(Chat chat) {
        cont.setText(chat.cont);
    }
}
