package com.daou.pjt2.util;

public class MailSizeFromResponseUtil {
	private final static int MAIL_SIZE = 6;
	
	public static long getMailSize(String response) {
		String [] resTokens;
		long mailSize = 0;
		
		resTokens = response.split(" ");
		
		mailSize+=Long.parseLong(resTokens[MAIL_SIZE].substring(0, resTokens[MAIL_SIZE].length() -1));
		
		return mailSize;
	}
}
