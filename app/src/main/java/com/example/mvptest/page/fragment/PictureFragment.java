package com.example.mvptest.page.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvptest.R;
import com.example.mvptest.adapter.NewsAdapter;
import com.example.mvptest.adapter.PictureAdapter;
import com.example.mvptest.mvp.picfra.presenter.PicturePresenter;
import com.example.mvptest.mvp.picfra.view.PictureView;
import com.example.mvptest.object.PictureObj;
import com.example.mvptest.page.activity.LunBoPictureActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends Fragment implements PictureView {


    @BindView(R.id.fra_recycleview)
    RecyclerView fraRecycleview;
    View view;
    PictureAdapter pictureAdapter;
    int lastVisibleItem;//最后一项显示数据
    PicturePresenter picturePresenter = new PicturePresenter(this);
    public PictureFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_picture, container, false);
        ButterKnife.bind(this, view);
        initdata();
        return view;
    }

    void initdata() {
        pictureAdapter = new PictureAdapter(getContext(), PictureObj.list);
        fraRecycleview.setAdapter(pictureAdapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);

        fraRecycleview.setLayoutManager(layoutManager);


        picturePresenter.getePictures(16);

        pictureAdapter.setItemClick(new PictureAdapter.ItemClick() {
            @Override
            public void itemclick(int position,View vv) {
                Intent intent = new Intent(getContext(), LunBoPictureActivity.class);
                intent.putExtra("page", position / 16 + 1);//这张图片在的页码位置
                intent.putExtra("position", position);//这张图片在的位置
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), vv, "shareonw");
                startActivity(intent, optionsCompat.toBundle());
            }

        });

        fraRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == pictureAdapter.getItemCount()) {
                    //判断是不是滑到了最后一项
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            picturePresenter.getePictures(16);
                            pictureAdapter.changeMoreStatus(NewsAdapter.PULLUP_LOAD_MORE);
                        }
                    }, 1000);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void showPicture(List<PictureObj> list) {
        int size = PictureObj.list.size();
        PictureObj.list.addAll(list);
        pictureAdapter.notifyItemRangeChanged(size, PictureObj.list.size());
        pictureAdapter.changeMoreStatus(NewsAdapter.PULLUP_LOAD_MORE);
    }
}
