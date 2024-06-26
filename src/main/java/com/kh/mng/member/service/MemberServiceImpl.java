package com.kh.mng.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mng.bosspage.model.vo.BossLocation;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.common.phonesms.PhoneSmsVo;
import com.kh.mng.community.model.vo.Shorts;
import com.kh.mng.location.model.dao.DetailDao;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.location.model.vo.MyPageEnter;
import com.kh.mng.location.model.vo.MyPageReview;
import com.kh.mng.location.model.vo.Picked;
import com.kh.mng.location.model.vo.WishListNo;
import com.kh.mng.member.model.dao.MemberDao;
import com.kh.mng.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private DetailDao detailDao;

	@Override
	public Member loginMember(Member m) {
		Member loginUser = memberDao.loginMember(sqlSession, m);
		if (loginUser != null) {
			log.info("들어옴 {}", loginUser.toString());
			loginUser.setUserProfile(memberDao.selectMemberProfile(sqlSession, loginUser.getUserNo()));
			log.info("여기는 {}", loginUser.toString());
		}
		return loginUser;
	}
	
//	MemberDto loginUser = memberDao.loginMember(sqlSession, m);
//	if (loginUser != null) {
//		loginUser.setUserProfile(memberDao.selectMemberProfile(sqlSession, loginUser));
//	}

	@Override
	public int checkMemberId(String userId) {
		return memberDao.checkMemberId(sqlSession, userId);
	}

	@Override
	public int insertCommonMember(Member m) {
		return memberDao.insertCommonMember(sqlSession, m);
	}

	@Override
	@Transactional
	public int insertBossMember(Member m, Location loc) {
		int result1 = memberDao.insertBossMember(sqlSession, m);
		int result2 = 1;
		int userNo = 0;

		if (result1 == 1) {
			userNo = memberDao.selectUserNo(sqlSession);

			if (userNo != 0) {
				loc.setUserNo(userNo);
				result2 = memberDao.insertLocation(sqlSession, loc);
			}
		} else {
			result1 = 0;
		}

		return result1 * result2;
	}
	
	// 핸드폰 중복 체크
	@Override
	public int checkPhoneNumber(String userPhone) {
		return memberDao.checkPhoneNumber(sqlSession, userPhone);
	}
	
	@Override
	@Transactional
	public int insertCertifyCode(PhoneSmsVo psv) {
//			이 메소드에서만 sqlSession이 null로 뜨는 현상 발생 / 생성자 주입 방식을 통해 해결
//			return bossPageDaoSecond.insertCertifyCode(sqlSession, psv);
		
		// 저장되어 있는 코드 있는지 전화번호로 확인
		int code = memberDao.selectCertifyCode(sqlSession, psv);
		int result1 = 1;
		int result2 = 1;
		
		// 저장된 코드 있는 경우 delete문 실행
		if (code > 0) {
			result1 = memberDao.deleteCertifyCode(sqlSession, psv);
		}
		
		// 삭제 쿼리 실행 후 결과가 성공이면 insert문 실행
		if (result1 > 0) {
			result2 = memberDao.insertCertifyCode(sqlSession, psv);
		}
		
		return result1 * result2;
	}

	@Override
	public PhoneSmsVo checkCertifyCode(String phone) {
		return memberDao.checkCertifyCode(sqlSession, phone);
	}
	
	@Override
	public Member selectUserByPhone(String userPhone) {
		return memberDao.selectUserByPhone(sqlSession, userPhone);
	}
	
	@Override
	public int updatePwd(Member m) {
		return memberDao.updatePwd(sqlSession, m);
	}
	

	@Override
	public int updateMember(Member m) {
		return memberDao.updateMember(sqlSession, m);
	}

	@Override
	public int deleteMember(int userNo) {
		return memberDao.deleteMember(sqlSession, userNo);
	}

	@Override
	public List<Picked> getPickList(int userNo) {
		return memberDao.getPickList(sqlSession, userNo);
	}

	@Override
	public List<BossLocation> getLocationList() {
		return memberDao.getLocationList(sqlSession);
	}

	@Override
	public int wishListDelete(WishListNo wishListNo) {
		return memberDao.wishListDelete(sqlSession, wishListNo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return memberDao.deleteBoard(sqlSession, boardNo);
	}

	@Override
	public int deleteReview(int reviewNo) {
		return memberDao.deleteReview(sqlSession, reviewNo);
	}

	@Override
	public List<Shorts> getShortsList(int userNo) {
		return memberDao.getShortsList(sqlSession, userNo);
	}

	@Override
	public int updateReview(MyPageReview myReview) {
		return memberDao.updateReview(sqlSession, myReview);
	}

	@Override
	public int insertProfileImg(ProfileImg profileImg) {
		return memberDao.insertProfileImg(sqlSession, profileImg);
	}

	@Override
	public ProfileImg getProfileImg(int userNo) {
		return memberDao.getProfileImg(sqlSession, userNo);
	}

	@Override
	public int updateProfileImg(ProfileImg profileImg) {
		return memberDao.updateProfileImg(sqlSession, profileImg);
	}

	@Override
	public ProfileImg getLocationImg(int locationNo) {
		return memberDao.getLocationImg(sqlSession, locationNo);
	}

	@Override
	public List<Picked> getPickCount(int locationNo) {
		return memberDao.getPickCount(sqlSession, locationNo);
	}

	@Override
	public List<MyPageEnter> getEnterList(int locationNo) {
		return memberDao.getEnterList(sqlSession, locationNo);
	}

	@Override
	public List<ProfileImg> getReivewImg(int reviewNo) {
		return memberDao.getReviewImg(sqlSession, reviewNo);
	}

	@Override
	public List<ProfileImg> getBoardImg(int boardNo) {
		return memberDao.getBoardImg(sqlSession, boardNo);
	}
}
