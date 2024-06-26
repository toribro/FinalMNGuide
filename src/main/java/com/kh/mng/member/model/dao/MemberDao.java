package com.kh.mng.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.bosspage.model.vo.BossLocation;
import com.kh.mng.bosspage.model.vo.LocationEnterGrade;
import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.common.phonesms.PhoneSmsVo;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.location.model.vo.EnterGrade;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.location.model.vo.MyPageEnter;
import com.kh.mng.location.model.vo.MyPageReview;
import com.kh.mng.location.model.vo.Picked;
import com.kh.mng.location.model.vo.WishListNo;
import com.kh.mng.member.model.vo.Member;

@Repository
public class MemberDao {
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}
	
	public ProfileImg selectMemberProfile(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectOne("imgMapper.getProfileImg", userNo);
	}
	
	public int checkMemberId(SqlSessionTemplate sqlSession, String checkId) {
//		Member m = new Member();
//		m.setUserId(userId);
//		userId는 이미 Member의 result값으로 받겠다고 하니까 userId로 동일하게 넣으면 파라미터 값이 없다고 오류가 뜨는 것 같다
//		SELECT USER_ID 하면 자료형이 String이니까 resultType int로 못 받지...
		return sqlSession.selectOne("memberMapper.checkMemberId", checkId);
	}
	
	// 회원가입
	public int insertCommonMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertCommonMember", m);
	}
	
	public int insertBossMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertBossMember", m);
	}
	
	public int selectUserNo(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("memberMapper.selectUserNo");
	}
	
	public int insertLocation(SqlSessionTemplate sqlSession, Location loc) {
		return sqlSession.insert("location.insertLocation", loc);
	}
	
//	핸드폰 인증
	public int checkPhoneNumber(SqlSessionTemplate sqlSession, String userPhone) {
		return sqlSession.selectOne("memberMapper.checkPhoneNumber", userPhone);
	}
	
	public int selectCertifyCode(SqlSessionTemplate sqlSession, PhoneSmsVo psv) {
		return sqlSession.selectOne("memberMapper.selectCertifyCode", psv);
	}
	
	public int deleteCertifyCode(SqlSessionTemplate sqlSession, PhoneSmsVo psv) {
		return sqlSession.delete("memberMapper.deleteCertifyCode", psv);
	}
	
	public int insertCertifyCode(SqlSessionTemplate sqlSession, PhoneSmsVo psv) {
		return sqlSession.insert("memberMapper.insertCertifyCode", psv);
	}
	
	public PhoneSmsVo checkCertifyCode(SqlSessionTemplate sqlSession, String phone) {
		return sqlSession.selectOne("memberMapper.checkCertifyCode", phone);
	}
	
	public Member selectUserByPhone(SqlSessionTemplate sqlSession, String userPhone) {
		return sqlSession.selectOne("memberMapper.selectUserByPhone", userPhone);
	}
	
	public int updatePwd(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMemberPwd", m);
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember", m);
	}
	
	public int deleteMember(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.update("memberMapper.deleteMember", userNo);
	}
	
	public List<Picked> getPickList(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectList("picked.getPickList", userNo);
	}
	
	public List<BossLocation> getLocationList(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("location.getLocationList");
	}
	
	public int wishListDelete(SqlSessionTemplate sqlSession, WishListNo wishListNo) {
		return sqlSession.delete("picked.pickedDelete", wishListNo);
	}

	public int deleteBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.delete("communityBoardMapper.deleteBoard", boardNo);
	}

	public int deleteReview(SqlSessionTemplate sqlSession, int reviewNo) {
		return sqlSession.delete("review.deleteMyReview", reviewNo);
	}

	public List<Shorts> getShortsList(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectList("shortsMapper.getVideoInfo", userNo);
	}

	public int updateReview(SqlSessionTemplate sqlSession, MyPageReview myReview) {
		return sqlSession.update("review.updateReview", myReview);
	}

	public int insertProfileImg(SqlSessionTemplate sqlSession, ProfileImg profileImg) {
		return sqlSession.insert("imgMapper.insertProfileImg", profileImg);
	}

	public ProfileImg getProfileImg(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectOne("imgMapper.getProfileImg", userNo);
	}

	public int updateProfileImg(SqlSessionTemplate sqlSession, ProfileImg profileImg) {
		return sqlSession.update("imgMapper.updateProfileImg", profileImg);
	}

	public ProfileImg getLocationImg(SqlSessionTemplate sqlSession, int locationNo) {
		return sqlSession.selectOne("imgMapper.getLocationImg", locationNo);
	}

	public List<Picked> getPickCount(SqlSessionTemplate sqlSession, int locationNo) {
		return sqlSession.selectList("picked.getPickCount", locationNo);
	}

	public List<MyPageEnter> getEnterList(SqlSessionTemplate sqlSession, int locationNo) {
		return sqlSession.selectList("location.getEnterList", locationNo);
	}

	public List<ProfileImg> getReviewImg(SqlSessionTemplate sqlSession, int reviewNo) {
		return sqlSession.selectList("imgMapper.getReviewImg", reviewNo);
	}

	public List<ProfileImg> getBoardImg(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectList("imgMapper.getBoardImg", boardNo);
	}
}
