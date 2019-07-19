package com.example.mvptest.page.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvptest.R;
import com.example.mvptest.adapter.NewsAdapter;
import com.example.mvptest.dagger.component.DaggerHttpUtilComponent;
import com.example.mvptest.dagger.component.DaggerNewsFragmentComponent;
import com.example.mvptest.dagger.model.NewsFragmentModel;
import com.example.mvptest.dagger.model.NewsModelDaoImpMoudle;
import com.example.mvptest.mvp.newsfra.presenter.NewsPresenter;
import com.example.mvptest.mvp.newsfra.view.NewsView;
import com.example.mvptest.object.NewsObj;
import com.example.mvptest.object.Weather;
import com.example.mvptest.util.okhttp分装.my.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment implements NewsView {

    View view;
    @BindView(R.id.weather)
    TextView weather;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.aqi)
    TextView aqi;
    @BindView(R.id.fra_recycleview)
    RecyclerView fraRecycleview;

    NewsAdapter newsAdapter;//适配器

    List<NewsObj> newsObjList;//新闻数据


    @Inject
    NewsPresenter newsPresenter ;//presenter层


    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        DaggerNewsFragmentComponent.builder().newsFragmentModel(new NewsFragmentModel(this)).build().inject(this);

        initdata();
        return view;
    }

    int lastVisibleItem;

    public void initdata() {
        newsObjList = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(), newsObjList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        fraRecycleview.setLayoutManager(layoutManager);
        fraRecycleview.setAdapter(newsAdapter);

        fraRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == newsAdapter.getItemCount()) {
                    //判断是不是滑到了最后一项
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newsPresenter.getNews(5);
                            newsAdapter.changeMoreStatus(NewsAdapter.PULLUP_LOAD_MORE);
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


        newsPresenter.getNews(5);
        newsPresenter.getWeather("杭州");

        newsAdapter.setItemClick(new NewsAdapter.ItemClick() {
            @Override
            public void itemclick(int position) {
               /* Intent intent = new Intent(getContext(), NewsInfoActivity.class);
                intent.putExtra("info", newsObjList.get(position).getPath());
                startActivity(intent);*/
            }
        });
    }

    @Override
    public void show_news(List<NewsObj> list) {
        int size = newsObjList.size();
        newsObjList.addAll(list);
        newsAdapter.notifyItemRangeChanged(size, newsObjList.size());
        newsAdapter.changeMoreStatus(NewsAdapter.PULLUP_LOAD_MORE);
    }

    @Override
    public void show_weather(Weather weathers) {
        weather.setText(weathers.getType());
        aqi.setText(weathers.getApi());
        temperature.setText("  " + weathers.getMint() + "-" + weathers.getMaxt());
    }
}
