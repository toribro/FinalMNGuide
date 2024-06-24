package com.kh.mng.bosspage.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.bosspage.model.dto.CouponKind;
import com.kh.mng.common.phonesms.PhoneSmsVo;
import com.kh.mng.community.model.dto.BoardEnroll;
import com.kh.mng.community.model.dto.BoardFileInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BossPageDaoSecond {
//	public ArrayList<CouponKind> selectCouponKindList(SqlSessionTemplate sqlSession, String loginUserNo){
//		return (ArrayList)sqlSession.selectList("searchMapper.selectCouponKindList", loginUserNo);
//	}
//	
//	public int selectLocationNo(SqlSessionTemplate sqlSession, CouponKind coupon) {
//		return sqlSession.selectOne("searchMapper.selectLocationNo", coupon);
//	}
//	
//	public int insertCouponKind(SqlSessionTemplate sqlSession, CouponKind coupon) {
//		return sqlSession.insert("searchMapper.insertCouponKind", coupon);
//	}
//
//	public int updateCouponKind(SqlSessionTemplate sqlSession, CouponKind coupon) {
//		return sqlSession.update("searchMapper.updateCouponKind", coupon);
//	}
//	
//	public CouponKind selectCouponKind(SqlSessionTemplate sqlSession, int couponNo) {
//		return sqlSession.selectOne("searchMapper.selectCouponKind", couponNo);
//	}
//	
//	public int deleteCouponKind(SqlSessionTemplate sqlSession, int couponNo) {
//		return sqlSession.update("searchMapper.deleteCouponKind", couponNo);
//	}
	
	
	
	
	
	
////	community 게시글
//	
//	public int insertBoard(SqlSessionTemplate sqlSession, BoardEnroll board) {
//		return sqlSession.insert("searchMapper.insertBoard", board);
//	}
//	
//	public int insertBoardAttachment(SqlSessionTemplate sqlSession, BoardFileInfo boardFile) {
//		return sqlSession.insert("searchMapper.insertBoardAttachment", boardFile);
//	}
	

	
	
//	핸드폰 인증
//	public int checkPhoneNumber(SqlSessionTemplate sqlSession, String userPhone) {
//		return sqlSession.selectOne("searchMapper.checkPhoneNumber", userPhone);
//	}
//	
//	public int selectCertifyCode(SqlSessionTemplate sqlSession, PhoneSmsVo psv) {
//		return sqlSession.selectOne("searchMapper.selectCertifyCode", psv);
//	}
//	
//	public int deleteCertifyCode(SqlSessionTemplate sqlSession, PhoneSmsVo psv) {
//		return sqlSession.delete("searchMapper.deleteCertifyCode", psv);
//	}
//	
//	public int insertCertifyCode(SqlSessionTemplate sqlSession, PhoneSmsVo psv) {
//		return sqlSession.insert("searchMapper.insertCertifyCode", psv);
//	}
//	
//	public PhoneSmsVo checkCertifyCode(SqlSessionTemplate sqlSession, String phone) {
//		return sqlSession.selectOne("searchMapper.checkCertifyCode", phone);
//	}
}
