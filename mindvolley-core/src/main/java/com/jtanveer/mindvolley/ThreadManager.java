package com.jtanveer.mindvolley;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.jtanveer.mindvolley.Logger.log;

/**
 * Created by jtanveer on 17/4/18.
 */

class ThreadManager {

    private static ThreadManager instance;

    private BlockingQueue<Runnable> taskQueue;

    private ThreadPoolExecutor executor;

    static {
        instance = new ThreadManager();
    }

    private ThreadManager() {
        taskQueue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(5, 10, 60l,
                TimeUnit.SECONDS, taskQueue);
    }

    static ThreadManager getInstance() {
        return instance;
    }

    Future<?> enqueue(Runnable task) {
        Future<?> future = executor.submit(task);
        log("task submitted. queue size: " + taskQueue.size());

        return future;
    }

    void cancelAll() {
        taskQueue.clear();
    }

    void shutdown() {
        executor.shutdownNow();
        log("executor shutdown");
    }
}
