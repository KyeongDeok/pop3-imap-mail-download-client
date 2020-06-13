package com.daou.pjt2.pop3.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.daou.pjt2.model.MailModel;
import com.daou.pjt2.pop3.config.Config;
import com.daou.pjt2.session.ClientSessionImpl;
import com.daou.pjt2.util.MakeSimpleFilePathUtil;

/*
 * 메일을 다운로드 받기 위한 클래스
 */

public class MailDownloadServiceImpl  implements MailDownloadService {
	
	private BufferedWriter bw;
	
	//FIXME:: 파일 다운로드 path 받는 법 수정해야함.
	private String emailFilePath = MakeSimpleFilePathUtil.getFilePath(Config.FILE_DOWNLOAD_DIRECTORY,Config.EMAIL_FILE_NAME);
	private ClientSessionImpl session;
	
	public MailDownloadServiceImpl(ClientSessionImpl session){
		try {
			bw = new BufferedWriter(new FileWriter(emailFilePath,true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.session = session;
	}
	
	@Override
	public void download() {
		DownloadedUniqIdServiceImpl mailUniqIdService = new DownloadedUniqIdServiceImpl(session);
		MailMessageServiceImpl mailMessageService = new MailMessageServiceImpl(session);

		Set <String> hs = mailUniqIdService.getCurrentDownloadedMailUniqueIds();
		Map <String, MailModel> messageHashMap = mailMessageService.getMessagesWithUniqId();
		Set <String> uniqIdHs = new HashSet<>();
		
		try {
			for(Map.Entry<String, MailModel> m : messageHashMap.entrySet()) {
				uniqIdHs.add(m.getKey());
				
				if(hs.contains(m.getKey())) continue;
				
				try {
					System.out.println(m.getKey());
					bw.append(m.getValue().getContents());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		mailUniqIdService.writeMailUniqueIds(uniqIdHs);
		System.out.println("complete!");
	}
}
