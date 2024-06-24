package com.kh.mng.bosspage.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.mng.bosspage.model.vo.BossLocation;
import com.kh.mng.bosspage.model.vo.BossLocationOption;
import com.kh.mng.bosspage.model.vo.BossPage;
import com.kh.mng.bosspage.model.vo.LocationEnterGrade;
import com.kh.mng.bosspage.model.vo.LocationOperationTime;
import com.kh.mng.bosspage.model.vo.LocationPetSize;
import com.kh.mng.bosspage.model.vo.LocationPicture;
import com.kh.mng.bosspage.service.BossPageService;
import com.kh.mng.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class bossPageController {

    @Autowired
    private BossPageService bossPageService;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @RequestMapping("bossMainPage.bm")
    public String bossPrivacy(Model model, HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");

        if (loginUser != null) {
            int userNo = loginUser.getUserNo();

            BossLocation location = bossPageService.getLocationInfo(userNo);

            model.addAttribute("location", location);
            model.addAttribute("userNo", userNo);

            return "bosspage/bossmainpage"; 
        } else {
            return "redirect:/";
        }
    }

    @ResponseBody
    @PostMapping(value="/updatePhoneNumber.bm", produces="text/plain;charset=UTF-8")
    public String updatePhoneNumber(BossPage bossPage, HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser != null) {
           int count = bossPageService.updatePhoneNumber(bossPage);
           if(count > 0) {
               return "전화번호 업데이트 성공";
           } else {
               return "전화번호 업데이트 실패"; 
           }
        }
        return "로그인이 필요합니다.";
    }

    @ResponseBody
    @PostMapping(value="/updateEmail.bm", produces="text/plain;charset=UTF-8")
    public String updateEmail(BossPage bossPage, HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser != null) {
            int count = bossPageService.updateEmail(bossPage);
            if(count > 0) {
                return "이메일 업데이트 성공";
            } else {
                return "이메일 업데이트 실패";
            }
        }
        return "로그인이 필요합니다.";
    }

    @ResponseBody
    @PostMapping(value="/updatePwd.bm", produces="text/plain;charset=UTF-8")
    public String updatePwd(BossPage bossPage, HttpSession session) {
        String encPwd = bcryptPasswordEncoder.encode(bossPage.getBossPwd());
        bossPage.setBossPwd(encPwd);

        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser != null) {
            int count = bossPageService.updatePwd(bossPage);
            if(count > 0) {
                return "비밀번호 변경에 성공였습니다.";
            } else {
                return "비밀번호 변경에 실패 하였습니다.";
            }
        }
        return "로그인이 필요합니다.";
    }

    @ResponseBody
    @PostMapping(value="deleteBossUser.bm", produces ="application/json; charset=utf-8")
    public String deleteBossUse(BossPage bossPage, HttpSession session) {
        String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();
        String bossId = ((Member)session.getAttribute("loginUser")).getUserId();

        if (bcryptPasswordEncoder.matches(bossPage.getBossPwd(), encPwd)) {
            int result = bossPageService.deleteBossUser(bossId);

            if(result > 0) {
                session.removeAttribute("loginUser");
                return "YYYY";
            } else {
                return "NNNN";
            }
        } else {
            return "RRRR";
        }
    }

    @RequestMapping(value = "bossManuBar.bm")
    public String bossManuBar() {
        return "bosspage/bossmanubar";
    }

    @RequestMapping("bossLocation.bl")
    public String bossLocation(Model model, HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");

        if (loginUser != null) {
            int userNo = loginUser.getUserNo();

            BossLocation location = bossPageService.getLocationInfo(userNo);

            model.addAttribute("location", location);
            model.addAttribute("userNo", userNo);

            return "bosspage/bosslocation"; 
        } else {
            return "redirect:/";
        }
    }

    @ResponseBody
    @PostMapping(value = "/saveLocationInfo.bm", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, Object>> saveLocationInfo(@RequestBody Map<String, Object> payload, HttpSession session) {
        log.info("saveLocationInfo called with payload: {}", payload);
        Map<String, Object> response = new HashMap<>();
        
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser != null) {
            try {
                int userNo = loginUser.getUserNo();

                BossLocation locationInfo = new BossLocation();
                locationInfo.setUserNo(userNo);
                locationInfo.setLocationPhone((String) payload.get("locationPhone"));
                locationInfo.setExplanation((String) payload.get("explanation"));
                locationInfo.setReservationLink((String) payload.get("reservationLink"));

                BossLocation existingLocation = bossPageService.getLocationInfo(userNo);
                int result;
                if (existingLocation != null) {
                    locationInfo.setLocationNo(existingLocation.getLocationNo());
                    result = bossPageService.updateLocationInfo(locationInfo);
                } else {
                    result = bossPageService.saveLocationInfo(locationInfo);
                }

                List<Map<String, Object>> operationTimesData = (List<Map<String, Object>>) payload.get("operationTimes");
                if (operationTimesData != null) {
                    List<LocationOperationTime> operationTimes = new ArrayList<>();
                    for (Map<String, Object> operationTimeData : operationTimesData) {
                        LocationOperationTime operationTime = new LocationOperationTime();
                        operationTime.setLocationNo(locationInfo.getLocationNo());
                        operationTime.setDay((String) operationTimeData.get("day"));
                        operationTime.setStartTime(Time.valueOf(operationTimeData.get("startTime") + ":00"));
                        operationTime.setEndTime(Time.valueOf(operationTimeData.get("endTime") + ":00"));
                        operationTime.setRestStatus((Boolean) operationTimeData.get("restStatus"));
                        operationTimes.add(operationTime);
                    }
                    bossPageService.saveOperationTimes(locationInfo.getLocationNo(), operationTimes);
                }

                // Pet size handling
                List<String> petSizes = (List<String>) payload.get("petSizes");
                if (petSizes != null && !petSizes.isEmpty()) {
                    bossPageService.savePetSizes(locationInfo.getLocationNo(), petSizes);
                }

                // Product handling
                List<Map<String, String>> productsData = (List<Map<String, String>>) payload.get("products");
                if (productsData != null) {
                    for (Map<String, String> productData : productsData) {
                        BossLocationOption product = new BossLocationOption();
                        product.setLocationNo(locationInfo.getLocationNo());
                        product.setGoods(productData.get("productName"));
                        product.setGoodPrice(productData.get("commodityPrice"));
                        bossPageService.saveLocationOption(product);
                    }
                }

                if (result > 0) {
                    response.put("message", "장소정보 업데이트를 완료하였습니다.");
                    response.put("success", true);
                    response.put("locationNo", locationInfo.getLocationNo());
                } else {
                    response.put("message", "장소정보 업데이트에 실패했습니다.");
                    response.put("success", false);
                }
            } catch (Exception e) {
                log.error("Error saving location info", e);
                response.put("message", "서버 오류로 인해 장소정보 업데이트에 실패했습니다.");
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } else {
            response.put("message", "로그인이 필요합니다.");
            response.put("success", false);
        }
        return ResponseEntity.ok(response);
    }


    @ResponseBody
    @GetMapping(value = "/getLocationInfo.bm", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, Object>> getLocationInfo(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser != null) {
            int userNo = loginUser.getUserNo();
            BossLocation location = bossPageService.getLocationInfo(userNo);
            if (location != null) {
                response.put("locationPhone", location.getLocationPhone());
                response.put("explanation", location.getExplanation());
                response.put("reservationLink", location.getReservationLink());

                List<LocationOperationTime> operationTimes = bossPageService.getOperationTimes(location.getLocationNo());
                response.put("operationTimes", operationTimes);
                response.put("locationNo", location.getLocationNo());
            }
        } else {
            response.put("message", "로그인이 필요합니다.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("locationNo") int locationNo, HttpSession session) {
        log.info("uploadImage called with file: {} and locationNo: {}", file.getOriginalFilename(), locationNo);

        try {
            // 기존 파일 삭제
            log.info("Deleting existing pictures for locationNo: {}", locationNo);
            int deleteResult = bossPageService.deletePictures(locationNo);
            log.info("Number of pictures deleted: {}", deleteResult);

            // 파일 저장 경로 설정
            String uploadDir = "resources/img/location/";  // 실제 파일 저장 경로로 변경해야 합니다.
            log.info("Upload directory: {}", uploadDir);

            // 디렉토리 존재 여부 확인 및 생성
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("Upload directory created: {}", uploadPath.toString());
            }

            String originalFileName = file.getOriginalFilename();
            String newFileName = saveFile(file, session, uploadDir);

            log.info("Original file name: {}, New file name: {}", originalFileName, newFileName);

            Path path = uploadPath.resolve(newFileName);
            Files.write(path, file.getBytes());
            log.info("File written to: {}", path.toString());

            // 파일 정보를 데이터베이스에 저장
            LocationPicture picture = new LocationPicture();
            picture.setOriginName(originalFileName);
            picture.setChangeName(newFileName);
            picture.setFilePath(uploadDir);
            picture.setFileLevel(1); // 파일 레벨을 적절히 설정
            picture.setLocationNo(locationNo);

            // bossPageService를 사용하여 파일 정보 저장
            int saveResult = bossPageService.savePictures(locationNo, Arrays.asList(picture));
            log.info("File information saved to database with result: {}", saveResult);

            return ResponseEntity.ok(newFileName);  // 업로드된 파일명을 반환
        } catch (IOException e) {
            log.error("IOException during file upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
        } catch (Exception e) {
            log.error("Exception during file upload", e);
            // 다른 예외 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청");
        }
    }
    
    private String saveFile(MultipartFile upfile, HttpSession session, String path) {
		// 파일명 수정 후 서버에 업로드하기("imgFile.jpg => 202404231004305488.jpg")
		String originName = upfile.getOriginalFilename();

		// 년월일시분초
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// 5자리 랜덤값
		int ranNum = (int) (Math.random() * 90000) + 10000;

		// 확장자
		String ext = originName.substring(originName.lastIndexOf("."));

		// 수정된 첨부파일명
		String changeName = currentTime + ranNum + ext;

		// 첨부파일을 저장할 폴더의 물리적 경로(session)
		String savePath = session.getServletContext().getRealPath(path);
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return changeName;

	}
	
    
    
    




    @GetMapping("/getPictures")
    @ResponseBody
    public ResponseEntity<List<LocationPicture>> getPictures(@RequestParam("locationNo") int locationNo) {
        List<LocationPicture> pictures = bossPageService.getPicturesByLocation(locationNo);
        return ResponseEntity.ok(pictures);
    }

    @RequestMapping(value = "bossAccommodationinfo.ba")
    public String bossAccommodationinfo() {
        return "bosspage/bossAccommodationinfo";
    }

    @RequestMapping(value = "animalhospital.ah")
    public String animalhospital() {
        return "bosspage/animalhospital";
    }

    @RequestMapping(value = "bossPageReviews.bp")
    public String bossPageReviews() {
        return "bosspage/bossPageReviews";
    }

    @RequestMapping(value = "bossCouponPage.bc")
    public String bossCouponPage() {
        return "bosspage/bossCouponPage";
    }
}