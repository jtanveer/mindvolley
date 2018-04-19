package com.jtanveer.mindvolley.sample.feed;

import com.jtanveer.mindvolley.DataRequestCallback;
import com.jtanveer.mindvolley.MindVolley;
import com.jtanveer.mindvolley.ModelMapper;
import com.jtanveer.mindvolley.sample.model.FeedResponse;

import java.util.List;

/**
 * Created by jtanveer on 20/4/18.
 */

public class FeedInteractorImpl implements FeedInteractor {

    private static final String URL = "http://pastebin.com/raw/wgkJgazE";

    @Override
    public void fetchFeed(DataRequestCallback<List<FeedResponse>, Object> callback) {
        MindVolley.getInstance().getDataVolley().modelMapper(new ModelMapper<FeedResponse, Object>())
                .newRequest(FeedResponse.class, Object.class).from(URL).load(callback);
    }
}
