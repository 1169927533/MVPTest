package com.example.mvptest.page.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.WindowManager;

import com.example.mvptest.R;
import com.example.mvptest.adapter.ViewpagerAdapter_Ceshi;
import com.example.mvptest.object.PictureObj;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LunBoPictureActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private int page;//页码数
    private int position;//当前图片的位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lun_bo_picture);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
        }

        page = getIntent().getIntExtra("page", 0);
        position = getIntent().getIntExtra("position", 0);
        initview();


    }

    void initview() {
        PictureObj.list.size();
        viewpager.setAdapter(new ViewpagerAdapter_Ceshi(this, PictureObj.list));
        viewpager.setCurrentItem(position);
    }
}
