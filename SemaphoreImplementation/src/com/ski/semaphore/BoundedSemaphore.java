package com.ski.semaphore;

public class BoundedSemaphore {

    private int signal = 0;
    private int boundary = 5;

    public BoundedSemaphore(int boundary){
        this.boundary = boundary;
    }

    public synchronized void take() throws InterruptedException {

        while (signal == boundary) {
            this.wait();
        }
        this.signal = signal + 1;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while(signal == 0) {
            this.wait();
        }
        this.signal = signal - 1;
        this.notify();
    }
}
