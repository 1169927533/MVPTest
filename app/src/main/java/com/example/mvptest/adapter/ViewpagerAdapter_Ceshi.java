package com.example.mvptest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.mvptest.R;
import com.example.mvptest.object.PictureObj;


import java.util.List;

public class ViewpagerAdapter_Ceshi extends PagerAdapter {
    private Context mContext;
    private List<PictureObj> mData;

    public ViewpagerAdapter_Ceshi(Context context, List<PictureObj> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = View.inflate(mContext, R.layout.item_picture, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.viewpage_image);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(800,
                800);//两个400分别为添加图片的大小
        imageView.setLayoutParams(params);

        Glide.with(mContext).load(mData.get(position).getImg()).into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错

         container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
