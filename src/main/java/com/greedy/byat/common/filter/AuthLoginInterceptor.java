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

/**
 * <pre>
 * Class : AuthLoginInterceptor
 * Comment : 각 Controller에 들어오는 요청을 가로채 로그인 및 권한 검증을 수행하는 Interceptor
 * History
 * 2021/03/08 (황인수) 처음 작성함
 * 2021/03/09 (황인수) postHandle 부분 보완
 * 2021/03/10 (황인수) postHandle DB 연결 성공
 * </pre>
 * @author 황인수
 * @version 3
 * @see MenuService,MenuServiceImpl, MenuMapper, MemberDTO
 * */
public class AuthLoginInterceptor implements HandlerInterceptor {
	
	private final MenuService menuService;
	
	@Autowired
	public AuthLoginInterceptor(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws IOException {
		
		MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
		
		if(loginMember != null) {
			return true;
		} else {
			
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

		String requestURI = request.getRequestURI();
		String url = requestURI.substring(5);
		
		try {
			if(!"/byat".equals(requestURI)) {

				MemberDTO member = (MemberDTO) request.getSession().getAttribute("loginMember");

				if(member != null && member.getId() != null) {		// 세션이 존재할 경우
					ArrayList<Integer> menuPermitList = menuService.selectPermitByURL(url);
					
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
			throw new ModelAndViewDefiningException(mv);
		}
	}
}
