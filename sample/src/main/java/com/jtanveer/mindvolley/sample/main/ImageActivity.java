package com.jtanveer.mindvolley.sample.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jtanveer.mindvolley.MindVolley;
import com.jtanveer.mindvolley.sample.R;
import com.jtanveer.mindvolley.sample.databinding.ActivityImageBinding;

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
        MindVolley.init(1048 * 1048 * 4);

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
        binding.progress.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setUrls(List<String> urls) {
        adapter = new ImageAdapter(getSupportFragmentManager(), urls);
        presenter.onAdapterPopulated();
    }

    @Override
    public void launchFeed() {
        // start feed activity
    }

    @Override
    public void displayPagerItem() {
        binding.pager.setAdapter(adapter);
        binding.indicator.setViewPager(binding.pager);
        binding.pager.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        // inform presenter about view clicked
        if (view.getId() == binding.fabFeed.getId()) {
            presenter.onFeedClicked();
        }
    }
}
