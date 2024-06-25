//package com.kh.mng.picture.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.kh.mng.pet.model.vo.Pet;
//import com.kh.mng.picture.model.vo.Picture;
//import com.kh.mng.picture.service.PictureService;
//
//@Controller
//public class PictureController {
//
//	@Autowired
//	private PictureService pictureService;
//	
//	@PostMapping("insertPicture.pt")
//	public String insertPicture(Picture pic, HttpSession session, Model model) {
//	    
//	    Pet pet = (Pet) session.getAttribute("pet");
//	    
//	    int userNo = pet.getUserNo();
//	    int petNo = pet.getPetNo();
//
//	    pic.setUserNo(userNo);
//	    pic.setPetNo(petNo);
//	    
//	    int result = pictureService.insertPicture(pic);
//	    
//	    if (result > 0) {
//			session.setAttribute("alertMsg", "사진 등록에 성공하셨습니다.");
//			return "redirect:/myPagePetInfo.mp";
//		} else {
//			model.addAttribute("errorMsg", "사진 등록에 실패하셨습니다.");
//			return "redirect:/myPagePetInfo.mp";
//		}		
//	}
//}
