package com.jtanveer.mindvolley;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import static com.jtanveer.mindvolley.Logger.log;

/**
 * Created by jtanveer on 18/4/18.
 */

class TaskManager implements TaskCompleteCallback {

    private static TaskManager instance;

    private Map<String, Future<?>> runningTasks;

    ThreadManager threadManager;

    static {
        instance = new TaskManager();
    }

    private TaskManager() {
        runningTasks = new HashMap<>();
        threadManager = ThreadManager.getInstance();
    }

    static TaskManager getInstance() {
        return instance;
    }

    private void enqueue(String key, Runnable task) {
        Future<?> future = threadManager.enqueue(task);
        runningTasks.put(key, future);
        log("task enqueued");
    }

    String enqueue(ImageRequester task) {
        String key = UniqueKeyGenerator.randomString(8);
        task.setKey(key);
        task.setTaskCompleteCallback(this);
        enqueue(key, task);

        return key;
    }

    void dequeue(String key) {
        runningTasks.remove(key);
        log("task dequeued");
    }

    void cancel(String key) {
        Future<?> future = runningTasks.remove(key);
        if (!future.isDone()) {
            future.cancel(true);
        }
        log("task cancelled");
    }

    void cancelAll() {
        threadManager.cancelAll();
        for (String key : runningTasks.keySet()) {
            cancel(key);
        }
        runningTasks.clear();
        log("all tasks cancelled");
    }

    void terminate() {
        cancelAll();
        threadManager.shutdown();
        log("task manager terminated");
    }

    @Override
    public void onTaskCompleted(String key) {
        dequeue(key);
    }
}
