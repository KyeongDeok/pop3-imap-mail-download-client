package com.daou.pjt2.pop3.controller;

import com.daou.pjt2.controller.AbstractController;
import com.daou.pjt2.pop3.service.ShowMailListServiceImpl;
import com.daou.pjt2.session.ClientSessionImpl;

public class ShowMailListController extends AbstractController {
	
	public ShowMailListController(ClientSessionImpl session) {
		super(session);
	}
	
	@Override
	public void run() {
		ShowMailListServiceImpl showlist =  new ShowMailListServiceImpl();
		
		showlist.showMessageList(session);
	}
}
