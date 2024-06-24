package com.kh.mng.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.mng.member.model.vo.Member;

public class BossIntercepter implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser != null && loginUser.getUserKind().equals("Y")) {
			return true;
		} else {
			session.setAttribute("alertMsg", "접근할 수 없는 페이지입니다.");
			response.sendRedirect(request.getContextPath());
			return false;
		}
	}
}
