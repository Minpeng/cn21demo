package com.concurrent.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock 的使用
 * 
 * @author pengm
 *
 */
public class LockDemo implements Runnable {
	// 定义电影票的总数
	private int tickets = 100;

	// 定义锁对象
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		while( true ) {
			try {
				// 加锁
				lock.lock();
				if( tickets > 0 ) {
					try {
						Thread.sleep( 100 );
					}
					catch( InterruptedException e ) {
						e.printStackTrace();
					}
					System.out.println( Thread.currentThread().getName() + "正在出售第" + (tickets--) + "张票" );
				}
			}

			finally {
				// 释放锁
				lock.unlock();
			}
		}
	}

	public static void main( String[] args ) {
		LockDemo str = new LockDemo();
		Thread tr1 = new Thread( str, "窗口1" );
	}
}
