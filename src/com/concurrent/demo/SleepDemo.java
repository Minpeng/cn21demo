package com.concurrent.demo;

public class SleepDemo {

	public static void main( String args[] ) throws InterruptedException {
		for( int i = 0; i < 100; i++ ) {
			System.out.println( "start..." + i );
			Thread.sleep( 1000 );
			System.out.println( "end..." + i );
			System.out.println();

		}

	}
}
