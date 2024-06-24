package com.kh.mng.pet.service;

import java.util.List;

import com.kh.mng.common.model.dto.PetPicture;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.community.model.vo.Board;
import com.kh.mng.community.model.vo.CommunityBoard;
import com.kh.mng.location.model.vo.MyPageReview;
import com.kh.mng.location.model.vo.Review;
import com.kh.mng.pet.model.vo.Pet;

public interface PetService {
	
	int insertPet(Pet p);
	
	int updatePet(Pet p);
	
	List<Pet> getPetByUserNo(int userNo);
	
	List<MyPageReview> getReviewList(int userNo);
	
	List<CommunityBoard> getBoardList(int userNo);
	
	int deletePet(Pet p);
	
	int insertPetImg(ProfileImg petImg);
	
	ProfileImg getPetImg(int petNo);

	int updatePetImg(ProfileImg petImg);

	List<ProfileImg> getImgList();
}
