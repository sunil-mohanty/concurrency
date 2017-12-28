package com.demo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    public static void main(String[] args) {
        Thread worker1 = new Thread(new Worker());
        worker1.setName("worker1");

        Thread worker2 = new Thread(new Worker());
        worker2.setName("worker2");
        worker1.start();
        worker2.start();

    }

    private static AtomicInteger at = new AtomicInteger(0);

    static class Worker implements Runnable {
        private int currentValue;
        private int previousValue;
        private int valueIncrementedByFive;
        private boolean isSwapped;

        public void run() {
            currentValue = at.incrementAndGet();
            System.out.println("Thread " + Thread.currentThread().getId() + "  / currentValue : " + currentValue);
            previousValue = at.getAndIncrement();
            System.out.println("Thread " + Thread.currentThread().getId() + " / previousValue : " + previousValue);
            valueIncrementedByFive = at.addAndGet(5);
            System.out.println("Thread " + Thread.currentThread().getId() + " / valueIncrementedByFive : " + valueIncrementedByFive);
            isSwapped= at.compareAndSet(8, 3);

            if (isSwapped) {
                System.out.println("Thread " + Thread.currentThread().getName()
                                + " / Value was equal to 9, so it was updated to " + at.intValue());
            }
        }
    }
}

