package com.newsfeed.api;

import com.newsfeed.Constants;
import com.newsfeed.model.CommentCount;
import com.newsfeed.model.ItemsContent;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface NewsFeedService {

    @GET(Constants.path)
    Call<ItemsContent> getFeed(@Query("feedtype") String type);
    Call<CommentCount> getCommentCount();
}
