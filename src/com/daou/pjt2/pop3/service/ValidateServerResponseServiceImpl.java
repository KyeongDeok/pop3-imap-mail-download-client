package com.daou.pjt2.pop3.service;

import com.daou.pjt2.session.ValidateServerResponse;

/*
 * 서버의 response를 체크하고 이를 각 서비스에 맞는 로그만을 처리할 수 있도록 하는 클래스
 * FIXME:: 클래스 이름과 메소드 이름 다 바꿔야함 (임시)
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
