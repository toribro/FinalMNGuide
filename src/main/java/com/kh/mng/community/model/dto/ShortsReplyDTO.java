package com.kh.mng.community.model.dto;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortsReplyDTO {
	private int replyNo;
	private int userNo;
	private String comment;
	private int shortsNo;
}
