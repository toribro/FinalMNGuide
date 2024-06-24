package com.kh.mng.pet.model.Dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.common.model.dto.PetPicture;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.community.model.vo.Board;
import com.kh.mng.community.model.vo.CommunityBoard;
import com.kh.mng.location.model.vo.MyPageReview;
import com.kh.mng.location.model.vo.Review;
import com.kh.mng.pet.model.vo.Pet;

@Repository
public class PetDao {
	public int insertPet(SqlSessionTemplate sqlSession, Pet p) {
		return sqlSession.insert("petMapper.insertPet", p);
	}
	
	public int updatePet(SqlSessionTemplate sqlSession, Pet p) {
		return sqlSession.update("petMapper.updatePet", p);
	}
	
    public List<Pet> getPetByUserNo(SqlSessionTemplate sqlSession, int userNo) {
        return sqlSession.selectList("petMapper.selectPet", userNo);
    }
    
    public List<MyPageReview> getReviewList(SqlSessionTemplate sqlSession, int userNo) {
    	return sqlSession.selectList("review.selectMyPageReview", userNo);
    }
    
    public List<CommunityBoard> getBoardList(SqlSessionTemplate sqlSession, int userNo) {
    	return sqlSession.selectList("communityBoardMapper.getBoardList", userNo);
    }
    
    public int deletePet(SqlSessionTemplate sqlSession, Pet p) {
    	return sqlSession.delete("petMapper.deletePet", p);
    }

	public int insertPetImg(SqlSessionTemplate sqlSession, ProfileImg petImg) {
		return sqlSession.insert("imgMapper.insertPetImg", petImg);
	}

	public ProfileImg getPetImg(SqlSessionTemplate sqlSession, int petNo) {
		return sqlSession.selectOne("imgMapper.getPetImg", petNo);
	}

	public int updatePetImg(SqlSessionTemplate sqlSession, ProfileImg petImg) {
		return sqlSession.update("imgMapper.updatePetImg", petImg);
	}

	public List<ProfileImg> getImgList(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("imgMapper.getImgList");
	}
}
