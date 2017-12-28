package com.ski.threadpooldemo;


import com.ski.blockingQueueDemo.BlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue = null;
    private List<Thread> workerThreads = new ArrayList<>();
    public static boolean isStopped = false;

    public ThreadPool(int numberOfWorkers, int maxNumberOfTasks) {
        taskQueue = new BlockingQueue<>(maxNumberOfTasks);

        for (int i = 0; i < numberOfWorkers; i++) {
            workerThreads.add(new Thread(new WorkerThread(taskQueue)));

        }

        for (Thread workers : workerThreads) {
            workers.start();
        }
    }

    public synchronized void execute (Runnable task) throws Exception{
        if(this.isStopped) {
            throw new IllegalAccessException("Thread pool shut down");
        }
        this.taskQueue.enqueue(task);
    }

    public synchronized void stop() {
        this.isStopped = true;
        workerThreads.forEach(e->e.interrupt());
    }
}



