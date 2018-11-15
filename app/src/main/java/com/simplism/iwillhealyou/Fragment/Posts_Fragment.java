package com.simplism.iwillhealyou.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.simplism.iwillhealyou.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Posts_Fragment extends MainFragment {


    public Posts_Fragment() {
        // Required empty public constructor
    } // 얘가 두번째 프래그먼트


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts_, container, false);
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_posts_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentPostsQuery = databaseReference.child("weekly")
                .limitToFirst(100);
        // [END recent_posts_query]

        return recentPostsQuery;
    }

}
