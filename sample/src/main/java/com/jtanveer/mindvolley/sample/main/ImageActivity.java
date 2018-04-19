package com.jtanveer.mindvolley.sample.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jtanveer.mindvolley.MindVolley;
import com.jtanveer.mindvolley.sample.R;
import com.jtanveer.mindvolley.sample.databinding.ActivityImageBinding;
import com.jtanveer.mindvolley.sample.feed.FeedActivity;

import java.util.List;

/**
 * Created by jtanveer on 19/4/18.
 */

public class ImageActivity extends AppCompatActivity implements ImageView, View.OnClickListener {

    private ActivityImageBinding binding;
    private ImagePresenter presenter;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MindVolley.init(1048 * 1048 * 10);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_image);

        setSupportActionBar(binding.toolbar);
        setTitle(R.string.title_activity_main);

        // setup views here
        binding.fabFeed.setOnClickListener(this);

        presenter = new ImagePresenterImpl(new ImageInteractorImpl(), this);
        presenter.fetchImages();
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        if (show) {
            binding.progress.show();
        } else {
            binding.progress.hide();
        }
    }

    @Override
    public void setUrls(List<String> urls) {
        adapter = new ImageAdapter(getSupportFragmentManager(), urls);
        presenter.onAdapterPopulated();
    }

    @Override
    public void launchFeed() {
        // start feed activity
        startActivity(new Intent(this, FeedActivity.class));
    }

    @Override
    public void displayPagerItem() {
        binding.pager.setAdapter(adapter);
        binding.pager.setOffscreenPageLimit(2);
        binding.indicator.setViewPager(binding.pager);
    }

    @Override
    public void onClick(View view) {
        // inform presenter about view clicked
        if (view.getId() == binding.fabFeed.getId()) {
            presenter.onFeedClicked();
        }
    }
}
