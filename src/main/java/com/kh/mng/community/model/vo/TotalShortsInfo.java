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
public class TotalShortsInfo {
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
	private boolean isLike;
	
	
	public TotalShortsInfo(int shortsNo, Date enrollDate, String shortsContent, String shortsPath, String shortsName, String userNickname, String profilePath, String profileName) {
        this.shortsNo = shortsNo;
        this.enrollDate = enrollDate;
        this.shortsContent = shortsContent;
        this.shortsPath = shortsPath;
        this.shortsName = shortsName;
        this.userNickname = userNickname;
        this.profilePath = profilePath;
        this.profileName = profileName;
    }
	
}
