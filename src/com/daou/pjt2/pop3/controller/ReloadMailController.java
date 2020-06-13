package com.daou.pjt2.pop3.controller;

import com.daou.pjt2.controller.AbstractController;
import com.daou.pjt2.pop3.service.UserLoginServiceImpl;
import com.daou.pjt2.session.ClientSessionImpl;

public class ReloadMailController extends AbstractController {
	
	public ReloadMailController(ClientSessionImpl session) {
		super(session);
	}
	
	@Override
	public void run() {
		UserLoginServiceImpl userLoginService = new UserLoginServiceImpl(session);
		
		try {
			userLoginService.reLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
