package com.kh.mng.location.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.location.model.dto.FileInfo;
import com.kh.mng.location.model.dto.ReplyInfo;
import com.kh.mng.location.model.dto.ReviewInfo;
import com.kh.mng.location.model.vo.MasterReply;
import com.kh.mng.location.model.vo.Review;

@Repository
public class ReviewDao {

	public ArrayList<Review> selectReviewList(SqlSessionTemplate sqlSession,int locationNo,PageInfo pi){
		
		int offset=(pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		//sqlSession.selectList("review.selectDetailReviewList", locationNo,rowBounds); //페이징 처리할 정보
		
		return (ArrayList)sqlSession.selectList("review.selectDetailReviewList",locationNo,rowBounds);
	}

	public ArrayList<Attachment> selectAttachmentList(SqlSessionTemplate sqlSession, int reviewNo) {
		
		return (ArrayList)sqlSession.selectList("review.selectAttachment",reviewNo);
	}
	
     public int insertReview(SqlSessionTemplate sqlSession, ReviewInfo reviewInfo) {
		
		return sqlSession.insert("review.insertReview",reviewInfo);
	}

	public int insertReviewAttachMent(SqlSessionTemplate sqlSession, FileInfo fileInfo) {
		
		return sqlSession.insert("review.insertAttachment",fileInfo);
	}

	public int deleteReview(SqlSessionTemplate sqlSession,ReviewInfo reviewInfo) {
	
		return sqlSession.delete("review.deleteReview",reviewInfo);
	}

	public int selectReviewCount(SqlSessionTemplate sqlSession,int locationNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.reviewCount",locationNo);
	}

	public int insertReply(SqlSessionTemplate sqlSession,ReplyInfo reply) {
		// TODO Auto-generated method stub
		return sqlSession.insert("review.insertReply",reply);
	}
	
	public int updateReply(SqlSessionTemplate sqlSession, ReplyInfo updateReply) {
	
		
		return sqlSession.update("review.updateReply",updateReply);
	}
	
	public MasterReply selectReply(SqlSessionTemplate sqlSession, int reviewNo) {
		
		return sqlSession.selectOne("review.selectReply",reviewNo);
	}


    public ArrayList<Review> selectCategoryReviewList(SqlSessionTemplate sqlSession, ReviewInfo reivew, PageInfo pi) {
		int offset=(pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("review.selectCategoryReviewList",reivew,rowBounds);
	}

	public Attachment selectProfile(SqlSessionTemplate sqlSession, int userNo) {
		
		return sqlSession.selectOne("review.selectUserAttachment",userNo);
	}

	public ArrayList<Integer> selectReviewStars(SqlSessionTemplate sqlSession, int locationNo) {
		// TODO Auto-generated method stub
		return (ArrayList) sqlSession.selectList("review.selectReviewStars",locationNo);
	}

	


	

	
}
