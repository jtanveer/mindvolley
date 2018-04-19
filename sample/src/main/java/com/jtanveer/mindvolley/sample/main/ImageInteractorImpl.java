package com.jtanveer.mindvolley.sample.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtanveer on 19/4/18.
 */

public class ImageInteractorImpl implements ImageInteractor {

    private List<String> urls = new ArrayList<>();

    public ImageInteractorImpl() {
        urls.add("https://images.unsplash.com/photo-1507904264083-3704325da4c4?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=2ddd394489f0e825bcab9443174edf88&auto=format&fit=crop&w=960&q=80");
        urls.add("https://images.unsplash.com/photo-1506469265591-1fba55e325ab?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=bf8c37653649d073ce1dc7c86357783e&auto=format&fit=crop&w=960&q=80");
        urls.add("https://images.unsplash.com/photo-1509653183608-c9bd54cc583b?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=83061a131cc145d2c25d5f16d626f0e6&auto=format&fit=crop&w=960&q=80");
    }

    @Override
    public void fetchUrls(ImageCRUDListener listener) {
        listener.onUrlsLoaded(urls);
    }
}
