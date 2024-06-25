//package com.kh.mng.picture.service;
//
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.kh.mng.picture.model.dao.PictureDao;
//import com.kh.mng.picture.model.vo.Picture;
//
//public class PictureServiceImpl implements PictureService{
//
//    @Autowired
//    private PictureDao pictureDao;
//    
//    @Autowired
//    private SqlSessionTemplate sqlSession;
//	
//	@Override
//	public int insertPicture(Picture pic) {
//		return pictureDao.insertPicture(sqlSession, pic);
//	}
//
//}
