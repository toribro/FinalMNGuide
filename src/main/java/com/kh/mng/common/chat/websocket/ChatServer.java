package com.kh.mng.common.chat.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.mng.common.chat.model.dto.ChatInfo;
import com.kh.mng.common.chat.model.dto.MsgVo;
import com.kh.mng.common.chat.service.ChatService;
import com.kh.mng.member.model.vo.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

    @Getter
    @Setter
	class ChatRoom{
		private int roomNo;
		private String userId;
		
	}

@Slf4j
@Component
public class ChatServer extends TextWebSocketHandler {
	
	private final Map<String,WebSocketSession> sessions= new ConcurrentHashMap<>();
//	private final Map<Integer,String> chatRooms =new ConcurrentHashMap<>();
	private String roomNo;
	private static final Map<String,Map<String,WebSocketSession>> rooms =new ConcurrentHashMap<>();
	
	private final ChatService chatService;
	
	@Autowired
	public ChatServer(ChatService chatService) {
		this.chatService=chatService;
		this.roomNo="";
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		Member member = (Member)session.getAttributes().get("loginUser");
		String userId=member.getUserId();
	    log.info("id:{}",userId);
//	    ChatRoom chatRoom =new ChatRoom();
//	    chatRoom.setUserId(userId);
//	    chatRoom.setRoomNo(0);
	    
	    this.roomNo=UUID.randomUUID().toString();
	    
	    chatService.joinChatRoom(member.getUserNo());
	    sessions.put(userId,session);
		
	
	 }
	

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Member loginUser = (Member)session.getAttributes().get("loginUser");
		String nickName=loginUser.getUserNickname();
		String userId=loginUser.getUserId();
		int userNo=loginUser.getUserNo();
		
		//메세지 db연결...
		JsonObject obj = new JsonParser().parse(message.getPayload()).getAsJsonObject();
		String sendMessage=obj.get("message").getAsString();
		
		String target=obj.get("target").getAsString();//
		String targetNo=obj.get("targetNo").getAsString();
		int locationNo=obj.get("locationNo").getAsInt();
		int roomNo=obj.get("roomNo").getAsInt();
		
//		ChatRoom receivechats =new ChatRoom();
//		receivechats.setRoomNo(roomNo);
//		receivechats.setUserId(target);
		
	
		
		
		//dto로 저장 (데이터베이스에 전송 준비)
		ChatInfo chatInfo =new ChatInfo();
		chatInfo.setUserId(userId);
		chatInfo.setUserNickName(nickName);
		chatInfo.setMessage(sendMessage);
		chatInfo.setTargetId(target);
		chatInfo.setTargetNo(Integer.parseInt(targetNo));
		chatInfo.setRoomNo(this.roomNo);
		chatInfo.setUserNo(loginUser.getUserNo());
		chatInfo.setLocationNo(locationNo);
		
		//db에 메세지 저장(ChatRoom에 참여)
		int count=chatService.insertChats(chatInfo);
		
		
		log.info("target:{}",target);
		
		MsgVo vo = new MsgVo();
		vo.setMsg(sendMessage);
		vo.setNick(nickName);
		vo.setId(userId);
		vo.setTime(new Date().toLocaleString());
		vo.setUserNo(userNo);
		log.info("msg:{}",vo);
		//다른 사용자에게 메세지 전송
		sendMessageUser(target,vo);
	
	}
	
	
	private void sendMessageUser(String targetId,MsgVo msgVo) {
		
		WebSocketSession targetSession =sessions.get(targetId);//메세지 수신자
		
	
		log.info("{}",targetSession);
		WebSocketSession mySession =sessions.get(msgVo.getId());
		
		if(targetSession!=null && targetSession.isOpen()) {
			String str=new Gson().toJson(msgVo);
			
			TextMessage msg = new TextMessage(str);
			try {
				//mySession.sendMessage(msg);
				targetSession.sendMessage(msg);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	




	// 클라이언트가 연결을 끊을때 호출되는 메소드 (클라이언트가 끊을때 반응해주어야한다.)
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		Member loginUser = (Member) session.getAttributes().get("loginUser");
		String userId = loginUser.getUserId();
		int userNo=loginUser.getUserNo();
		
		log.info("{}", "연결끊낌.....");
		sessions.remove(userId);
		
		//방에서 나가게하기 status=false로 변경
		chatService.outChatRoom(userNo);
		
		super.afterConnectionClosed(session, status);
	}
	
	
	
	
}
