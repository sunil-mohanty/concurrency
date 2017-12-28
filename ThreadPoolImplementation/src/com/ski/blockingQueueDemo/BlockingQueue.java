package com.ski.blockingQueueDemo;

import java.util.LinkedList;

public class BlockingQueue<T> {

    private int limit = 10;
    private LinkedList<T> queue = new LinkedList<T>();
    boolean processed = false;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }


    public synchronized void enqueue(T t)  {

        try {
            while (queue.size() == limit) {
                wait();
            }

            if (queue.size() == 0) {
                notifyAll();
            }
            queue.add(t);

        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized T dqueue() {
        try {
            while (queue.size() == 0) {
                wait();
            }

            if (queue.size() == this.limit) {
                notifyAll();
            }
            processed = true;
            return queue.remove(0);

        }catch(InterruptedException e) {
            //e.printStackTrace();
            return null;
        }
    }

}
