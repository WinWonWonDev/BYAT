package com.greedy.byat.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.greedy.byat.common.filter.model.service.MenuService;
import com.greedy.byat.member.model.dto.MemberDTO;

public class AuthLoginInterceptor implements HandlerInterceptor {
	
	private final MenuService menuService;
	
	@Autowired
	public AuthLoginInterceptor(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws IOException {
		
		System.out.println("================ preHandle 호출 ================");
		System.out.println("요청한 URI : " + request.getRequestURI());
		
		MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
		
		if(loginMember != null) {
			return true;
		} else {
			
			System.out.println("로그인 화면으로 이동");
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href='/byat/member/login';</script>");
			out.flush();
			out.close();

			return false;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {

		System.out.println("================ postHandle 호출 ================");
		String requestURI = request.getRequestURI();
		String url = requestURI.substring(5);
		
		System.out.println("requestURI : " + requestURI);
		System.out.println("url : " + url);
		
		try {
			if(!"/byat".equals(requestURI)) {
				
				System.out.println("권한 인터셉터 실행");
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("loginMember");
				System.out.println("memberId : " + member.getId());

				if(member != null && member.getId() != null) {		// 세션이 존재할 경우
					ArrayList<Integer> menuPermitList = menuService.selectPermitByURL(url);
					System.out.println("menuPermitList : " + menuPermitList);
					
					int memberPermitCode = member.getPermitCode();
					boolean isPermitCheck = false;
					
					for(int menuPermitCode : menuPermitList) {
						if(menuPermitCode == memberPermitCode) {
							isPermitCheck = true;
						}
					}
					
					if(!isPermitCheck) {
						mv.addObject("message", "권한이 없습니다.");
						throw new ModelAndViewDefiningException(mv);
					}
					
				} else { // 세션이 없을 경우
					System.out.println("바깥 else문 동작");
					mv.addObject("message", "세션이 만료되어 로그아웃되었습니다. 다시 로그인해주세요 !");
					throw new ModelAndViewDefiningException(mv);
				}
			}
		} catch(Exception e) {
			System.out.println("catch문 동작");
//			mv = new ModelAndView("redirect:/member/login");
			throw new ModelAndViewDefiningException(mv);
		}
	}
}
