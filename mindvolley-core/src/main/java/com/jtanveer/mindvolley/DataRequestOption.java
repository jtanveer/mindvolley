package com.jtanveer.mindvolley;

import java.util.Map;

/**
 * Created by jtanveer on 18/4/18.
 */

class DataRequestOption<S, E> {

    String url;
    Map<String, String> fieldParams;
    Class<S> success;
    Class<E> error;


    DataRequestOption(Builder<S, E> builder) {
        this.url = builder.url;
        this.fieldParams = builder.fieldParams;
        this.success = builder.success;
        this.error = builder.error;
    }

    static class Builder<S, E> {

        String url;
        Map<String, String> fieldParams;
        Class<S> success;
        Class<E> error;

        Builder(Class<S> success, Class<E> error) {
            success(success);
            error(error);
        }

        Builder<S, E> url(String url) {
            this.url = url;
            return this;
        }

        Builder<S, E> fieldParams(Map<String, String> fieldParams) {
            this.fieldParams = fieldParams;
            return this;
        }

        Builder<S, E> success(Class<S> success) {
            this.success = success;
            return this;
        }

        Builder<S, E> error(Class<E> error) {
            this.error = error;
            return this;
        }

        DataRequestOption<S, E> build() {
            return new DataRequestOption<>(this);
        }
    }

}
