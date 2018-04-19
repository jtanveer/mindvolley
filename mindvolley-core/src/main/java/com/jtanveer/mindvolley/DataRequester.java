package com.jtanveer.mindvolley;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.jtanveer.mindvolley.Logger.log;

/**
 * Created by jtanveer on 18/4/18.
 */

class DataRequester<S, E, A, B> extends Requester implements Runnable {

    private DataRequestOption<S, E> option;
    private DataRequestCallback<A, B> requestCallback;
    private CacheManager cacheManager;
    private Gson gson;
    private JsonParser parser;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case TASK_COMPLETED:
                    A success = (A) message.obj;
                    log(success.getClass().getName());
                    requestCallback.onSuccess(success);
                    break;
                case TASK_ERROR:
                    B error = (B) message.obj;
                    requestCallback.onError(error);
                    break;
                case TASK_FAILED:
                    requestCallback.onFailed();
                    break;
                default:
                    super.handleMessage(message);
            }
        }
    };

    DataRequester(DataRequestOption<S, E> option, DataRequestCallback<A, B> requestCallback) {
        this.option = option;
        this.requestCallback = requestCallback;
        cacheManager = CacheManager.getInstance();
        gson = new Gson();
        parser = new JsonParser();
    }

    @Override
    public void run() {
        try {
            String url = option.url;
            Map<String, String> data = option.fieldParams;
            String responseString = cacheManager.getData(url);
            if (responseString == null) {
                Response response;
                log("connecting to " + url);
                if (data != null) {
                    response = NetworkHandler.getInstance().request(url, data);
                } else {
                    response = NetworkHandler.getInstance().request(url);
                }
                log("received data response");
                MediaType contentType = response.body().contentType();
                responseString = response.body().string();
                log(contentType.toString());
                if (isTypeMatch(contentType, JSON)
                        || isTypeMatch(contentType, JSON_VND)
                        || isTypeMatch(contentType, JSON_PLAIN)) {
                    if (response.code() == 200 || response.code() == 201) {
                        processJson(responseString, option.success, TASK_COMPLETED);
                        if (response.code() == 200) {
                            cacheManager.setData(url, responseString);
                        }
                    } else {
                        processJson(responseString, option.error, TASK_FAILED);
                    }
                } else if (isTypeMatch(contentType, XML) || isTypeMatch(contentType, XML_PLAIN)) {
                    // process XML
                    throw new UnsupportedOperationException("XML is not supported yet!");
                } else {
                    throw new IllegalArgumentException("Not a valid content type!");
                }
                log("data loaded from network");
            } else {
                log("returning cached data");
                processJson(responseString, option.success, TASK_COMPLETED);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sendMessageToHandler(TASK_FAILED, null);
        }
    }

    private void sendMessageToHandler(int what, Object obj) {
        Message message = handler.obtainMessage(what, obj);
        message.sendToTarget();
    }

    private <T> void processJson(String jsonString, Class<T> type, int state) {
        log(jsonString.toString());
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonString);
        if (element.isJsonObject()) {
            T success = gson.fromJson(element.toString(), type);
            log("POJO: " + success.toString());
            sendMessageToHandler(state, success);
        } else {
            List<T> success = new ArrayList<>();
            JsonArray container = element.getAsJsonArray();
            for (JsonElement obj : container) {
                T item = gson.fromJson(obj.toString(), type);
                success.add(item);
            }
            log("POJO: " + success.toString());
            sendMessageToHandler(state, success);
        }
    }
}
