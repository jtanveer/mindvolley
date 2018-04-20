package com.jtanveer.mindvolley;

import android.graphics.Bitmap;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.graphics.BitmapCompat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by jtanveer on 20/4/18.
 */
@RunWith(AndroidJUnit4.class)
public class CacheManagerInstrumentedTest {

    private final String A = "{ \"name\" : \"Jamael\", \"occupation\" : \"Engineer\"}";
    private final Bitmap B = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);

    private final int CACHE_SIZE = 1048 * 1048 * 10;

    @Before
    public void init() {
        CacheManager.init(CACHE_SIZE);
    }

    @Test
    public void testInit() {
        assertNotEquals(null, CacheManager.getInstance());
    }

    @Test
    public void testInsert() {
        CacheManager cacheManager = CacheManager.getInstance();
        cacheManager.clear();
        cacheManager.setData("key1", A);

        assertEquals(A, cacheManager.getData("key1"));
    }

    @Test
    public void testSize() throws UnsupportedEncodingException {
        CacheManager cacheManager = CacheManager.getInstance();
        cacheManager.clear();
        cacheManager.setData("key1", A);
        cacheManager.setImage("key2", B);

        int totalSize = A.getBytes("UTF-8").length + BitmapCompat.getAllocationByteCount(B);
        assertEquals(totalSize, cacheManager.size());
    }
}
