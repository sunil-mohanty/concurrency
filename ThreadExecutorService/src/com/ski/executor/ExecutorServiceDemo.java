package com.ski.executor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		//ExecutorService threadPoolExecutorService = new ThreadPoolExecutor(arg0, arg1, arg2, arg3, arg4, arg5)
		//ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
		//ExecutorService executorService = Executors.;

		
		// Demonstration of simple executor service with execute
		executorService.execute(new Runnable() {
		    public void run() {
		        System.out.println("Asynchronous task");
		    }
		});

		//executorService.shutdown();
		
		
		// Demonstration of Executor service with future and submit(taking runnable argument)
		// submit method takes a runnable argument and returns a future presenting 
		// task is completed successfully or not.
		    
		Future<?> futureWithRunnable = executorService.submit(new Runnable() {
		    public void run() {
		        System.out.println("Asynchronous task");
		    }
		}); 
        
		// Future will return null if the execution is success
		System.out.println(futureWithRunnable.get());
		
		
		// Demonstration of executorService with submit (taking callable argument) . We use callable to
		// catch if any result is getting returned after the execution of the job. 
		Future<Object> futureWithCallable = executorService.submit(new Callable<Object>() {
			 public Object call() throws Exception {
			        System.out.println("Asynchronous Callable");
			        return "Callable Result";
			 }
		});
		
		System.out.println(futureWithCallable.get());
		
		
		// Demonstration of executorService with invokeAny() [invokeAny takes a collection of Callable and picks
		// one of them for execution. The return will be a result string of the callable that got executed.
		// There is no guarantee on which callable will be executed]

		Set<Callable<String>> callableTasksInvokeAny = new HashSet<Callable<String>>();
		
		Callable<String> callable1 = new Callable<String>(){

			@Override
			public String call() throws Exception {
				System.out.println("callable1 executed");
				return "callable1";
			}
			
		};
		callableTasksInvokeAny.add(callable1);
		
		Callable<String> callable2 = new Callable<String>(){

			@Override
			public String call() throws Exception {
				System.out.println("callable2 executed");
				return "callable2";
			}
			
		};
		callableTasksInvokeAny.add(callable2);
		
		Callable<String> callable3 = new Callable<String>(){

			@Override
			public String call() throws Exception {
				System.out.println("callable3 executed");
				return "callable3";
			}
			
		};
		callableTasksInvokeAny.add(callable3);
		
		Callable<String> callable4 = new Callable<String>(){

			@Override
			public String call() throws Exception {
				System.out.println("callable4 executed");
				return "callable4";
			}
			
		};
		callableTasksInvokeAny.add(callable4);
		
		String result = executorService.invokeAny(callableTasksInvokeAny);
		System.out.println(result);
		
		
		
		//Demonstration of InvokeAll [it takes collection of callable objects and returns list of
		// Future objects through which we can retrive the result]  method of executorService
		
		Set<Callable<String>> callableTasksInvokeAll = new HashSet<>();
		
		Callable<String> callable11 = new Callable<String>(){
			public String call(){
				System.out.println("callable1 geting executed");
				 return "callable1";
			}
		};
		
		Callable<String> callable12 = new Callable<String>(){
			public String call(){
				System.out.println("callable2 geting executed");
				 return "callable2";
			}
		};
		
		Callable<String> callable13 = new Callable<String>(){
			public String call(){
				System.out.println("callable3 geting executed");
				 return "callable3";
			}
		};
		
		callableTasksInvokeAll.add(callable11);
		callableTasksInvokeAll.add(callable12);
		callableTasksInvokeAll.add(callable13);
		
		List<Future<String>> results = executorService.invokeAll(callableTasksInvokeAll);
		
		for (Future<String> callableResult : results) {
			System.out.println(callableResult.get());
			System.out.println(callableResult.isDone());
		}
		
		executorService.shutdown();
		
	}
}
