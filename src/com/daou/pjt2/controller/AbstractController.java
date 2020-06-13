package com.daou.pjt2.controller;

import com.daou.pjt2.session.ClientSessionImpl;

public abstract class AbstractController {
	protected ClientSessionImpl session;
	
	public  AbstractController(ClientSessionImpl session) {
		this.session = session;
	}
	
	public abstract void run();
}