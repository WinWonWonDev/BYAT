package com.greedy.byat.issue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.byat.common.exception.issue.IssueModifyStatusException;
import com.greedy.byat.common.exception.issue.IssueRegistStatusHistoryException;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.service.IssueService;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;

@Controller
@RequestMapping("/issue")
public class IssueController {

	private final IssueService issueService;
	
	@Autowired
	public IssueController(IssueService issueService) {
		
		this.issueService = issueService;
	}
	
	@GetMapping("list")
	public ModelAndView selectIssueList(ModelAndView mv, HttpServletRequest request) {
		
		int projectCode = Integer.parseInt(request.getParameter("code"));
		
		List<SprintDTO> sprintList = issueService.selectSprintList(projectCode);

		System.out.println(sprintList);
		
		mv.addObject("sprintList", sprintList);
		mv.setViewName("/issue/list");
		
		return mv;
	}
	
	@GetMapping("modifyissuestatus")
	public ModelAndView modifyIssueStatus(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IssueModifyStatusException, JsonProcessingException, IssueRegistStatusHistoryException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("issueCode"));
		String progress = request.getParameter("progress");
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		IssueDTO issue = new IssueDTO();
		issue.setCode(code);
		issue.setProgress(progress);
		issue.setName(member.getName());
		
		int result = issueService.updateIssueStatus(issue);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		mv.addObject("result", objectMapper.writeValueAsString(result));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
}
