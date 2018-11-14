package com.simplism.iwillhealyou.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.simplism.iwillhealyou.Fragment.Debate_List_Fragment;
import com.simplism.iwillhealyou.Fragment.Posts_Fragment;
import com.simplism.iwillhealyou.Fragment.Profile_Fragment;
import com.simplism.iwillhealyou.Fragment.MainFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_SUB01 = 0;
    private static final int FRAGMENT_SUB02 = 1;
    private static final int FRAGMENT_SUB03 = 2;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case FRAGMENT_SUB01:
                return new Debate_List_Fragment();
            case FRAGMENT_SUB02:
                return new Posts_Fragment();
            case FRAGMENT_SUB03:
                return new Profile_Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case FRAGMENT_SUB01:
                return "Debate Rooms";
            case FRAGMENT_SUB02:
                return "Moderator Rooms";
            case FRAGMENT_SUB03:
                return "Profile";
            default:
                return null;
        }
    }

}
