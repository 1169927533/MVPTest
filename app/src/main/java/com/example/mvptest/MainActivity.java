package com.example.mvptest;

import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mvptest.adapter.ViewpagerAdapter;
import com.example.mvptest.page.fragment.NewsFragment;
import com.example.mvptest.page.fragment.PictureFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_tablayout)
    TabLayout mainTablayout;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Slide(Gravity.LEFT));
        }
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        initview();
    }
    private void initview(){
        NewsFragment newsFragment = new NewsFragment();
        PictureFragment pictureFragment = new PictureFragment();
        fragmentArrayList.add(newsFragment);
        fragmentArrayList.add(pictureFragment);
        list.add("新闻");
        list.add("美图");
        mainTablayout.setupWithViewPager(mainViewpager);
        mainViewpager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(), fragmentArrayList, list));
    }

    /*@BindView(R.id.main_tablayout)
    TabLayout mainTablayout;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Slide(Gravity.LEFT));
        }
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        initview();
    }
    private void initview(){
        NewsFragment newsFragment = new NewsFragment();
        PictureFragment pictureFragment = new PictureFragment();
        fragmentArrayList.add(newsFragment);
        fragmentArrayList.add(pictureFragment);
        list.add("新闻");
        list.add("美图");
        mainTablayout.setupWithViewPager(mainViewpager);
        mainViewpager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager(), fragmentArrayList, list));
    }*/
}
