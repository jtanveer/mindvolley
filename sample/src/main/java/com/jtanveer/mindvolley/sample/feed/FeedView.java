package com.jtanveer.mindvolley.sample.feed;

import com.jtanveer.mindvolley.sample.model.FeedResponse;

import java.util.List;

/**
 * Created by jtanveer on 20/4/18.
 */

public interface FeedView {

    void showLoadingIndicator(boolean show);

    void setData(List<FeedResponse> data);

    void showErrorMessage();
}
