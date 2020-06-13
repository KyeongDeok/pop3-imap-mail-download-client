package com.daou.pjt2.view;

import java.util.Map;

import com.daou.pjt2.util.ConvertDataTypeUtil;
import com.daou.pjt2.util.ConvertToBytesUtil;


public class ShowMailListVIew <T extends Map<Integer, String>> implements ClientViewWithParams<T>{
	
	@Override
	public void show(T args) {
		args.entrySet().stream().sorted((e1,e2) -> e1.getKey() - e2.getKey()).forEach(entry -> System.out.println(entry.getKey()+". "+ConvertToBytesUtil.convertBytesUtil(ConvertDataTypeUtil.stringToLong(entry.getValue()))));
	}
}
