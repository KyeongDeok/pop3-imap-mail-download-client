package com.daou.pjt2.pop3.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.daou.pjt2.model.MailModel;
import com.daou.pjt2.session.ClientSessionImpl;

/*
 * 메일의 메세지를 가져오고 이를 알맞은 자료형으로 리턴하는 기능을 하는 클래스
 */

public class MailMessageServiceImpl implements MailMessageService {
	
	private ClientSessionImpl session;
	private final int MAIL_CNT = 1;
	
	public MailMessageServiceImpl(ClientSessionImpl session) {
		this.session = session;
	}
	
	@Override
	public Map <String, MailModel> getMessagesWithUniqId() {
		
		DownloadedUniqIdServiceImpl mailUniqService = new DownloadedUniqIdServiceImpl(session);
		HashMap <String,MailModel> messageMap = new HashMap<String, MailModel>();
		
		List <String> uniqIds = new LinkedList<>();
		List <MailModel> msgs = new LinkedList<>();
		
		int msgCnt = getMessageCount();
		
		uniqIds = mailUniqService.getMailUniqueIds(msgCnt);
		msgs = getMessages(msgCnt);
		
		for(int msgNum=1; msgNum<msgCnt; msgNum++) {
			messageMap.put(uniqIds.get(msgNum), msgs.get(msgNum));
		}
		
		return messageMap;
	}
	
	@Override
	public MailModel getMessage(int msgNum) {
		StringBuilder contents = new StringBuilder();
		
		List <String> responseList = session.sendCommand("RETR "+ msgNum, true);
		
		for(String response : responseList) {
			contents.append(response+"\n");
		}
		
		return new MailModel(contents.toString());
	}
	
	@Override
	public List <MailModel> getMessages(int msgCnt) {
		List <MailModel> msgs = new LinkedList<>();
		
		for(int msgNum=1; msgNum<=msgCnt; msgNum++) {
			msgs.add(getMessage(msgNum));
		}
		
		return msgs;
	}
	
	@Override
	public int getMessageCount() {
		String response = session.sendCommand("STAT");
		String [] values = response.split(" ");
		
		return Integer.parseInt(values[MAIL_CNT]);
	}
}
