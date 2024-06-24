package com.kh.mng.bosspage.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mng.bosspage.model.dao.BossPageDao;
import com.kh.mng.bosspage.model.vo.BossLocation;
import com.kh.mng.bosspage.model.vo.BossLocationOption;
import com.kh.mng.bosspage.model.vo.BossPage;
import com.kh.mng.bosspage.model.vo.LocationEnterGrade;
import com.kh.mng.bosspage.model.vo.LocationOperationTime;
import com.kh.mng.bosspage.model.vo.LocationPetSize;
import com.kh.mng.bosspage.model.vo.LocationPicture;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BossPageServiceImpl implements BossPageService {

    @Autowired
    private BossPageDao bossPageDao;

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public BossLocation getLocationInfo(int userNo) {
        return bossPageDao.getLocation(userNo);
    }

    @Override
    public BossLocationOption getLocationOption(int locationNo) {
        return bossPageDao.getLocationOption(locationNo);
    }

    @Override
    public int updatePhoneNumber(BossPage bossPage) {
        return bossPageDao.updatePhonNo(bossPage);
    }

    @Override
    public int updateEmail(BossPage bossPage) {
        return bossPageDao.updateEmail(bossPage);
    }

    @Override
    public int updatePwd(BossPage bossPage) {
        return bossPageDao.updatePwd(bossPage);
    }

    @Override
    @Transactional
    public int deleteBossUser(String bossId) {
        return bossPageDao.deleteBossUser(bossId);
    }

    @Override
    @Transactional
    public int saveLocationInfo(BossLocation locationInfo) {
        return bossPageDao.saveLocationInfo(locationInfo);
    }

    @Override
    @Transactional
    public int updateLocationInfo(BossLocation locationInfo) {
        return bossPageDao.updateLocationInfo(locationInfo);
    }

    @Override
    @Transactional
    public int saveOperationTimes(int locationNo, List<LocationOperationTime> operationTimes) {
        bossPageDao.deleteOperationTimes(locationNo);
        for (LocationOperationTime operationTime : operationTimes) {
            bossPageDao.insertOperationTime(operationTime);
        }
        return 1;
    }

    @Override
    public List<LocationOperationTime> getOperationTimes(int locationNo) {
        return bossPageDao.getOperationTimes(locationNo);
    }

    @Override
    @Transactional
    public int savePetSizes(int locationNo, List<String> petSizes) {
        if (petSizes == null) {
            petSizes = new ArrayList<>();
        }

        // 기존 사이즈 삭제
        bossPageDao.deletePetSizesByLocation(locationNo);

        for (String petSize : petSizes) {
            // petSize 이름으로 기존 petSize 검색
            LocationPetSize existingPetSize = bossPageDao.getPetSizeByName(petSize);

            // LocationEnterGrade 정보 저장
            if (existingPetSize != null) {
                LocationEnterGrade locationEnterGrade = new LocationEnterGrade();
                locationEnterGrade.setLocationNo(locationNo);
                locationEnterGrade.setPetSizeNo(existingPetSize.getPetSizeNo());
                bossPageDao.insertLocationEnterGrade(locationEnterGrade);
            }
        }
        return 1;
    }

    @Override
    public List<String> getPetSizes(int locationNo) {
        return bossPageDao.getPetSizes(locationNo);
    }

    @Override
    public LocationPetSize getPetSizeByName(String petSizeName) {
        return bossPageDao.getPetSizeByName(petSizeName);
    }

    @Override
    @Transactional
    public int saveLocationEnterGrade(LocationEnterGrade locationEnterGrade) {
        return bossPageDao.insertLocationEnterGrade(locationEnterGrade);
    }

    @Override
    public List<LocationEnterGrade> getLocationEnterGrades(int locationNo) {
        return bossPageDao.getLocationEnterGrades(locationNo);
    }

    @Override
    public int saveImages(int locationNo, List<LocationPicture> pictures) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<LocationPicture> getImages(int locationNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int deleteImages(int locationNo) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Transactional
    public int savePictures(int locationNo, List<LocationPicture> pictures) {
        for (LocationPicture picture : pictures) {
            bossPageDao.insertPicture(picture);
        }
        return pictures.size();
    }
    
    @Override
    @Transactional
    public int deletePictures(int locationNo) {
        log.info("Deleting pictures for locationNo: {}", locationNo);
        int result = bossPageDao.deletePicturesByLocation(locationNo);
        log.info("Number of pictures deleted: {}", result);
        return result;
    }

    @Override
    public List<LocationPicture> getPicturesByLocation(int locationNo) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    @Transactional
    public int saveLocationOption(BossLocationOption product) {
        return bossPageDao.insertProduct(product);
    }
}
