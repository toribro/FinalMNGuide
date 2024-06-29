package com.kh.mng.common.chat.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.mng.common.chat.model.dto.UserTarget;
import com.kh.mng.common.chat.model.vo.Chat;
import com.kh.mng.common.chat.model.vo.ChatLocationInfo;
import com.kh.mng.common.chat.model.vo.MasterInfo;
import com.kh.mng.common.chat.model.vo.UserInfo;
import com.kh.mng.common.chat.service.ChatService;
import com.kh.mng.location.service.LocationService;
import com.kh.mng.member.model.vo.Member;
import com.kh.mng.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {

	private final ChatService chatService;
	
	@Autowired 
	public  ChatController(ChatService chatService) {
		this.chatService=chatService;
	}
	
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
		    
		    //사장님정보 저장
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
	
	
	//채팅 목록 삭제 (나가기 버튼을 누르면 장소정보받아 사장님 아이디 가져오고 userNo target지정후 삭제
	
	@GetMapping(value="delete.chat")
	public String deleteChat(@RequestParam(value="locationNo") int locationNo,HttpSession session) {
		Member loginUser =((Member)session.getAttribute("loginUser"));
	    UserTarget userMasterInfo =new UserTarget();
		
		
		//채팅 데이터베이스에 사장님 목록 가져오기 (target이 자신인것만 채팅 목록에 띄우기 위해) 
	    MasterInfo masterInfo= chatService.selectMasterInfo(locationNo);
	    
	    //유저정보와 타겟정보(사장님정보담기)
	    userMasterInfo.setTargetNo(masterInfo.getMasterNo());
		userMasterInfo.setUserNo(loginUser.getUserNo());
		
		int count=chatService.deleteUserChats(userMasterInfo);
		
		if(count>0) {
			 session.setAttribute("alertMsg", "채팅방을 나갔습니다.");
		}else {
			 session.setAttribute("alertMsg", "실패");
		}
	    
	    
	    return "redirect:/userchat.view";
	}

	
}
