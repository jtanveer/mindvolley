package com.jtanveer.mindvolley;

/**
 * Created by jtanveer on 16/4/18.
 */

public class MindVolley {

    private static MindVolley instance;

    public static void init() {
        if (instance == null) {
            instance = new MindVolley();
        }
    }

    public static void init(int cacheSize) {
        if (instance == null) {
            instance = new MindVolley(cacheSize);
        }
    }

    private MindVolley() {
        CacheManager.init();
    }

    private MindVolley(int cacheSize) {
        CacheManager.init(cacheSize);
    }

    public static MindVolley getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MindVolley.init() needs to be called first!");
        }
        return instance;
    }

    public ImageVolley getImageVolley() {
        return ImageVolley.getInstance();
    }

    public DataVolley getDataVolley() {
        return null;
    }
}
