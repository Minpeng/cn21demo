package com.singleton.demo;

/**
 * 枚举类
 * 
 * @author pengm
 *
 */
public enum EnumSingleton {
	INSTANCE;

	public EnumSingleton getInstance() {
		return INSTANCE;
	}
}
