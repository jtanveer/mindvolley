package com.jtanveer.mindvolley;

import okhttp3.MediaType;

/**
 * Created by jtanveer on 18/4/18.
 */

class Requester {

    static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    static final MediaType JSON_PLAIN = MediaType.parse("text/plain");
    static final MediaType XML = MediaType.parse("application/xml; charset=UTF-8");
    static final MediaType XML_PLAIN = MediaType.parse("text/xml");
    static final MediaType JPG = MediaType.parse("image/jpeg");
    static final MediaType PNG = MediaType.parse("image/png");

    static final int TASK_COMPLETE = 0;

    String key;

    TaskCompleteCallback taskCompleteCallback;

    void setKey(String key) {
        this.key = key;
    }

    void setTaskCompleteCallback(TaskCompleteCallback taskCompleteCallback) {
        this.taskCompleteCallback = taskCompleteCallback;
    }

    boolean isTypeMatch(MediaType c1, MediaType c2) {
        return c1.type().equals(c2.type());
    }
}
