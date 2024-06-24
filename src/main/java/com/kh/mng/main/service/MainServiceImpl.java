package com.kh.mng.main.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mng.community.model.vo.Board;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.main.model.dao.MainDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	private MainDao mainDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	@Transactional
	public ArrayList<Location> ajaxSelectLocationMainList() {
		
		ArrayList<Location> list = new ArrayList<Location>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 1; i <= 3; i++) {
			map.put("locNum", i);
			Location loc = mainDao.selectLocationTop(sqlSession, map);
			loc.setAttachment(mainDao.selectAttachment(sqlSession, loc));
			loc.setEnterList(mainDao.selectEnterGradeList(sqlSession, loc));
			list.add(loc);
			map.remove("locNum");
		}
//		Location restaurant = mainDao.selectLocationTop(sqlSession, locNum1);
//		restaurant.setAtList(mainDao.selectAttachment(sqlSession, restaurant));
//		
//		Location cafe = mainDao.selectLocationTop(sqlSession, locNum2);
//		cafe.setAtList(mainDao.selectAttachment(sqlSession, cafe));
//		
//		Location hotel = mainDao.selectLocationTop(sqlSession, locNum3);
//		hotel.setAtList(mainDao.selectAttachment(sqlSession, hotel));
		return list;
	}

	@Override
	@Transactional
	public ArrayList<Board> ajaxSelectBoardCountList() {
		// type = 1 : count DESC로 결과를 먼저 가져온 후에 reply 개수를 가져올 것
		// type = 2 : GOOD 카운트를... 컬럼을 추가하는 게 나을까 / 없는 경우 good을 보드별로 다 카운트 한 데이터를 넣어야 함
		// type = 3 : reply 카운트도 마찬가지
		
		// 조회수 순 list 가져오기
		ArrayList<Board> countList = mainDao.ajaxSelectBoardCountList(sqlSession);
		
		// 해당 게시글의 reply 개수 가져오기
		for(int i = 0; i < countList.size(); i++) {
			int replyCount = mainDao.selectReplyCount(sqlSession, countList.get(i).getBoardNo());
			countList.get(i).setReplyCount(replyCount);
		}
		
		return countList;
	}
	
	@Override
	@Transactional
	public ArrayList<Board> ajaxSelectBoardGoodList() {
		// 좋아요 개수 카운트 해서 가장 많은 5개의 boardNo 가져오기
		ArrayList<Board> good = mainDao.selectGoodCountList(sqlSession);
		ArrayList<Board> goodList = new ArrayList<Board>();
		
		// 좋아요 수 많은 5개 게시글 정보와 댓글수 가져오기
		for(int i = 0; i < good.size(); i++) {
			Board b = mainDao.ajaxSelectBoardMain(sqlSession, good.get(i).getBoardNo());
			log.info(b.toString());
			Integer goodCount = mainDao.selectReplyCount(sqlSession, good.get(i).getBoardNo());
			log.info("{}", goodCount);
			if (goodCount == 0) {
				b.setReplyCount(0);
			} else {
				b.setReplyCount(goodCount);
			}

			goodList.add(b);
		}
		
		return goodList;
	}

	@Override
	@Transactional
	public ArrayList<Board> ajaxSelectBoardReplyList() {
		// 댓글 개수 카운트 해서 가장 많은 5개의 boardNo 가져오기
		ArrayList<Board> reply = mainDao.selectReplyCountList(sqlSession);
		ArrayList<Board> replyList = new ArrayList<Board>();
		log.info(reply.toString());
		// 댓글 개수 많은 5개 게시글 정보 가져오기
		for(int i = 0; i < reply.size(); i++) {
			Board b = mainDao.ajaxSelectBoardMain(sqlSession, reply.get(i).getBoardNo());
			log.info(b.toString());
			b.setReplyCount(mainDao.selectReplyCount(sqlSession, reply.get(i).getBoardNo()));
			replyList.add(b);
		}
		
		return replyList;
	}

	@Override
	@Transactional
	public ArrayList<Shorts> ajaxSelectShortsList() {
		ArrayList<Shorts> list = mainDao.ajaxSelectShortsList(sqlSession);
		
		for (Shorts s : list) {
			s.setAttachment(mainDao.selectAttachmentShorts(sqlSession, s));
		}
		return list;
	}

	@Override
	public int ajaxSelectPetCount(int userNo) {
		return mainDao.ajaxSelectPetCount(sqlSession, userNo);
	}

}
