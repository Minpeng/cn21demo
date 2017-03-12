package com.concurrent.demo;

/**
 * 线程通信 :一个线程打印数字 ,另外一个线程打印字母
 * 
 * @author pengmin
 * @return 1a2b3c
 */
public class SynchronizedTest {
	/**
	 * 打印数
	 * 
	 * @author pengmin
	 *
	 */
	static class printNum implements Runnable {
		// 声明一个类的引用
		private Object obj;

		// 通过构造器将共享的资源（对象）传进来
		public printNum(Object obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (obj) {
				for (int i = 1; i < 53; i++) {
					System.out.print(i + " ");
					if (i % 2 == 0) {
						// 唤醒打印字母进程
						/** 先唤醒 再等待 **/
						obj.notifyAll();
						try {
							obj.wait();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	static class printLetter implements Runnable {
		private Object obj;

		public printLetter(Object obj) {
			this.obj = obj;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (obj) {
				for (int i = 0; i < 26; i++) {
					System.out.print((char) ('A' + i) + "\n");
					obj.notifyAll();
					try {
						if (i != 25) {
							obj.wait();

						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}

	}

	public static void main(String[] args) {

		Object obj = new Object();
		printNum pNum = new printNum(obj);
		printLetter pLetter = new printLetter(obj);

		Thread threadNum = new Thread(pNum);
		Thread threadLetter = new Thread(pLetter);
		/* 数字先打印 */
		threadNum.start();
		threadLetter.start();

	}
}
