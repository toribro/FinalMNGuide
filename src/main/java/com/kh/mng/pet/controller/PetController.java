package com.kh.mng.pet.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.mng.common.model.dto.PetPicture;
import com.kh.mng.common.model.vo.ProfileImg;
import com.kh.mng.member.model.vo.Member;
import com.kh.mng.pet.model.vo.Pet;
import com.kh.mng.pet.service.PetService;

@Controller
public class PetController {

	@Autowired
	private PetService petService;

	@RequestMapping("insertPet.mp")
	public String insertPet(PetPicture pic, Pet p, MultipartFile upfile, HttpSession session, Model model) {
	    // 세션에서 로그인한 사용자 정보 가져오기
	    Member loginUser = (Member) session.getAttribute("loginUser");
	    // 로그인한 사용자 정보에서 사용자 번호 가져오기
	    int userNo = loginUser.getUserNo();

	    // 사용자 번호를 Member 객체에 설정
	    p.setUserNo(userNo);

	    // 반려동물 정보 저장
	    int result = petService.insertPet(p);

	    if (result > 0) {
	    	session.setAttribute("pet", p);
	        session.setAttribute("alertMsg", "반려동물 등록에 성공하셨습니다.");
	    } else {
	        model.addAttribute("errorMsg", "반려동물 등록에 실패하셨습니다.");
	    }

	    return "redirect:/myPagePetInfo.mp";
	}
	
	@PostMapping(value = "updatePet.mp")
	public String updatePet(Pet p, HttpSession session) {
		// 세션에서 로그인한 사용자 정보 가져오기
		Member loginUser = (Member) session.getAttribute("loginUser");
		// 로그인한 사용자 정보에서 사용자 번호 가져오기
		int userNo = loginUser.getUserNo();
		// 사용자 번호를 Member 객체에 설정
		p.setUserNo(userNo);

		int result = petService.updatePet(p);
		System.out.println(p);
		if (result > 0) {
			session.setAttribute("pet", p); // 여기서 p 대신에 pet 객체를 세션에 추가해야 합니다.
			session.setAttribute("alertMsg", "반려동물 정보 수정 성공");
			return "redirect:/myPagePetInfo.mp";
		} else {
		    session.setAttribute("errorMsg", "반려동물 정보 수정 실패");
			return "redirect:/myPagePetInfo.mp";
		}
	}

	@ResponseBody
	@GetMapping("deletePet.mp")
	public String deletePet(Pet p, HttpSession session, Model model) {
		int result = petService.deletePet(p);
		System.out.println(p.getPetNo());
		if (result > 0) {
			return "성공";
		} else {
			return "실패";
		}
	}
	
//	@ResponseBody
//	@PostMapping("/insertPetImg.mp")
//	public String insertOrUpdateProfileImg(@RequestParam("petImage") MultipartFile upfile, HttpSession session) {
//	    Member loginUser = (Member) session.getAttribute("loginUser");
//	    int userNo = loginUser.getUserNo();
//	    
//	    // 이미지 정보를 데이터베이스에서 가져오기
//	    ProfileImg existingProfileImg = petService.getPetImg(petNo);
//	    
//	    System.out.println(existingProfileImg);
//	    // 새로 업로드된 파일 처리
//	    if (!upfile.isEmpty()) {
//	        String changeName = saveFile(upfile, session);
//	        
//	        ProfileImg petImg = new ProfileImg();
//	        petImg.setOriginName(upfile.getOriginalFilename());
//	        petImg.setChangeName(changeName);
//	        petImg.setUserNo(userNo);
//	        petImg.setFilePath("resources/img/user/");
//	        petImg.setFileLevel(1);
//	        petImg.setPetNo(petNo);
//	        
//	        // 이미 등록된 이미지가 있으면 update 수행
//	        if (existingProfileImg != null) {
//	        	petImg.setPicNo(existingProfileImg.getPicNo());
//	            int updateResult = petService.updatePetImg(petImg);
//	            
//	            if (updateResult > 0) {
//	            	p.setPetImg(petImg);
//	            	session.setAttribute("pet", p);
//	                return "NNNNY"; // Update 성공 시 반환할 메시지
//	            } else {
//	                return "NNNNN"; // Update 실패 시 반환할 메시지
//	            }
//	        } else {
//	            // 등록된 이미지가 없으면 insert 수행
//	            int insertResult = petService.insertPetImg(petImg);
//	            
//	            if (insertResult > 0) {
//	            	p.setPetImg(petImg);
//	            	session.setAttribute("pet", p);
//	                return "NNNNY"; // Insert 성공 시 반환할 메시지
//	            } else {
//	                return "NNNNN"; // Insert 실패 시 반환할 메시지
//	            }
//	        }
//	    }
//	    
//	    return "NNNNN"; // 파일이 없을 경우 반환할 메시지
//	}
	
