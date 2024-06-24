package com.kh.mng.pet.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.community.model.vo.Board;
import com.kh.mng.community.model.vo.CommunityBoard;
import com.kh.mng.location.model.vo.MyPageReview;
import com.kh.mng.location.model.vo.Review;
import com.kh.mng.pet.model.Dao.PetDao;
import com.kh.mng.pet.model.vo.Pet;

@Service
public class PetServiceImpl implements PetService {
    
    @Autowired
    private PetDao petDao;
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public int insertPet(Pet p) {
        return petDao.insertPet(sqlSession, p);
    }

	@Override
	public int updatePet(Pet p) {
		return petDao.updatePet(sqlSession, p);
	}

	@Override
	public List<Pet> getPetByUserNo(int userNo) {
		return petDao.getPetByUserNo(sqlSession, userNo);
	}

	@Override
	public List<MyPageReview> getReviewList(int userNo) {
		return petDao.getReviewList(sqlSession, userNo);
	}

	@Override
	public List<CommunityBoard> getBoardList(int userNo) {
		return petDao.getBoardList(sqlSession, userNo);
	}

	@Override
	public int deletePet(Pet p) {
		return petDao.deletePet(sqlSession, p);
	}

	@Override
	public int insertPetImg(ProfileImg petImg) {
		return petDao.insertPetImg(sqlSession, petImg);
	}

	@Override
	public ProfileImg getPetImg(int petNo) {
		return petDao.getPetImg(sqlSession, petNo);
	}

	@Override
	public int updatePetImg(ProfileImg petImg) {
		return petDao.updatePetImg(sqlSession, petImg);
	}

	@Override
	public List<ProfileImg> getImgList() {
		return petDao.getImgList(sqlSession);
	}
}