package com.kh.mng.bosspage.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mng.bosspage.model.dao.BossPageDaoSecond;
import com.kh.mng.bosspage.model.dto.CouponKind;
import com.kh.mng.common.phonesms.PhoneSmsVo;
import com.kh.mng.community.model.dto.BoardEnroll;
import com.kh.mng.community.model.dto.BoardFileInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BossPageServiceImplSecond implements BossPageServiceSecond{
	
//private final MemberService memberService;
//	
//	private final BCryptPasswordEncoder bcryptPasswordEncoder;
//	
////	이 생성자는 객체가 생성될 때 만들어지는 것이므로 사이에 null이 들어갈 시차가 생기지 않음
//	@Autowired
//	public MemberController(MemberService memberService, BCryptPasswordEncoder bcryptPasswordEncoder) {
//		this.memberService = memberService;
//		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
//	}
	
//	private final BossPageDaoSecond bossPageDaoSecond;
//    private final SqlSessionTemplate sqlSession;
//    
//    @Autowired
//    public BossPageServiceImplSecond(BossPageDaoSecond bossPageDaoSecond, SqlSessionTemplate sqlSession) {
//    	this.bossPageDaoSecond = bossPageDaoSecond;
//    	this.sqlSession = sqlSession;
//    }
////	@Autowired
////    private BossPageDaoSecond bossPageDaoSecond;
//
////    @Autowired
////    private SqlSessionTemplate sqlSession;
//
//    
//    
//	@Override
//	public ArrayList<CouponKind> selectCouponKindList(String loginUserNo) {
//		return bossPageDaoSecond.selectCouponKindList(sqlSession, loginUserNo);
//	}
//
//	@Override
//	public int insertCouponKind(CouponKind coupon) {
//		int locNo = bossPageDaoSecond.selectLocationNo(sqlSession, coupon);
//		coupon.setLocationNo(String.valueOf(locNo));
//		return bossPageDaoSecond.insertCouponKind(sqlSession, coupon);
//	}
//
//	@Override
//	public CouponKind updateCouponKind(CouponKind coupon) {
//		int result = bossPageDaoSecond.updateCouponKind(sqlSession, coupon);
//		CouponKind newCoupon = null;
//		
//		if (result > 0) {
//			newCoupon = bossPageDaoSecond.selectCouponKind(sqlSession, coupon.getCouponNo());
//		} 
//		
//		return newCoupon;
//	}
//
//	@Override
//	public int deleteCouponkind(int couponNo) {
//		return bossPageDaoSecond.deleteCouponKind(sqlSession, couponNo);
//	}

//	@Override
//	public int insertBoard(BoardEnroll board, BoardFileInfo boardfile) {
//		int result1 = bossPageDaoSecond.insertBoard(sqlSession, board);
//		int result2 = 1;
//		
//		//게시글 등록이 성공하고 첨부파일이 비어있지 않으면
//		if (result1 > 0 && boardfile.getChangeName() != null) {
//			result2 = bossPageDaoSecond.insertBoardAttachment(sqlSession, boardfile);
//		} 
//		
////		else if (result1 > 0 && boardfile == null){
////			result2 = 1;
////		} else {
////			result1 = result1 * 0;
////		}
//		
//		
//		
//		return result1 * result2;
//	}

	
	
	
	
//	// 핸드폰 중복 체크
//	@Override
//	public int checkPhoneNumber(String userPhone) {
//		return bossPageDaoSecond.checkPhoneNumber(sqlSession, userPhone);
//	}
//	
//	@Override
//	@Transactional
//	public int insertCertifyCode(PhoneSmsVo psv) {
////		이 메소드에서만 sqlSession이 null로 뜨는 현상 발생 / 생성자 주입 방식을 통해 해결
////		return bossPageDaoSecond.insertCertifyCode(sqlSession, psv);
//		
//		// 저장되어 있는 코드 있는지 전화번호로 확인
//		int code = bossPageDaoSecond.selectCertifyCode(sqlSession, psv);
//		int result1 = 1;
//		int result2 = 1;
//		
//		// 저장된 코드 있는 경우 delete문 실행
//		if (code > 0) {
//			result1 = bossPageDaoSecond.deleteCertifyCode(sqlSession, psv);
//		}
//		
//		// 삭제 쿼리 실행 후 결과가 성공이면 insert문 실행
//		if (result1 > 0) {
//			result2 = bossPageDaoSecond.insertCertifyCode(sqlSession, psv);
//		}
//		
//		return result1 * result2;
//	}
//
//	@Override
//	public PhoneSmsVo checkCertifyCode(String phone) {
//		return bossPageDaoSecond.checkCertifyCode(sqlSession, phone);
//	}

	
	
}
