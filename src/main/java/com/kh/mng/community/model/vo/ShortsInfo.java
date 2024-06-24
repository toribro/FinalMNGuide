package com.kh.mng.community.model.vo;

import java.sql.Date;

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
public class ShortsInfo {
	private int shortsNo;
	private Date enrollDate;
	private String shortsContent;
	private String shortsPath; // 숏츠 파일 경로
	private String shortsName; // 숏츠 파일 이름
	private String userNickname;
	private String profilePath;
	private String profileName;
	private int likeCount;
	private int replyCount;
}