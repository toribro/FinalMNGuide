package com.kh.spring.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//REPLY_NO
//REPLY_CONTENT
//REF_BNO
//REPLY_WRITER
//CREATE_DATE
//STATUS

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private int replyNo;
	private String replyContent;
	private String refBno;
	private String replyWriter;
	private String createDate;
	private String status;
}
