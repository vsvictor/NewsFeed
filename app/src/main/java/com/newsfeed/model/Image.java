
package com.newsfeed.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("Photo")
    private String photoURL;
    @SerializedName("Thumb")
    private String thumbURL;
    @SerializedName("PhotoCaption")
    private String photoCaption;

    public String getPhotoURL() {
        return photoURL;
    }
    public void setPhotoURL(final String photoURL) {
        this.photoURL = photoURL;
    }
    public String getThumbURL() {
        return thumbURL;
    }
    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }
    public String getPhotoCaption() {return photoCaption;}
    public void setPhotoCaption(String photoCaption) {this.photoCaption = photoCaption;}
}
