package com.daou.pjt2.pop3.controller;

import com.daou.pjt2.controller.AbstractController;
import com.daou.pjt2.pop3.service.MailDownloadServiceImpl;
import com.daou.pjt2.session.ClientSessionImpl;
import com.daou.pjt2.view.AlertNoMoreMailView;
import com.daou.pjt2.view.ClientView;

public class MailDownloadController extends AbstractController {

	protected ClientView alert = new AlertNoMoreMailView();
	
	public MailDownloadController(ClientSessionImpl session) {
		super(session);
	}
	
	@Override
	public void run() {
		MailDownloadServiceImpl downloader = new MailDownloadServiceImpl(session);
		downloader.download();
	}
}