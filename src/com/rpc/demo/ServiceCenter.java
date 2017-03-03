package com.rpc.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务中心实现类
 * 
 * @author pengm
 *
 */
public class ServiceCenter implements Server {
	private static ExecutorService executor = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );

	private static final HashMap<String, Class> serviceRegistry = new HashMap<String, Class>();

	private static boolean isRunning = false;

	private static int port;

	public ServiceCenter(int port) {
		this.port = port;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		isRunning = false;
		executor.shutdown();
	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind( new InetSocketAddress( port ) );

		System.out.println( "start Server..." );
		try {
			while( true ) {
				// 1.监听客服端的链接
				executor.execute( new ServiceTask( serverSocket.accept() ) );
			}
		}
		finally {
			// TODO: handle finally clause
			serverSocket.close();
		}
	}

	@Override
	public void register( Class serviceInterface, Class impl ) {
		// TODO Auto-generated method stub
		serviceRegistry.put( serviceInterface.getName(), impl );
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return isRunning;
	}

	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		return port;
	}

	private static class ServiceTask implements Runnable {
		Socket clent = null;

		public ServiceTask(Socket client) {
			this.clent = client;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ObjectInputStream inputStream = null;
			ObjectOutputStream outputStream = null;

			try {
				// 2.将客户端发送的码反序列化成对象,反射调用服务实现者，获取执行结果
				inputStream = new ObjectInputStream( clent.getInputStream() );
				String serviceName = inputStream.readUTF();
				String methodName = inputStream.readUTF();
				Class<?>[] parameterTypes = (Class<?>[])inputStream.readObject();
				Object[] arguments = (Object[])inputStream.readObject();
				Class serviceClass = serviceRegistry.get( serviceName );
				if( serviceClass == null ) {
					throw new ClassNotFoundException( serviceName + " not found" );
				}
				Method method = serviceClass.getMethod( methodName, parameterTypes );
				Object result = method.invoke( serviceClass.newInstance(), arguments );
				// 3.将执行结果反序列化，通过socket发送给客户端
				outputStream = new ObjectOutputStream( clent.getOutputStream() );
				outputStream.writeObject( result );
			}
			catch( Exception e ) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if( outputStream != null ) {
					try {
						outputStream.close();
					}
					catch( IOException e ) {
						e.printStackTrace();
					}
				}
				if( inputStream != null ) {
					try {
						inputStream.close();
					}
					catch( IOException e ) {
						e.printStackTrace();
					}
				}
				if( clent != null ) {
					try {
						clent.close();
					}
					catch( IOException e ) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}