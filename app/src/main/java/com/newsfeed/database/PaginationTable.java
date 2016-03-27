package com.newsfeed.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.newsfeed.Constants;
import com.newsfeed.model.Pagination;

public class PaginationTable {
    private static final String TAG = "PaginationTable";
    public static final Uri URI = DBHelper.BASE_CONTENT_URI.buildUpon().appendPath(Requests.TABLE_NAME).build();

    public static void save(Context context, Pagination pagination){
        context.getContentResolver().insert(URI, toContentValues(pagination));
    }
    @NonNull
    public static ContentValues toContentValues(@NonNull Pagination pagination){
        ContentValues values = new ContentValues();
        values.put(Columns.TOTAL_PAGE, pagination.getTotalPages());
        values.put(Columns.PAGE_NO, pagination.getPageNo());
        values.put(Columns.PER_PAGE, pagination.getPerPage());
        values.put(Columns.WEB_URL, pagination.getWebURL());
        return values;
    }
    @NonNull
    public static Pagination fromCursor(@NonNull Cursor cursor){
        Pagination pagination = new Pagination();
        int totalPage = cursor.getInt(cursor.getColumnIndex(Columns.TOTAL_PAGE));
        int pageNo = cursor.getInt(cursor.getColumnIndex(Columns.PAGE_NO));
        int perPage = cursor.getInt(cursor.getColumnIndex(Columns.PER_PAGE));
        String webURL = cursor.getString(cursor.getColumnIndex(Columns.WEB_URL));
        pagination.setTotalPages(totalPage);
        pagination.setPageNo(pageNo);
        pagination.setPerPage(perPage);
        pagination.setWebURL(webURL);
        return pagination;
    }
    public static void clear(Context context){
        Log.i(TAG, URI.toString());
        context.getContentResolver().delete(URI, null, null);
    }

    public interface Columns{
        String TOTAL_PAGE = "total_page";
        String PAGE_NO = "page_no";
        String PER_PAGE = "per_page";
        String WEB_URL = "web_url";
    }
    public interface Requests{
        String TABLE_NAME = PaginationTable.class.getSimpleName();
        String CREATE_REQUEST = "CREATE TABLE "+TABLE_NAME+" ("+
                                Columns.TOTAL_PAGE+" int not null,"+
                                Columns.PAGE_NO+" int not null,"+
                                Columns.PER_PAGE+" int not null,"+
                                Columns.WEB_URL+" varchar(256));";
        String DROP_REQUEST = "DROP TABLE IF EXIST "+TABLE_NAME;
    }
}
