package com.kh.mng.community.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.common.model.vo.Pagination;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.community.model.dto.BoardEnroll;
import com.kh.mng.community.model.dto.BoardFileInfo;
import com.kh.mng.community.model.dto.BoardGoodInfo;
import com.kh.mng.community.model.dto.BoardInfo;
import com.kh.mng.community.model.dto.BoardPage;
import com.kh.mng.community.model.dto.BoardReplyInfo;
import com.kh.mng.community.model.dto.DeleteBoardAttachmentInfo;
import com.kh.mng.community.model.dto.ForIsLike;
import com.kh.mng.community.model.dto.ReplyInfo;
import com.kh.mng.community.model.dto.ShorstInfo;
import com.kh.mng.community.model.dto.ShortPage;
import com.kh.mng.community.model.dto.ShortsFileInfo;
import com.kh.mng.community.model.vo.BoardCategory;
import com.kh.mng.community.model.vo.BoardReply;
import com.kh.mng.community.model.vo.CommunityBoard;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.community.model.vo.ShortsReply;
import com.kh.mng.community.model.vo.TotalShortsInfo;
import com.kh.mng.community.service.CommunityService;
import com.kh.mng.member.model.vo.Member;
import com.kh.mng.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class CommunityController {
	
	
	private final CommunityService communityService;
	private final MemberService memberService;
	
	@Autowired
	public CommunityController(CommunityService communityService,MemberService memberService) {
		this.communityService= communityService;
		this.memberService=memberService;
		
	}
	
	@GetMapping("/community")
	public String communityMain(@RequestParam(value="shortsPageNo",defaultValue="1") int shortsPageNo,  
			@RequestParam(value="boardPageNo",defaultValue="1") int boardPageNo, 
			@RequestParam(value="boardCategoryNo",defaultValue="0") int boardCategoryNo,
			@RequestParam(value="boardContent",defaultValue="default") String boardContent,
			Model model) {
		
	
		//쇼츠 목록 가져오기
		int shortsCount=communityService.selectShortsCount();
		PageInfo shortsPi =Pagination.getPageInfo(shortsCount,shortsPageNo,10,10);
		ArrayList<Shorts> shorts =communityService.selectShortsList(shortsPi);
		
	
		//게시판 목록 가져오기
		BoardInfo boardInfo=new BoardInfo();
		boardInfo.setBoardCategoryNo(boardCategoryNo);
		boardInfo.setBoardContent(boardContent);
		 
		int boardCount=communityService.selectBoardCount(boardInfo);
		PageInfo boardPi =Pagination.getPageInfo(boardCount,boardPageNo,10,10);
		ArrayList<CommunityBoard> boards = communityService.selectBoardList(boardPi,boardInfo);

		//카테고리 목록 가져오기
		ArrayList<BoardCategory> boardCategory=communityService.selectBoardCategoryList();
		
	
		model.addAttribute("shorts",shorts);
		model.addAttribute("shortsPi",shortsPi);
		
		model.addAttribute("boards",boards);
		model.addAttribute("boardPi",boardPi);
		
		model.addAttribute("boardCategory",boardCategory);
		
		
		return "community/communityMain";
	}
	
	//게시판 페이징처리 비동기
	@ResponseBody
	@GetMapping(value="boards", produces="application/json; charset=utf-8")
	public String selectBoards(@RequestParam(value="boardCategoryNo",defaultValue="0")int boardCategoryNo
			                  ,@RequestParam(value="boardPageNo",defaultValue="1")  int boardPageNo,
			                   @RequestParam(value="boardContent",defaultValue="default") String boardContent
			                    ) {
		 
		
		//게시판 목록 가져오기
		BoardInfo boardInfo=new BoardInfo();
		boardInfo.setBoardCategoryNo(boardCategoryNo);
		boardInfo.setBoardContent(boardContent);
		int boardCount=communityService.selectBoardCount(boardInfo);
		PageInfo boardPi =Pagination.getPageInfo(boardCount,boardPageNo,10,10);
		ArrayList<CommunityBoard> boards = communityService.selectBoardList(boardPi,boardInfo);
		
		BoardPage pages = new BoardPage();
		pages.setPage(boardPi);
		pages.setBoards(boards);
		
		return new Gson().toJson(pages);
		
	}
	
	//게시글 카테고리 별 처리 비동기
	@ResponseBody
	@GetMapping(value="category", produces="application/json; charset=utf-8")
	public String categoryBoard(@RequestParam(value="boardCategoryNo",defaultValue="0")int boardCategoryNo,
			                  @RequestParam(value="boardContent",defaultValue="default") String boardContent) {
		System.out.println("boardCategoryNo"+boardCategoryNo);
		BoardInfo boardInfo=new BoardInfo();
		boardInfo.setBoardCategoryNo(boardCategoryNo);
		boardInfo.setBoardContent(boardContent);
		
		int boardCount=communityService.selectBoardCount( boardInfo);
		PageInfo boardPi =Pagination.getPageInfo(boardCount,1,10,10);
		ArrayList<CommunityBoard> boards = communityService.selectBoardList(boardPi,  boardInfo);
		BoardPage pages = new BoardPage();
		pages.setPage(boardPi);
		pages.setBoards(boards);
		return new Gson().toJson(pages);
	}
	
	//게시글 검색 비동기 처리
	
	@ResponseBody
	@GetMapping(value="search", produces="application/json; charset=utf-8")
	public String searchBoard(@RequestParam(value="boardCategoryNo",defaultValue="0")int boardCategoryNo,
			                  @RequestParam(value="boardContent",defaultValue="default") String boardContent) {
		BoardInfo boardInfo=new BoardInfo();
		boardInfo.setBoardCategoryNo(boardCategoryNo);
		boardInfo.setBoardContent(boardContent);
		
		int boardCount=communityService.selectBoardCount( boardInfo);
		PageInfo boardPi =Pagination.getPageInfo(boardCount,1,10,10);
		ArrayList<CommunityBoard> boards = communityService.selectBoardList(boardPi,  boardInfo);
		BoardPage pages = new BoardPage();
		pages.setPage(boardPi);
		pages.setBoards(boards);
		return new Gson().toJson(pages);
	}
	
	
	//쇼츠 페이징 처리 비동기
	@ResponseBody
	@GetMapping(value = "shorts", produces = "application/json; charset=utf-8")
	public String selecShorts(int shortsPageNo) {
		int shortsCount = communityService.selectShortsCount();

		PageInfo shortsPi = Pagination.getPageInfo(shortsCount, shortsPageNo, 10, 10);
		ArrayList<Shorts> shorts = communityService.selectShortsList(shortsPi);
		ShortPage pages = new ShortPage();
		pages.setPage(shortsPi);
		pages.setShorts(shorts);

		return new Gson().toJson(pages);

	}
	
	@GetMapping(value="enrollshort.view")
	public String enrollShortsView() {
		
		return "community/shortsEnroll";
	}
	

	//쇼츠 등록
	@PostMapping(value = "enroll.short")
	public String enrollShorts(List<MultipartFile> files , ShorstInfo shortsInfo, HttpSession session,Model model) {
		
	   log.info("files:");
	   log.info("text:"+shortsInfo.getShortsContent());
	 
	   Member loginMember=(Member)(session.getAttribute("loginUser"));
	   shortsInfo.setUserNo(loginMember.getUserNo());
	
	   Map<String,ShortsFileInfo>fileInfos= new HashMap<String,ShortsFileInfo>();
	   
	   String filePath="resources/img/user/etc";
	   
	   if(files!=null) {
		   
		   for(MultipartFile f:files) {
			   
			   if(f.getContentType().contains("image")) {
				   filePath="resources/video/thumnail/";
			   }
			   else {
				   filePath="resources/video/mp4/";
			   }
			   
			   String changeName= saveFile(f,session,filePath);
			   ShortsFileInfo filesInfo=new  ShortsFileInfo();
			   filesInfo.setOriginName(f.getOriginalFilename());
			   filesInfo.setChangeName(changeName);
			   filesInfo.setFilePath(filePath);
			   fileInfos.put(f.getContentType(),filesInfo);
		   }
	   }
	 
	  int count= communityService.insertShorts(fileInfos,shortsInfo);
	  if(count>0) {
		  session.setAttribute("alertMsg","쇼츠가 등록되었습니다.");
	  }else {
		  session.setAttribute("alertMsg", "쇼츠 등록에 실패하셨습니다.");
	  }
		
	   return "redirect:/community";
	}

	private String saveFile(MultipartFile upfile, HttpSession session, String path) {
		// 파일명 수정 후 서버에 업로드하기("imgFile.jpg => 202404231004305488.jpg")
		String originName = upfile.getOriginalFilename();

		// 년월일시분초
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// 5자리 랜덤값
		int ranNum = (int) (Math.random() * 90000) + 10000;

		// 확장자
		String ext = originName.substring(originName.lastIndexOf("."));

		// 수정된 첨부파일명
		String changeName = currentTime + ranNum + ext;

		// 첨부파일을 저장할 폴더의 물리적 경로(session)
		String savePath = session.getServletContext().getRealPath(path);
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return changeName;

	}
	
	
	
	
	//쇼츠 상세 페이지
	@GetMapping(value="shortsView.bo")
	public String detailShortsView() {
		return "community/shortsShow";
	}
	
	//커뮤니티 게시글 상세  컨트롤러
	@GetMapping(value="detailView.bo")
	public String detailBoardView(@RequestParam(value="bno") int bno,
								  @RequestParam(value="pageNo",defaultValue="1") int pageNo,
								  Model model,HttpSession session) {
		
		int userNo=((Member)session.getAttribute("loginUser")).getUserNo();
		
		int  replyCount= communityService.selectBoardReplyCount(bno);
	    PageInfo replyPi = Pagination.getPageInfo(replyCount ,pageNo, 10, 10);
		
		CommunityBoard communityBoard = communityService.selectBoardDetail(replyPi,bno,0);
		ProfileImg profileImg= memberService.getProfileImg(userNo);
		if(profileImg==null) {
			ProfileImg defaultImg =new ProfileImg();
			defaultImg.setFilePath("resources/img/default/");
			defaultImg.setChangeName("default_profile.jpg");
			model.addAttribute("userProfile",defaultImg);
		}
		else {
			model.addAttribute("userProfile", profileImg);
		}
		 
		log.info("communityBoard:{}",communityBoard);
		 
		
		model.addAttribute("board",communityBoard);
	    model.addAttribute("replyPi",replyPi);
	
		 
		 return "community/boardContent";
	}
	
	//댓글 대댓글 입력 컨트롤러
	@ResponseBody
	@PostMapping(value="detailViewReply.in" ,produces="application/text; charset=utf-8;")
	public String detailInsertReply(ReplyInfo replyInfo,HttpSession session) {
		Member loginUser=(Member)session.getAttribute("loginUser");
		if(loginUser==null) {
			return "로그인을 먼저 해주세요";
		}else {
		
			int userNo=loginUser.getUserNo();
			replyInfo.setUserNo(userNo);
			
			log.info("응답:{}",replyInfo);
			
			int count = communityService.insertBoardReply(replyInfo);
			
			if(count>0) {
				return "등록되었습니다.";
			}else{
				return "등록에 실패하였습니다.";
			}
		}
	}
	
	//댓글정보 비동기로가져오는 컨트롤러
	@ResponseBody
	@GetMapping(value="detailViewReply.view", produces = "application/json; charset=utf-8")
	public String detailReplyView(ReplyInfo replyInfo,  HttpSession session) {
		
		//댓글개수만 가져와야된다. (답글x)
		int  replyCount= communityService.selectBoardReplyCount(replyInfo.getBoardNo());

		
		PageInfo replyPi = Pagination.getPageInfo(replyCount ,replyInfo.getPageNo(), 10, 10);
		ArrayList<BoardReply> replys =  communityService.selectBoardReplys(replyPi,replyInfo);
		
		BoardReplyInfo boardReplyInfo =new BoardReplyInfo();
		boardReplyInfo.setPage(replyPi);
		boardReplyInfo.setReplys(replys);
		
		log.info("{}",boardReplyInfo.getReplys());
		log.info("{}",boardReplyInfo.getPage());
		
		return new Gson().toJson(boardReplyInfo);
		
	}
	
	//댓글 삭제 컨트롤러
	@ResponseBody
	@GetMapping(value="deleteViewReply.view", produces = "application/text; charset=utf-8")
	public String deleteDetailReply(ReplyInfo replyInfo,  HttpSession session) {
		
		Member logined=(Member) session.getAttribute("loginUser");
		if(logined==null) {
			return "로그인을 먼저 해주세요";
		}else {
			//자신의 댓글인지 체크
			int userNo= communityService.checkReplyOwner(replyInfo.getReplyNo());
			int loginUserNo=logined.getUserNo();
			
					
			if(userNo!=loginUserNo) {
				return "본인댓글만 삭제할수 있습니다.";
				
			}else{
				int count = communityService.deleteReply(replyInfo.getReplyNo());
				
				if(count>0) {
					return "삭제되었습니다";
				}
				else {
					return "삭제 실패";
				}
			}
		}
		
		
		
	}
	
	//좋아요 처리 컨트롤러
	@ResponseBody
	@GetMapping(value="updategoodcount.bo",produces = "application/json; charset=utf-8")
	public String updateBoardGood(BoardInfo boardInfo,HttpSession session) {
		
		
		Member loginUser=(Member)session.getAttribute("loginUser");
		BoardGoodInfo goodInfo=new BoardGoodInfo();
		if(loginUser==null) {
			goodInfo.setMessage("fail");
		}else {
			int userNo=(loginUser.getUserNo());
			boardInfo.setUserNo(userNo);
			goodInfo=communityService.updateBoardGoodCount(boardInfo);
		}
		
		return new Gson().toJson(goodInfo);
		
	}
	
	//게시글 등록 페이지이동
	@GetMapping(value="enrollBoard.bo")
	public String enrollBoard() {
		return "community/writingPage";
	}
	
//	커뮤니티 게시글 등록
	
	@PostMapping("enrollBoard.bo")
	public String enrollBoard(BoardEnroll board, MultipartFile upfile, HttpSession session, Model model) {
		BoardFileInfo boardFile = new BoardFileInfo();
		Member loginUser = (Member)(session.getAttribute("loginUser"));

		if(!upfile.getOriginalFilename().equals("")) {
			String changeName = saveFile(upfile, session);
			
			boardFile.setOriginName(upfile.getOriginalFilename());
			boardFile.setChangeName(changeName);
			boardFile.setFilePath("resources/img/community/");
			boardFile.setUserNo(loginUser.getUserNo());
		}
		
		board.setUserNo(loginUser.getUserNo());
		
		int result = communityService.insertBoard(board, boardFile);
		if(result > 0) { // 성공 -> list 페이지로 이동
			session.setAttribute("alertMsg", "게시글 작성 성공");
			return "redirect:/community";
		} else { // 실패 -> 에러 페이지
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "";
		}
	}
	
	// 실제 넘어온 파일의 이름을 변경해서 서버에 저장하는 메소드
	public String saveFile(MultipartFile upfile, HttpSession session) {
		String originName = upfile.getOriginalFilename();
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int ranNum = (int)(Math.random() * 90000 ) + 10000;
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = currentTime + ranNum + ext;
		
		String savePath = session.getServletContext().getRealPath("resources/img/community/");
	
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		경로 또는 url, 해당 파일 객체를 저장할 때 사용
		
		return changeName;
	}
	
	//게시글 삭제 컨트롤러
	@GetMapping(value="delete.bo")
	public String deleteBoard(BoardInfo boardInfo,HttpSession session) {
		Member loginUser=(Member)session.getAttribute("loginUser");
		if(loginUser==null) {
			session.setAttribute("alertMsg", "로그인을 먼저 해주세요");
			return "redirect:/detailView.bo?bno="+boardInfo.getBoardNo();
		}
		//본인 게시글인지 check
		int checkNo=communityService.checkBoardOwner(boardInfo.getBoardNo());
		if(loginUser.getUserNo()!=checkNo) {
			session.setAttribute("alertMsg", "본인게시글만 삭제할 수 있습니다.");
			return "redirect:/detailView.bo?bno="+boardInfo.getBoardNo();
		}else {
			
			//게시글 삭제첨부파일까지 삭제
			String savePath = session.getServletContext().getRealPath("resources/img/community/");
			ArrayList<Attachment> deletedBoardAttachment =communityService.deleteBoard(boardInfo); 
			if(deletedBoardAttachment!=null) {
				if(!deletedBoardAttachment.isEmpty()) {
					for(Attachment attachment:deletedBoardAttachment) {
						File file =new File(savePath+attachment.getChangeName());
						if(file.exists()) {
							file.delete();
						}
					}
				}
				session.setAttribute("alertMsg", "삭제되었습니다.");
				return "redirect:/community";
			}
			session.setAttribute("alertMsg", "삭제실패");
			return "redirect:/detailView.bo?bno="+boardInfo.getBoardNo();
		}
	}
	//게시글 수정페이지 이동 컨트롤러 (수정 페이지로 이동)
    @GetMapping(value="updateview.bo")
    public String updateBoardView(int boardNo ,Model model ,HttpSession session) {
    	
    	
    	Member loginUser = (Member)session.getAttribute("loginUser");
    	
    	//본인 게시글인지 check
		int checkNo=communityService.checkBoardOwner(boardNo);
		if(loginUser.getUserNo()!=checkNo) {
			session.setAttribute("alertMsg", "본인게시글만 수정할 수 있습니다.");
			return "redirect:/detailView.bo?bno="+boardNo;
		}else {
			
			CommunityBoard updateBoard = communityService.selectBoardDetail(boardNo);
			System.out.println(updateBoard.toString());
	    	model.addAttribute("updateBoard",updateBoard);
	    	return "community/editPage";
		}
    	
    
    }
    
    //게시글 수정
    
    @PostMapping(value="update.bo")
    public String updatedBoard(@RequestParam(value="existedFileChangeName",defaultValue="none")String existedFileChangeName, BoardEnroll board, MultipartFile upfile,HttpSession session, Model model) {
    	
    	
    	BoardFileInfo boardFile = new BoardFileInfo();
		Member loginUser = (Member)(session.getAttribute("loginUser"));
		board.setUserNo(loginUser.getUserNo());
		boardFile.setBoardNo(board.getBoardNo());
		
	
		String path="resources/img/community/";
		if(!upfile.getOriginalFilename().equals("")) {
			String changeName = saveFile(upfile, session,path);
			
			boardFile.setOriginName(upfile.getOriginalFilename());
			boardFile.setChangeName(changeName);
			boardFile.setFilePath("resources/img/community/");
			
		}
		//파일이 안넘어올때 디폴트 값으로 
//		else {
//			boardFile.setFilePath("resources/img/default/");
//			boardFile.setChangeName("defaultImg.png");
//			boardFile.setOriginName("defaultImg.png");
//		}
		boardFile.setUserNo(loginUser.getUserNo());
		
		
		int result=communityService.updateBoard(board, boardFile);
		log.info("result:{}",result);
		
		if(result>0) {
			session.setAttribute("alertMsg", "게시글이 수정 되었습니다.");
			
			//게시글 첨부파일까지 삭제
			//게시글 수정 되는 첨부파일이 있을때
			if(!existedFileChangeName.equals("none")) {
				String savePath = session.getServletContext().getRealPath("resources/img/community/");
				DeleteBoardAttachmentInfo deleteInfo= new DeleteBoardAttachmentInfo();
				deleteInfo.setBoardNo(board.getBoardNo());
				deleteInfo.setChangeName(existedFileChangeName);
				int count=communityService.deleteBoardAttachment(deleteInfo);
				File file =new File(savePath+existedFileChangeName);
				if(file.exists()) {
					file.delete();
					log.info("삭제성공");
				}
			}
			else {
				
			}
			
			
		}
		else {
			session.setAttribute("alertMsg", "게시글 수정에 실패하였습니다.");
		}
		return "redirect:/community";
		
    }
	
	
	
	@ResponseBody
	@GetMapping(value="getVideo.sh", produces="application/json; charset=utf-8")
	public String loadShorts(@RequestParam(value="videoId") int videoId,
							@RequestParam(value="userNo") int userNo) { // 로그인 안 했을 경우 default : 0

	    TotalShortsInfo totalShortsInfo = communityService.getVideoInfo(videoId); // 여기 들어오는 거 확인
	    if (totalShortsInfo == null) {
	        return "Error: Video info not found.";
	    } else {
	        int shortsNum = totalShortsInfo.getShortsNo();
	        totalShortsInfo.setLikeCount(communityService.getVideoLikeCount(shortsNum));
	        totalShortsInfo.setReplyCount(communityService.getVideoReplyCount(shortsNum));
	        
	        boolean isLike = false;
	        
	        
	        if(userNo != 0) {
	        	// 검사해봄
	        	ForIsLike forIsLike = new ForIsLike(userNo, shortsNum);
	        	log.info("like객체:" + forIsLike);
	        	int isLikeReturn = communityService.getIsLike(forIsLike);
	        	
	        	if (isLikeReturn > 0) {
	        		isLike = true;
	        	}
	        }
	        
	        totalShortsInfo.setLike(isLike);
	        
	        
	        log.info("totalInfo:" + totalShortsInfo);
	        
	        return new Gson().toJson(totalShortsInfo);
	    }
	}

	
	@ResponseBody
	@GetMapping(value="addComment.sh", produces="application/json; charset=utf-8")
	public String addComment(@RequestParam(value="userNo") int userNo, 
				            @RequestParam(value="num") int videoId, 
				            @RequestParam(value="comment") String comment) {
		
		log.info("userNo:" + userNo);
		log.info("num:" + videoId);
		log.info("comment:" + comment);
		
		int shortsNo = communityService.getShortsNum(videoId);
		
		log.info("shortsNum:" + shortsNo);
		
		ShortsReply shortsReply = communityService.addComment(userNo, shortsNo, comment);
		return new Gson().toJson(shortsReply);
	}
	
	@ResponseBody
	@GetMapping(value="loadReply.sh", produces="application/json; charset=utf-8")
	public String loadReply(@RequestParam(value="num") int videoId){
		int shortsNum = communityService.getShortsNum(videoId);
		
		ArrayList<ShortsReply> replyList = communityService.loadReply(shortsNum);
		return new Gson().toJson(replyList);
	}
	
	@ResponseBody
	@GetMapping(value="like.sh", produces="application/json; charset=utf-8")
	public String likeShorts(@RequestParam(value="num") int videoId,
							@RequestParam(value="userNum") int userNo){
		//totalInfo 가져와서 isLike가
		// true: 좋아요 삭제하는 쿼리 날림
		// false: 좋아요 추가하는 쿼리 날림
		// 미완성.....
		int shortsNum = communityService.getShortsNum(videoId);
		
		boolean isLike = false;
        
        if(userNo != 0) {
        	// 검사해봄
        	ForIsLike forIsLike = new ForIsLike(userNo, shortsNum);
        	log.info("forIsLikeObject:" + forIsLike);
        	int isLikeReturn = communityService.getIsLike(forIsLike);
        	log.info("isLikeReturn: " + isLikeReturn);
        	
        	if (isLikeReturn > 0) {
        		isLike = true;
        	} else {
        		isLike = false;
        	}
        }
        
        int result;
        if(isLike) {
        	// 좋아요 삭제하는 쿼리
        	ForIsLike forIsLike = new ForIsLike(userNo, shortsNum);
        	result = communityService.deleteLike(forIsLike); // 1 이상이면 성공
        } else {
        	// 좋아요 추가하는 쿼리
        	ForIsLike forIsLike = new ForIsLike(userNo, shortsNum);
        	result = communityService.likeShorts(forIsLike); // 1 이상이면 성공
        }
        
        if (result > 0) {
        	return "success";
        } else {
        	return "false";
        }
//		return communityService.likeShorts(forisLike);
//		return new Gson().toJson(replyList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 쇼츠 페이지
	@GetMapping("shortsContent.sh")
	public String shortsContent() {
		return "community/shortsContent";
	}
}
