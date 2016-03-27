package com.newsfeed.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ItemsContent {

    @SerializedName("Pagination")
    private Pagination pagination;
    @SerializedName("NewsItem")
    private ArrayList<NewsItem> items;

    public ItemsContent(){
        pagination = new Pagination();
        items = new ArrayList<NewsItem>();
    }
    public ArrayList<NewsItem> getData(){return items;}
    public Pagination getPagination(){return pagination;}
}
