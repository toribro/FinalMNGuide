package com.kh.mng.member.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.mng.common.phonesms.PhoneSmsVo;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.member.model.vo.Member;
import com.kh.mng.member.service.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	// security 담당 객체
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	// 로그인 창으로 가는 메소드
	@GetMapping("loginForm.me")
	public String memberLoginForm() {
		return "member/memberLogin";
	}
	
	// 로그인 메소드
	@PostMapping("login.me")
	public ModelAndView memberLogin(Member m, ModelAndView mv, HttpSession session, HttpServletResponse response) {
		Member loginUser = memberService.loginMember(m);
//		// 아이디가 없을 때
//				if (loginUser == null) {
//					model.addAttribute("errorMsg", "아이디가 일치하지 않습니다.");
//					return "member/memberLogin";
//				// 비밀번호가 맞지 않을 때
//				} else if (!bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
//					model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
//					return "member/memberLogin";
//				// 성공 시
//				} else {
//					// 일반 회원인 경우
//					if(loginUser.getUserKind().equals("N")) {
//						session.setAttribute("loginUser", loginUser);
//						return "redirect:/";	
//					// 사장 회원인 경우
//					} else {
//						session.setAttribute("loginUser", loginUser);
//						return "bosspage/bossmainpage";
//					}
//				}
		// 아이디가 없을 때
		if (loginUser == null) {
			mv.addObject("errorMsg", "아이디가 일치하지 않습니다.");
			mv.setViewName("member/memberLogin");
		// 비밀번호가 맞지 않을 때
		} else if (!bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			mv.addObject("errorMsg", "비밀번호가 일치하지 않습니다.");
			mv.setViewName("member/memberLogin");
		// 성공 시
		} else {
			// 일반 회원인 경우
			if(loginUser.getUserKind().equals("N")) {
				session.setAttribute("loginUser", loginUser);
				mv.setViewName("redirect:/");	
			// 사장 회원인 경우
			} else {
				session.setAttribute("loginUser", loginUser);
				mv.setViewName("redirect:/bossMainPage.bm");	
			}
		}

		return mv;
	}
	
	// 로그아웃
	@GetMapping("logout.me")
	public String logoutMember(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	// 아이디 중복 확인(회원가입 시)
	@ResponseBody
	@GetMapping("checkId.me")
	public String checkMemberId(String userId) {
		int result = memberService.checkMemberId(userId);
		System.out.println(userId + " " + result);
		if (result > 0) {
			return "NNNNY";
		} else {
			return "NNNNN";
		}
	}
	
	// 일반회원가입
	@PostMapping("memberEnrollCommon.me")
	public String insertCommonMember(Member m, Model model, HttpSession session, HttpServletResponse response) {
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		m.setUserPwd(encPwd);
		
		int result = memberService.insertCommonMember(m);
		
		if (result > 0) {
			session.setAttribute("alertMsg", "회원가입이 성공적으로 완료 되었습니다. 멍냥가이드와 함께 행복한 하루 되세요!");
			return "redirect:/";
		} else {
			session.setAttribute("alertMsg", "회원가입에 실패했습니다. 다시 한 번 시도해주세요.");
			return "member/memberEnrollCommon";
		}
	}
	
	// 사장회원가입
	@PostMapping("memberEnrollBoss.me")
	public String insertBossMember(Member m, Location loc, Model model, HttpSession session, HttpServletResponse response) {
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		m.setUserPwd(encPwd);
		
		loc.setExplanation("장소에 대한 정보를 입력해주세요.");
		loc.setLocationPhone(m.getUserPhone());
		
		int result = memberService.insertBossMember(m, loc);
		
		if (result > 0) {
			session.setAttribute("alertMsg", "회원가입이 성공적으로 완료 되었습니다. 멍냥가이드와 함께 행복한 하루 되세요!");
			return "redirect:/";
		} else {
			session.setAttribute("alertMsg", "회원가입에 실패했습니다. 다시 한 번 시도해주세요.");
			return "member/memberEnrollCommon";
		}
	}
	
	@RequestMapping("searchMemberForm.me")
	public String memberSearchForm() {
		return "member/memberSearch";
	}
	
	@RequestMapping("changePwdForm.me")
	public String changePwdForm() {
		return "member/memberChangePwd";
	}
	
	@RequestMapping("memberEnrollCommonForm.me")
	public String memberEnrollCommonForm() {
		return "member/memberEnrollCommon";
	}
	
	@RequestMapping("memberEnrollAdminForm.me")
	public String memberEnrollAdminForm() {
		return "member/memberEnrollAdmin";
	}
	
	@RequestMapping("memberEnrollSelectForm.me")
	public String memberEnrollSelectForm() {
		return "member/memberEnrollSelect";
	}
	
	// 핸드폰 번호 중복 체크
	@ResponseBody
	@GetMapping("checkPhone.me")
	public String checkPhoneNumber(String userPhone) {
		int result = memberService.checkPhoneNumber(userPhone);
		log.info(userPhone);
		log.info("{}", result);
		if (result > 0) {
			return "NNNNY";
		} else {
			return "NNNNN";
		}
	}

	@RequestMapping("update.mp")
	public String updateMember(Member m, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	    // 세션에서 로그인한 사용자 정보 가져오기
	    Member loginUser = (Member) session.getAttribute("loginUser");

	    // 로그인한 사용자 정보에서 사용자 번호 가져오기
	    int userNo = loginUser.getUserNo();
	    String userId = loginUser.getUserId();

	    // 사용자 번호를 Member 객체에 설정
	    m.setUserNo(userNo);
	    m.setUserId(userId);
	    // 개인정보 업데이트
	    int result = memberService.updateMember(m);
	    
	    if (result > 0) {
	        // 업데이트가 성공하면 세션에 다시 로그인한 사용자 정보 설정
	        session.setAttribute("loginUser", memberService.loginMember(m));
	        session.setAttribute("alertMsg", "개인정보 수정에 성공하였습니다.");
	        return "redirect:/myPageInfo.mp";
	    } else {
	        redirectAttributes.addFlashAttribute("errorMsg", "개인정보 수정에 실패하였습니다.");
	        return "redirect:/myPageInfo.mp";
	    }
	}
	
	
	@PostMapping("searchUserId")
	public String searchUserId(String userPhone, String certifyCode, HttpSession session) {
//		PhoneSmsVo psv = memberService.checkCertifyCode(userPhone);
//		if (psv != null && bcryptPasswordEncoder.matches(certifyCode, psv.getCertifyCode())) {
//			// 인증번호 삭제 메소드 추가
//			return "";
//		} else {
//			session.setAttribute("alertMsg", "인증번호가 일치하지 않습니다. 다시 시도해주세요.");
//			return "NNNNN";
//		}
		return "";
	}
	
	@PostMapping("changeUserPwd")
	public String changeUserPwd() {
		return "";
	}
}
