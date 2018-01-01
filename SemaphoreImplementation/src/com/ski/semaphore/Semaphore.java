package com.ski.semaphore;

public class Semaphore {

    private boolean signal = false;

    public synchronized void take() {
        this.signal = true;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while(!signal) {
            this.wait();
        }
        this.signal = false;
    }
}
