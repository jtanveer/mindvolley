package com.jtanveer.mindvolley;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by jtanveer on 20/4/18.
 */
@RunWith(AndroidJUnit4.class)
public class ImageRequestOptionTest {

    @Test
    public void testDataValidity() {
        ImageRequestOption option = new ImageRequestOption(
                new ImageRequestOption.Builder("http://mock.url"));
        assertEquals(640, option.targetWidth);
        assertEquals(480, option.targetHeight);
        assertEquals(R.drawable.broken, option.fallbackImageResource);
    }
}
