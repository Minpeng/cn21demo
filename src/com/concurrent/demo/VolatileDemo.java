package com.concurrent.demo;

public class VolatileDemo {
	private static volatile int count = 0;

	private static void increaseCount() {

		// 延迟1s
		try {
			Thread.sleep( 1 );

		}
		catch( InterruptedException e ) {
			e.printStackTrace();
		}
		Object lock = new Object();
		synchronized( lock ) {
			count = count + 1;
		}
	}



	public static void main( String[] args ) {
		for( int i = 0; i < 1000; i++ ) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {

					VolatileDemo.increaseCount();
					

				}
			} ).start();
		}
		System.out.println( count );
	}

}
