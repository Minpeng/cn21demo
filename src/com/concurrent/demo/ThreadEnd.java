package com.concurrent.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 等待线程结束
 * 
 * @author pengm
 *
 */
public class ThreadEnd {
	public static void main( String[] args ) throws InterruptedException {
		final List list = new ArrayList<>();
		ExecutorService executorService = Executors.newFixedThreadPool( 2 );

		executorService.submit( new Runnable() {

			@Override
			public void run() {
				for( int i = 0; i < 10; i++ ) {
					list.add( doSomething( 11 ) );

				}

			}
		} );

		executorService.submit( new Runnable() {
			@Override
			public void run() {
				for( int j = 100; j < 105; j++ ) {
					doSomething( j );
				}
			}
		} );
		// 线程池不再接收任何新任务，但此时线程池并不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出
		executorService.shutdown();

		while( true ) {
			if( executorService.isTerminated() ) {
				System.out.println( "所有的子线程都结束了！" );
				break;
			}
			Thread.sleep( 1000 );
		}
		System.out.println( list );
	}

	private static int doSomething( int id ) {
		System.out.println( "start do " + id + " task..." );
		try {
			Thread.sleep( 2000 );
		}
		catch( Exception e ) {
			e.printStackTrace();
		}

		System.out.println( "start do " + id + " finished!" );
		return id;
	}
}
