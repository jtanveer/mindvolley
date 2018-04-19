package com.jtanveer.mindvolley;

/**
 * Created by jtanveer on 16/4/18.
 */

public class DataVolley {

    static DataVolley instance;

    TaskManager taskManager;

    static {
        instance = new DataVolley();
    }

    private DataVolley() {
        taskManager = TaskManager.getInstance();
    }

    public static DataVolley getInstance() {
        return instance;
    }

    public <S, E> ModelMapper<S, E> modelMapper(ModelMapper<S, E> modelMapper) {
        modelMapper.setDataVolley(this);
        return modelMapper;
    }

    public void cancel(String key) {
        taskManager.cancel(key);
    }

}
