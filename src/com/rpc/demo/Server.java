package com.rpc.demo;

import java.io.IOException;

/**
 * 服务中心代码
 * 
 * @author pengm
 *
 */
public interface Server {
	public void stop();

	public void start() throws IOException;

	public void register( Class serviceInterface, Class impl );

	public boolean isRunning();

	public int getPort();
}
