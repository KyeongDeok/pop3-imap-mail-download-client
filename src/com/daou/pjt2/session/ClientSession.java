package com.daou.pjt2.session;

import java.util.List;

public interface ClientSession {
	
	String sendCommand(String command);
	String readResponseLine();
	List<String> readResponseAllLine();
	
	void connect();
	void disconnect();
	
}