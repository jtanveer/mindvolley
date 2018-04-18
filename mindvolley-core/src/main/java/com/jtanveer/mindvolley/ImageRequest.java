package com.jtanveer.mindvolley;

/**
 * Created by jtanveer on 18/4/18.
 */

class ImageRequest {

    String url;
    int targetWidth;
    int targetHeight;

    ImageRequest(Builder builder) {
        this.url = builder.url;
        this.targetWidth = builder.targetWidth;
        this.targetHeight = builder.targetHeight;
    }

    static class Builder {
        String url;
        int targetWidth;
        int targetHeight;

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

        ImageRequest build() {
            return new ImageRequest(this);
        }
    }

}
