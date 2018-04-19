package com.jtanveer.mindvolley;

/**
 * Created by jtanveer on 18/4/18.
 */

class ImageRequestOption {

    String url;
    int targetWidth;
    int targetHeight;
    int fallbackImageResource;

    ImageRequestOption(Builder builder) {
        this.url = builder.url;
        this.targetWidth = builder.targetWidth;
        this.targetHeight = builder.targetHeight;
        this.fallbackImageResource = builder.fallbackImageResource;
    }

    static class Builder {

        private static final int DEFAULT_TARGET_WIDTH = 640;
        private static final int DEFAULT_TARGET_HEIGHT = 480;
        private static final int DEFAULT_FALLBACK_IMAGE = R.drawable.broken;

        String url;
        int targetWidth = DEFAULT_TARGET_WIDTH;
        int targetHeight = DEFAULT_TARGET_HEIGHT;
        int fallbackImageResource = DEFAULT_FALLBACK_IMAGE;

        Builder(String url) {
            url(url);
        }

        Builder url(String url) {
            this.url = url;
            return this;
        }

        Builder targetWidth(int targetWidth) {
            this.targetWidth = targetWidth;
            return this;
        }

        Builder targetHeight(int targetHeight) {
            this.targetHeight = targetHeight;
            return this;
        }

        Builder fallbackImageResource(int fallbackImageResource) {
            this.fallbackImageResource = fallbackImageResource;
            return this;
        }

        ImageRequestOption build() {
            return new ImageRequestOption(this);
        }
    }

}
