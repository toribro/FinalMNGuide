package com.kh.mng.location.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MasterReply {
	private int ownerReplyNo;
	private String ownerReplyContent;
	private Date modifyDate;
	private Date enrollDate;
	private int reviewNo;
}
