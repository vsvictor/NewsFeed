package com.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsfeed.database.NewsFeedTable;
import com.newsfeed.loader.BaseLoader;
import com.newsfeed.loader.NewsFeedLoader;
import com.newsfeed.model.NewsItem;
import com.newsfeed.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static MainActivity instance;
    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        NewsFeedApplication.actionBarHeight = toolbar.getLayoutParams().height;

        getLoaderManager().initLoader(R.id.db_feed_loader, Bundle.EMPTY, this);

        recyclerView = (RecyclerView) findViewById(R.id.item_list);
        assert recyclerView != null;
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        CursorLoader loader;
        switch (id) {
            case R.id.db_feed_loader:{loader = new CursorLoader(this, NewsFeedTable.URI,null,null,null,null);break;}
            default:{loader = null; break;}
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int id = loader.getId();
        if (id == R.id.db_feed_loader) {
            List<NewsItem>  items = NewsFeedTable.listFromCursor(data);
            //startActivity(new Intent(this, MainActivity.class));
            adapter.setList(items);
        }
        getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }

    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

        private List<NewsItem> mValues;
        Typeface fontHeader;
        Typeface fontDateAndAgency;
        Typeface fontCaption;
        public MainAdapter() {
            mValues = new ArrayList<NewsItem>();
            fontHeader = Typeface.createFromAsset(instance.getAssets(), "fonts/AlegreyaSans-Bold.ttf");
            fontDateAndAgency = Typeface.createFromAsset(instance.getAssets(), "fonts/Exo2-LightItalic.ttf");
            fontCaption = Typeface.createFromAsset(instance.getAssets(), "fonts/NotoSerif-Regular.ttf");
        }
        public MainAdapter(List<NewsItem> items) {
            mValues = items;
            fontHeader = Typeface.createFromAsset(instance.getAssets(), "fonts/AlegreyaSans-Bold.ttf");
            fontDateAndAgency = Typeface.createFromAsset(instance.getAssets(), "fonts/Exo2-LightItalic.ttf");
            fontCaption = Typeface.createFromAsset(instance.getAssets(), "fonts/NotoSerif-Regular.ttf");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            holder.mItem = mValues.get(position);
            Picasso.with(getApplicationContext()).load(mValues.get(position).getImage().getThumbURL()).into(holder.ivThumb);
            holder.tvHeadLine.setText(mValues.get(position).getHeadLine());
            holder.tvAgency.setText(mValues.get(position).getAgency()+", ");
            holder.tvDate.setText(mValues.get(position).getDateLine());
            holder.tvCaption.setText(mValues.get(position).getCaption());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(DetailFragment.ARG_ITEM_ID, holder.mItem.getID());
                        arguments.putString(DetailFragment.ARG_ITEM_HEADER, holder.mItem.getHeadLine());
                        arguments.putString(DetailFragment.ARG_ITEM_STOTY, holder.mItem.getStory());
                        arguments.putString(DetailFragment.ARG_ITEM_PHOTO_URL, holder.mItem.getImage().getPhotoURL());
                        arguments.putString(DetailFragment.ARG_ITEM_PHOTO_CAPTION, holder.mItem.getImage().getPhotoCaption());
                        arguments.putString(DetailFragment.ARG_ITEM_COMMENT_COUNT_URL, holder.mItem.getCommentCountUrl());
                        arguments.putString(DetailFragment.ARG_ITEM_COMMENT_URL, holder.mItem.getCommentFeedUrl());
                        arguments.putString(DetailFragment.ARG_ITEM_RELATED_URL, holder.mItem.getRelated());
                        DetailFragment fragment = new DetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra(DetailFragment.ARG_ITEM_ID, holder.mItem.getID());
                        intent.putExtra(DetailFragment.ARG_ITEM_HEADER, holder.mItem.getHeadLine());
                        intent.putExtra(DetailFragment.ARG_ITEM_STOTY, holder.mItem.getStory());
                        intent.putExtra(DetailFragment.ARG_ITEM_PHOTO_URL, holder.mItem.getImage().getPhotoURL());
                        intent.putExtra(DetailFragment.ARG_ITEM_PHOTO_CAPTION, holder.mItem.getImage().getPhotoCaption());
                        intent.putExtra(DetailFragment.ARG_ITEM_COMMENT_COUNT_URL, holder.mItem.getCommentCountUrl());
                        intent.putExtra(DetailFragment.ARG_ITEM_COMMENT_URL, holder.mItem.getCommentFeedUrl());
                        intent.putExtra(DetailFragment.ARG_ITEM_RELATED_URL, holder.mItem.getRelated());
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public void setList(List<NewsItem> newList){
            mValues = newList;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public ImageView ivThumb;
            public TextView tvHeadLine;
            public TextView tvAgency;
            public TextView tvDate;
            public TextView tvCaption;
            public NewsItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                ivThumb = (ImageView) view.findViewById(R.id.ivThumb);
                tvHeadLine = (TextView) view.findViewById(R.id.tvHeadLine);
                tvHeadLine.setTypeface(fontHeader);
                tvAgency = (TextView) view.findViewById(R.id.tvAgency);
                tvAgency.setTypeface(fontDateAndAgency);
                tvDate = (TextView) view.findViewById(R.id.tvDate);
                tvDate.setTypeface(fontDateAndAgency);
                tvCaption = (TextView) view.findViewById(R.id.tvCaption);
                tvCaption.setTypeface(fontCaption);
            }
/*
            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
*/
        }
    }
}
