package com.jtanveer.mindvolley;

/**
 * Created by jtanveer on 16/4/18.
 */

public class ImageVolley {

    static ImageVolley instance;

    TaskManager taskManager;

    static {
        instance = new ImageVolley();
    }

    private ImageVolley() {
        taskManager = TaskManager.getInstance();
    }

    public static ImageVolley getInstance() {
        return instance;
    }

    public ImageRequestCreator from(String url) {
        return new ImageRequestCreator(this, url);
    }

    public void cancel(String key) {
        taskManager.cancel(key);
    }

}
