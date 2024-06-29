package com.kh.mng.common.chat.model.dao;

import java.util.ArrayList;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.common.chat.model.dto.ChatInfo;
import com.kh.mng.common.chat.model.dto.UserTarget;
import com.kh.mng.common.chat.model.vo.Chat;
import com.kh.mng.common.chat.model.vo.ChatLocationInfo;
import com.kh.mng.common.chat.model.vo.MasterInfo;
import com.kh.mng.common.chat.model.vo.UserInfo;

@Repository
public class ChatDao {

	public int insert(SqlSessionTemplate sqlSession, ChatInfo chatInfo) {
		
		return sqlSession.insert("chatMapper.insertChatRoom",chatInfo);
	}

	public int outChat(SqlSessionTemplate sqlSession, int userNo) {
	
		return sqlSession.update("chatMapper.outChatRoom",userNo);
	}

	public int joinChat(SqlSessionTemplate sqlSession, int userNo) {
		
		return sqlSession.update("chatMapper.joinChatRoom",userNo);
	}

	public MasterInfo selectMasterInfo(SqlSessionTemplate sqlSession,int locationNo) {
	
		return sqlSession.selectOne("chatMapper.selectMasterInfo",locationNo);
	}
	
	public ArrayList<UserInfo> selectUserInfo(SqlSessionTemplate sqlSession,UserTarget userMasterInfo) {
		
		return (ArrayList) sqlSession.selectList("chatMapper.selectUserInfo",userMasterInfo);
	}

	public ArrayList<Chat> selectUserChats(SqlSessionTemplate sqlSession, UserTarget userMasterInfo) {
		
		
		return (ArrayList) sqlSession.selectList("chatMapper.selectUserChats", userMasterInfo);
	}

	public int selectNotifyMessageCount(SqlSessionTemplate sqlSession,UserTarget targetUser) {
		
		return sqlSession.selectOne("chatMapper.selectNotifyMessageCount",targetUser);
	}

	public Map<String, String> selectNotifyMessage(SqlSessionTemplate sqlSession,UserTarget targetUser) {
		
		return (Map)sqlSession.selectOne("chatMapper.selectlastestMessage", targetUser);
	}

	public int updateNotify(SqlSessionTemplate sqlSession,int chatNo) {
		
		return sqlSession.update("chatMapper.updateNotify",chatNo);
	}

	public ChatLocationInfo selectLocationInfo(SqlSessionTemplate sqlSession, int locationNo) {
		
		return sqlSession.selectOne("chatMapper.locationInfo",locationNo);
	}

	public ArrayList<Integer> selectLocationNo(SqlSessionTemplate sqlSession, int userNo) {
		
		return (ArrayList)sqlSession.selectList("chatMapper.selectlocationNo",userNo);
	}

	public int deleteChats(SqlSessionTemplate sqlSession, UserTarget userMasterInfo) {
		
		return sqlSession.delete("chatMapper.deleteChats",userMasterInfo);
	}

}
