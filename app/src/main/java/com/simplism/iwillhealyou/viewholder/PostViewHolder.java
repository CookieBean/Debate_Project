package com.simplism.iwillhealyou.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.simplism.iwillhealyou.Data.Debate;
import com.simplism.iwillhealyou.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView rm;
    public TextView p1;
    public TextView p2;
    public TextView p3;
    public TextView p4;

    public PostViewHolder(View itemview) {
        super(itemview);

        rm = (TextView)itemview.findViewById(R.id.rm);
        p1 = (TextView)itemview.findViewById(R.id.p1);
        p2 = (TextView)itemview.findViewById(R.id.p2);
        p3 = (TextView)itemview.findViewById(R.id.p3);
        p4 = (TextView)itemview.findViewById(R.id.p4);

        itemview.setTranslationZ(10);

    }

    public void bindToPost(Debate debate) {
        rm.setText(debate.rm); // 여기서 아이템 레이아웃에 글자 넣어주고

        if(debate.p1) {
            p1.setTextColor(Color.parseColor("#008BFF"));
        } else p1.setTextColor(Color.parseColor("#000000"));

        if(debate.p2) {
            p2.setTextColor(Color.parseColor("#008BFF"));
        } else p2.setTextColor(Color.parseColor("#000000"));

        if(debate.p3) {
            p3.setTextColor(Color.parseColor("#008BFF"));
        } else p3.setTextColor(Color.parseColor("#000000"));

        if(debate.p4) {
            p4.setTextColor(Color.parseColor("#008BFF"));
        } else p4.setTextColor(Color.parseColor("#000000"));

    }

}
