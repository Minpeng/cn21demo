package com.test.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections.EMPTY_LIST() 和 new ArrayList() 对比 <br>
 * 结论:优先使用Collections.EMPTY_LIST()
 * 
 * @author pengm
 *
 */
public class EmptyArrayList {
	public static void main( String[] args ) {
		long startTime = System.nanoTime();
		long endTime;

		for( long i = 0; i < 1000000000; i++ ) {
			List<String> list = Collections.EMPTY_LIST;
		}
		endTime = System.nanoTime();
		System.out.println( "Collections.EMPTY_LIST:  " + (endTime - startTime) );

		startTime = System.nanoTime();
		for( long i = 0; i < 1000000000; i++ ) {
			List<String> list = new ArrayList<>();
		}
		endTime = System.nanoTime();
		System.out.println( "new ArrayList:  " + (endTime - startTime) );
	}
}
