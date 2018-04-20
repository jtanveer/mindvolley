package com.jtanveer.mindvolley;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by jtanveer on 20/4/18.
 */
@RunWith(AndroidJUnit4.class)
public class MindVolleyTest {

    @Before
    public void init() {
        MindVolley.init();
    }

    @Test
    public void testInit() {
        assertNotEquals(null, MindVolley.getInstance());
    }

    @Test
    public void testImageVolley() {
        ImageVolley imageVolley = ImageVolley.getInstance();
        assertEquals(imageVolley, MindVolley.getInstance().getImageVolley());
    }

    @Test
    public void testDataVolley() {
        DataVolley dataVolley = DataVolley.getInstance();
        assertEquals(dataVolley, MindVolley.getInstance().getDataVolley());
    }
}
