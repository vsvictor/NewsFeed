package com.newsfeed;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.newsfeed.database.NewsFeedTable;
import com.newsfeed.loader.BaseLoader;
import com.newsfeed.loader.CommentCountLoader;
import com.newsfeed.loader.NewsFeedLoader;
import com.newsfeed.model.NewsItem;

import java.util.List;

public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_HEADER = "header";
    public static final String ARG_ITEM_STOTY = "story";
    public static final String ARG_ITEM_PHOTO_URL = "photo_url";
    public static final String ARG_ITEM_PHOTO_CAPTION = "photo_caption";
    public static final String ARG_ITEM_COMMENT_COUNT_URL = "comment_count_url";
    public static final String ARG_ITEM_COMMENT_URL = "comment_url";
    public static final String ARG_ITEM_RELATED_URL = "related_url";

    private WebView web;
    private String story;
    private String photoURL;
    private String comment_count_url;
    private String comment_url;
    private String related;
    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getLoaderManager().initLoader(R.id.comment_count_loader, Bundle.EMPTY, this);
        story = getArguments().getString(DetailFragment.ARG_ITEM_STOTY);
        photoURL = getArguments().getString(DetailFragment.ARG_ITEM_PHOTO_URL);
        comment_count_url = getArguments().getString(DetailFragment.ARG_ITEM_COMMENT_COUNT_URL);
        comment_url = getArguments().getString(DetailFragment.ARG_ITEM_COMMENT_URL);
        related = getArguments().getString(DetailFragment.ARG_ITEM_RELATED_URL);
        Log.i("URL", comment_count_url==null?"empty":comment_count_url);
        Log.i("URL", comment_url==null?"empty":comment_url);
        Log.i("URL", related==null?"empty":related);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.one_item, container, false);
        web = (WebView) rootView.findViewById(R.id.item_detail);
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append(story);
        sb.append("</body>");
        sb.append("</html>");
        web.loadData(sb.toString(), "text/html", null);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        BaseLoader loader;
        switch (id) {
            case R.id.comment_count_loader:{loader = new CommentCountLoader(getActivity());break;}
            default:{loader = null; break;}
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int id = loader.getId();
        if (id == R.id.comment_count_loader) {
        }
        getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
