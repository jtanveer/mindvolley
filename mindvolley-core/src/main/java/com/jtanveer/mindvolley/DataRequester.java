package com.jtanveer.mindvolley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.jtanveer.mindvolley.Logger.log;

/**
 * Created by jtanveer on 18/4/18.
 */

class DataRequester extends Requester implements Runnable {

    private String url;
    private Bundle data;

    DataRequester(String url) {
        this.url = url;
    }

    public DataRequester(String url, Bundle data) {
        this.url = url;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            Response response;
            if (data != null) {
                response = NetworkHandler.getInstance().request(url, data);
            } else {
                response = NetworkHandler.getInstance().request(url);
            }
            MediaType contentType = response.body().contentType();
            log(contentType.toString());
            if (isTypeMatch(contentType, JSON) || isTypeMatch(contentType, JSON_PLAIN)) {
                // process JSON
            } else if (isTypeMatch(contentType, XML) || isTypeMatch(contentType, XML_PLAIN)) {
                // process XML
                throw new UnsupportedOperationException("XML is not supported yet!");
            } else {
                throw new IllegalArgumentException("Not a valid content type!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
