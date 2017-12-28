package com.ski.threadpooldemo;


import com.ski.blockingQueueDemo.BlockingQueue;

public class WorkerThread implements Runnable {

    BlockingQueue taskQueue = null;
    boolean isStopped = false;

    public WorkerThread(BlockingQueue<Runnable> taskQueue ){
        this.taskQueue = taskQueue;
    }

    public void run() {
        while(!ThreadPool.isStopped) {
            try {
                Runnable task = (Runnable) taskQueue.dqueue();
                task.run();
            }catch(Exception e) {

            }
        }
    }

}
