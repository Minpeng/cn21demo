package com.hashmap.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class IterateHashMap {
	public static void main( String[] args ) {
		Map<String, String> map = new HashMap<>();

		for( int i = 0; i < 1000000; i++ ) {
			map.put( "key" + i, "value" + i );
		}

		// 1.entrySet迭代
		long startTime = System.nanoTime();
		Iterator iter = map.entrySet().iterator();
		while( iter.hasNext() ) {
			Map.Entry entry = (Map.Entry)iter.next();
			String key = (String)entry.getKey();
			String val = (String)entry.getValue();
		}
		long endTime = System.nanoTime();

		System.out.println( "1.entrySet迭代 :" + (endTime - startTime) );

		// 2.entrySet for-each
		long startTime2 = System.nanoTime();

		for( Entry<String, String> entry : map.entrySet() ) {
			String key = entry.getKey();
			String val = entry.getValue();
		}

		long endTime2 = System.nanoTime();

		System.out.println( "2. entrySet for-each :" + (endTime2 - startTime2) );

		// 3. keySet迭代
		long startTime3 = System.nanoTime();

		Iterator iter3 = map.keySet().iterator();
		while( iter3.hasNext() ) {
			String key = (String)iter3.next();
			String val = map.get( key );
		}

		long endTime3 = System.nanoTime();

		System.out.println( "3. keySet迭代 :" + (endTime3 - startTime3) );

		// 4.keySet for-each
		long startTime4 = System.nanoTime();
		for( String kString : map.keySet() ) {
			String key = kString;
			String val = map.get( kString );
		}
		long endTime4 = System.nanoTime();

		System.out.println( "4. keySet for-each :" + (endTime4 - startTime4) );

	}
}
