package com.newsfeed.loader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.newsfeed.api.ApiFactory;
import com.newsfeed.api.NewsFeedService;
import com.newsfeed.api.RetrofitCallback;
import com.newsfeed.database.NewsFeedTable;
import com.newsfeed.database.PaginationTable;
import com.newsfeed.model.CommentCount;
import com.newsfeed.model.ItemsContent;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by victor on 04.03.16.
 */
public class CommentCountLoader extends BaseLoader{
    private final NewsFeedService mNewsFeedService;

    public CommentCountLoader(Context context) {
        super(context);
        mNewsFeedService = ApiFactory.getService();
    }

    @Override
    protected void onForceLoad() {
        Call<CommentCount> call = mNewsFeedService.getCommentCount();
        call.enqueue(new RetrofitCallback<CommentCount>() {
            @Override
            public void onResponse(Response<CommentCount> response) {
                if (response.isSuccess()) {
                    String id = response.body().getID();
                    int count = response.body().getCount();
                    ContentValues values = new ContentValues();
                    values.put(NewsFeedTable.Columns.COMMENT_COUNT, count);
                    String[] args = {id};
                    getContext().getContentResolver().update(NewsFeedTable.URI,values,NewsFeedTable.Columns.ID+"=?",args);
                    String[] field = {NewsFeedTable.Columns.COMMENT_COUNT};
                    Cursor cursor = getContext().getContentResolver().query(NewsFeedTable.URI,field, NewsFeedTable.Columns.ID+"=?", args, null);
                    deliverResult(cursor);
                } else {
                    deliverResult(null);
                }
            }
        });
    }
}
