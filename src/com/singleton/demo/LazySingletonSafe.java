package com.singleton.demo;

/**
 * 懒汉式 线程安全
 * 
 * @author pengm
 *
 */
public class LazySingletonSafe {
	private static LazySingletonSafe lazySingletonSafe;

	private LazySingletonSafe() {
		
	}

	/**
	 * 加入synchronized 线程安全
	 */
	public static synchronized LazySingletonSafe getInstance() {

			if( lazySingletonSafe==null ) {
			lazySingletonSafe = new LazySingletonSafe();
			}
			return lazySingletonSafe;
	}
}
