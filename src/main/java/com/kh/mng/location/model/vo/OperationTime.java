package com.kh.mng.location.model.vo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import com.kh.mng.common.model.vo.Attachment;

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
public class OperationTime {

	private Time startTime;//시작시간
	private Time endTime;//종료시간
	private boolean restStatus;//휴무여부
	private String day; //체크인 체크아웃
}
