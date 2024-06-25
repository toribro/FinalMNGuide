package com.kh.mng.main.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.community.model.vo.Board;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.location.model.vo.EnterGrade;
import com.kh.mng.location.model.vo.Location;

@Repository
public class MainDao {
	public Location selectLocationTop(SqlSessionTemplate sqlSession, HashMap<String, Integer> map) {
		return sqlSession.selectOne("mainMapper.selectLocationTop", map);
	}

	public Attachment selectAttachment(SqlSessionTemplate sqlSession, Location loc) {
		return sqlSession.selectOne("mainMapper.selectAttachmentLocation", loc);
	}
	
	public ArrayList<EnterGrade> selectEnterGradeList(SqlSessionTemplate sqlSession, Location loc) {
		return (ArrayList)sqlSession.selectList("mainMapper.selectEnterGradeList", loc);
	}
	
	public ArrayList<Board> ajaxSelectBoardCountList(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("mainMapper.ajaxSelectBoardCountList");
	}
	
	public ArrayList<Board> selectGoodCountList(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("mainMapper.selectGoodCountList");
	}
	
	// 댓글수 많은 것 boardNo 5개
	public ArrayList<Board> selectReplyCountList(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("mainMapper.selectReplyCountList");
	}
	
	// 넘긴 boardNo에 해당하는 게시글 하나
	public Board ajaxSelectBoardMain(SqlSessionTemplate sqlSession, int boardNo){
		return sqlSession.selectOne("mainMapper.ajaxSelectBoardMain", boardNo);
	}
	
	// 넘긴 boardNo에 해당하는 댓글수
	public int selectReplyCount(SqlSessionTemplate sqlSession, int boardNo){
		return sqlSession.selectOne("mainMapper.selectReplyCount", boardNo);
	}
	
	public ArrayList<Shorts> ajaxSelectShortsList(SqlSessionTemplate sqlSession){
		return (ArrayList)sqlSession.selectList("mainMapper.ajaxSelectShortsList");
	}
	
	public Attachment selectAttachmentShorts(SqlSessionTemplate sqlSession, Shorts s) {
		return sqlSession.selectOne("mainMapper.selectAttachmentShorts", s);
	}
	
	public int ajaxSelectPetCount(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectOne("mainMapper.ajaxSelectPetCount", userNo);
	}
	
//	1. COUNT 순서대로 조회한 걸 DESC
//	2. BOARD_NO을 가진 GOOD을 COUNT 해서 GOOD 개수 기준으로 DESC
//	3. BOARD_NO을 가진 REPLY를 COUNT 해서 REPLY 개수 기준으로 DESC
//	그런데 모든 경우의 수에 반드시 REPLY 개수를 COUNT 해서 조회해야 함
	
}
