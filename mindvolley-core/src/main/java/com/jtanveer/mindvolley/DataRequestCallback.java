package com.jtanveer.mindvolley;

/**
 * Created by jtanveer on 18/4/18.
 */

public interface DataRequestCallback<S, E> {

    void onSuccess(S success);

    void onError(E error);

    void onFailed();
}
