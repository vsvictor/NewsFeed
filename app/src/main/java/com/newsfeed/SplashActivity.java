package com.newsfeed;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.newsfeed.database.NewsFeedTable;
import com.newsfeed.loader.BaseLoader;
import com.newsfeed.loader.NewsFeedLoader;
import com.newsfeed.model.NewsItem;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static SplashActivity instance;
    private View vIndian;
    private View vNews;
    private Animation animFirst;
    protected Animation animSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        instance = this;
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        vIndian = findViewById(R.id.tvIndian);
        vNews= findViewById(R.id.tvNews);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        animFirst = AnimationUtils.loadAnimation(this, R.anim.top);
        animSecond = AnimationUtils.loadAnimation(this, R.anim.bottom);
        animSecond.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                getLoaderManager().initLoader(R.id.newsfeed_loader, Bundle.EMPTY, instance);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        vIndian.startAnimation(animFirst);
        vNews.startAnimation(animSecond);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        BaseLoader loader;
        switch (id) {
            case R.id.newsfeed_loader:{loader = new NewsFeedLoader(this, "sjson");break;}
            default:{loader = null; break;}
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int id = loader.getId();
        if (id == R.id.newsfeed_loader) {
            List<NewsItem>  items = NewsFeedTable.listFromCursor(data);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
