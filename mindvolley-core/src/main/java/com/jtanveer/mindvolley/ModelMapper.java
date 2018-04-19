package com.jtanveer.mindvolley;

/**
 * Created by jtanveer on 19/4/18.
 */

public class ModelMapper<S, E> {

    private DataVolley dataVolley;

    void setDataVolley(DataVolley dataVolley) {
        this.dataVolley = dataVolley;
    }

    public DataRequestCreator<S, E> newRequest(Class<S> success, Class<E> error) {
        return new DataRequestCreator<>(dataVolley, success, error);
    }
}
