package com.kh.mng.location.model.vo;

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
public class WishList {
	private int locationNo;
	private String locationName;
	private String locationPhone;
	private double locationStar;
	private String address;
	private String status;
	private int locationCategoryNo;
	private String categoryName;//카테고리 명
    private ArrayList<OperationTime>operationTime;//운영시간
    private ArrayList<PetKindGrade> petKindGrade;
    private int wishCount;
}
