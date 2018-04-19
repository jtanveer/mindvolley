package com.jtanveer.mindvolley.sample.main;

import java.util.List;

/**
 * Created by jtanveer on 19/4/18.
 */

public interface ImageInteractor {

    interface ImageUrlLoadListener {
        void onUrlsLoaded(List<String> urls);
    }

    void fetchUrls(ImageUrlLoadListener listener);
}
