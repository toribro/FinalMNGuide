package com.kh.mng.search.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mng.common.model.vo.CurrentDate;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.main.model.dao.MainDao;
import com.kh.mng.search.model.dao.SearchDao;
import com.kh.mng.search.model.dto.SearchFilter;
import com.kh.mng.search.model.dto.SearchPick;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int selectLocationListCount(String keyword) {
		return searchDao.selectLocationListCount(sqlSession, keyword);
	}
	
	@Override
	public int selectFilterLocationListCount(SearchFilter sf) {
		return searchDao.selectFilterLocationListCount(sqlSession, sf);
	}

	@Override
	@Transactional
	public ArrayList<Location> selectSearchLocationList(SearchFilter sf, PageInfo pi) {
		ArrayList<Location> list = searchDao.selectSearchLocationList(sqlSession, sf, pi);
		// list 해당 petSize
		for (Location loc : list) {
			loc.setLocationStarCount((int)Math.round(loc.getLocationStar()));
			loc.setCurrentDay(CurrentDate.currentDay()); // 현재 요일 (월 화 수 목 금 토 일 형식)
			loc.setEnterList(new MainDao().selectEnterGradeList(sqlSession, loc));
			loc.setOpTime(searchDao.selectOperationTime(sqlSession, loc));
			loc.setAttachment(new MainDao().selectAttachment(sqlSession, loc));
			if (sf.getLoginUserNo() != "") {
				loc.setUserNo(Integer.parseInt(sf.getLoginUserNo()));
			}
			loc.setUserPick(searchDao.selectUserPick(sqlSession, loc));
		}
		// list 해당 운영시간
		// 현재 요일 넘겨서 해당 요일 운영시간 가져오기
		// 찜 개수 가져오기
		
		return list;
	}

	@Override
	@Transactional
	public ArrayList<Location> selectFilterLocationList(SearchFilter sf, PageInfo pi) {
		ArrayList<Location> list = searchDao.selectFilterLocationList(sqlSession, sf, pi);
		// list 해당 petSize
		for (Location loc : list) {
			loc.setLocationStarCount((int)Math.round(loc.getLocationStar()));
			loc.setCurrentDay(CurrentDate.currentDay()); // 현재 요일 (월 화 수 목 금 토 일 형식)
			loc.setEnterList(new MainDao().selectEnterGradeList(sqlSession, loc));
			loc.setOpTime(searchDao.selectOperationTime(sqlSession, loc));
			loc.setAttachment(new MainDao().selectAttachment(sqlSession, loc));
			if (sf.getLoginUserNo() != "") {
				loc.setUserNo(Integer.parseInt(sf.getLoginUserNo()));
			}
			loc.setUserPick(searchDao.selectUserPick(sqlSession, loc));
		}
		// list 해당 운영시간
		// 현재 요일 넘겨서 해당 요일 운영시간 가져오기
		// 찜 개수 가져오기
		
		return list;
	}
	
	@Override
	public int selectUserPick(SearchPick pick) {
		return searchDao.selectUserPick(sqlSession, pick);
	}
	
	@Override
	@Transactional
	public SearchPick insertUserPick(SearchPick pick) {
		int result = searchDao.insertUserPick(sqlSession, pick);
		
		if (result > 0) {
			pick.setLocPickCount(searchDao.selectLocationPickCount(sqlSession, pick));
		}
		return pick;
	}
	
	@Override
	@Transactional
	public SearchPick deleteUserPick(SearchPick pick) {
		int result = searchDao.deleteUserPick(sqlSession, pick);
		
		if (result > 0) {
			pick.setLocPickCount(searchDao.selectLocationPickCount(sqlSession, pick));
		}
		return pick;
	}
}
