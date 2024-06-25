package com.kh.mng.member.service;

import java.util.ArrayList;
import java.util.List;

import com.kh.mng.bosspage.model.vo.BossLocation;
import com.kh.mng.bosspage.model.vo.LocationEnterGrade;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.common.phonesms.PhoneSmsVo;
import com.kh.mng.community.model.vo.BoardNum;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.location.model.vo.EnterGrade;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.location.model.vo.MyPageEnter;
import com.kh.mng.location.model.vo.MyPageReview;
import com.kh.mng.location.model.vo.Picked;
import com.kh.mng.location.model.vo.WishListNo;
import com.kh.mng.member.model.vo.Member;

public interface MemberService {

	// 로그인
	Member loginMember(Member m);
	
	// 아이디 중복 확인
	int checkMemberId(String userId);
	
	// 일반 회원가입
	int insertCommonMember(Member m);
	
	// 사장 회원가입
	int insertBossMember(Member m, Location loc);
	
	// 핸드폰 번호 중복 체크
		int checkPhoneNumber(String userPhone);
		
	// 기존 인증코드 있는지 확인 및 삭제 후 암호화된 6자리 랜덤 숫자 저장
	int insertCertifyCode(PhoneSmsVo psv);

	// 저장된 핸드폰 번호로 된 인증번호 가져오기
	PhoneSmsVo checkCertifyCode(String phone);
	
	// 개인정보 수정
	int updateMember(Member m);
	
	// 회원 탈퇴
	int deleteMember(int userNo);
	
	// 공감 목록 불러오기
	List<Picked> getPickList(int userNo);
	
	List<BossLocation> getLocationList();
	
	// 공감 목록 삭제
	int wishListDelete(WishListNo wishListNo);
	
	// 게시글 삭제
	int deleteBoard(int boardNo);
	
	// 리뷰 삭제
	int deleteReview(int reviewNo);
	
	// 쇼츠 불러오기
	List<Shorts> getShortsList(int userNo);
	
	// 리뷰 업데이트
	int updateReview(MyPageReview myReview);
	
	// 프로필 사진 업로드
	int insertProfileImg(ProfileImg profileImg);

	ProfileImg getProfileImg(int userNo);

	int updateProfileImg(ProfileImg profileImg);

	ProfileImg getLocationImg(int locationNo);

	List<Picked> getPickCount(int locationNo);

	List<MyPageEnter> getEnterList(int locationNo);

	List<ProfileImg> getReivewImg(int reviewNo);

	List<ProfileImg> getBoardImg(int boardNo);
}
