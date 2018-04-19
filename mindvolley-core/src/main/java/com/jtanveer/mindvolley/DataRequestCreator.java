package com.jtanveer.mindvolley;

import java.util.Map;

/**
 * Created by jtanveer on 18/4/18.
 */

public class DataRequestCreator<S, E> {

    private final DataVolley dataVolley;
    private final DataRequestOption.Builder<S, E> builder;

    DataRequestCreator(DataVolley dataVolley, Class<S> success, Class<E> error) {
        this.dataVolley = dataVolley;
        this.builder = new DataRequestOption.Builder<>(success, error);
    }

    public DataRequestCreator<S, E> from(String url) {
        builder.url(url);
        return this;
    }

    public DataRequestCreator<S, E> fieldParams(Map<String, String> fieldParams) {
        builder.fieldParams(fieldParams);
        return this;
    }

    public DataRequestCreator<S, E> success(Class<S> success) {
        builder.success(success);
        return this;
    }

    public DataRequestCreator<S, E> error(Class<E> error) {
        builder.error(error);
        return this;
    }

    public <A, B> String load(DataRequestCallback<A, B> callback) {
        DataRequestOption<S, E> request = builder.build();
        DataRequester<S, E, A, B> runnable = new DataRequester<>(request, callback);
        return dataVolley.taskManager.enqueue(runnable);
    }
}
