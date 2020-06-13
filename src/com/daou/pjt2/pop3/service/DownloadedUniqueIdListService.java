package com.daou.pjt2.pop3.service;

import java.util.Set;

public interface DownloadedUniqueIdListService {
	
	String getMailUniqueId(int index);
	void writeMailUniqueIds(Set <String> hs);
}
