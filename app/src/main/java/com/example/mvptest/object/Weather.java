package com.example.mvptest.object;

import android.os.Parcel;
import android.os.Parcelable;

public class Weather implements Parcelable {
    private String city,api,maxt,mint,type;

    public Weather(String city, String api, String maxt, String mint,String type) {
        this.city = city;
        this.api = api;
        this.maxt = maxt;
        this.mint = mint;
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getMaxt() {
        return maxt;
    }

    public void setMaxt(String maxt) {
        this.maxt = maxt;
    }

    public String getMint() {
        return mint;
    }

    public void setMint(String mint) {
        this.mint = mint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
