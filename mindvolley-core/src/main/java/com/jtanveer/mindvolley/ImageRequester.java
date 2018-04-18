package com.jtanveer.mindvolley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.jtanveer.mindvolley.Logger.log;

/**
 * Created by jtanveer on 18/4/18.
 */

class ImageRequester extends Requester implements Runnable {

    private String url;
    private ImageRequestCallback requestCallback;
    private CacheManager cacheManager;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case TASK_COMPLETE:
                    Bitmap bitmap = (Bitmap) message.obj;
                    requestCallback.onImageLoaded(bitmap);
                    break;
                default:
                    super.handleMessage(message);
            }
        }
    };

    ImageRequester(String url, ImageRequestCallback requestCallback) {
        this.url = url;
        this.requestCallback = requestCallback;
        cacheManager = CacheManager.getInstance();
    }

    @Override
    public void run() {
        try {
            Bitmap bitmap = cacheManager.getImage(url);
            if (bitmap == null) {
                log("connecting to " + url);
                Response response = NetworkHandler.getInstance().request(url);
                log("received response");
                MediaType contentType = response.body().contentType();
                log(contentType.toString());
                if (isTypeMatch(contentType, JPG) || isTypeMatch(contentType, PNG)) {
                    log("processing image bytestream");
                    InputStream stream = response.body().byteStream();
                    bitmap = BitmapFactory.decodeStream(stream);
                } else {
                    throw new IllegalArgumentException("Not a valid image!");
                }
                log("image loaded from network");
            } else {
                log("returning cached bitmap");
            }
            Message message = handler.obtainMessage(TASK_COMPLETE, bitmap);
            message.sendToTarget();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
