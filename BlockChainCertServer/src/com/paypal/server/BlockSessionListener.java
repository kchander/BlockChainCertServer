package com.paypal.server;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class BlockSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		System.out.println("Session Created :: ID => " + sessionEvent.getSession().getId());
		System.out.println("BlockChain Server last accessed Time :: => " + sessionEvent.getSession().getLastAccessedTime());
		System.out.println("BlockChain Server was inactive during this period :: Server Inactivity ==>" + sessionEvent.getSession().getMaxInactiveInterval());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionDestroyed) {
		System.out.println("Session Destroyed :: Leaving out of the container => " + sessionDestroyed.getSession().getId());
	}

}
