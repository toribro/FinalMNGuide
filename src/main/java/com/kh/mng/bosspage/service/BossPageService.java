package com.kh.mng.bosspage.service;

import java.util.List;

import com.kh.mng.bosspage.model.vo.BossLocation;
import com.kh.mng.bosspage.model.vo.BossLocationOption;
import com.kh.mng.bosspage.model.vo.BossPage;
import com.kh.mng.bosspage.model.vo.LocationEnterGrade;
import com.kh.mng.bosspage.model.vo.LocationOperationTime;
import com.kh.mng.bosspage.model.vo.LocationPetSize;
import com.kh.mng.bosspage.model.vo.LocationPicture;

public interface BossPageService {
    BossLocation getLocationInfo(int userNo);
    BossLocationOption getLocationOption(int locationNo);
    List<LocationOperationTime> getOperationTimes(int locationNo);

    int updatePhoneNumber(BossPage bossPage);
    int updateEmail(BossPage bossPage);
    int updatePwd(BossPage bossPage);
    int deleteBossUser(String bossId);
    int saveLocationInfo(BossLocation locationInfo);
    int updateLocationInfo(BossLocation locationInfo);
    int saveOperationTimes(int locationNo, List<LocationOperationTime> operationTimes);

    int savePetSizes(int locationNo, List<String> petSizes);
    List<String> getPetSizes(int locationNo);

    int saveImages(int locationNo, List<LocationPicture> pictures);
    List<LocationPicture> getImages(int locationNo);
    int deleteImages(int locationNo);
    int savePictures(int locationNo, List<LocationPicture> pictures);
    List<LocationPicture> getPicturesByLocation(int locationNo);

    LocationPetSize getPetSizeByName(String petSizeName);
    int saveLocationEnterGrade(LocationEnterGrade locationEnterGrade);
    List<LocationEnterGrade> getLocationEnterGrades(int locationNo);
    int deletePictures(int locationNo);
    
    int saveLocationOption(BossLocationOption product);
}