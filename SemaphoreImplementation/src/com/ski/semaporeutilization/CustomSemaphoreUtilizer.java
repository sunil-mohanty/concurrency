package com.ski.semaporeutilization;

import com.ski.semaphore.BoundedSemaphore;

public class CustomSemaphoreUtilizer {

    public static void main(String args[]) {
        BoundedSemaphore semaphore = new BoundedSemaphore(2);

        Worker sr1 = new Worker(semaphore);
        Worker sr2 = new Worker(semaphore);
        Worker sr3 = new Worker(semaphore);

        Thread th1 = new Thread(sr1, "TH1");
        Thread th2 = new Thread(sr2, "TH2");
        Thread th3 = new Thread(sr3, "TH3");

        try {
            th1.start();
            Thread.sleep(2000);
            th2.start();
            th3.start();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Worker implements Runnable {
    private BoundedSemaphore semaphore;

    Worker(BoundedSemaphore semaphore){
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.take();
            System.out.println("acuired by : " + Thread.currentThread().getName());
            Thread.sleep(5000);
            semaphore.release();
            System.out.println("Relesae by : " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
