package com.kh.mng.bosspage.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BossPage {
	
	private int userNo;
	private String bossId;
	private String bossPwd;
	private String bossName;
	private String gender;
	private String email;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	private Date bossBirthday;
	private String phoneNumber;
	private String userKind;
}
