package com.daou.pjt2.pop3.service;

import com.daou.pjt2.session.ClientSessionImpl;
import com.daou.pjt2.util.ConvertDataTypeUtil;
import com.daou.pjt2.util.ConvertToBytesUtil;

/*
 * 
 */

public class ShowMailDataServiceImpl implements ShowMailDataService {
	
	private ClientSessionImpl session;
	private final int MAIL_CNT = 1;
	private final int MAIL_SIZE = 2;
	
	public ShowMailDataServiceImpl(ClientSessionImpl session) {
		this.session = session;
	}
	
	@Override
	public void showMailData() {
		String response = session.sendCommand("STAT");
		String [] values = response.split(" ");
		
		System.out.println("메일 수: "+values[MAIL_CNT]+", 용량: "+ConvertToBytesUtil.convertBytesUtil(ConvertDataTypeUtil.stringToLong(values[MAIL_SIZE])));
	}
}
