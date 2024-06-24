package com.kh.mng.member.model.vo;

import java.sql.Date;

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

public class Member {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userNickname;
	private String userGender;
	private String userEmail;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	private Date userBirthday;
	private String userPhone;
	private String userKind;
	private ProfileImg userProfile;
}
