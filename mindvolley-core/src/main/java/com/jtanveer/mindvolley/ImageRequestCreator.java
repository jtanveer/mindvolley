package com.jtanveer.mindvolley;

/**
 * Created by jtanveer on 18/4/18.
 */

public class ImageRequestCreator {

    private final ImageVolley imageVolley;
    private final ImageRequestOption.Builder builder;

    ImageRequestCreator(ImageVolley imageVolley, String url) {
        this.imageVolley = imageVolley;
        this.builder = new ImageRequestOption.Builder(url);
    }

    public ImageRequestCreator targetWidht(int targetWidth) {
        builder.targetWidth(targetWidth);
        return this;
    }

    public ImageRequestCreator targetHeight(int targetHeight) {
        builder.targetWidth(targetHeight);
        return this;
    }

    public ImageRequestCreator fallbackImageResource(int fallbackImageResource) {
        builder.fallbackImageResource(fallbackImageResource);
        return this;
    }

    public String load(ImageRequestCallback callback) {
        ImageRequestOption request = builder.build();
        ImageRequester runnable = new ImageRequester(request, callback);
        return imageVolley.taskManager.enqueue(runnable);
    }
}
