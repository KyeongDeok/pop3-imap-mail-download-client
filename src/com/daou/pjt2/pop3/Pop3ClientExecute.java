package com.daou.pjt2.pop3;

import java.util.Scanner;

import com.daou.pjt2.config.Config;
import com.daou.pjt2.controller.ControllerExecute;
import com.daou.pjt2.pop3.controller.MailDownloadController;
import com.daou.pjt2.pop3.controller.ReloadMailController;
import com.daou.pjt2.pop3.controller.ShowMailDataController;
import com.daou.pjt2.pop3.controller.ShowMailListController;
import com.daou.pjt2.pop3.service.ValidateServerResponseServiceImpl;
import com.daou.pjt2.pop3.service.UserLoginServiceImpl;
import com.daou.pjt2.session.ClientSessionImpl;
import com.daou.pjt2.view.ClientExitView;
import com.daou.pjt2.view.ClientView;
import com.daou.pjt2.view.Pop3ClientHomeView;

public class Pop3ClientExecute {
	
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
		//FIXME:: response checker를 생성자 말고 객체 생성하는 방식으로 변경해야할 듯.
		ValidateServerResponseServiceImpl responseChecker = new ValidateServerResponseServiceImpl();
		ClientSessionImpl session = new ClientSessionImpl(Config.HOST, Config.POP3_PORT,responseChecker);
		
		//로그인
		UserLoginServiceImpl userLoginService = new UserLoginServiceImpl(session);
		userLoginService.login(sc);
		
		ClientView view = new Pop3ClientHomeView();
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
				
				case SHOW_MSG_LIST:
					controllerExecute = new ControllerExecute(new ShowMailListController(session));
					break;
				
				case MSG_DOWNLOAD:
					controllerExecute = new ControllerExecute(new MailDownloadController(session));
					break;
					
				case RELOAD:
					controllerExecute = new ControllerExecute(new ReloadMailController(session));
					break;
				
				case EXIT:
					flag = false;
					break;
				
				default:
					System.out.println("");
					System.out.println("=== please enter... (1-6) ===");
					System.out.println("");
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
