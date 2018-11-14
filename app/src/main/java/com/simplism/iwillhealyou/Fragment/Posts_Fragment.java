package com.simplism.iwillhealyou.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplism.iwillhealyou.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Posts_Fragment extends Fragment {


    public Posts_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts_, container, false);
    }

}
