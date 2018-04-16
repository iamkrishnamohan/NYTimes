package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ResponseBean implements Parcelable{

    private String title;
    private String description;
    private String date;
    private String image;

    public ResponseBean(String title, String description, String date, String image) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;
    }

    public ResponseBean() {
    }

    protected ResponseBean(Parcel in) {
        title = in.readString();
        description = in.readString();
        date = in.readString();
        image = in.readString();
    }

    public static final Creator<ResponseBean> CREATOR = new Creator<ResponseBean>() {
        @Override
        public ResponseBean createFromParcel(Parcel in) {
            return new ResponseBean(in);
        }

        @Override
        public ResponseBean[] newArray(int size) {
            return new ResponseBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(image);
    }
}
