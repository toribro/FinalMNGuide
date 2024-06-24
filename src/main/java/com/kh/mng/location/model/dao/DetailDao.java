package com.kh.mng.location.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.common.chat.model.vo.MasterInfo;
import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.location.model.dto.FileInfo;
import com.kh.mng.location.model.dto.ReviewInfo;
import com.kh.mng.location.model.dto.ReviewStarInfo;
import com.kh.mng.location.model.vo.LocationOption;
import com.kh.mng.location.model.vo.OperationTime;
import com.kh.mng.location.model.vo.PetKindGrade;
import com.kh.mng.location.model.vo.AddressInfo;
import com.kh.mng.location.model.vo.DetailLocation;
import com.kh.mng.location.model.vo.DetailLocationAttachment;


@Repository
public class DetailDao {
	
	//DB에서 여러 값들 가져오기
	public DetailLocation selectDetailLocation(SqlSessionTemplate sqlSession, int locationNo) {
			
		return sqlSession.selectOne("detail.selectDetailLocation", locationNo);
	}

	public ArrayList<DetailLocationAttachment> selectMainImg(SqlSessionTemplate sqlSession, int locationNo) {
	  
		return (ArrayList) sqlSession.selectList("attachment.selectMainAttachment",locationNo);
	}
	public ArrayList<DetailLocationAttachment> selectDetailImg(SqlSessionTemplate sqlSession, int locationNo) {
		  
		return (ArrayList) sqlSession.selectList("attachment.selectdetailAttachment",locationNo);
	}


	public ArrayList<LocationOption> selectLocationOptionList(SqlSessionTemplate sqlSession, int locationNo) {
		
		return (ArrayList) sqlSession.selectList("detail.selectLocationOptionList", locationNo);
	}


  //메인이미지
	public ArrayList<Attachment> selectAttachMentList(SqlSessionTemplate sqlSession, int locationNo) {
	
		return (ArrayList) sqlSession.selectList("detail.selectMainAttachMentList",locationNo);
	}
	
	public ArrayList<Attachment> selectDetailAttachMentList(SqlSessionTemplate sqlSession, int locationNo) {
		
		return (ArrayList) sqlSession.selectList("detail.selectDetailAttachMentList",locationNo);
	}


	public ArrayList<OperationTime> selectOperationTimeList(SqlSessionTemplate sqlSession, int locationNo) {
	
		return  (ArrayList) sqlSession.selectList("detail.selectOperationTimeList",locationNo);
	}

	public ArrayList<PetKindGrade> selectPetKindGradeList(SqlSessionTemplate sqlSession, int locationNo) {
		
		return  (ArrayList) sqlSession.selectList("detail.selectPetKindGradeList",locationNo);
	}

	public int updateLocationTotalScore(SqlSessionTemplate sqlSession, ReviewStarInfo reviewStar) {
	
		return sqlSession.update("detail.updateLocationTotalScore",reviewStar);
	}

	public ArrayList<AddressInfo> selectAddress(SqlSessionTemplate sqlSession,String type) {
		
		return  (ArrayList)sqlSession.selectList("detail.getAddress",type);
	}


}
