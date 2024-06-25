package com.kh.mng.community.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.community.model.dto.BoardEnroll;
import com.kh.mng.community.model.dto.BoardFileInfo;
import com.kh.mng.community.model.dto.BoardInfo;
import com.kh.mng.community.model.dto.DeleteBoardAttachmentInfo;
import com.kh.mng.community.model.dto.ForIsLike;
import com.kh.mng.community.model.dto.ReplyInfo;
import com.kh.mng.community.model.dto.ShorstInfo;
import com.kh.mng.community.model.dto.ShortsFileInfo;
import com.kh.mng.community.model.dto.ShortsReplyDTO;
import com.kh.mng.community.model.vo.BoardCategory;
import com.kh.mng.community.model.vo.CommunityBoard;
import com.kh.mng.community.model.vo.BoardReply;
import com.kh.mng.community.model.vo.BoardReplyReply;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.community.model.vo.ShortsInfo;
import com.kh.mng.community.model.vo.ShortsReply;
import com.kh.mng.community.model.vo.TotalShortsInfo;

@Repository
public class CommunityDao {
	
	public TotalShortsInfo getVideoInfo(SqlSessionTemplate sqlSession, int videoId) {
		return sqlSession.selectOne("shortsMapper.getVideoInfo", videoId);
	}
	
	public int getVideoLikeCount(SqlSessionTemplate sqlSession, int shortsNum) {
		return sqlSession.selectOne("shortsMapper.getVideoLikeCount", shortsNum);
	}

	public int getVideoReplyCount(SqlSessionTemplate sqlSession, int shortsNum) {
		return sqlSession.selectOne("shortsMapper.getVideoReplyCount", shortsNum);
	}

	public int addComment(SqlSessionTemplate sqlSession, ShortsReplyDTO shortsReplyDTO) {
	    
		return sqlSession.insert("shortsMapper.shortsCommentEnroll", shortsReplyDTO);
	}


