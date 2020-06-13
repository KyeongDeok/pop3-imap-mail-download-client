package com.daou.pjt2.pop3.service;

import java.util.HashMap;
import java.util.List;

import com.daou.pjt2.session.ClientSessionImpl;
import com.daou.pjt2.util.ConvertDataTypeUtil;
import com.daou.pjt2.view.ShowMailListVIew;

/*
 * ������ ������ �ִ� ���� ����Ʈ�� �����ִ� Ŭ����
 */

public class ShowMailListServiceImpl implements ShowMailListService {
	
	private final int MSG_INDEX = 0;
	private final int MSG_SIZE = 1;
	
	@Override
	public void showMessageList(ClientSessionImpl session) {
		HashMap <Integer,String> hm = new HashMap<>();
		String [] message;
		
		session.sendCommand("LIST");
		List <String> responseList = session.readResponseAllLine();
		
		for(String response : responseList) {
			message = response.split(" ");
			
			hm.put(ConvertDataTypeUtil.stringToInteger(message[MSG_INDEX]), message[MSG_SIZE]);
		}

		ShowMailListVIew<HashMap<Integer,String>> view = new ShowMailListVIew<>();
		view.show(hm);
	}
}
