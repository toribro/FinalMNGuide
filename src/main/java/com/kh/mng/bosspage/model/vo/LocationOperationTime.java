package com.kh.mng.bosspage.model.vo;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LocationOperationTime {
	
	private int operationNo; // 시퀀스 적용을 위해 필드 추가
    private int locationNo;
    private String day;
    private Time startTime;
    private Time endTime;
    private boolean restStatus;
	
}
