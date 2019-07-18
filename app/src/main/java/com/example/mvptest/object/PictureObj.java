package com.example.mvptest.object;

import java.util.ArrayList;
import java.util.List;

public class PictureObj {
    private String id;
    private String time;
    private String img;

    public static List<PictureObj> list = new ArrayList<>();

    public PictureObj(String id, String time, String img) {
        this.id = id;
        this.time = time;
        this.img = img;
    }

    public PictureObj() {

    }
    public PictureObj(String img) {
        this.img = img;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
