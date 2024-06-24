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
public class DetailLocation {
	private int locationNo;//장소 번호
	private String locationName;//장소 이름
	private String locationPhone;//장소 전화번호
	private String explanation;//설명
	private double locationStar;//장소 평점
	private String reservationLink;//예약 링크
	private String address;//장소 주소
	private String locationCategoryNo;//카테고리 번호
	private String categoryName;//카테고리 명
	private int userNo;//유저넘버
	private String userKind;//사장여부
	private ArrayList<LocationOption> locationOption; //장소 옵션들
    private ArrayList<OperationTime>operationTime;//운영시간
    
    //출입등급,반려동물크기,반려동물 분류를 조인시킨 값들
    private ArrayList<PetKindGrade> petKindGrade;
  
     //메인 첨부파일들
    private ArrayList<Attachment> mainAttachMent; 
    
   //서브 첨부파일들
    private ArrayList<Attachment> detailAttachMent;

   
    
    
    
    
    


	
	
	
	
}
