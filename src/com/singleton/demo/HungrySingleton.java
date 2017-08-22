package com.singleton.demo;

/**
 * 饿汉式 程序启动就在jvm中加载了
 * 
 * @author pengm
 *
 */
public class HungrySingleton {
	private static final HungrySingleton instance = new HungrySingleton();

	private HungrySingleton() {

	}

	public static HungrySingleton getInstance() {
		return instance;
	}
}
