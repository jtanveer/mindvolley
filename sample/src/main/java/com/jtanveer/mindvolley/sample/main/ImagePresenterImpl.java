package com.jtanveer.mindvolley.sample.main;

import java.util.List;

/**
 * Created by jtanveer on 19/4/18.
 */

public class ImagePresenterImpl implements ImagePresenter, ImageInteractor.ImageCRUDListener {

    private ImageInteractor imageInteractor;
    private ImageView imageView;

    public ImagePresenterImpl(ImageInteractor imageInteractor, ImageView imageView) {
        this.imageInteractor = imageInteractor;
        this.imageView = imageView;
    }

    @Override
    public void fetchImages() {
        imageView.showLoadingIndicator(true);
        imageInteractor.fetchUrls(this);
    }

    @Override
    public void onFeedClicked() {
        imageView.launchFeed();
    }

    @Override
    public void onAdapterPopulated() {
        imageView.displayPagerItem();
    }

    @Override
    public void onUrlsLoaded(List<String> urls) {
        imageView.showLoadingIndicator(false);
        imageView.setUrls(urls);
    }
}
