package com.jtanveer.mindvolley;

import android.os.Bundle;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jtanveer on 17/4/18.
 */

class NetworkHandler {

    static NetworkHandler instance;

    private OkHttpClient client;

    static {
        instance = new NetworkHandler();
    }

    private NetworkHandler() {
        client = new OkHttpClient();
    }

    static NetworkHandler getInstance() {
        return instance;
    }

    Response request(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return client.newCall(request).execute();
    }

    Response request(String url, Map<String, String> data) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(buildFormBody(data))
                .build();

        return client.newCall(request).execute();
    }

    private RequestBody buildFormBody(Map<String, String> data) {
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> keys = data.keySet();
        for (String key : keys) {
            builder.add(key, data.get(key));
        }
        return builder.build();
    }
}
