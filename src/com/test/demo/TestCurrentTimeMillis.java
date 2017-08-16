package com.test.demo;

public class TestCurrentTimeMillis {

	public static void main( String[] args ) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		System.out.println( "开始休眠10秒..." );
		Thread.sleep( 10000 );
		System.out.println( "休眠结束..." );
		long endTime = System.currentTimeMillis();
		long runTime = endTime - startTime;
		System.out.println( "程序运行时间:" + runTime );
	}

}
