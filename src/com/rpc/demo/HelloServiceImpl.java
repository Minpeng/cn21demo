package com.rpc.demo;

/**
 * HelloService接口实现类
 * 
 * @author pengm
 *
 */
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello( String name ) {
		// TODO Auto-generated method stub
		return "Hello" + name;
	}

}
