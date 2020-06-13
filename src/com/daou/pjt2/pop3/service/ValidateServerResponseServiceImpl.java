package com.daou.pjt2.pop3.service;

import com.daou.pjt2.session.ValidateServerResponse;

/*
 * ������ response�� üũ�ϰ� �̸� �� ���񽺿� �´� �α׸��� ó���� �� �ֵ��� �ϴ� Ŭ����
 * FIXME:: Ŭ���� �̸��� �޼ҵ� �̸� �� �ٲ���� (�ӽ�)
 */

public class ValidateServerResponseServiceImpl implements ValidateServerResponse {

	public boolean validateResponse(String response) {
		if(response == null) return false;
		
		if(response.equals(".")) return false;
		
		if(response.startsWith("+ERR "))
			throw new RuntimeException("Server has returned an error: "+ response.replaceFirst("-ERR ",""));
		
//		if(response.startsWith("+OK")) {
//			System.out.println(response);
//		}

		return true;
	}
}
