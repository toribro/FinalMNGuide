package com.kh.mng.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.mng.common.phonesms.PhoneSmsVo;
import com.kh.mng.location.model.vo.Location;
import com.kh.mng.member.model.vo.Member;
import com.kh.mng.member.service.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Value("${businessNo.apiKey}")
	private String businessNoApiKey;
	
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
	
	@ResponseBody
	@GetMapping("certifyBusinessNo.me")
	public String certifyBusinessNo(String businessNo) {
		
		String url = "https://api.odcloud.kr/api/nts-businessman/v1/status";
		url += "?serviceKey="+businessNoApiKey;
		url += "&returnType=JSON";
		
		String data = "{\"b_no\": [\"" + businessNo + "\"]}";
		
		String businessNoResult = null;

			try {
				URL requestUrl = new URL(url);
				HttpURLConnection urlConnection =(HttpURLConnection)requestUrl.openConnection();
				urlConnection.setRequestMethod("POST");
				urlConnection.setDoOutput(true);
				urlConnection.setRequestProperty("Content-Type", "application/json charset:UTF-8");
				urlConnection.setRequestProperty("Accept", "application/json charset:UTF-8");
				
				OutputStream os= urlConnection.getOutputStream();
				byte[] input = data.getBytes("utf-8");
				os.write(input,0,input.length);
				
				int responseCode = urlConnection.getResponseCode();
				
				String responseText = "";
		        String line = "";
		        StringBuffer response =new StringBuffer();
		        
			    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				while((line = br.readLine())!=null) {
						response.append(line);
				}
				
			    br.close();
			    
			    String result = response.toString();
				JsonObject totalObj = JsonParser.parseString(result).getAsJsonObject();
				
				if (totalObj.get("match_cnt") != null) {
					businessNoResult = totalObj.get("match_cnt").getAsString();	
				} else {
					businessNoResult = "0";
				}
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
				
			} catch(ProtocolException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			if (Integer.parseInt(businessNoResult) > 0) {
				return "NNNNY";
			} else {
				return "NNNNN";
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
	
	// 인증번호 일치 확인 후 아이디 혹은 비밀번호 찾기
	@PostMapping("searchUserInfo.me")
	public String searchUserInfo(String userPhone, String certifyCode, String type, HttpSession session) {
		PhoneSmsVo psv = memberService.checkCertifyCode(userPhone);
		
		if (type.equals("1")) {
			if (psv != null && bcryptPasswordEncoder.matches(certifyCode, psv.getCertifyCode())) {
				// 인증번호 삭제 메소드 추가
				Member m = memberService.selectUserByPhone(userPhone);
				
				if (m != null) {
					session.setAttribute("alertMsg", "해당 번호로 가입된 아이디입니다. " + m.getUserId());
					return "member/memberLogin";
				} else {
					session.setAttribute("alertMsg", "아이디 정보를 불러오는 데 실패했습니다.");
					return "redirect:/searchMemberForm.mee?type=1";
				}
			} else {
				session.setAttribute("alertMsg", "인증번호가 일치하지 않습니다. 다시 시도해주세요.");
				return "redirect:/searchMemberForm.mee?type=1";
			}
		} else {
			if (psv != null && bcryptPasswordEncoder.matches(certifyCode, psv.getCertifyCode())) {
				// 인증번호 삭제 메소드 추가
				Member m = memberService.selectUserByPhone(userPhone);
				session.setAttribute("signUpId", m.getUserId());
				return "member/memberChangePwd";
			} else {
				session.setAttribute("alertMsg", "인증번호가 일치하지 않습니다. 다시 시도해주세요.");
				return "redirect:/searchMemberForm.mee?type=2";
			}
		}
	}
	
	@PostMapping("updatePwd.me")
	public String updatePwd(String signUpId, String userPwd, HttpSession session) {
		String encPwd = bcryptPasswordEncoder.encode(userPwd);
		Member m = new Member();
		m.setUserId(signUpId);
		m.setUserPwd(encPwd);
		
		int result = memberService.updatePwd(m);
		
		if (result > 0) {
			session.removeAttribute("signUpId");
			session.setAttribute("alertMsg", "비밀번호 변경에 성공했습니다.");
			return "member/memberLogin";
		} else {
			session.setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");
			return "redirect:/searchMemberForm.mee?type=2";
		}
	}
}
