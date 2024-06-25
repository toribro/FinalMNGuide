package com.kh.mng.community.model.vo;

import java.sql.Date;

import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.Video;

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
public class Shorts {	
	private int shortsNo; //숏츠번호
	private String shortsContent; //숏츠내용
	private int count; //조회수
	private Date enrollDate; //작성날짜
	private Date modifyDate; //수정날짜
	private boolean status; //숏츠상태
	private String userNo; //회웝번호
	private Attachment attachment; // 썸네일 첨부파일
	private Video video;//비디오첨부파일
}
