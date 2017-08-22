package com.singleton.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试单例模式
 * 
 * @author pengm
 *
 */
public class TestSingleton {

	static final CountDownLatch CDL = new CountDownLatch( 1 ); // 为0时开始执行

	static final ExecutorService exec = Executors.newFixedThreadPool( 9 );

	public static void main( String[] args ) {
		// 1.测试懒汉式 线程不安全 单例模式
		testLazySingleton();
	}

	/**
	 * 测试懒汉式 线程不安全 单例模式
	 */
	private static void testLazySingleton() {
		for( int i = 0; i < 9; i++ ) {
			final int NO = i + 1;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						// 等待直到 CountDownLatch减到1
						CDL.await();
						// LazySingleton lazySingleton =
						// LazySingleton.getInstance();
						// LazySingletonSafe lazySingletonSafe =
						// LazySingletonSafe.getInstance();
						// StaticNestedClassSingleton staticNestedClassSingleton
						// = StaticNestedClassSingleton.getInstance();
						// HungrySingleton hungrySingleton =
						// HungrySingleton.getInstance();
						EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
						System.out.println( "lazySingleton" + String.valueOf( NO ) + ": " + enumSingleton );
					}
					catch( Exception e ) {
						e.printStackTrace();
					}
				}
			};
			exec.submit( runnable );
		}
		System.out.println( "开始执行" );
		CDL.countDown(); // begin减一，开始并发执行
		exec.shutdown();
	}
}
