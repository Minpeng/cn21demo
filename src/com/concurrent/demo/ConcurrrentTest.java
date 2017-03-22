package com.concurrent.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrrentTest {

	public static void main( String[] args ) {
		System.out.println( "11111" );
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newFixedThreadPool( 2 );
		executorService.submit( new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				doSomething( 1 );
			}
		} );

		executorService.submit( new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				doSomething( 2 );
			}
		} );

		executorService.submit( new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				doSomething( 3 );
			}
		} );
		/*** 方式一 ***/
		/*
		 * try { // 等待全部子线程执行完毕 TimeUnit.SECONDS.sleep( 5 ); } catch(
		 * InterruptedException e ) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		/*** 方式2 ***/
		// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
		executorService.shutdown();
		try {
			// 设置最长等待5秒
			executorService.awaitTermination( 5, TimeUnit.SECONDS );
		}
		catch( Exception e ) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println( "主线执行。" );
		System.out.println( "22222" );
	}

	private static void doSomething( int id ) {
		System.out.println( "start do " + id + " task..." );
		try {
			Thread.sleep( 2000 );
		}
		catch( Exception e ) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println( "start do " + id + " finished!" );
	}
}
