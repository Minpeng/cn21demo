package com.test.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WriteToFile {
	public static void main( String[] args ) {
		String target = "</configuration>";
		StringBuffer coreBuffer = new StringBuffer();
		coreBuffer.append( "\t" + "<property>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<name>" + "fs.defaultFS" + "</name>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<value>" + "hdfs://hadoopMasterIp:9000" + "</value>" + "\n" );
		coreBuffer.append( "\t" + "</property>" + "\n" );

		coreBuffer.append( "\t" + "<property>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<name>" + "hadoop.tmp.dir" + "</name>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<value>" + "/home/currentUser/hadoopdata" + "</value>" + "\n" );
		coreBuffer.append( "\t" + "</property>" + "\n" );

		coreBuffer.append( "\t" + "<property>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<name>" + "hadoop.proxyuser.currentUser.groups" + "</name>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<value>" + "*" + "</value>" + "\n" );
		coreBuffer.append( "\t" + "</property>" + "\n" );

		coreBuffer.append( "\t" + "<property>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<name>" + "hadoop.proxyuser.currentUser.hosts" + "</name>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<value>" + "*" + "</value>" + "\n" );
		coreBuffer.append( "\t" + "</property>" + "\n" );

		coreBuffer.append( "\t" + "<property>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<name>" + "dfs.client.read.shortcircuit" + "</name>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<value>" + "true" + "</value>" + "\n" );
		coreBuffer.append( "\t" + "</property>" + "\n" );

		coreBuffer.append( "\t" + "<property>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<name>" + "dfs.client.read.shortcircuit.skip.checksum" + "</name>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<value>" + "false" + "</value>" + "\n" );
		coreBuffer.append( "\t" + "</property>" + "\n" );

		coreBuffer.append( "\t" + "<property>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<name>" + "dfs.datanode.hdfs-blocks-metadata.enabled" + "</name>" + "\n" );
		coreBuffer.append( "\t" + "\t" + "<value>" + "true" + "</value>" + "\n" );
		coreBuffer.append( "\t" + "</property>" + "\n" );

		coreBuffer.append( "</configuration>" );
		appendToXml( new File( "D:\\cn21work\\智慧化运营\\core-site.xml" ), target, coreBuffer );
		// 重命名文件
		reNameFile( "D:\\cn21work\\智慧化运营\\core-site.xml.tmp", "D:\\cn21work\\智慧化运营\\core-site.xml23.tmp" );

	}

	private static void reNameFile( String oldName, String newName ) {
		File oldFile = new File( oldName );
		File newFile = new File( newName );

		boolean fileRenamed = oldFile.renameTo( newFile );
		if( fileRenamed ) {
			System.out.println( oldFile + "  renamed  to " + newFile );
		}
		else {
			System.out.println( "Renaming " + oldFile + "  to " + newFile + "  failed." );
		}

	}



	public static void appendToXml( File file, String target, StringBuffer appendBuffer ) {
		BufferedReader bufReader = null;
		BufferedWriter writer = null;
		try {
			StringBuffer strBuffer = new StringBuffer();
			InputStream inputStream = new FileInputStream( file );
			bufReader = new BufferedReader( new InputStreamReader( inputStream ) );

			String filename = file.getName();
			File tmpfile = new File( file.getParentFile().getAbsolutePath() + "\\" + filename + ".tmp" );
			writer = new BufferedWriter( new FileWriter( tmpfile ) );

			Boolean flag = false;
			String str = null;
			while( !flag == true ) {
				str = bufReader.readLine();

				if( str != null && str.contains( target ) ) {
					str = str.replace( target, appendBuffer );
					flag = true;
				}
				strBuffer.append( str + "\n" );
			
			}
			bufReader.close();
			writer.write( strBuffer.toString() );
			writer.flush();
			writer.close();
		}
		catch( Exception e ) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				bufReader.close();
				writer.close();
			}
			catch( IOException e1 ) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
