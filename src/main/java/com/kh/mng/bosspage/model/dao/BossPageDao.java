package com.kh.mng.bosspage.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mng.bosspage.model.vo.BossLocation;
import com.kh.mng.bosspage.model.vo.BossLocationOption;
import com.kh.mng.bosspage.model.vo.BossPage;
import com.kh.mng.bosspage.model.vo.LocationEnterGrade;
import com.kh.mng.bosspage.model.vo.LocationOperationTime;
import com.kh.mng.bosspage.model.vo.LocationPetSize;
import com.kh.mng.bosspage.model.vo.LocationPicture;

@Repository
public class BossPageDao {

    private static final Logger log = LoggerFactory.getLogger(BossPageDao.class);

    @Autowired
    private SqlSessionTemplate sqlSession;

    public BossLocation getLocation(int userNo) {
        return sqlSession.selectOne("location.getLocation", userNo);
    }

    public int updatePhonNo(BossPage bossPage) {
        return sqlSession.update("memberMapper.updatePhonNo", bossPage);
    }

    public int updateEmail(BossPage bossPage) {
        return sqlSession.update("memberMapper.updateEmail", bossPage);
    }

    public int updatePwd(BossPage bossPage) {
        return sqlSession.update("memberMapper.updatePwd", bossPage);
    }

    public int deleteBossUser(String bossId) {
        return sqlSession.update("memberMapper.deactivateBossUser", bossId);
    }

    public int saveLocationInfo(BossLocation locationInfo) {
        return sqlSession.insert("location.insertLocation", locationInfo);
    }

    public int updateLocationInfo(BossLocation locationInfo) {
        return sqlSession.update("location.updateLocation", locationInfo);
    }

    public BossLocationOption getLocationOption(int locationNo) {
        return sqlSession.selectOne("location.getLocationOption", locationNo);
    }

    public int insertOperationTime(LocationOperationTime operationTime) {
        return sqlSession.insert("location.insertOperationTime", operationTime);
    }

    public int updateOperationTime(LocationOperationTime operationTime) {
        return sqlSession.update("location.updateOperationTime", operationTime);
    }

    public List<LocationOperationTime> getOperationTimes(int locationNo) {
        return sqlSession.selectList("location.getOperationTimes", locationNo);
    }

    public int deleteOperationTimes(int locationNo) {
        return sqlSession.delete("location.deleteOperationTimes", locationNo);
    }

    public List<LocationEnterGrade> getLocationEnterGrades(int locationNo) {
        return sqlSession.selectList("location.getLocationEnterGrades", locationNo);
    }

    public int insertPicture(LocationPicture picture) {
        return sqlSession.insert("location.insertPicture", picture);
    }

    public List<LocationPicture> getPicturesByLocation(int locationNo) {
        return sqlSession.selectList("location.getPicturesByLocation", locationNo);
    }

    public int deletePetSizesByLocation(int locationNo) {
        return sqlSession.delete("location.deletePetSizesByLocation", locationNo);
    }

    public int deletePicturesByLocation(int locationNo) {
        log.info("Deleting pictures for locationNo: {}", locationNo);
        return sqlSession.delete("location.deletePicturesByLocation", locationNo);
    }

    public LocationPetSize getPetSizeByName(String petSizeName) {
        return sqlSession.selectOne("location.getPetSizeByName", petSizeName);
    }

    public int insertLocationEnterGrade(LocationEnterGrade locationEnterGrade) {
        return sqlSession.insert("location.insertLocationEnterGrade", locationEnterGrade);
    }

    public List<String> getPetSizes(int locationNo) {
        return sqlSession.selectList("location.getPetSizes", locationNo);
    }
    
    public int insertProduct(BossLocationOption product) {
        return sqlSession.insert("location.insertProduct", product);
    }
    
}
