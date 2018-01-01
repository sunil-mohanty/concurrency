package com.ski.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MonitoringThreadPool {

	public static void main(String args[]) throws InterruptedException {
		
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		
		// Create thread pool via Thread Factory
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
		
		// start the monitoring thread
		MonitoringThread monitor = new MonitoringThread(executorPool, 5);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();
		
		for (int i = 0; i < 10; i++) {
			executorPool.execute(new WorkerThread("cmd" + i));
		}

		Thread.sleep(30000);
		executorPool.shutdown();
		Thread.sleep(5000);
		monitor.shutdown();

	}

}

class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " is rejected");
    }
}

class MonitoringThread implements Runnable{
    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run=true;

    public MonitoringThread(ThreadPoolExecutor executor, int delay){
        this.executor = executor;
        this.seconds=delay;
    }
    public void shutdown(){
        this.run=false;
    }
    
    /*
     If the number of threads is less than the corePoolSize, create a new Thread to run a new task.
     If the number of threads is equal (or greater than) the corePoolSize, put the task into the queue.
     If the queue is full, and the number of threads is less than the maxPoolSize, create a new thread to run tasks in.
     If the queue is full, and the number of threads is greater than or equal to maxPoolSize, reject the task.
    */
    @Override
    public void run(){
        while(run){
                System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                        this.executor.getPoolSize(),
                        this.executor.getCorePoolSize(),
                        this.executor.getActiveCount(),
                        this.executor.getCompletedTaskCount(),
                        this.executor.getTaskCount(),
                        this.executor.isShutdown(),
                        this.executor.isTerminated()));
                try {
                    Thread.sleep(seconds*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
            
    }
}
