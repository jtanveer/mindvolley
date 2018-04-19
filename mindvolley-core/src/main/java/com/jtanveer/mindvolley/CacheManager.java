package com.jtanveer.mindvolley;

import android.graphics.Bitmap;
import android.support.v4.graphics.BitmapCompat;
import android.util.LruCache;

import java.io.UnsupportedEncodingException;

import static com.jtanveer.mindvolley.Logger.log;

/**
 * Created by jtanveer on 17/4/18.
 */

class CacheManager {

    private static final int CACHE_SIZE = 1048 * 1048 * 2;
    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_DATA = 1;

    private static CacheManager instance;

    private final LruCache<String, Content> cache;

    private CacheManager(int maxSizeInByte) {
        cache = new LruCache<String, Content>(maxSizeInByte) {
            @Override
            protected int sizeOf(String key, Content content) {
                return content.byteCount;
            }
        };
    }

    static void init() {
        init(CACHE_SIZE);
    }

    static void init(int cacheSize) {
        if (instance == null) {
            instance = new CacheManager(cacheSize);
        }
    }

    static CacheManager getInstance() {
        return instance;
    }

    public Bitmap getImage(String key) {
        Content content = cache.get(key);
        if (content != null) {
            log("cache has the contain!");
            if (content.contentType == TYPE_IMAGE) {
                return content.bitmap;
            } else {
                throw new IllegalArgumentException("Invalid image!");
            }
        }
        return null;
    }

    public String getData(String key) {
        Content content = cache.get(key);
        if (content != null) {
            if (content.contentType == TYPE_DATA) {
                return content.data;
            } else {
                throw new IllegalArgumentException("Invalid data!");
            }
        }
        return null;
    }

    void setImage(String key, Bitmap image) {
        int byteCount = BitmapCompat.getAllocationByteCount(image);
        cache.put(key, new Content(TYPE_IMAGE, image, null, byteCount));
        log("image cached. byte count: " + byteCount);
    }

    void setData(String key, String data) {
        try {
            int byteCount = data.getBytes("UTF-8").length;
            cache.put(key, new Content(TYPE_DATA, null, data, byteCount));
            log("data cached");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    int size() {
        return cache.size();
    }

    int maxSize() {
        return cache.maxSize();
    }

    void clear() {
        cache.evictAll();
    }

    int hitCount() {
        return cache.hitCount();
    }

    int missCount() {
        return cache.missCount();
    }

    int putCount() {
        return cache.putCount();
    }

    int evictionCount() {
        return cache.evictionCount();
    }

    final class Content {
        int contentType;
        Bitmap bitmap;
        String data;
        int byteCount;

        public Content(int contentType, Bitmap bitmap, String data, int byteCount) {
            this.contentType = contentType;
            this.bitmap = bitmap;
            this.data = data;
            this.byteCount = byteCount;
        }
    }

}
