package com.demo.sumOfAllArrayElement;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class AddContents extends RecursiveTask<Integer>{
	private static final long serialVersionUID = -196522408291343951L;
	final private int[] container;
	private static int threshold = 3;
	
	public AddContents(int[] container) {
		this.container = container;
	}

	@Override
	protected Integer compute() {
		
		if (container.length <= threshold) {
			int result =  Arrays.stream(container).sum();
			return result;
		} else {
			
			int  container1Size = container.length/2; 
			int remainder = container.length%2;
			
			int[] container1 = new int[container1Size];
			int container2Size ;
			
			if (remainder >= 1 ) {
				container2Size = container1Size + 1;
			} else {
				container2Size = container1Size;
			}
					
			int[] container2 = new int[container2Size];
			
			System.arraycopy(container, 0, container1, 0, container1Size);
			System.arraycopy(container, container1Size, container2, 0, container2Size);
			
			
			AddContents addContents1 = new AddContents(container1);
			AddContents addContents2 = new AddContents(container2);
			
			
			showArray(container1);
			showArray(container2);
			
			addContents1.fork();
			addContents2.fork();
			
			int totalSize = addContents1.join() +  addContents2.join();
			return totalSize;
		}
	}
	
	public void showArray(int arr[]) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println("");
	}
 }

public class ContentAdder{
	final private int[] container;
	public ContentAdder(int[] container) {
		this.container = container;
	}
	
	public int calculateArrayConetntSum() {
		
		//Here any number of threads can be added
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		
		int size = forkJoinPool.invoke(new AddContents(container));
		return size;
	}
}
