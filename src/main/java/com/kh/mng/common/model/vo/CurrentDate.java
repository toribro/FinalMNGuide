package com.kh.mng.common.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
	// 현재 요일 반환 메소드
	public static String currentDay() {
		Date sysdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String date = sdf.format(sysdate);
		
		return date;
	}
}
