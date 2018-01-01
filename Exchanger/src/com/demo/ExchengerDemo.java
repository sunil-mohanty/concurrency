package com.demo;

import java.util.concurrent.Exchanger;

public class ExchengerDemo {
	
	public static void main(String[] args) {
		Exchanger<Object> exchanger = new Exchanger<>();

		ExchangerRunnable ex1 = new ExchangerRunnable(exchanger, "A");
		ExchangerRunnable ex2 = new ExchangerRunnable(exchanger, "B");

		Thread th1 = new Thread(ex1, "Demo-Thread1-initialvalue-A");
		Thread th2 = new Thread(ex2, "Demo-Thread2-initialvalue-B");
		
		th1.start();
		th2.start();
	}
}

class ExchangerRunnable implements Runnable{
	
	private Exchanger<Object> exchanger;
	private Object value;
	
	public ExchangerRunnable(Exchanger<Object> exchanger,  Object value) {
		this.exchanger = exchanger;
		this.value = value;
	}
	
	public void run() {
		try {
			Object previousValue = this.value;
			Object newValue = this.exchanger.exchange(this.value);
			System.out.println(Thread.currentThread().getName() + " exchanged the value " 
			+ previousValue + " for " + newValue);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}