package com.daou.pjt2.imap.service;

import com.daou.pjt2.session.ValidateServerResponse;

/*
 * response�� �ٷ�� Ŭ����
 * Ŭ���� �̸��� �޼ҵ� �̸� �� �ٲ���� (�ӽ�)
 */

public class ValidateResponseServiceImpl implements ValidateServerResponse {

	public boolean validateResponse(String response) {
		
		if(response == null) return false;
			
		if(response.startsWith("+ERR "))
			throw new RuntimeException("Server has returned an error: "+ response.replaceFirst("-ERR ",""));
		
		if(response.startsWith("A OK")) {
			System.out.println(response);
			return false;
		}
		
		return true;
	}
}
