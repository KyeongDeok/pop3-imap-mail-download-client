package com.daou.pjt2.pop3.service;

import java.util.List;
import java.util.Map;

import com.daou.pjt2.model.MailModel;

public interface MailMessageService {
	
	Map <String, MailModel> getMessagesWithUniqId();
	List <MailModel> getMessages(int msgCnt);
	MailModel getMessage(int msgNum);
	int getMessageCount();
	
}
