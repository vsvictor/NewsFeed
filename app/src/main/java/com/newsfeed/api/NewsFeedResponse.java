package com.newsfeed.api;

import android.content.Context;

import com.newsfeed.database.NewsFeedTable;
import com.newsfeed.database.PaginationTable;
import com.newsfeed.model.ItemsContent;

import java.util.List;

public class NewsFeedResponse extends Response {

    @Override
    public void save(Context context) {
        ItemsContent data = getTypedAnswer();
        if (data != null) {
            PaginationTable.save(context, data.getPagination());
            NewsFeedTable.save(context, data.getData());
        }
    }
}
