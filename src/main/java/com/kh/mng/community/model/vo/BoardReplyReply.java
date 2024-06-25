package com.kh.mng.community.model.vo;

import java.sql.Date;

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
public class BoardReplyReply {
	private int replyNo;
	private String userNickName;
	private String content;
	private Date createDate;
	private Date editDate;
	private boolean status;
	private int userNo;
	private int boardNo;
	private int shortsNo;
	private int replyReNo;
	private Attachment replyUserProfile;
}
