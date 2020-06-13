package com.daou.pjt2.pop3.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.daou.pjt2.pop3.config.Config;
import com.daou.pjt2.session.ClientSessionImpl;
import com.daou.pjt2.util.MakeSimpleFilePathUtil;

/*
 * ������ �� ����ũ ���̵� �����ϰ� ó���ϴ� Ŭ����
 */

public class DownloadedUniqIdServiceImpl implements DownloadedUniqueIdListService {
	
	private ClientSessionImpl session;
	private final int UNIQUE_ID=2;
	
	//FIXME:: ���� �ٿ�ε� path �޴� �� �����ؾ���.
	private String uniqIdFilePath = MakeSimpleFilePathUtil.getFilePath(Config.FILE_DOWNLOAD_DIRECTORY, Config.UNIQ_ID_FILE_NAME);
	
	public DownloadedUniqIdServiceImpl (ClientSessionImpl session) {
		this.session = session;
	}
	
	public Set <String> getCurrentDownloadedMailUniqueIds(){
		HashSet <String> hs = new HashSet<>();
		try {
			File file = new File(uniqIdFilePath);
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			BufferedReader br = new BufferedReader(new FileReader(uniqIdFilePath));
			String id;
			
			while((id = br.readLine()) != null) {
				hs.add(id);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hs;
	}
	
	@Override
	public String getMailUniqueId(int index) {
		String res = session.sendCommand("UIDL "+index);
		String [] value = res.split(" ");
		
		return value[UNIQUE_ID];
	}
	
	public List<String> getMailUniqueIds(int numOfMessages) {
		List <String> uniqIds = new LinkedList<>();
		for(int msgNum=1; msgNum<=numOfMessages; msgNum++) {
			uniqIds.add(getMailUniqueId(msgNum));
		}
		
		return uniqIds;
	}
	
	public void writeMailUniqueIds(Set <String> hs) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(uniqIdFilePath));
			for(String s : hs) {
				bw.write(s+"\r\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
