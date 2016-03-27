package com.newsfeed.loader;

import android.content.Context;
import android.database.Cursor;

import com.newsfeed.api.ApiFactory;
import com.newsfeed.api.NewsFeedService;
import com.newsfeed.api.RetrofitCallback;
import com.newsfeed.database.NewsFeedTable;
import com.newsfeed.database.PaginationTable;
import com.newsfeed.model.ItemsContent;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Response;

public class NewsFeedLoader extends BaseLoader {

    private final String mType;

    private final NewsFeedService mNewsFeedService;

    public NewsFeedLoader(Context context, String type) {
        super(context);
        mType = type;
        mNewsFeedService = ApiFactory.getService();
    }

    @Override
    protected void onForceLoad() {
        Call<ItemsContent> call = mNewsFeedService.getFeed(mType);
        call.enqueue(new RetrofitCallback<ItemsContent>() {
            @Override
            public void onResponse(Response<ItemsContent> response) {
                if (response.isSuccess()) {
                    PaginationTable.clear(getContext());
                    PaginationTable.save(getContext(), response.body().getPagination());
                    NewsFeedTable.clear(getContext());
                    NewsFeedTable.save(getContext(),response.body().getData());
                    Cursor cursor = getContext().getContentResolver().query(NewsFeedTable.URI,null, null, null, null);
                    deliverResult(cursor);
                } else {
                    deliverResult(null);
                }
            }
        });

    }
}
