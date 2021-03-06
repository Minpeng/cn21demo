package com.test.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Collections.EMPTY_MAP() 和 new HashMap() 对比 <br>
 * 结论:优先使用Collections.EMPTY_MAP()
 * 
 * @author pengm
 *
 */
public class EmptyMap {
	public static void main( String[] args ) {
		long startTime = System.nanoTime();
		long endTime;

		for( int i = 0; i < 100000000; i++ ) {
			Map<String, String> map = Collections.EMPTY_MAP;
		}
		endTime = System.nanoTime();
		System.out.println( "Collections.EMPTY_MAP:  " + (endTime - startTime) );

		startTime = System.nanoTime();
		for( int i = 0; i < 100000000; i++ ) {
			Map<String, String> map = new HashMap<>();
		}
		endTime = System.nanoTime();
		System.out.println( "new HashMap:  " + (endTime - startTime) );
	}

}
