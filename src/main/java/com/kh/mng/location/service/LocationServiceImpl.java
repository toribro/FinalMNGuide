package com.kh.mng.location.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mng.common.chat.model.vo.MasterInfo;
import com.kh.mng.common.model.vo.Attachment;
import com.kh.mng.common.model.vo.PageInfo;
import com.kh.mng.location.model.dao.DetailDao;
import com.kh.mng.location.model.dao.PickedDao;
import com.kh.mng.location.model.dao.ReviewDao;
import com.kh.mng.location.model.dto.FileInfo;
import com.kh.mng.location.model.dto.PickedInfo;
import com.kh.mng.location.model.dto.ReplyInfo;
import com.kh.mng.location.model.dto.ReviewInfo;
import com.kh.mng.location.model.dto.ReviewStarInfo;
import com.kh.mng.location.model.vo.AddressInfo;
import com.kh.mng.location.model.vo.DetailLocation;
import com.kh.mng.location.model.vo.DetailLocationAttachment;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.location.model.vo.LocationOption;
import com.kh.mng.location.model.vo.MasterReply;
import com.kh.mng.location.model.vo.OperationTime;
import com.kh.mng.location.model.vo.PetKindGrade;
import com.kh.mng.location.model.vo.Review;

@Service
public class LocationServiceImpl implements LocationService {
	
	private SqlSessionTemplate sqlSession;
	private PickedDao pickedDao;
	private DetailDao detailDao;
	private ReviewDao reviewDao;
	 
	@Autowired
	public  LocationServiceImpl (SqlSessionTemplate sqlSession,
			PickedDao picked,DetailDao detailDao,ReviewDao reviewDao) {
		this.sqlSession=sqlSession;
		this.pickedDao=picked;
		this.detailDao=detailDao;
		this.reviewDao=reviewDao;
	}
	
	@Override
	@Transactional
	public DetailLocation selectDetailLocation(int locationNo) {
		
		DetailLocation detailLocation=detailDao.selectDetailLocation(sqlSession,locationNo);
		if(detailLocation!=null) {
			ArrayList<LocationOption> locationOption=detailDao.selectLocationOptionList(sqlSession,detailLocation.getLocationNo());
			ArrayList<OperationTime> operationTime=detailDao.selectOperationTimeList(sqlSession,detailLocation.getLocationNo());
			ArrayList<PetKindGrade> petKindGrade=detailDao.selectPetKindGradeList(sqlSession,detailLocation.getLocationNo());
			ArrayList<Attachment> mainAttachment = detailDao.selectAttachMentList(sqlSession,detailLocation.getLocationNo());
			if(mainAttachment.isEmpty()) {
				Attachment defaultAttachment = new Attachment();
				defaultAttachment.setFilePath("resources/img/default/");
				defaultAttachment.setChangeName("detailImgRestaurant1.jpg");
				mainAttachment.add(defaultAttachment);
			}
			ArrayList<Attachment> detailAttchment = detailDao.selectDetailAttachMentList(sqlSession,detailLocation.getLocationNo());
			detailLocation.setLocationOption(locationOption);
			detailLocation.setOperationTime(operationTime);
			detailLocation.setPetKindGrade(petKindGrade);// 이 다오에서만 조인문 
			detailLocation.setMainAttachMent(mainAttachment);
			detailLocation.setDetailAttachMent(detailAttchment);
		}
		return detailLocation;
	}

	@Override
	public ArrayList<DetailLocationAttachment> selectDetailMainImg(int locationNo) {
	
		return detailDao.selectMainImg(sqlSession, locationNo);
	}

	@Override
	public ArrayList<DetailLocationAttachment> selectDetailDetailImg(int locationNo) {
	
		return detailDao.selectDetailImg(sqlSession, locationNo);
	}

	
	
	
	//찜관련 서비스
	
	@Override
	public int pickedStatus(PickedInfo pickedInfo) {
		return pickedDao. pickedStatus(sqlSession,pickedInfo);
	}

	@Override
	public int pickedCount(int spaceNo) {
		return pickedDao.pickedCount(sqlSession,spaceNo);
	}

	@Override
	public int insertPicked(PickedInfo pickedInfo) {
		return pickedDao.insertPicked(sqlSession,pickedInfo);
	}

	@Override
	public int deletePicked(PickedInfo pickedInfo) {
		return pickedDao.deletePicked(sqlSession,pickedInfo);
	}


	//리뷰 관련 서비스
	
