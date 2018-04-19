package com.jtanveer.mindvolley.sample.feed;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.jtanveer.mindvolley.DataRequestCallback;
import com.jtanveer.mindvolley.sample.R;
import com.jtanveer.mindvolley.sample.databinding.ActivityFeedBinding;
import com.jtanveer.mindvolley.sample.model.FeedResponse;

import java.util.List;

/**
 * Created by jtanveer on 20/4/18.
 */

public class FeedActivity extends AppCompatActivity implements FeedView,
        DataRequestCallback<List<FeedResponse>, Object> {

    private ActivityFeedBinding binding;
    private FeedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);

        setSupportActionBar(binding.toolbar);
        setTitle(R.string.title_activity_feed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setup views here
        binding.rvFeed.setLayoutManager(new LinearLayoutManager(this));

        presenter = new FeedPresenterImpl(new FeedInteractorImpl(), this);
        presenter.fetchFeed(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void setData(List<FeedResponse> data) {
        binding.setData(data);
        binding.rvFeed.setAdapter(new FeedAdapter(data));
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(binding.rvFeed, "Feed could not be loaded due to an error", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onSuccess(List<FeedResponse> data) {
        presenter.onFeedFetched(data);
    }

    @Override
    public void onError(Object error) {
        presenter.onFeedError(error);
    }

    @Override
    public void onFailed() {
        presenter.onFailed();
    }
}
