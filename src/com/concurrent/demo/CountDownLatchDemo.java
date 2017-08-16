package com.concurrent.demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	private final static int THREAD_NUM = 30;
	public static void main( String args[] ) {
		concurrent();
	}

	// 模拟大并发
	private static void concurrent() {
		final CountDownLatch latch = new CountDownLatch( 1 );

		for( int i = 0; i < THREAD_NUM; i++ ) {
			final int NO = i + 1;
			new Runnable() {
				@Override
				public void run() {
					try {
						latch.await();
						System.out.println( "No." + NO + " execute" );
					}
					catch( Exception e ) {
						e.printStackTrace();
					}

				}
			};
			System.out.println( i + "开始执行" );
			latch.countDown();
		}

	}
}
