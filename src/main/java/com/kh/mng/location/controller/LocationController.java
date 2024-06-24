package com.kh.mng.location.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.mng.common.chat.model.dto.UserTarget;
import com.kh.mng.common.chat.model.vo.Chat;
import com.kh.mng.common.chat.model.vo.ChatLocationInfo;
import com.kh.mng.common.chat.model.vo.MasterInfo;
import com.kh.mng.common.chat.model.vo.UserInfo;
import com.kh.mng.common.chat.service.ChatService;
import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.common.model.vo.Pagination;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.location.model.dto.PickedInfo;
import com.kh.mng.location.model.dto.ReplyInfo;
import com.kh.mng.location.model.dto.ReviewInfo;
import com.kh.mng.location.model.dto.ReviewPage;
import com.kh.mng.location.model.vo.DetailLocationAttachment;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.location.model.vo.Review;
import com.kh.mng.location.service.LocationService;
import com.kh.mng.member.model.vo.Member;
import com.kh.mng.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

import com.kh.mng.location.model.vo.AddressInfo;
import com.kh.mng.location.model.vo.DetailLocation; 


@Controller
@Slf4j
public class LocationController {

	private final LocationService detailService;
	private final MemberService memberService;
	private final ChatService chatService;
	
	
//	@GetMapping("/chat")
//	public String ChatList(int locationNo,Model model,HttpSession session) {
//		log.info("{}",locationNo);
//		
//		Member connectedUser =((Member)session.getAttribute("loginUser"));
//		session.setAttribute("connectedUser", connectedUser);
//		
//		//사장님 아이디 가져오기 
//    	String masterId= detailService.getMasterId(locationNo);
//    	log.info(masterId);
//		model.addAttribute("entityId", masterId);
//		return "chat/chat";
//	}
	
	
	@Autowired 
	public  LocationController(LocationService detailService,ChatService chatService,MemberService memberService) {
		this.detailService=detailService;
		this.chatService=chatService;
		this.memberService=memberService;
	}

	
	@GetMapping("/detail")
	public String DetailLocation(@RequestParam(value="locationNo",defaultValue="1") int locationNo,Model model,HttpSession session) {
		
	
		//장소정보와 ,리뷰 정보도
		// 공간 이미지를 db에서 받아온다. ,리뷰 정보도
	  //	DetailLocation_ detailLocation =  detailService.selectDetailLocation_(locationNo);
	  	
	  	
		int reviewCount=detailService.selectReviewCount(locationNo);
		PageInfo pi =Pagination.getPageInfo(reviewCount,1,10,5);
	  	ArrayList<Review> reviews =detailService.selectDetailReviewList(locationNo,pi);
	  	
	
        DetailLocation detailLocations =detailService.selectDetailLocation(locationNo);
		
		//로그인 유저 정보 가져오기
		Member loginUser= (Member)session.getAttribute("loginUser");
		String checkedMaster="NNNN";
		int userNo=-1;
		
		ProfileImg userProfile =null;
		if(loginUser!=null) {
			//사장인지 체크
			if((loginUser.getUserNo()==detailLocations.getUserNo())&& (detailLocations.getUserKind().equals("Y"))) {
				checkedMaster="YYYY";
			}
			userNo=loginUser.getUserNo();
			userProfile = memberService.getProfileImg(userNo);
		}
		
		System.out.println(checkedMaster);
		model.addAttribute("reviewPi",pi);
		model.addAttribute("review",reviews);
		model.addAttribute("l",detailLocations);
		model.addAttribute("checkedMaster",checkedMaster);
		model.addAttribute("reviewCount",reviewCount);
		model.addAttribute("userNo",userNo);
		model.addAttribute("userProfile",userProfile);
		
		return "location/detail";
	}
	
	
	//유저 찜 상태 가져오는 컨트롤러
	@ResponseBody
	@PostMapping(value="state",produces="application/json; charset=utf-8")
	public String pickedState(@RequestBody PickedInfo pickedInfo) {
		 
		
		//찜 확인(db거친다)
		//이 공간에대한 유저 찜 상태 받기
		int count= detailService.pickedStatus(pickedInfo);
		char status=' ';
		
		//찜 상태검사
		if(count==0) {
			status='n';
			//count++;
		}
		else {
			status='y';
			//count--;
		}

	    return new Gson().toJson(status);
	}
	
	
	 //공간 찜 전체개수
	@ResponseBody
	@PostMapping(value="count",produces="application/json; charset=utf-8")
	public String pickedCount(@RequestBody PickedInfo pickedInfo) {
			 
			//찜 확인(db거친다)
		     //이 공간에대한 전채찜 개수 받기
			int count= detailService.pickedCount(pickedInfo.getLocationNo());
	
		    return new Gson().toJson(count);
	}
	
	
	//유저가 찜하기 
	@ResponseBody
	@PostMapping(value="pick",produces="application/json; charset=utf-8")
	public String pick(@RequestBody PickedInfo pickedInfo) {
		System.out.println("pickedSErver:"+pickedInfo);
//		
		char status=pickedInfo.getStatus();
		int count= detailService.pickedStatus( pickedInfo);
		
		int result=0;
		if(count==0) {
		    result=detailService.insertPicked(pickedInfo);
			status='y';
		}
		else {
			result=detailService.deletePicked(pickedInfo);
			status='n';
		}
		
	    return new Gson().toJson(status);
	}
	
	
	//Review ,file upload
	
	
	
