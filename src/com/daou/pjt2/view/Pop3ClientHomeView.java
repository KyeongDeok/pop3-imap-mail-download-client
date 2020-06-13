package com.daou.pjt2.view;

public class Pop3ClientHomeView implements ClientView {
	
	@Override
	public void show()  {
		System.out.println("1. 가져올 총 메일 갯수와 용량 표시");
		System.out.println("2. 전체 메일 리스트 가져오기");
		System.out.println("3. 전체 메일 다운로드");
		System.out.println("4. 새로고침");
		System.out.println("5. 프로그램 종료");
	}
}
