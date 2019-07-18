package com.example.mvptest.object;

public class NewsObj {
    private String path ;//新闻对应的网址
    private String image ;//新闻的图片
    private String title ;//新闻对应的标题
    private String passtime ;//新闻时间

    public NewsObj(String path, String image, String title, String passtime) {
        this.path = path;
        this.image = image;
        this.title = title;
        this.passtime = passtime;
    }

    public NewsObj() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPasstime() {
        return passtime;
    }

    public void setPasstime(String passtime) {
        this.passtime = passtime;
    }
}