	@ResponseBody
	@PostMapping("/insertPetImg.mp")
	public String insertPetImg(@RequestParam("petImage") MultipartFile upfile, @RequestParam("petNo") int petNo, HttpSession session) {
	    try {
	        Member loginUser = (Member) session.getAttribute("loginUser");
	        if (loginUser == null) {
	            return "LOGIN_REQUIRED"; // 로그인 필요 메시지 반환
	        }
	        
	        int userNo = loginUser.getUserNo();
	        
	        List<Pet> petList = (List<Pet>)session.getAttribute("petList");
	        
	        // 이미지 정보를 데이터베이스에서 가져오기
	        ProfileImg existingPetImg = petService.getPetImg(petNo);
	        
	        System.out.println(petNo);
	        
	        // 새로 업로드된 파일 처리
	        if (!upfile.isEmpty()) {
	            String changeName = saveFile(upfile, session);
	            
	            ProfileImg petImg = new ProfileImg();
	            petImg.setOriginName(upfile.getOriginalFilename());
	            petImg.setChangeName(changeName);
	            petImg.setUserNo(userNo);
	            petImg.setFilePath("resources/img/user/");
	            petImg.setFileLevel(1); // 반려동물 이미지 레벨
	            petImg.setPetNo(petNo); // petNo 설정
	            
	            // 이미 등록된 이미지가 있으면 update 수행
	            if (existingPetImg != null) {
	                petImg.setPicNo(existingPetImg.getPicNo());
	                int updateResult = petService.updatePetImg(petImg);
	                
	                if (updateResult > 0) {	                	
	                	session.setAttribute("petList", petList);
	                    return "NNNNY"; // Update 성공 시 반환할 메시지
	                } else {
	                    return "NNNNN"; // Update 실패 시 반환할 메시지
	                }
	            } else {
	                // 등록된 이미지가 없으면 insert 수행
	                int insertResult = petService.insertPetImg(petImg);
	                
	                if (insertResult > 0) {
	                	session.setAttribute("petList", petList);
	                    return "NNNNY"; // Insert 성공 시 반환할 메시지
	                } else {
	                    return "NNNNN"; // Insert 실패 시 반환할 메시지
	                }
	            }
	        }
	        
	        return "NNNNN"; // 파일이 없을 경우 반환할 메시지
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "ERROR"; // 예외 발생 시 반환할 메시지
	    }
	}
	
	public String saveFile(MultipartFile upfile, HttpSession session) {
		//파일명 수정 후 서버에 업로드하기("imgFile.jpg => 202404231004305488.jpg")
				String originName = upfile.getOriginalFilename();
				
				//년월일시분초 
				String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
				//5자리 랜덤값
				int ranNum = (int)(Math.random() * 90000) + 10000;
				
				//확장자
				String ext = originName.substring(originName.lastIndexOf("."));
				
				//수정된 첨부파일명
				String changeName = currentTime + ranNum + ext;
				
				//첨부파일을 저장할 폴더의 물리적 경로(session)
				String savePath = session.getServletContext().getRealPath("resources/img/user/");
				try {
					upfile.transferTo(new File(savePath + changeName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return changeName;
		
	}
}
