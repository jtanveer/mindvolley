package com.jtanveer.mindvolley.sample.feed;

import com.jtanveer.mindvolley.DataRequestCallback;
import com.jtanveer.mindvolley.sample.model.FeedResponse;

import java.util.List;

/**
 * Created by jtanveer on 20/4/18.
 */

public class FeedPresenterImpl implements FeedPresenter {

    private FeedInteractor feedInteractor;
    private FeedView feedView;

    public FeedPresenterImpl(FeedInteractor feedInteractor, FeedView feedView) {
        this.feedInteractor = feedInteractor;
        this.feedView = feedView;
    }

    @Override
    public void fetchFeed(DataRequestCallback<List<FeedResponse>, Object> callback) {
        feedView.showLoadingIndicator(true);
        feedInteractor.fetchFeed(callback);
    }

    @Override
    public void onFeedFetched(List<FeedResponse> data) {
        feedView.showLoadingIndicator(false);
        feedView.setData(data);
    }

    @Override
    public void onFeedError(Object obj) {
        feedView.showLoadingIndicator(false);
        feedView.showErrorMessage();
    }

    @Override
    public void onFailed() {
        feedView.showLoadingIndicator(false);
        feedView.showErrorMessage();
    }
}
