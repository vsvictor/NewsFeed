package com.newsfeed.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.newsfeed.Constants;
import com.newsfeed.model.NewsItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsFeedTable {
    public static final Uri URI = DBHelper.BASE_CONTENT_URI.buildUpon().appendPath(Requests.TABLE_NAME).build();

    public static void save(Context context, NewsItem news){
        context.getContentResolver().insert(URI, toContentValues(news));
    }
    public static void save(Context context, @NonNull List<NewsItem> news){
        ContentValues[] values = new ContentValues[news.size()];
        for(int i = 0; i<news.size();i++){
            values[i] = toContentValues(news.get(i));
        }
        context.getContentResolver().bulkInsert(URI, values);
    }
    @NonNull
    public static ContentValues toContentValues(@NonNull NewsItem news){
        ContentValues values = new ContentValues();
        values.put(Columns.ID, news.getID());
        values.put(Columns.HEAD_LINE, news.getHeadLine());
        values.put(Columns.BY_LINE, news.getByLine());
        values.put(Columns.AGENCY, news.getAgency());
        values.put(Columns.DATE_LINE, news.getDateLine());
        values.put(Columns.WEB_URL, news.getWebURL());
        values.put(Columns.CAPTION, news.getCaption());
        values.put(Columns.PHOTO_URL, news.getImage().getPhotoURL());
        values.put(Columns.THUMB_URL, news.getImage().getThumbURL());
        values.put(Columns.PHOTO_CAPTION, news.getImage().getPhotoCaption());
        values.put(Columns.KEYWORDS, news.getKeywords());
        values.put(Columns.STORY, news.getStory());
        values.put(Columns.COMMENT_COUNT_URL, news.getCommentCountUrl());
        values.put(Columns.COMMENT_COUNT, news.getCommentCount());
        values.put(Columns.COMMENT_FEED_URL, news.getCommentFeedUrl());
        values.put(Columns.RELATED, news.getRelated());
        return values;
    }
    @NonNull
    public static NewsItem fromCursor(@NonNull Cursor cursor){
        NewsItem value = new NewsItem();
        value.setID(cursor.getString(cursor.getColumnIndex(Columns.ID)));
        value.setHeadLine(cursor.getString(cursor.getColumnIndex(Columns.HEAD_LINE)));
        value.setByLine(cursor.getString(cursor.getColumnIndex(Columns.BY_LINE)));
        value.setAgency(cursor.getString(cursor.getColumnIndex(Columns.AGENCY)));
        value.setDateLine(cursor.getString(cursor.getColumnIndex(Columns.DATE_LINE)));
        value.setWebURL(cursor.getString(cursor.getColumnIndex(Columns.WEB_URL)));
        value.setCaption(cursor.getString(cursor.getColumnIndex(Columns.CAPTION)));
        value.getImage().setPhotoURL(cursor.getString(cursor.getColumnIndex(Columns.PHOTO_URL)));
        value.getImage().setThumbURL(cursor.getString(cursor.getColumnIndex(Columns.THUMB_URL)));
        value.getImage().setPhotoCaption(cursor.getString(cursor.getColumnIndex(Columns.PHOTO_CAPTION)));
        value.setKeywords(cursor.getString(cursor.getColumnIndex(Columns.KEYWORDS)));
        value.setStory(cursor.getString(cursor.getColumnIndex(Columns.STORY)));
        value.setCommentCountUrl(cursor.getString(cursor.getColumnIndex(Columns.COMMENT_COUNT_URL)));
        value.setCommentCount(cursor.getInt(cursor.getColumnIndex(Columns.COMMENT_COUNT)));
        value.setCommentFeedUrl(cursor.getString(cursor.getColumnIndex(Columns.COMMENT_FEED_URL)));
        value.setRelated(cursor.getString(cursor.getColumnIndex(Columns.RELATED)));
        return value;
    }
    @NonNull
    public static List<NewsItem> listFromCursor(@NonNull Cursor cursor){
        List<NewsItem> values = new ArrayList<NewsItem>();
        if(!cursor.moveToFirst()){
            return values;
        }
        try {
            do {
                values.add(fromCursor(cursor));
            }while (cursor.moveToNext());
        }finally {
            cursor.close();
        }
        return values;
    }
    public static void clear(Context context){
        context.getContentResolver().delete(URI, null, null);
    }
    public interface Columns{
        String ID = "id";
        String HEAD_LINE = "head_line";
        String BY_LINE = "by_line";
        String AGENCY = "agency";
        String DATE_LINE = "date_line";
        String WEB_URL = "web_url";
        String CAPTION = "caption";
        String PHOTO_URL = "photo_url";
        //String PHOTO = "photo";
        String THUMB_URL = "thumb_url";
        //String THUMB = "thumb";
        String PHOTO_CAPTION = "photo_caption";
        String KEYWORDS = "keywords";
        String STORY = "story";
        String COMMENT_COUNT_URL = "comment_count_url";
        String COMMENT_COUNT = "comment_count";
        String COMMENT_FEED_URL = "comment_feed_url";
        String RELATED = "related";
    }
    public interface Requests{
        String TABLE_NAME = NewsFeedTable.class.getSimpleName();
        String CREATE_REQUEST = "CREATE TABLE "+TABLE_NAME+" ("+
                Columns.ID+" int not null,"+
                Columns.HEAD_LINE+" varchar(128),"+
                Columns.BY_LINE+" varchar(64),"+
                Columns.AGENCY+" varchar(128),"+
                Columns.DATE_LINE+" varchar(12),"+
                Columns.WEB_URL+" varchar(128),"+
                Columns.CAPTION +" varchar(256),"+
                //Columns.PHOTO+" blob,"+
                Columns.PHOTO_URL+" varchar(256),"+
                //Columns.THUMB+" blob,"+
                Columns.THUMB_URL+" varchar(256),"+
                Columns.PHOTO_CAPTION+" varchar(256),"+
                Columns.KEYWORDS+" varchar(256),"+
                Columns.STORY+" varchar(1024),"+
                Columns.COMMENT_COUNT_URL+" varchar(256),"+
                Columns.COMMENT_COUNT+" int default 0,"+
                Columns.COMMENT_FEED_URL+" varchar(256),"+
                Columns.RELATED+" varchar(256));";
        String DROP_REQUEST = "DROP TABLE IF EXIST "+TABLE_NAME;
    }
}
