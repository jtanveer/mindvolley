package com.jtanveer.mindvolley.sample.feed;

import com.jtanveer.mindvolley.DataRequestCallback;
import com.jtanveer.mindvolley.ImageRequestCallback;
import com.jtanveer.mindvolley.sample.model.FeedResponse;

import java.util.List;

/**
 * Created by jtanveer on 20/4/18.
 */

public interface FeedInteractor {

    void fetchFeed(DataRequestCallback<List<FeedResponse>, Object> callback);
}
