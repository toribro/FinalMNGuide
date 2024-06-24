package com.kh.mng.location.model.vo;

import java.sql.Date;
import java.sql.Time;
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
public class Review {
	private int reviewNo;
	private int reviewStar; //리뷰 평점
	private String  reviewContent;
	private Date enrollDate;
	private Date modifyDate;
	private String userNickName;
	private double locationStar;//장소 평균평점
	private int userNo;
	private int locationNo;
    private ArrayList<Attachment> attachment; //첨부파일
    private Attachment userProfile;//유저 프로필
    private MasterReply masterReply;//사장님답글
    
}