	//여기서 페이징 처리
	@ResponseBody
	@GetMapping(value="list.re",produces="application/json; charset=utf-8")
	public String selectReview(@RequestParam(value="locationNo") int locationNo,
							@RequestParam(value="currentPage",defaultValue="1") int currentPage,
							@RequestParam(value="type",defaultValue="o") String type ) {
		
		int reviewCount=detailService.selectReviewCount(locationNo);
		PageInfo pi =Pagination.getPageInfo(reviewCount,currentPage,10,5);
		
		//selectCategoryReviewList
		
		ReviewInfo reviewInfo = new ReviewInfo();
		reviewInfo.setLocationNo(locationNo);
		reviewInfo.setCurrentPage(currentPage);
		reviewInfo.setType(type);
		
		
	 	ArrayList<Review> reviews =detailService.selectCategoryReviewList(reviewInfo,pi);
	 	ReviewPage reivewPage =new ReviewPage();
	 	reivewPage.setPage(pi);
	 	reivewPage.setReviews(reviews);
	 	
	 	//리뷰 평점 업데이트
	 	int count=detailService.updateDateReviewScore(locationNo,reviewCount);
	
		//System.out.println(new Gson().toJson(pageReview));
		//Type type = new TypeToken<Map<PageInfo, ArrayList<Review>>>(){}.getType();
		System.out.println(new Gson().toJson(reivewPage ));
		//return new Gson().toJson(pageReview);
		return new Gson().toJson(reivewPage);
	}
	
//	@ResponseBody
//	@GetMapping(value="paging.re",produces="application/json; charset=utf-8")
//	public String arrowAjax(@RequestParam(value="locationNo") int locationNo,
//							@RequestParam(value="currentPage",defaultValue="1") int currentPage ) {
//		
//		int reviewCount=detailService.selectReviewCount(locationNo);
//		PageInfo pi =Pagination.getPageInfo(reviewCount,currentPage,10,5);
//		
//		
//	 	ArrayList<Review> reviews =detailService.selectDetailReviewList(locationNo,pi);
//		//System.out.println(reviews);
//		System.out.println(pi.getCurrentPage());
//		System.out.println(pi);
//		return new Gson().toJson(pi.getCurrentPage());
//	}
	
	
	
