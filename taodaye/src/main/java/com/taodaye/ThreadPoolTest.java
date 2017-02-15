package com.taodaye;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadPoolTest {

	public static void main(String[] args) {
		try {
			testCallable();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testRunnable(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(()->{
			String threadName = Thread.currentThread().getName();
			System.out.println(String.format("Hello, %s", threadName));
		}); 
		shutdownExecutor(executor);
	}
	
	public static void testCallable() throws InterruptedException, ExecutionException, TimeoutException{
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Callable<Integer> task = ()->{
			try{
				TimeUnit.SECONDS.sleep(5);
				return 123;
			}catch (InterruptedException  e){
				throw new IllegalStateException("task interrupted", e);
			}
		};
		Future<Integer> future = executor.submit(task);
		
		System.out.println("Future done? " + future.isDone());
		
		Integer res = future.get(1, TimeUnit.SECONDS);
		System.out.println("Future done? " + future.isDone());
		System.out.println("result: " + res);
		
		shutdownExecutor(executor);
	}

	public static void shutdownExecutor(ExecutorService executor){
		try{
			System.out.println("attempt to shutdown executor.");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}catch(InterruptedException  e){
			System.out.println("tasks interrupted");
		}finally{
			if (!executor.isTerminated()) {
				System.out.println("cancel non-finished tasks.");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished.");
		}
		
	}
}
