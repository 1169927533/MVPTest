package com.example.mvptest.util.okhttp分装.my;

public interface Comback {
    void success_back(String content);
    void error_back();
}
/*
    String url = Net.news_Netease + "/getWangYiNews?page=" + start_num + "&count=" + end_num;
        HttpUtil.getInstance().get(url,new dsa());
class dsa implements Comback{
    @Override
    public void success_back(String content) {
        System.out.println(content);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(content);
            if (jsonObject.getString("code").equals("200")) {
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    NewsObj newsObj = gson.fromJson(jsonArray.get(i).toString(), NewsObj.class);
                    newsObjslist.add(newsObj);
                }

                newsPresenter.getNews(newsObjslist);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error_back() {

    }
}*/
