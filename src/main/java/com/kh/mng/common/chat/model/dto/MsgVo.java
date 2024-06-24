package com.kh.mng.common.chat.model.dto;



import lombok.Data;

@Data
public class MsgVo {
	private String msg;
	private String id;
	private String nick;
	private String target;
	private String time;
	private int userNo;
}
