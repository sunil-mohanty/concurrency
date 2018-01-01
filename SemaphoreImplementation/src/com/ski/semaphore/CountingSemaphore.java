package com.ski.semaphore;

public class CountingSemaphore {

    private int signal = 0;

    public synchronized void take() {
        this.signal = signal + 1;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while(signal == 0) {
            this.wait();
        }
        this.signal = signal - 1;
    }
}
