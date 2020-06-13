package com.daou.pjt2;

import java.util.Scanner;

import com.daou.pjt2.imap.ImapClientExecute;
import com.daou.pjt2.pop3.Pop3ClientExecute;

/*
 * imap or pop3의 조건을 받아 처리하는 클래스
 */
public class ClientMainExecuter {
	
	public enum Select {
		IMAP,
		POP3,
		EXIT,
		DEFAULT;
		
		public static Select getValueByNum(int num) {
			Select [] selects = values();
			int len = selects.length;
			
			if(len <= num || num < 0) {
				return DEFAULT;
			}else {
				return selects[num-1];
			}
		}
	}
	
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.imap, 2.pop3");
		
		int selectNumber = sc.nextInt();
		Select select = Select.getValueByNum(selectNumber);
		
		switch(select) {
			case IMAP:
				ImapClientExecute.execute();
				break;
				
			case POP3:
				Pop3ClientExecute.execute();
				break;
				
			default :
				System.out.println("please enter...(1-2)");
				break;
		}
		sc.close();
	}
}
