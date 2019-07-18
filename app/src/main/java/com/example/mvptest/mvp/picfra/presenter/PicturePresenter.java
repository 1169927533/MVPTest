package com.example.mvptest.mvp.picfra.presenter;

import com.example.mvptest.mvp.picfra.model.PicturemodelDao;
import com.example.mvptest.mvp.picfra.model.PicturemodelDaoImp;
import com.example.mvptest.mvp.picfra.view.PictureView;
import com.example.mvptest.object.PictureObj;

import java.util.List;

public class PicturePresenter {
    PictureView pictureView;
    PicturemodelDaoImp picturemodelDaoImp;
    int startnum = 1;

    public PicturePresenter(PictureView pictureView) {
        this.pictureView = pictureView;
        picturemodelDaoImp = new PicturemodelDaoImp();
    }

   public void getePictures(int endnum) {
        picturemodelDaoImp.getPictureList(startnum, endnum, new PicturemodelDao() {
            @Override
            public void showpicture(List<PictureObj> list) {
                pictureView.showPicture(list);
            }
        });
        startnum++;
    }
}
