package com.jtanveer.mindvolley.sample.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtanveer on 19/4/18.
 */

public class ImageAdapter extends FragmentStatePagerAdapter {

    private List<String> urls;

    public ImageAdapter(FragmentManager fm, List<String> urls) {
        super(fm);
        this.urls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageItemFragment.getInstance(urls.get(position));
    }

    @Override
    public int getCount() {
        return urls.size();
    }
}
