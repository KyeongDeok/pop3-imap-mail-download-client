package com.daou.pjt2.imap.service;

import java.util.Scanner;

import com.daou.pjt2.config.Config;
import com.daou.pjt2.session.ClientSessionImpl;



public class UserLoginServiceImpl implements UserLoginService {
	
	private static ClientSessionImpl session;
	
	public UserLoginServiceImpl(ClientSessionImpl session) {
		UserLoginServiceImpl.session = session;
	}
	
	@Override
	public void login(Scanner sc) {
		System.out.println("Login: 아이디와 비밀번호를 입력해 주세요.");
		
		System.out.print("id: ");
		String user = sc.nextLine();
		Config.USER = user;
		
		System.out.print("password: ");
		String password = sc.nextLine();
		Config.PASS = password;
		
		session.sendCommand("A LOGIN "+Config.USER+"@"+Config.HOST+" "+Config.PASS);
	}
	
	@Override
	public void logout() {
		session.sendCommand("A LOGOUT");
		session.disconnect();
	}
	
	@Override
	public void reLogin() throws Exception {
		if(Config.USER == null || Config.PASS == null) {
			throw new Exception("재로그인에 실패하였습니다.");
		}
		
		logout();
		session.connect();
		session.sendCommand("A LOGIN "+Config.USER+"@"+Config.HOST+" "+Config.PASS);
		return;
	}
}
