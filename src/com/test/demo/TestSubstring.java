package com.test.demo;

public class TestSubstring {
	public static void main( String[] args ) {
		String aString = "select * from waf* a join firewall*  b on a.sbt_dip=b.sbt_dip";
		int index = aString.indexOf( "join" );

		String bString = aString.substring( index - 2, index );
		System.out.println( bString );
	}
}
