package com.daou.pjt2.imap.service;

import java.util.List;

import com.daou.pjt2.session.ClientSessionImpl;
import com.daou.pjt2.util.ConvertToBytesUtil;
import com.daou.pjt2.util.MailSizeFromResponseUtil;

public class ShowMailDataServiceImpl implements ShowMailDataService {
	
	private ClientSessionImpl session;
	private final String ALL_MAIL_LIST_COMMAND = "A FETCH 1:* (UID RFC822.SIZE)";
	private final int MAIL_CNT = 1;
	
	public ShowMailDataServiceImpl(ClientSessionImpl session) {
		this.session = session;
	}
	
	@Override
	public void showMailData() {
		String mailCnt = session.sendCommand("A SELECT INBOX").split(" ")[MAIL_CNT];
		session.readResponseAllLine();
		
		System.out.println("메일 수: "+mailCnt+", 전체 용량: "+ConvertToBytesUtil.convertBytesUtil(calcMailSize()));
	}

	@Override
	public long calcMailSize() {
		long mailSize=0;
		List <String> responseList = session.sendCommand(ALL_MAIL_LIST_COMMAND, true);
		
		for(String response : responseList) {
			mailSize += MailSizeFromResponseUtil.getMailSize(response);
		}
		
		return mailSize;
	}
}
