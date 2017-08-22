package com.singleton.demo;

/**
 * 静态内部类
 * 
 * @author pengm
 *
 */
public class StaticNestedClassSingleton {
	private static class SingletonHolder {
		private static final StaticNestedClassSingleton INSTANCE = new StaticNestedClassSingleton();
	}

	private StaticNestedClassSingleton() {
	}

	public static final StaticNestedClassSingleton getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
