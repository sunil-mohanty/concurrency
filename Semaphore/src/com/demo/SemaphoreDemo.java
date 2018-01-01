package com.demo;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2, true);
		
		SemaphoreRunner sr1 = new SemaphoreRunner(semaphore);
		SemaphoreRunner sr2 = new SemaphoreRunner(semaphore);
		SemaphoreRunner sr3 = new SemaphoreRunner(semaphore);
		
		Thread th1 = new Thread(sr1, "TH1");
		Thread th2 = new Thread(sr2, "TH2");
		Thread th3 = new Thread(sr3, "TH3");
		
		th1.start();
		th2.start();
		th3.start();
	}
}

class SemaphoreRunner implements Runnable {
	private Semaphore semaphore;
	
	public SemaphoreRunner(Semaphore semaphore){
		this.semaphore = semaphore;
	}
	
	public void run() {
		try {
			semaphore.acquire();
			System.out.println("acuired by : " + Thread.currentThread().getName());
			Thread.sleep(2000);
			semaphore.release();
			System.out.println("Relesae by : " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
