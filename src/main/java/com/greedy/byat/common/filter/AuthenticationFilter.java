package com.greedy.byat.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.greedy.byat.member.model.dto.MemberDTO;


@WebFilter(urlPatterns = {"/backlog/*", "/member/*", "/history/*", "/issue/*"
		, "/management/*", "/meetinglog/*", "/member/*", "/mytask/*", "/notice/*", "/profile/*"
		, "/project/*", "/retrospect/*", "/sprint/*", "/task/*"})
public class AuthenticationFilter implements Filter {
	
	Map<String, List<String>> permitURIList;
	
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hrequest = (HttpServletRequest) request;
		String uri = hrequest.getRequestURI();
		
		String intent = uri.replace(hrequest.getContextPath(), "");
		
		HttpSession requestSession = hrequest.getSession();
		MemberDTO loginMember = (MemberDTO) requestSession.getAttribute("loginMember");
		
		boolean isAuthorized = false;
		
		if(loginMember != null) {									
			boolean isPermitAdmin = permitURIList.get("adminPermitList").contains(intent);
			boolean isPermitMember = permitURIList.get("memberPermitList").contains(intent);
			boolean isPermitAll = permitURIList.get("allPermitList").contains(intent);
			
			if("ROLE_ADMIN".equals(loginMember.getRole())) {
				
				if(isPermitAdmin || isPermitMember || isPermitAll) {
					isAuthorized = true;
				}
			} else if("ROLE_MEMBER".equals(loginMember.getRole())) {
				
				if((isPermitMember || isPermitAll) && !isPermitAdmin) {
					isAuthorized = true;
				}
			}
			
			if(isAuthorized) {
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse) response).sendError(403);
			}
		} else {													

			if(permitURIList.get("allPermitList").contains(intent)) {
				chain.doFilter(request, response);
			} else {
				request.setAttribute("message", "로그인이 필요한 서비스 입니다.");
				request.getRequestDispatcher("/WEB-INF/views/error/default.jsp").forward(hrequest, response);
			}
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		permitURIList = new HashMap<>();
		List<String> adminPermitList = new ArrayList<>();
		List<String> memberPermitList = new ArrayList<>();
		List<String> allPermitList = new ArrayList<>();
		
		adminPermitList.add("/notice/regist");
		adminPermitList.add("/notice/update");
		adminPermitList.add("/notice/delete");
		adminPermitList.add("/board/delete");
		
		memberPermitList.add("/member/update");
		memberPermitList.add("/member/delete");
		memberPermitList.add("/member/logout");
		memberPermitList.add("/notice/list");
		memberPermitList.add("/notice/detail");
		memberPermitList.add("/board/list");
		memberPermitList.add("/board/regist");
		memberPermitList.add("/board/detail");
		memberPermitList.add("/board/update");
		memberPermitList.add("/board/registReply");
		memberPermitList.add("/board/removeReply");
		memberPermitList.add("/thumbnail/list");
		memberPermitList.add("/thumbnail/regist");
		memberPermitList.add("/thumbnail/detail");
		memberPermitList.add("/board/delete");
		

		allPermitList.add("/member/regist");
		allPermitList.add("/member/login");
		allPermitList.add("/member/idDupCheck");
		
		permitURIList.put("adminPermitList", adminPermitList);
		permitURIList.put("memberPermitList", memberPermitList);
		permitURIList.put("allPermitList", allPermitList);
		
	}

}