	public ArrayList<Shorts> selectShortsList(SqlSessionTemplate sqlSession,PageInfo pi) {
		
		int offset=(pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("communityShortsMapper.communityShortsSelectList",null,rowBounds);
	}

	public Attachment selectOneShortAttachment(SqlSessionTemplate sqlSession,int shortsNo) {
		
		return sqlSession.selectOne("communityShortsMapper.selectOneShortAttachment",shortsNo);
	}

	public int selectShortsCount(SqlSessionTemplate sqlSession) {
	
		return sqlSession.selectOne("communityShortsMapper.selectShortsCount");
	}
	
	public ArrayList<CommunityBoard> selectBoardList(SqlSessionTemplate sqlSession, PageInfo pi, BoardInfo boardInfo) {
		
		int offset=(pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("communityBoardMapper.selectBoardList",boardInfo,rowBounds);
	}
	
	public int selectBoardCount(SqlSessionTemplate sqlSession, BoardInfo boardInfo) {
		
		return sqlSession.selectOne("communityBoardMapper.selectBoardCount",boardInfo);
	}
	
	public ArrayList<Attachment> selectBoardAttachMent(SqlSessionTemplate sqlSession,int boardNo) {
	
		return (ArrayList)sqlSession.selectList("communityBoardMapper.selectBoardAttachment",boardNo);
	}

	public int selectBoardApplyCount(SqlSessionTemplate sqlSession,int boardNo) {
		
		return sqlSession.selectOne("communityBoardMapper.selectBoardApplyCount",boardNo);
	}

	public ArrayList<BoardCategory> selectBoardCategoryList(SqlSessionTemplate sqlSession) {
		
		return (ArrayList) sqlSession.selectList("communityBoardMapper.selectBoardCategoryList");
	}

	public Attachment selectUserProfile(SqlSessionTemplate sqlSession, int userNo) {
		
		return sqlSession.selectOne("communityBoardMapper.selectUserAttachment",userNo);
	}
	
	public CommunityBoard selectBoardDetail(SqlSessionTemplate sqlSession,int bno) {
		
	  return sqlSession.selectOne("communityBoardMapper.selectDetailBoard", bno);
	}
	
	public ArrayList<BoardReply> selectBoardReplys(SqlSessionTemplate sqlSession, PageInfo replyPi,int boardNo) {
		
		int offset=(replyPi.getCurrentPage()-1)*replyPi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,replyPi.getBoardLimit());
		
		return  (ArrayList) sqlSession.selectList("communityBoardMapper.selectDetailBoardReply",boardNo,rowBounds);
	}
	
	public ArrayList<BoardReplyReply> selectBoardrReplyReplys(SqlSessionTemplate sqlSession,  ReplyInfo replyInfo) {
		
		return  (ArrayList) sqlSession.selectList("communityBoardMapper.selectDetailBoardReplyReply",replyInfo);
	}
	
	public int insertReply(SqlSessionTemplate sqlSession, ReplyInfo replyInfo) {

		return sqlSession.insert("communityBoardMapper.insertReply",replyInfo);
	}

	public int insertReplyReply(SqlSessionTemplate sqlSession, ReplyInfo replyInfo) {
		
		 return sqlSession.insert("communityBoardMapper.insertReplyReply",replyInfo);
	}
	
	public int selectBoardReplyCount(SqlSessionTemplate sqlSession, int boardNo) {
		
		return sqlSession.selectOne("communityBoardMapper.selectBoardReplyCount",boardNo);
	}
	

	//좋아요체크
	public int checkUserGoodCount(SqlSessionTemplate sqlSession,BoardInfo boardInfo) {
		
		return sqlSession.selectOne("communityBoardMapper.checkUserGoodCount",boardInfo);
	}

	//게시글공감해제
	public int deleteGoodCount(SqlSessionTemplate sqlSession,BoardInfo boardInfo) {
	
		return sqlSession.delete("communityBoardMapper.deleteGoodCount",boardInfo);
	}

	//게시글공감
	public int insertGoodCount(SqlSessionTemplate sqlSession,BoardInfo boardInfo) {
	
		return sqlSession.insert("communityBoardMapper.insertGoodCount",boardInfo);
	}

	//게시글 공감수 조회
	public int selectGoodCount(SqlSessionTemplate sqlSession,int boardNo) {
	
		return sqlSession.selectOne("communityBoardMapper.selectGoodCount",boardNo);
	}
	
	public int updateBoardViewCount(SqlSessionTemplate sqlSession,int boardNo) {
		
		return sqlSession.update("communityBoardMapper.updateBoardViewCount",boardNo);
	}

	public int deletReply(SqlSessionTemplate sqlSession, int replyNo) {
		
		 return sqlSession.delete("communityBoardMapper.deletBoardReply",replyNo);
	}
	
	
	public int checkReplyOwner(SqlSessionTemplate sqlSession, int replyNo) {
	
		return sqlSession.selectOne("communityBoardMapper.checkReplyOwner",replyNo);
	}
    
	public int deleteBoard(SqlSessionTemplate sqlSession, BoardInfo boardInfo) {
		
		return sqlSession.delete("communityBoardMapper.deleteBoard",boardInfo);
	}
	public int checkBoardOwner(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("communityBoardMapper.checkBoardOwner",boardNo);
	}
	

	public int insertShortContents(SqlSessionTemplate sqlSession, ShorstInfo shortsInfo) {
		
		return sqlSession.insert("communityShortsMapper.insertShortContents",shortsInfo);
	}

	public int insertShortsVideo(SqlSessionTemplate sqlSession, ShortsFileInfo fileInfos) {
	
		return sqlSession.insert("communityShortsMapper.insertShortsVideo",fileInfos);
	}
	
	public int insertShortsAttachment(SqlSessionTemplate sqlSession, ShortsFileInfo fileInfos) {
		
		return sqlSession.insert("communityShortsMapper.insertShortsAttachment",fileInfos);
	}
	

	public int getShortsNum(SqlSessionTemplate sqlSession, int videoId) {
		return sqlSession.selectOne("shortsMapper.getShortsNo",videoId);
	}

	public ArrayList<ShortsReply> loadReply(SqlSessionTemplate sqlSession, int shortsNum) {
	    return (ArrayList)sqlSession.selectList("shortsMapper.loadReply", shortsNum);
	}

	public int getReplyNo(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("shortsMapper.getReplyNo");
	}

	public ShortsReply getRecentReply(SqlSessionTemplate sqlSession, int replyNo) {
		return sqlSession.selectOne("communityBoardMapper.getRecentReply", replyNo);
	}

	public int getIsLike(SqlSessionTemplate sqlSession, ForIsLike forIsLike) {
		
		Integer result = sqlSession.selectOne("shortsMapper.getIsLike", forIsLike);
		
	    return (result != null) ? result : 0;
	}

	public int updateBoard(SqlSessionTemplate sqlSession, BoardEnroll board) {
		
		return sqlSession.update("communityBoardMapper.updateBoard",board);
	}

	public int updateAttachment(SqlSessionTemplate sqlSession,BoardFileInfo boardFile) {
		
		return sqlSession.update("communityBoardMapper.updateBoardAttachment",boardFile);
	}


//	community 게시글 등록


	public int insertBoard(SqlSessionTemplate sqlSession, BoardEnroll board) {
		return sqlSession.insert("communityBoardMapper.insertBoard", board);
	}
	
	public int insertBoardAttachment(SqlSessionTemplate sqlSession, BoardFileInfo boardFile) {
		return sqlSession.insert("communityBoardMapper.insertBoardAttachment", boardFile);
	}


	// 숏츠 좋아요 관련
	public int likeShorts(SqlSessionTemplate sqlSession, ForIsLike forIsLike) {
		System.out.println("like: " + sqlSession.insert("shortsMapper.enrollLike", forIsLike)); // 1
		return sqlSession.insert("shortsMapper.enrollLike", forIsLike);
	}
	

	public int deleteLike(SqlSessionTemplate sqlSession, ForIsLike forIsLike) {
		System.out.println("delete: " + sqlSession.delete("shortsMapper.deleteLike", forIsLike)); // 1
		return sqlSession.delete("shortsMapper.deleteLike", forIsLike);
	}

	public int insertUpdateBoardAttachment(SqlSessionTemplate sqlSession, BoardFileInfo boardFile) {
		// TODO Auto-generated method stub
		return sqlSession.insert("communityBoardMapper.insertUpdateBoardAttachment", boardFile);

	}

	public int deleteBoardAttachment(SqlSessionTemplate sqlSession, DeleteBoardAttachmentInfo deleteInfo) {
		
		return sqlSession.delete("communityBoardMapper.deleteBoardAttachment",deleteInfo);
	}

	

	
	
	


//    public ArrayList<ShortsReply> loadReply(SqlSessionTemplate sqlSession, int shortsNum) {
//		List<ShortsReply> replyList = sqlSession.selectList("shortsMapper.loadReply", shortsNum);
//	    return new ArrayList<>(replyList);
//	}


}
