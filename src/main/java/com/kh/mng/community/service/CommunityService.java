package com.kh.mng.community.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.community.model.dto.BoardEnroll;
import com.kh.mng.community.model.dto.BoardFileInfo;
import com.kh.mng.community.model.dto.BoardGoodInfo;
import com.kh.mng.community.model.dto.BoardInfo;
import com.kh.mng.community.model.dto.DeleteBoardAttachmentInfo;
import com.kh.mng.community.model.dto.ForIsLike;
import com.kh.mng.community.model.dto.ReplyInfo;
import com.kh.mng.community.model.dto.ShorstInfo;
import com.kh.mng.community.model.dto.ShortsFileInfo;
import com.kh.mng.community.model.vo.BoardCategory;
import com.kh.mng.community.model.vo.BoardReply;
import com.kh.mng.community.model.vo.CommunityBoard;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.community.model.vo.ShortsReply;
import com.kh.mng.community.model.vo.TotalShortsInfo;


public interface CommunityService {
	
	int selectListCount();

	ShortsReply addComment(int userNo, int videoId, String comment);

	String getVideo(int videoId);

	ArrayList<Shorts> selectShortsList(PageInfo pi);

	int selectShortsCount();
	
	TotalShortsInfo getVideoInfo(int videoId);
	

	int getVideoLikeCount(int shortsNum);

	int getVideoReplyCount(int shortsNum);

	ArrayList<ShortsReply> loadReply(int shortsNum);

	int selectBoardCount(BoardInfo boardInfo);

	ArrayList<CommunityBoard> selectBoardList(PageInfo boardPi, BoardInfo boardInfo);

	ArrayList<BoardCategory> selectBoardCategoryList();

	int getShortsNum(int videoId);

	int insertShorts(Map<String, ShortsFileInfo> fileInfos, ShorstInfo shortsInfo);

	CommunityBoard selectBoardDetail(PageInfo replyPi,int bno,int userNo);
	CommunityBoard selectBoardDetail(int boardNo);

	int insertBoardReply(ReplyInfo replyInfo);

	int selectBoardReplyCount(int boardNo);

	ArrayList<BoardReply> selectBoardReplys(PageInfo replyPi,ReplyInfo replyInfo);

	BoardGoodInfo updateBoardGoodCount(BoardInfo boardInfo);

	int deleteReply(int replyNo);

	int checkReplyOwner(int replyNo);

	ArrayList<Attachment> deleteBoard(BoardInfo boardInfo);

	int checkBoardOwner(int boardNo);

	int getIsLike(ForIsLike forIsLike);

	int likeShorts(ForIsLike forisLike);


	int updateBoard(BoardEnroll board, BoardFileInfo boardFile);

	


	// 게시글 등록
	int insertBoard(BoardEnroll board, BoardFileInfo boardFile);

	int deleteLike(ForIsLike forIsLike);

	int deleteBoardAttachment(DeleteBoardAttachmentInfo deleteInfo);


}
