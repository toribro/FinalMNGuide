package com.kh.mng.community.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyInfo {
	private int replyNo;//부모 댓글 번호
	//private int replyReplyNo;//자식 댓글 번호
	private int boardNo;
	private String content;
	private int userNo;
	private int pageNo;
}
