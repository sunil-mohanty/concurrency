package com.demo.atomic;


import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Why AutomicStampedReference -
 *
 * Let's have a focus on A-B-A problem. Suppose two threads are working on a shared resource R with initial value A (In this state both the threads are aware
 * of the current value of R is A).
 * Now Thread2 went to waiting state and by that time Thread1 changed the value of R to B and again back to A . When Thread2 wakes up, it sees the value of R as A remaining
 * unaware of in between changes . In other words Thread1 makes fool of Thread2. This type of situation can be avoided using the "AutomicStampedReferenceDemo"
 *
 */
public class AutomicStampedReferenceDemo {

    public static void main(String args[]) {

        String initialReferenc = "initial value";
        String newReference = "new value";
        AtomicStampedReference<String> automicStampedReference = new AtomicStampedReference<>(initialReferenc, 1);
        Thread workerThread = new Thread(new Worker(automicStampedReference));
        workerThread.start();
        try {
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }
        automicStampedReference.compareAndSet(initialReferenc,newReference, 1,2);

        automicStampedReference.compareAndSet(newReference,initialReferenc, 2,3);

    }

    static class Worker implements Runnable{
        AtomicStampedReference<String> automicStampedReference = null;

        Worker(AtomicStampedReference<String> automicStampedReference) {
            this.automicStampedReference = automicStampedReference;
        }
        public void run() {

            int holder1[] = new int[1];
            Object intVal1 = automicStampedReference.get(holder1);
            int intStamp1 = holder1[0];

            try {
                Thread.sleep(5000);

                int holder2[] = new int[1];
                Object intVal2 = automicStampedReference.get(holder2);
                int intStamp2 = (Integer)holder2[0];

                if (intVal1 == intVal2 && intStamp1 == intStamp2) {
                    System.out.println(" Vlaue never changed ");

                } else {
                    System.out.println(" Vlaue got updated");
                }
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

