package com.jtanveer.mindvolley.sample.main;

import java.util.List;

/**
 * Created by jtanveer on 19/4/18.
 */

public interface ImageView {

    void showLoadingIndicator(boolean show);

    void setUrls(List<String> urls);

    void launchFeed();

    void displayPagerItem();
}
