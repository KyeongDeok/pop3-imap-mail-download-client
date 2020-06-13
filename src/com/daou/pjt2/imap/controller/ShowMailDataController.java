package com.daou.pjt2.imap.controller;

import com.daou.pjt2.controller.AbstractController;
import com.daou.pjt2.imap.service.ShowMailDataServiceImpl;
import com.daou.pjt2.session.ClientSessionImpl;

public class ShowMailDataController extends AbstractController {
	
	public ShowMailDataController(ClientSessionImpl session) {
		super(session);
	}
	
	@Override
	public void run() {
		ShowMailDataServiceImpl showMailDataService = new ShowMailDataServiceImpl(session);
		
		showMailDataService.showMailData();
	}
}
