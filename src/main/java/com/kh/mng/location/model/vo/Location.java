package com.kh.mng.location.model.vo;

import java.sql.Date;
import java.util.ArrayList;

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
public class Location {
	private int locationNo;
	private String locationName;
	private String locationPhone;
	private String explanation;
	private double locationStar;
	private String reservationLink;
	private String businessNo;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private boolean status;
    private int userNo;
	private String locationCategoryNo;
	private ArrayList<Attachment> atList; // 사진 여러 장
	private Attachment attachment; // 사진 한 장
	private ArrayList<EnterGrade> enterList; // 출입 등급
	private OperationTime opTime; // 운영 시간 한 가지
	private String currentDay; // 현재 요일
	private int pickCount; // 전체 공감 개수
	private int userPick; // 유저가 공감한 가게인지
	private int locationStarCount; // 평점 반올림한 수
	
}
