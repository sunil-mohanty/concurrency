package com.demo;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service implements Runnable{

	 private final String name;
	 private final int timeToStart;
	 private final CountDownLatch latch;
	  
	public Service(String name, int timeToStart, CountDownLatch latch) {
		this.name = name;
		this.timeToStart = timeToStart;
		this.latch = latch;
	}
 
	@Override
	public void run() {
		try {
			Thread.sleep(timeToStart);
			System.out.println(name + " is Up");
			latch.countDown(); // reduce count of CountDownLatch by 1
		} catch (InterruptedException ex) {
			Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
