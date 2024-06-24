package com.kh.mng.community.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.common.model.vo.Pagination;
import com.kh.mng.community.model.dao.CommunityDao;
import com.kh.mng.community.model.dto.BoardEnroll;
import com.kh.mng.community.model.dto.BoardFileInfo;
import com.kh.mng.community.model.dto.BoardGoodInfo;
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
import com.kh.mng.community.model.vo.ShortsReply;
import com.kh.mng.community.model.vo.TotalShortsInfo;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	private CommunityDao communityDao;
	
	@Autowired
    private SqlSessionTemplate sqlSession;

	@Override
	public int selectListCount() {
		return 0;
	}


	@Override
	@Transactional
	public ShortsReply addComment(int userNo, int shortsNo, String comment) {
		// 1. 시퀀스 가져오기
		int replyNo = communityDao.getReplyNo(sqlSession);
		
		log.info("replyNo:" + replyNo);
		
		// 2. 가져온 시퀀스로 댓글 insert하기
		ShortsReplyDTO shortsReplyDTO = new ShortsReplyDTO();
		shortsReplyDTO.setComment(comment);
		shortsReplyDTO.setReplyNo(replyNo);
		shortsReplyDTO.setShortsNo(shortsNo);
		shortsReplyDTO.setUserNo(userNo);
		
		communityDao.addComment(sqlSession, shortsReplyDTO);
		
		// 3. 시퀀스로 댓글 가져오기
		return communityDao.getRecentReply(sqlSession, replyNo);
	}

	@Override
	public ArrayList<Shorts> selectShortsList(PageInfo pi) {
		
		ArrayList<Shorts> shorts=communityDao.selectShortsList(sqlSession,pi);
		if(!shorts.isEmpty()) {
			for(Shorts s : shorts) {
				Attachment shortsAttachment=communityDao.selectOneShortAttachment(sqlSession,s.getShortsNo());
				 if(shortsAttachment==null) {
					 shortsAttachment=new Attachment();
					 shortsAttachment.setFilePath("resources/img/default/");
					 shortsAttachment.setChangeName("cat1.jpg");
				 }
				s.setAttachment(shortsAttachment);
			}
		}
		
		return shorts;
	}
	

	@Override
	public int selectShortsCount() {
		
		int count =communityDao.selectShortsCount(sqlSession);
		return count;
	}
	
	@Override
	@Transactional
	public ArrayList<CommunityBoard> selectBoardList(PageInfo boardPi,BoardInfo boardInfo) {
		
		 ArrayList<CommunityBoard> boards=communityDao.selectBoardList(sqlSession,boardPi,boardInfo);
		 if(!boards.isEmpty()) {
			 for(CommunityBoard board:boards) {
				 ArrayList<Attachment> attachment=communityDao.selectBoardAttachMent(sqlSession,board.getBoardNo());
				 Attachment userProfile=communityDao.selectUserProfile(sqlSession,board.getUserNo());
				 if(attachment.isEmpty()) {
					 Attachment defaultAttachMent = new Attachment();
					 defaultAttachMent.setFilePath("resources/img/default/");
					 defaultAttachMent.setChangeName("default_location_img.png");
					 attachment.add(defaultAttachMent);
				 }
				 
				 if(userProfile==null) {
					 userProfile=new Attachment();
					 userProfile.setFilePath("resources/img/default/");
					 userProfile.setChangeName("default_profile.jpg");
				 }
				 
				 int replyCount =communityDao.selectBoardApplyCount(sqlSession,board.getBoardNo());
				 board.setAttachment(attachment);
				 board.setReplyCount(replyCount);
				 board.setUserProfile(userProfile);
			 }
		 }
		
		return boards;
	}
	
	
	@Override
	public int selectBoardCount(BoardInfo boardInfo){
		
		return communityDao.selectBoardCount(sqlSession,boardInfo);
	}
	
	@Override
	public ArrayList<BoardCategory> selectBoardCategoryList() {
		
		return communityDao.selectBoardCategoryList(sqlSession);
	}
	
	
	@Override
	public TotalShortsInfo getVideoInfo(int videoId) {
		return communityDao.getVideoInfo(sqlSession, videoId);
	}

	@Override
	public int getVideoLikeCount(int shortsNum) {
		return communityDao.getVideoLikeCount(sqlSession, shortsNum);
	}


	@Override
	public int getVideoReplyCount(int shortsNum) {
		return communityDao.getVideoReplyCount(sqlSession, shortsNum);
	}
	
	
	@Override
	@Transactional
	public int insertShorts(Map<String,ShortsFileInfo> fileInfos, ShorstInfo shortsInfo) {
	
		int count=communityDao.insertShortContents(sqlSession,shortsInfo);
		int count1=1;
		
		if(count>0) {
			
			if(!fileInfos.isEmpty()) {
				for(Map.Entry<String, ShortsFileInfo> files :fileInfos.entrySet()) {
					if(files.getKey().contains("video")) {
						count1=communityDao.insertShortsVideo(sqlSession,files.getValue());
					}
					if(files.getKey().contains("image")) {
						count1=communityDao.insertShortsAttachment(sqlSession,files.getValue());
					}
					count1*=count1;
				}
			
			}
			else {
				ShortsFileInfo defaultImg= new ShortsFileInfo();
				defaultImg.setFilePath("resources/img/default/");
				defaultImg.setChangeName("default_location_img.png");
				
				count1=communityDao.insertShortsAttachment(sqlSession,defaultImg);
			}
			
		}
		
		return count*count1;
	}
	

	//게시판 상세 정보 가져오는 서비스
	@Override
	@Transactional
	public CommunityBoard selectBoardDetail(PageInfo replyPi,int bno,int userNo) {
	   
		
	    CommunityBoard communityBoard =  communityDao.selectBoardDetail(sqlSession,bno);
   
	    if(communityBoard!=null) {
	    	
	    	//조회수 업데이트
	    	communityDao.updateBoardViewCount(sqlSession,bno);
	    
	    	//댓글수
	    	int replyCount = communityDao.selectBoardReplyCount(sqlSession, bno);
	    	
	    	//추천횟수
	    	int goodCount = communityDao.selectGoodCount(sqlSession, bno);
	    	
	    	//커뮤니티게시글 첨부파일
	    	ArrayList<Attachment> boardAttachment=communityDao.selectBoardAttachMent(sqlSession, communityBoard.getBoardNo());
	    	
	    	
	    	 ArrayList<BoardReply> boardReply =  communityDao.selectBoardReplys(sqlSession,replyPi,communityBoard.getBoardNo());
	    	 Attachment userProfile = communityDao.selectUserProfile(sqlSession,communityBoard.getUserNo());
	    
	    	 	if(!boardReply.isEmpty()) {
	    	 		ReplyInfo replyInfo = new ReplyInfo();
	    	 		
	    	 		for(BoardReply re:boardReply) {
	    	 			Attachment replyProfile = communityDao.selectUserProfile(sqlSession,re.getUserNo());
	    	 			replyInfo.setBoardNo(communityBoard.getBoardNo());
	    	 			replyInfo.setReplyNo(re.getReplyNo());
	    	 			
	    	 			
	    	 			
	    	 			ArrayList<BoardReplyReply> boardReplyReply = communityDao.selectBoardrReplyReplys(sqlSession, replyInfo);
	    	 			if(!boardReplyReply.isEmpty()) {
	    	 				for(BoardReplyReply rr:boardReplyReply) {
	    	 					Attachment replyReplyProfile = communityDao.selectUserProfile(sqlSession,rr.getUserNo());
	    	 					if(replyReplyProfile==null) {
	    	 						 Attachment defaultUserProfile=new  Attachment();
	    	 			    		 defaultUserProfile.setFilePath("resources/img/default/");
	    	 			    		 defaultUserProfile.setChangeName("default_profile.jpg");
	    	 			    		 rr.setReplyUserProfile(defaultUserProfile);
	    	 					}
	    	 					else {
	    	 						rr.setReplyUserProfile(replyReplyProfile);
	    	 					}
	    	 					
	    	 				}
	    	 			}
	    	 			
	    	 			if(replyProfile==null) {
	    	 				 Attachment defaultUserProfile=new  Attachment();
	 			    		 defaultUserProfile.setFilePath("resources/img/default/");
	 			    		 defaultUserProfile.setChangeName("default_profile.jpg");
	 			    		 re.setReplyUserProfile(defaultUserProfile);

	    	 			}
	    	 			else {
	    	 				re.setReplyUserProfile(replyProfile);
	    	 			}
	    	 			
	    	 		    re.setReplyReply(boardReplyReply);
	    	 		
	    	 		}
	    	 	 }
	    	 	
	    
	    	 if(userProfile==null) {
	    		 Attachment defaultUserProfile=new  Attachment();
	    		 defaultUserProfile.setFilePath("resources/img/default/");
	    		 defaultUserProfile.setChangeName("default_profile.jpg");
	    		 communityBoard.setUserProfile(defaultUserProfile);
	    	 }else {
	    		 communityBoard.setUserProfile(userProfile);
	    	 }
	    	 
	    	 communityBoard.setReplys(boardReply);
	    	 communityBoard.setReplyCount(replyCount);
	    	 communityBoard.setGoodCount(goodCount);
	    	 communityBoard.setAttachment(boardAttachment);
	    
	    }
	    
	    
	    return communityBoard;
	}
	
	
	@Override
	@Transactional
	public CommunityBoard selectBoardDetail(int boardNo) {
		
		 CommunityBoard board=communityDao.selectBoardDetail(sqlSession, boardNo);
			//커뮤니티게시글 첨부파일
		 if(board!=null) {
				ArrayList<Attachment> boardAttachment=communityDao.selectBoardAttachMent(sqlSession, boardNo);
				board.setAttachment(boardAttachment);
		 }
    
		return board;
	}
	
	
	
	
	//게시판댓글 대댓글 입력 서비스
	@Override
	@Transactional
	public int insertBoardReply(ReplyInfo replyInfo) {
		
		int count=0;
		if(replyInfo.getReplyNo()==-1) {//댓글이면
			 count=communityDao.insertReply(sqlSession,replyInfo);
		}
		else {//댓글이아니고 대댓글이면
			 count=communityDao.insertReplyReply(sqlSession,replyInfo);
		}
		
		
		return count;
	}
	
	@Override
	public int selectBoardReplyCount(int boardNo) {
		
		return communityDao.selectBoardReplyCount(sqlSession,boardNo);
	}


	//댓글과 대댓글 비동기로 다시 가져오기
	@Override
	@Transactional
	public ArrayList<BoardReply> selectBoardReplys(PageInfo replyPi, ReplyInfo replyInfo) {
		
		// Attachment userProfile = communityDao.selectUserProfile(sqlSession,communityBoard.getUserNo());
		 
		 ArrayList<BoardReply>selectBoardReplys=communityDao.selectBoardReplys(sqlSession,replyPi,replyInfo.getBoardNo());
		 log.info("서비스 부모댓글 비동기 확인:{}",selectBoardReplys);
		 if(!selectBoardReplys.isEmpty()) {

			 for(BoardReply replys:selectBoardReplys) {
				 replyInfo.setReplyNo(replys.getReplyNo());
				 
			
				 ArrayList<BoardReplyReply> selectBoardReplyReply =communityDao.selectBoardrReplyReplys(sqlSession, replyInfo);
				 log.info("서비스 비동기 확인:{}",selectBoardReplyReply);
				 
				 	if(!selectBoardReplyReply.isEmpty()) {
				 		for(BoardReplyReply rr:selectBoardReplyReply) {
				 			 
							 Attachment userProfile = communityDao.selectUserProfile(sqlSession,rr.getUserNo());
							 if(userProfile==null) {
								 Attachment defaultUserProfile = new Attachment();
								 defaultUserProfile.setFilePath("resources/img/default/");
								 defaultUserProfile.setChangeName("default_profile.jpg");
								 rr.setReplyUserProfile(defaultUserProfile);
							 }else {
								 rr.setReplyUserProfile(userProfile);
							 }
				 		}
				 	}
				         
				 
				 
				 
				 Attachment userProfile = communityDao.selectUserProfile(sqlSession,replys.getUserNo());
				 if(userProfile==null) {
					 Attachment defaultUserProfile = new Attachment();
					 defaultUserProfile.setFilePath("resources/img/default/");
					 defaultUserProfile.setChangeName("default_profile.jpg");
					 replys.setReplyUserProfile(defaultUserProfile);
				 }else {
					 replys.setReplyUserProfile(userProfile);
				 }
				 
				 replys.setReplyReply(selectBoardReplyReply);
			 }
			 
		 }
		
		
		return selectBoardReplys;
	}
	
	//좋아요 체크 서비스
	@Override
	@Transactional
	public BoardGoodInfo updateBoardGoodCount(BoardInfo boardInfo) {
		
		BoardGoodInfo goodInfo =new BoardGoodInfo();
		
		//좋아요체크
		int count = communityDao.checkUserGoodCount(sqlSession,boardInfo);
		
		int result=0;
		if(count==1) {
			//공감해제
			result=communityDao.deleteGoodCount(sqlSession,boardInfo);
			goodInfo.setMessage("공감해제되었습니다");
		}else {
			result=communityDao.insertGoodCount(sqlSession,boardInfo);
			goodInfo.setMessage("공감되었습니다");
		}
		
		//게시물 추천수(좋아요)조회
		if(result>0) {
			int updateGoodCount=communityDao.selectGoodCount(sqlSession,boardInfo.getBoardNo());
			goodInfo.setGoodCount(updateGoodCount);
			
		}
		else {
			goodInfo.setMessage("공감실패하였습니다.");
		}
		
		return  goodInfo;
		
		
	}
	
	@Override
	public int deleteReply(int replyNo) {
	
		return communityDao.deletReply(sqlSession,replyNo);
	}

	@Override
	public int checkReplyOwner(int replyNo) {
		
		return communityDao.checkReplyOwner(sqlSession,replyNo);
	}

	//게시글 삭제
	@Override
	@Transactional
	public ArrayList<Attachment> deleteBoard(BoardInfo boardInfo) {
	
		ArrayList<Attachment> attachment= communityDao.selectBoardAttachMent(sqlSession, boardInfo.getBoardNo());
		
		int count=communityDao.deleteBoard(sqlSession,boardInfo);
		
		if(count>0) {
			return attachment;
		}else {
			return null;
		}
		
	}
	
	//게시글 수정
	@Override
	@Transactional
	public int updateBoard(BoardEnroll board, BoardFileInfo boardFile) {
		
		int result=communityDao.updateBoard(sqlSession, board);
		log.info(" s result1:{}",result);
		int result2=1;
		if(result>0&&boardFile.getChangeName()!=null) {
			
			ArrayList<Attachment>attachment= communityDao.selectBoardAttachMent(sqlSession, board.getBoardNo());
			if(attachment.isEmpty()) {
				result2=communityDao.insertUpdateBoardAttachment(sqlSession, boardFile);
			}
			else {
				result2=communityDao.updateAttachment(sqlSession,boardFile);
			}
			
			
		}
		
		return result*result2;
	}
	
	

	@Override
	public int deleteBoardAttachment(DeleteBoardAttachmentInfo deleteInfo) {
		
		return communityDao.deleteBoardAttachment(sqlSession,deleteInfo);
	}


	
	@Override
	public int checkBoardOwner(int boardNo) {
		
		return communityDao.checkBoardOwner(sqlSession, boardNo);
	}



	@Override
     public String getVideo(int videoId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<ShortsReply> loadReply(int shortsNum) {
		return communityDao.loadReply(sqlSession, shortsNum);
	}


	@Override
	public int getShortsNum(int videoId) {
		return communityDao.getShortsNum(sqlSession, videoId);
	}


	@Override
	public int getIsLike(ForIsLike forIsLike) {
		return communityDao.getIsLike(sqlSession, forIsLike);
	}


	@Override
	public int likeShorts(ForIsLike forIsLike) {
		return communityDao.likeShorts(sqlSession, forIsLike);
	}
	
	
	@Override
	public int deleteLike(ForIsLike forIsLike) {
		return communityDao.deleteLike(sqlSession, forIsLike);
	}

	










	@Override
	public int insertBoard(BoardEnroll board, BoardFileInfo boardfile) {
		int result1 = communityDao.insertBoard(sqlSession, board);
		int result2 = 1;
		
		//게시글 등록이 성공하고 첨부파일이 비어있지 않으면
		if (result1 > 0 && boardfile.getChangeName() != null) {
			result2 = communityDao.insertBoardAttachment(sqlSession, boardfile);
		} 
		
//		else if (result1 > 0 && boardfile == null){
//			result2 = 1;
//		} else {
//			result1 = result1 * 0;
//		}
		
		
		
		return result1 * result2;
	}




	


}

