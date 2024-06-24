package com.kh.mng.search.model.dto;

import java.util.ArrayList;

import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.location.model.vo.Location;

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
public class SearchFilter {
	private String keyword; // 검색 키워드
	private ArrayList<Integer> petList; // 선택한 반려동물 사이즈 리스트
	private ArrayList<Integer> locList; // 선택한 장소 카테고리 리스트
	private String order; // 정렬 기준
	private ArrayList<Location> locationList; // 조회된 장소 리스트
	private PageInfo pi; // 페이지 정보
	private String loginUserNo; // 로그인 된 유저 PK
}
