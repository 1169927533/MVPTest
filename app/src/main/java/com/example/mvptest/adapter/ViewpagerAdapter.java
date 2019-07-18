package com.example.mvptest.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewpagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragements;
    private ArrayList<String> list;

    public ViewpagerAdapter(FragmentManager fm, ArrayList<Fragment> fragements, ArrayList<String> list) {
        super(fm);
        this.fragements = fragements;
        this.list = list;
    }

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return fragements.get(position);
    }

    @Override
    public int getCount() {
        return fragements.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
