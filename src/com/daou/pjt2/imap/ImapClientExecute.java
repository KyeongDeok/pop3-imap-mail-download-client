package com.daou.pjt2.imap;

import java.util.Scanner;

import com.daou.pjt2.config.Config;
import com.daou.pjt2.controller.ControllerExecute;
import com.daou.pjt2.imap.controller.ShowMailDataController;
import com.daou.pjt2.imap.service.ValidateResponseServiceImpl;
import com.daou.pjt2.imap.service.UserLoginServiceImpl;
import com.daou.pjt2.session.ClientSessionImpl;
import com.daou.pjt2.view.ClientExitView;
import com.daou.pjt2.view.ClientView;
import com.daou.pjt2.view.ImapClientHomeView;


public class ImapClientExecute {
	
	public enum Select {
		SHOW_MSG_STAT,
		SHOW_MSG_LIST,
		MSG_DOWNLOAD,
		RELOAD,
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
		
		//세션 연결
		ValidateResponseServiceImpl responseChecker = new ValidateResponseServiceImpl();
		ClientSessionImpl session = new ClientSessionImpl(Config.HOST, Config.IMAP_PORT, responseChecker);
		UserLoginServiceImpl userLoginService = new UserLoginServiceImpl(session);
		userLoginService.login(sc);
		
		ClientView view = new ImapClientHomeView();
		boolean flag = true;
		int selectNumber;
		Select select;
		ControllerExecute controllerExecute = null;
		
		while(flag) {
			view.show();
			selectNumber = sc.nextInt();
			select = Select.getValueByNum(selectNumber);

			switch(select) {
				case SHOW_MSG_STAT:
					controllerExecute = new ControllerExecute(new ShowMailDataController(session));
					break;
				
				case EXIT:
					flag = false;
					break;
				
				default:
					System.out.println("=== please enter... (1-5) ===");
					continue;
			}
			
			if(!flag) {
				view = new ClientExitView();
				view.show();
				
				userLoginService.logout();
				sc.close();
				
				break;
			}
			
			controllerExecute.execute();
		}
	}
}