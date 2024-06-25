package com.kh.mng.common.chat.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatInfo {
	private String userId;
	private String userNickName;
	private String message;
	private String targetId;
	private String roomNo;
	private int userNo;
	private int targetNo;
	private int locationNo;
}
