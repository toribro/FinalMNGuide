package com.kh.mng.common.chat.model.vo;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfo {
	private int userNo;
	private String userNickName;
	private String userId;
//	private String latestMessage;
//    private String lastestTime;
	private Map<String,String> lastestMessage;
    
    private int messageCount;
    
}