	@ResponseBody
	@PostMapping(value="insert.re")
	public String insertReview(List<MultipartFile> files,
							  ReviewInfo reviewInfo,
			                  HttpSession session,Model model) {
	
	     
		// List<String> changeNamesList = new ArrayList<String>();
		 Map<String,String>fileNames= new HashMap<String,String>();
		 String path="resources/img/user/";
		 if(files!=null) {
		 	
		 
	      	for(MultipartFile f :files) {
	      		String changeName=saveFile(f,session,"resources/img/user/");
	      		//changeNamesList.add(changeName);
	      		fileNames.put(f.getOriginalFilename(),changeName);
	      	}
		 }
 
	        int count=detailService.insertReview(reviewInfo,fileNames, path);
	        
	      	
	        if(count>0) {
	        	 return "ok";
	        }
	        else {
	        	return "error";
	        }
	 }
	
	private String saveFile(MultipartFile upfile,HttpSession session,String path) {
		//파일명 수정 후 서버에 업로드하기("imgFile.jpg => 202404231004305488.jpg")
				String originName = upfile.getOriginalFilename();
				
				//년월일시분초 
				String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
				//5자리 랜덤값
				int ranNum = (int)(Math.random() * 90000) + 10000;
				
				//확장자
				String ext = originName.substring(originName.lastIndexOf("."));
				
				//수정된 첨부파일명
				String changeName = currentTime + ranNum + ext;
				
				//첨부파일을 저장할 폴더의 물리적 경로(session)
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
	
	
	@ResponseBody
	@PostMapping(value="delete.re")
	public String deleteReview( ReviewInfo reviewInfo,HttpSession session) {
	
//		ReviewInfo reviewInfo= new ReviewInfo();
//		reviewInfo.setLocationNo(locationNo);
//		reviewInfo.setReviewNo(reviewNo);
//		reviewInfo.setUserNo(userNo);
		
		String savePath = session.getServletContext().getRealPath("resources/img/user/");
		ArrayList<Attachment> deleteAttachment=detailService.deleteReview(reviewInfo);
		if(deleteAttachment!=null) {
			if(!deleteAttachment.isEmpty()) {
			for(Attachment attachment:deleteAttachment) {
				File file = new File(savePath+attachment.getChangeName());
				System.out.println(file.getPath());
				if(file.exists()) {
					file.delete();
				}
			}
	    }
		   return "ok";
		}
		else {
	
			return "fail";
		}
		
		
		
		
	}
	
	//사장님답글 insert
	@ResponseBody
	@PostMapping(value="reply.in")
	public String replyInsert(ReplyInfo reply) {
		
		System.out.println(reply.getContent());
		int count = detailService.insertReply(reply);
		
		return "response";
	}
	
	
//	//분류별 리뷰 분류
//	@ResponseBody
//	@GetMapping(value="review.ca", produces="application/json; charset=utf-8")
//	public String reviewCategory(ReviewInfo review) {
//		
//		System.out.println(review.getType());
//
//		int reviewCount=detailService.selectReviewCount(review.getLocationNo());
//		PageInfo pi =Pagination.getPageInfo(reviewCount,1,10,5);
//		
//		
//	 	ArrayList<Review> reviews =detailService.selectCategoryReviewList(review,pi);
//	 	ReviewPage reivewPage =new ReviewPage();
//	 	reivewPage.setPage(pi);
//	 	reivewPage.setReviews(reviews);
//	 	
//	
//		//System.out.println(new Gson().toJson(pageReview));
//		//Type type = new TypeToken<Map<PageInfo, ArrayList<Review>>>(){}.getType();
//		System.out.println(new Gson().toJson(reivewPage));
//		//return new Gson().toJson(pageReview);
//		return new Gson().toJson(reivewPage);
//	}
	
	//==========================/////채팅/////==============================================//
	
	@GetMapping(value = "chatPage.cp")
	public String chatPage(@RequestParam(value="locationNo",defaultValue="1") int locationNo,  Model model,HttpSession session) {
		
		Member loginUser =((Member)session.getAttribute("loginUser"));
		log.info("loginUser:{}",loginUser.getUserId());;
		
		String check=loginUser.getUserKind();
		log.info("userKind:{}",check);
		
		// MasterInfo masterInfo= chatService.selectMasterInfo(locationNo);
		
		
		//접속 유저가 사장님이면?
		if(check.equals("Y")) {
			//ArrayList<Chat> masterchats=chatService.selectChats(loginUser.getUserNo());
			
		
			//저장할 사장님 정보
		    UserTarget masterUserInfo =new UserTarget();
		    
			//채팅 데이터베이스에 사장님 목록 가져오기 (target이 자신인것만 채팅 목록에 띄우기 위해) 
		    MasterInfo userInfo= chatService.selectMasterInfo(locationNo);
		    masterUserInfo.setTargetNo(userInfo.getMasterNo());
		    masterUserInfo.setUserNo(loginUser.getUserNo());
			 
		    
		    //가져온 사장님정보로 타켓이 자신인 유저 정보를 가져오기
		    ArrayList<UserInfo> userInfos= chatService.selectUserInfo(masterUserInfo);//dao에서 TargetNo사용하지 않음
		 
			
			//사장님이라는 것을 알려줄수있는 표시
			model.addAttribute("master","YMYMYMMYYY");
			//model.addAttribute("entireChats",entireChats);
			model.addAttribute("chatUserList",userInfos);
			
			
			
		}
		
		//접속유저가 일반 회원이면
		else {
			 UserTarget userMasterInfo =new UserTarget();
			
			//사장님아이디찾기
			 MasterInfo masterInfo= chatService.selectMasterInfo(locationNo);
	         
			 //저장
			 userMasterInfo.setTargetNo(masterInfo.getMasterNo());
			 userMasterInfo.setUserNo(loginUser.getUserNo());
			 
			 
			//채팅 데이터베이스에서 현재 자신의 채팅 내용들 가져오기  status true인 상태인 채팅만
			ArrayList<Chat> chats=chatService.selectUserChats(userMasterInfo);
			log.info("chats:{}",chats.toString());
			model.addAttribute("master","NNNNN");
			model.addAttribute("masterInfo",masterInfo);
			model.addAttribute("chats",chats);
			
			//채팅 목록 가져오기
				
		}
		
	   return "chat/chat";
	}
	
	//접속유저가 사장님일시   유저리스트 클릭시 비동기로 채팅목록 띄워주기 notify를 false로 만들자
	@ResponseBody
	@GetMapping(value="view.chat" ,produces="application/json; charset=utf-8")
	public String mastGetChatList(UserTarget userMasterInfo) {
		ArrayList<Chat> chats=chatService.selectUserChats(userMasterInfo);
		log.info("{}"+chats);
		return new Gson().toJson(chats);
	}
	
	//접속유저가 유저일시 채팅목록 띄워주기 , 채팅방 이름은 장소이름으로 한다.
	//목록을 클릭하면  프론트에서 해당 채팅방으로 포워딩 시키기
	@GetMapping(value="userchat.view")
	public String userGetChatList(Model model,HttpSession session) {
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		ArrayList<ChatLocationInfo> locationInfo = chatService.selectChatLocationInfo(userNo);
		model.addAttribute("locationInfo",locationInfo);
		return "chat/chatUserList";
	}
	
	

	//////////////////////////////////////////////////////////////////////////////////
	
	//장소 목록 가져오기 (메인페이지 지도)
	
	@ResponseBody
	@GetMapping(value="getAddress",produces="application/json; charset=utf-8")
	public String getAddress(@RequestParam(value="type",defaultValue="default") String type) {
		
		log.info("type:{}",type);
		ArrayList<AddressInfo> addressInfo = detailService.selectAddress(type);
		log.info("address:{}",addressInfo.toString());
		return new Gson().toJson(addressInfo);
		
	}
	
	
	//로그인 정보 가져오기-->실패
	@ResponseBody
	@GetMapping(value="user.ge")
	public String getLoginUserNo(HttpSession session) {
		
		Member loginUser= (Member)session.getAttribute("loginUser");
		if(loginUser==null) {
			return "-1";
		}
		else {
			return loginUser.getUserNo()+"";
		}
		
	}
	
	
}
