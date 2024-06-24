package com.kh.mng.location.model.vo;

import java.sql.Date;
import java.util.List;

import com.kh.mng.common.model.vo.ProfileImg;

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
public class MyPageReview {
	private int reviewNo;
	private int userNo;
	private String reviewContent;
	private String userNickName;
	private Date modifyDate;
	private String locationName;
	private int reviewStar;
	private int locationNo;
	private List<ProfileImg> reviewImg;
}
