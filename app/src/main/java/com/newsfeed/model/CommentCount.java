package com.newsfeed.model;

import com.google.gson.annotations.SerializedName;

public class CommentCount {
    @SerializedName("NewsItemId")
    private String id;
    @SerializedName("CommentCount")
    private int count;

    public void setID(String id){
        this.id = id;
    }
    public String getID(){
        return this.id;
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
}
