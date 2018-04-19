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

    private ImageRequestOption option;
    private ImageRequestCallback requestCallback;
    private CacheManager cacheManager;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case TASK_COMPLETED:
                    Bitmap bitmap = (Bitmap) message.obj;
                    requestCallback.onImageLoaded(bitmap);
                    taskCompleteCallback.onTaskCompleted(key);
                    break;
                case TASK_ERROR:
                    int fallbackImageResource = (int) message.obj;
                    requestCallback.onError(fallbackImageResource);
                    taskCompleteCallback.onTaskCompleted(key);
                    break;
                default:
                    super.handleMessage(message);
            }
        }
    };

    ImageRequester(ImageRequestOption option, ImageRequestCallback requestCallback) {
        this.option = option;
        this.requestCallback = requestCallback;
        cacheManager = CacheManager.getInstance();
    }

    @Override
    public void run() {
        try {
            String url = option.url;
            Bitmap bitmap = cacheManager.getImage(url);
            log("hit count: " + cacheManager.hitCount());
            if (bitmap == null) {
                log("connecting to " + url);
                Response response = NetworkHandler.getInstance().request(url);
                log("received response");
                MediaType contentType = response.body().contentType();
                log(contentType.toString());
                if (isTypeMatch(contentType, JPG) || isTypeMatch(contentType, PNG)) {
                    log("processing image bytestream");
                    bitmap = getScaledBitmap(response.body().byteStream());
                    cacheManager.setImage(url, bitmap);
                } else {
                    log("invalid image!");
                    sendMessageToHandler(TASK_ERROR, option.fallbackImageResource);
                    return;
                }
                log("image loaded from network");
            } else {
                log("returning cached bitmap");
            }
            sendMessageToHandler(TASK_COMPLETED, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            sendMessageToHandler(TASK_ERROR, option.fallbackImageResource);
        }
    }

    private void sendMessageToHandler(int what, Object obj) {
        Message message = handler.obtainMessage(what, obj);
        message.sendToTarget();
    }

    private Bitmap getScaledBitmap(InputStream stream) {
        Bitmap bitmap = BitmapFactory.decodeStream(stream);

        int outWidth;
        int outHeight;
        int inWidth = bitmap.getWidth();
        int inHeight = bitmap.getHeight();
        if (inWidth > option.targetWidth && inHeight > option.targetHeight) {
            if(inWidth > inHeight){
                outWidth = option.targetWidth;
                outHeight = (inHeight * option.targetWidth) / inWidth;
            } else {
                outHeight = option.targetHeight;
                outWidth = (inWidth * option.targetHeight) / inHeight;
            }

            Bitmap resized = Bitmap.createScaledBitmap(bitmap, outWidth, outHeight, false);

            return resized;
        } else {
            // image is already scaled
            return bitmap;
        }
    }
}
