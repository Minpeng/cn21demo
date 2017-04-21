package com.concurrent.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * callable
 * 
 * @author pengm
 *
 */
public class CallableDemo {
	public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool( 2 );

	public static void main( String[] args ) throws InterruptedException, ExecutionException {

		// Futrue 返回值 来查看进程是否结束
		Future<Boolean> futureFirst = EXECUTOR_SERVICE.submit( new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {

				return doSomething( 10 );
			}
		} );

		Future<Boolean> futureSecond = EXECUTOR_SERVICE.submit( new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {

				return doSomethingSecond( 5 );
			}
		} );

		Boolean first = futureFirst.get();

		Boolean second = futureFirst.get();

		// 两个都为ture
		if( first == true && true == second ) {
			System.out.println( "子线程全都结结束了..." );
		}
	}


	private static Boolean doSomething( int id ) {
		for( int i = 0; i < id; i++ ) {
			System.out.println( "start do " + i + " task..." );
			try {
				Thread.sleep( 2000 );
			}
			catch( Exception e ) {
				e.printStackTrace();
				return false;
			}

			System.out.println( "start do " + i + " finished!" );
		}

		return true;
	}

	private static Boolean doSomethingSecond( int id ) {
		for( int i = id; i > 0; i-- ) {
			System.out.println( "start do " + i + " task..." );
			try {
				Thread.sleep( 2000 );
			}
			catch( Exception e ) {
				e.printStackTrace();
				return false;
			}

			System.out.println( "start do " + i + " finished!" );
		}

		return true;
	}
}
