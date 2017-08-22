package com.singleton.demo;

/**
 *
 * 懒汉式，线程不安全
 * 
 * @author pengm
 *
 */
public class LazySingleton {
	private static LazySingleton lazySingleton;

	private LazySingleton() {

	}

	public static LazySingleton getInstance() {
		if( lazySingleton == null ) {
			return new LazySingleton();
		}

		return lazySingleton;
	}
}
