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
			
			if ("ADMIN".equals(loginMember.getPermit())) {
				
				if(isPermitAdmin || isPermitMember || isPermitAll) {
					isAuthorized = true;
				}
			} else if ("PM".equals(loginMember.getPermit())) {
				
				if((isPermitMember || isPermitAll) && !isPermitAdmin) {
					isAuthorized = true;
				}
			} else if("일반 멤버".equals(loginMember.getPermit())) {
				
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
				request.getRequestDispatcher("/WEB-INF/views/common/errorDefault.jsp").forward(hrequest, response);
			}
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		permitURIList = new HashMap<>();
		List<String> adminPermitList = new ArrayList<>();
		List<String> memberPermitList = new ArrayList<>();
		List<String> allPermitList = new ArrayList<>();
		
		adminPermitList.add("/management/list");
		adminPermitList.add("/management/regist");
		adminPermitList.add("/management/modify");
		adminPermitList.add("/management/remove");

		memberPermitList.add("/project/list");
		memberPermitList.add("/project/regist");
		memberPermitList.add("/project/remove");
		memberPermitList.add("/project/detail");
		memberPermitList.add("/project/modify");
		memberPermitList.add("/project/searchmembers");
		memberPermitList.add("/project/registmember");
		
		memberPermitList.add("/sprint/list");
		memberPermitList.add("/sprint/regist");
		memberPermitList.add("/sprint/remove");
		memberPermitList.add("/sprint/modify");
		memberPermitList.add("/sprint/start");
		memberPermitList.add("/sprint/end");
		memberPermitList.add("/sprint/select");
		memberPermitList.add("/sprint/selecttasks");
		

		memberPermitList.add("/mytask/list");
		memberPermitList.add("/mytask/regist");
		memberPermitList.add("/mytask/remove");
		memberPermitList.add("/mytask/modify");
		
		memberPermitList.add("/history/list");
		
		memberPermitList.add("/calendar/list");
		memberPermitList.add("/calendar/regist");
		memberPermitList.add("/calendar/remove");
		memberPermitList.add("/calendar/modify");
		
		memberPermitList.add("/member/logout");
		memberPermitList.add("/member/moveprofile");
		memberPermitList.add("/member/emailduplicationcheckforinit");
		memberPermitList.add("/member/registverification");
		
		
	    allPermitList.add("/member/login");
	    allPermitList.add("/member/selectemail");
		allPermitList.add("/member/checkverification");
		allPermitList.add("/member/modifypassword");
		
		permitURIList.put("adminPermitList", adminPermitList);
		permitURIList.put("memberPermitList", memberPermitList);
		permitURIList.put("allPermitList", allPermitList);
		
	}

}
