package com.kh.mng.bosspage.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kh.mng.bosspage.model.dto.CouponKind;
import com.kh.mng.bosspage.service.BossPageServiceSecond;
import com.kh.mng.community.model.dto.BoardEnroll;
import com.kh.mng.community.model.dto.BoardFileInfo;
import com.kh.mng.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BossPageControllerSecond {
	
	@Autowired
	private BossPageServiceSecond bossPageServiceSecond;
	
//	@GetMapping("bossCouponPage.bcc")
//	public String bossCouponPage(String uno, Model model) {
//		ArrayList<CouponKind> couponList = bossPageServiceSecond.selectCouponKindList(uno);
//		
////		1. 쿠폰 내용
////		2. 쿠폰 유효기간
////		3. 발매한 총 쿠폰 수량 / 발매 쿠폰 중 사용 완료한 쿠폰 수량
////		4. 
//		
//		model.addAttribute("couponList", couponList);
//		log.info(couponList.toString());
//		
//		return "bosspage/bossCouponPage";
//	}
//	
//	@GetMapping("insertCoupon.bc")
//	public String insertCouponKind(CouponKind coupon, Model model, HttpSession session) {
//		log.info(coupon.toString());
//		int result = bossPageServiceSecond.insertCouponKind(coupon);
//		ArrayList<CouponKind> couponList = bossPageServiceSecond.selectCouponKindList(coupon.getLoginUserNo());
//		if (result > 0) {
//			session.setAttribute("alertMsg", "쿠폰 등록에 성공했습니다.");
//			
//		} else {
//			session.setAttribute("alertMsg", "쿠폰 등록에 실패했습니다. 다시 시도해주세요.");
//		}
//		
//		model.addAttribute("couponList", couponList);
//		
//		return "redirect:/bossCouponPage.bcc?uno=" + coupon.getLoginUserNo();
//	}
//	
//	@ResponseBody
//	@GetMapping(value="updateCoupon.bc", produces="application/json; charset=utf-8")
//	public String ajaxUpdateCouponKind(CouponKind coupon) {
//		CouponKind newCoupon = bossPageServiceSecond.updateCouponKind(coupon);
//		log.info(newCoupon.toString());
//		return new Gson().toJson(newCoupon);
//	}
//	
//	@GetMapping("deleteCoupon.bc")
//	public String deleteCoupon(String cno, String uno, HttpSession session) {
//		log.info(uno);
//		int couponNo = Integer.parseInt(cno);
//		int result = bossPageServiceSecond.deleteCouponkind(couponNo);
//		if (result > 0) {
//			session.setAttribute("alertMsg", "쿠폰 삭제에 성공했습니다.");
//		} else {
//			session.setAttribute("alertMsg", "쿠폰 삭제에 실패했습니다.");
//		}
//		return "redirect:/bossCouponPage.bcc?uno=" + uno;
//	}
//	
	
	
	
	
	
	
	
	
////	커뮤니티 게시글 등록
//	
//	@PostMapping("enrollBoard.bo")
//	public String enrollBoard(BoardEnroll board, MultipartFile upfile, HttpSession session, Model model) {
//		BoardFileInfo boardFile = new BoardFileInfo();
//		Member loginUser = (Member)(session.getAttribute("loginUser"));
//
//		if(!upfile.getOriginalFilename().equals("")) {
//			String changeName = saveFile(upfile, session);
//			
//			boardFile.setOriginName(upfile.getOriginalFilename());
//			boardFile.setChangeName(changeName);
//			boardFile.setFilePath("resources/img/community/");
//			boardFile.setUserNo(loginUser.getUserNo());
//		}
//		
//		board.setUserNo(loginUser.getUserNo());
//		
//		int result = bossPageServiceSecond.insertBoard(board, boardFile);
//		if(result > 0) { // 성공 -> list 페이지로 이동
//			session.setAttribute("alertMsg", "게시글 작성 성공");
//			return "redirect:/community";
//		} else { // 실패 -> 에러 페이지
//			model.addAttribute("errorMsg", "게시글 작성 실패");
//			return "";
//		}
//	}
//	
//	// 실제 넘어온 파일의 이름을 변경해서 서버에 저장하는 메소드
//	public String saveFile(MultipartFile upfile, HttpSession session) {
//		String originName = upfile.getOriginalFilename();
//		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//		int ranNum = (int)(Math.random() * 90000 ) + 10000;
//		String ext = originName.substring(originName.lastIndexOf("."));
//		
//		String changeName = currentTime + ranNum + ext;
//		
//		String savePath = session.getServletContext().getRealPath("resources/img/community/");
//	
//		try {
//			upfile.transferTo(new File(savePath + changeName));
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
////		경로 또는 url, 해당 파일 객체를 저장할 때 사용
//		
//		return changeName;
//	}
	
	
	
	
	
	
	
	
	
	// 핸드폰 번호 중복 체크
//	@ResponseBody
//	@GetMapping(value="checkPhone.me")
//	public String checkPhoneNumber(String userPhone) {
//		int result = bossPageServiceSecond.checkPhoneNumber(userPhone);
//		if (result > 0) {
//			return "NNNNY";
//		} else {
//			return "NNNNN";
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 아이디 및 비밀번호 찾기
	@RequestMapping("searchMemberForm.mee")
	public String memberSearchForm(String type, Model model) {
		model.addAttribute("type", type);
		return "member/memberSearch";
	}
}
