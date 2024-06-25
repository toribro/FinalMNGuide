package com.kh.mng.location.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mng.location.model.dto.PickedInfo;

@Repository
public class PickedDao {

	public int  pickedStatus(SqlSessionTemplate sqlSession, PickedInfo pickedInfo) {
	    return sqlSession.selectOne("picked.pickedStatus",pickedInfo);
	}

	public int  pickedCount(SqlSessionTemplate sqlSession, int space) {
	     return sqlSession.selectOne("picked.pickedCount",space);
	}

	public int insertPicked(SqlSessionTemplate sqlSession, PickedInfo pickedInfo) {
	     return sqlSession.insert("picked.pickedInsert",pickedInfo);
				 
	}

	public int deletePicked(SqlSessionTemplate sqlSession, PickedInfo pickedInfo) {
		return sqlSession.delete("picked.pickedDelete",pickedInfo);
	}

	
}
