package com.kh.mng.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.mng.member.model.vo.Member;

public class MemberIntercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session =request.getSession();
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser != null && loginUser.getUserKind().equals("N")) {
			return true;
		} else {
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스 입니다.");
			response.sendRedirect(request.getContextPath() + "/loginForm.me");
			return false;
		}
	}

}
