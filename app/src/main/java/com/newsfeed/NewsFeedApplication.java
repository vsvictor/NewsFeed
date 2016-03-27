package com.newsfeed;

import android.app.Application;
import android.content.Context;

public class NewsFeedApplication extends Application {
    private static NewsFeedApplication instance;
    public static int actionBarHeight;
    public void onCreate(){
        super.onCreate();
        instance = this;
        actionBarHeight = 0;
    }
    public static Context getContext(){
        return instance;
    }
}