	@Override
	@Transactional
	public ArrayList<Review> selectDetailReviewList(int locationNo,PageInfo pi) {
		
		 ArrayList<Review> reviews=reviewDao.selectReviewList(sqlSession,locationNo, pi);
		 if(!reviews.isEmpty()) {
			 for(Review review:reviews) {
				 ArrayList<Attachment> attachMents =reviewDao.selectAttachmentList(sqlSession, review.getReviewNo());
				 MasterReply masterReply = reviewDao.selectReply(sqlSession,review.getReviewNo());
				 Attachment userProfile= reviewDao.selectProfile(sqlSession,review.getUserNo());
				 if(userProfile==null) {
					userProfile=new Attachment();
					userProfile.setFilePath("resources/img/default/");
					userProfile.setChangeName("default_profile.jpg");
				  }
				 review.setAttachment(attachMents);
				 review.setUserProfile(userProfile);
				 review.setMasterReply(masterReply);
			 }
		 }
		return reviews;
	}
	
	
	@Override
	@Transactional 
	public int insertReview(ReviewInfo reviewInfo, Map<String,String> changeNamesList,String path) {
	
		int count1=reviewDao.insertReview(sqlSession,reviewInfo);
		int count2=1;
		if(count1>0) {
			 if(!changeNamesList.isEmpty()) {
				  for(Map.Entry<String, String> files : changeNamesList.entrySet()){
					  FileInfo fileInfo =new FileInfo(files.getKey(),files.getValue(),path);
					  count2= reviewDao.insertReviewAttachMent(sqlSession,fileInfo);
					  count2*=count2;
				  }
			}
		}
	
		
		return count1*count2;
	}

	@Override
	@Transactional
	public ArrayList<Attachment>  deleteReview(ReviewInfo  reviewInfo) {
		
		ArrayList<Attachment> reviewAttachMent =reviewDao.selectAttachmentList(sqlSession,reviewInfo.getReviewNo());
		
		int count=reviewDao.deleteReview(sqlSession, reviewInfo);
		
		if(count>0) {
			return reviewAttachMent;
		}
		else {
			return null;
		}
		
	
	}

	@Override
	public int selectReviewCount(int locatonNo) {
		
		return reviewDao.selectReviewCount(sqlSession,locatonNo);
	}

	@Override
	@Transactional
	public int insertReply(ReplyInfo reply) {
		 MasterReply masterReply = reviewDao.selectReply(sqlSession,reply.getReviewNo());
		 if(masterReply!=null) {
			 ReplyInfo updateReply = new ReplyInfo();
			 updateReply.setContent(reply.getContent());
			 updateReply.setReviewNo(masterReply.getReviewNo());
			   
			  return reviewDao.updateReply(sqlSession,updateReply);
			 
		  }else {
			   return reviewDao.insertReply(sqlSession,reply);
		  }
		 
		 
	}

	@Override
	@Transactional
	public ArrayList<Review> selectCategoryReviewList(ReviewInfo reivew, PageInfo pi) {
		ArrayList<Review> reviews=reviewDao.selectCategoryReviewList(sqlSession,reivew, pi);
		if(!reviews.isEmpty()) {
			 for(Review review:reviews) {
				 ArrayList<Attachment> attachMents =reviewDao.selectAttachmentList(sqlSession, review.getReviewNo());
				 Attachment userProfile= reviewDao.selectProfile(sqlSession,review.getUserNo());
				 if(userProfile==null) {
					userProfile=new Attachment();
					userProfile.setFilePath("resources/img/default/");
					userProfile.setChangeName("default_profile.jpg");
				  }
				 review.setAttachment(attachMents);
				 review.setUserProfile(userProfile);
			 }
		}
		return reviews;
	}

	@Override
	@Transactional
	public int updateDateReviewScore(int locationNo, int reviewCount) {
	    ArrayList<Integer> reviewStars=reviewDao.selectReviewStars(sqlSession,locationNo);
	    ReviewStarInfo reviewStarInfo= new  ReviewStarInfo();
	    double total=0.0;
	    double score=0.0;
	    int count=0;
	    if(!reviewStars.isEmpty()) {
	    	 for(int star:reviewStars) {
	 	    	total+=star;
	 	    }
	    	 
	    	
	    	 score=total/reviewCount;
	    	 
	    	 //2째 짜리 까지 표현
	    	 score=Math.round(score * 100) / 100.0;
	    	 
	    	 reviewStarInfo.setLocationNo(locationNo);
	    	 reviewStarInfo.setScore(score);
	    	
	 		count= detailDao.updateLocationTotalScore(sqlSession,reviewStarInfo);
	    }
	    
	   
		
		return count;
	}

	@Override
	public ArrayList<AddressInfo> selectAddress(String type) {
	
		return detailDao.selectAddress(sqlSession,type);
	}

	

}
