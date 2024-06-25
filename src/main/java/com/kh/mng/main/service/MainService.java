package com.kh.mng.main.service;

import java.util.ArrayList;

import com.kh.mng.community.model.vo.Board;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.location.model.vo.Location;

public interface MainService {
	// 식당, 카페, 숙소 중 별점이 높은 장소 하나씩 가져옴
	ArrayList<Location> ajaxSelectLocationMainList();
	
	// 커뮤니티의 조회수가 많은 글들 5위까지 가져옴
	ArrayList<Board> ajaxSelectBoardCountList();
	
	// 커뮤니티의 추천수가 많은 글들 5위까지 가져옴
	ArrayList<Board> ajaxSelectBoardGoodList();
	
	// 커뮤니티의 댓글수가 많은 글들 5위까지 가져옴
	ArrayList<Board> ajaxSelectBoardReplyList();
	
	// 조회수 높은 쇼츠 3위까지 가져옴
	ArrayList<Shorts> ajaxSelectShortsList();
	
	// userNo에 해당하는 반려동물의 수 가져옴
	int ajaxSelectPetCount(int userNo);
}
