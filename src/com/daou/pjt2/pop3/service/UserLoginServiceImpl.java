package com.daou.pjt2.pop3.service;

import java.util.Scanner;

import com.daou.pjt2.config.Config;
import com.daou.pjt2.session.ClientSessionImpl;

/*
 * ������ �α��� �α׾ƿ��� ����ϴ� Ŭ����
 */

public class UserLoginServiceImpl implements UserLoginService {
	
	private static ClientSessionImpl session;
	
	public UserLoginServiceImpl(ClientSessionImpl session) {
		UserLoginServiceImpl.session = session;
	}
	
	@Override
	public void login(Scanner sc) {
		System.out.println("Login: ���̵�� ��й�ȣ�� �Է��� �ּ���.");
		System.out.print("id: ");
		
		String user = sc.nextLine();
		session.sendCommand("USER "+user);
		Config.USER = user;
		
		System.out.print("password: ");
		String password = sc.nextLine();
		session.sendCommand("PASS "+password);
		Config.PASS = password;
	}
	
	@Override
	public void logout() {
		session.sendCommand("QUIT");
		session.disconnect();
	}
	
	@Override
	public void reLogin() throws Exception {
		if(Config.USER == null || Config.PASS == null) {
			throw new Exception("��α��ο� �����Ͽ����ϴ�.");
		}
		
		logout();
		session.connect();
		session.sendCommand("USER "+Config.USER);
		session.sendCommand("PASS "+Config.PASS);
		
		return;
	}
}
