package com.kh.mng.search.service;

import java.util.ArrayList;

import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.search.model.dto.SearchFilter;
import com.kh.mng.search.model.dto.SearchPick;

public interface SearchService {
	// 검색 페이지 키워드 별 조회 결과 개수
	int selectLocationListCount(String keyword);
	
	// 검색 페이지 필터링 별 조회 결과 개수
	int selectFilterLocationListCount(SearchFilter sf);
	
	// 장소 키워드로 검색
	ArrayList<Location> selectSearchLocationList(SearchFilter sf, PageInfo pi);
	
	// 키워드 가지고 필터링 해서 장소 검색
	ArrayList<Location> selectFilterLocationList(SearchFilter sf, PageInfo pi);
	
	// 사용자 찜 여부 확인
	int selectUserPick(SearchPick pick);
	
	// 사용자 찜하기
	SearchPick insertUserPick(SearchPick pick);
	
	// 사용자 찜 삭제
	SearchPick deleteUserPick(SearchPick pick);
}
 