package com.daou.pjt2.pop3.service;

import java.util.Scanner;

public interface UserLoginService {
	
	void login(Scanner sc);
	void logout();
	void reLogin() throws Exception;
	
}
