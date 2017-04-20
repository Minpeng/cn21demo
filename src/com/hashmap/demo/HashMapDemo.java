package com.hashmap.demo;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
	public static void main( String[] args ) {
		Map<String, Object> map = new HashMap<>();
		if( map == null ) {
			System.out.println( "map is null" );
		}
		else {
			System.out.println( map );
			System.out.println( "map is not null" );
		}

		if( map.isEmpty() ) {
			System.out.println( "map is empty" );
		}
		else {
			System.out.println( "map is not empty" );
		}

		int i = 0;
		double n = 0.0;
		if( i == 0 ) {
			System.out.println( "i==0" );
		}

		if( n == 0 ) {
			System.out.println( "n==0" );
		}

		System.out.println( Float.valueOf( "123" ) );
		System.out.println( Long.valueOf( "124.4" ) );
	}


}
