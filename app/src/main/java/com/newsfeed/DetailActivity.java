package com.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivPhoto;
    private TextView tvCaption;
    private Toolbar toolbar;
    private AppBarLayout appBar;

    private String actHeader;
    private String ph;
    private String caption;
    private int actBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                int l = Math.abs(verticalOffset);
                Log.i("LAY", ""+l/4);
                Log.i("CON", ""+NewsFeedApplication.actionBarHeight);
                if((l-(NewsFeedApplication.actionBarHeight*2))>0){
                    tvCaption.setVisibility(View.INVISIBLE);
                    ivPhoto.setVisibility(View.INVISIBLE);
                }
                else{
                    tvCaption.setVisibility(View.VISIBLE);
                    ivPhoto.setVisibility(View.VISIBLE);
                }

            }
        });
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        tvCaption = (TextView) findViewById(R.id.tvCaption);

        actHeader = getIntent().getExtras().getString(DetailFragment.ARG_ITEM_HEADER);
        ph = getIntent().getStringExtra(DetailFragment.ARG_ITEM_PHOTO_URL);
        caption = getIntent().getStringExtra(DetailFragment.ARG_ITEM_PHOTO_CAPTION);
        if(ph != null) {
            appBar.setExpanded(true);
            Picasso.with(this).load(ph).into(ivPhoto);
        }
        else{
            appBar.setExpanded(false);
        }
        if(caption != null) {
            tvCaption.setText(caption);
        }

        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        toolbar.setTitle(actHeader);
        setSupportActionBar(toolbar);
        actBarHeight = toolbar.getHeight();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(DetailFragment.ARG_ITEM_ID,getIntent().getStringExtra(DetailFragment.ARG_ITEM_ID));
            arguments.putString(DetailFragment.ARG_ITEM_HEADER, getIntent().getStringExtra(DetailFragment.ARG_ITEM_HEADER));
            arguments.putString(DetailFragment.ARG_ITEM_STOTY, getIntent().getStringExtra(DetailFragment.ARG_ITEM_STOTY));
            arguments.putString(DetailFragment.ARG_ITEM_PHOTO_URL, getIntent().getStringExtra(DetailFragment.ARG_ITEM_PHOTO_URL));
            arguments.putString(DetailFragment.ARG_ITEM_PHOTO_CAPTION, getIntent().getStringExtra(DetailFragment.ARG_ITEM_PHOTO_CAPTION));
            arguments.putString(DetailFragment.ARG_ITEM_COMMENT_COUNT_URL, getIntent().getStringExtra(DetailFragment.ARG_ITEM_COMMENT_COUNT_URL));
            arguments.putString(DetailFragment.ARG_ITEM_COMMENT_URL, getIntent().getStringExtra(DetailFragment.ARG_ITEM_COMMENT_URL));
            arguments.putString(DetailFragment.ARG_ITEM_RELATED_URL, getIntent().getStringExtra(DetailFragment.ARG_ITEM_RELATED_URL));

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container, fragment).commit();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
