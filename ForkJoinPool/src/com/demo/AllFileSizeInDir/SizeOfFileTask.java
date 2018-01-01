package com.demo.AllFileSizeInDir;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SizeOfFileTask extends RecursiveTask<Long>{

	private final File file;
	
	public SizeOfFileTask(final File file) {
		this.file = file;
	}
	
	@Override
	protected Long compute() {
		if (!file.isDirectory()) {
			System.out.println(file.getName() + " : " + file.length());
			return file.length();
		} else {
			final List<SizeOfFileTask> tasks = new ArrayList<SizeOfFileTask>();
			File children[]  = file.listFiles();
			
			if (null != children){
				for (File child : children) {
					SizeOfFileTask sizeTask = new SizeOfFileTask(child);
					sizeTask.fork();
					tasks.add(sizeTask);
				}
			}
			
			long size = 0;
			
			for (SizeOfFileTask task : tasks) {
				size +=  task.join();
			}
			
			return size;
			
		}
		
	}
	
	public long getSize() {
		final ForkJoinPool forkJoinPool = new ForkJoinPool();
		
		try {
			return forkJoinPool.invoke(new SizeOfFileTask(this.file));
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		finally {
			forkJoinPool.shutdown();
		}
	}

}
