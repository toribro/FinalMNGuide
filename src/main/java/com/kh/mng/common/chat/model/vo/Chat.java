package com.kh.mng.common.chat.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Chat {
	private int chatNo;
	private String userId;
	private String userNickName;
	private String message;
	private String roomNo;
	private int userNo;
	private String targetId;
	private String enrollTime;
}
