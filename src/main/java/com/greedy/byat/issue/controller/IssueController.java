package com.greedy.byat.issue.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.byat.common.exception.issue.IssueInsertMemberHistoryException;
import com.greedy.byat.common.exception.issue.IssueInsertVersionHistoryException;
import com.greedy.byat.common.exception.issue.IssueModifyMemberException;
import com.greedy.byat.common.exception.issue.IssueModifyStatusException;
import com.greedy.byat.common.exception.issue.IssueRegistStatusHistoryException;
import com.greedy.byat.common.exception.issue.IssueRemoveException;
import com.greedy.byat.common.exception.issue.IssueRemoveMemberException;
import com.greedy.byat.common.exception.issue.IssueUpdateContentException;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
import com.greedy.byat.issue.model.service.IssueService;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.dto.SprintMembersDTO;

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
	
	@GetMapping("issuelist")
	public ModelAndView selectSprintIssueList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("sprintCode"));
		
		List<IssueDTO> issueList = issueService.selectIssueList(code);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		
		mv.addObject("issueList", objectMapper.writeValueAsString(issueList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("sprintmemberlist")
	public ModelAndView selectSprintMembers(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("sprintCode"));

		List<SprintMembersDTO> sprintMemberList = issueService.selectSprintMembers(code);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		System.out.println(sprintMemberList);
		
		mv.addObject("sprintMemberList", objectMapper.writeValueAsString(sprintMemberList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("modify")
	public String modifyIssue(HttpServletRequest request, RedirectAttributes rttr) throws IssueModifyMemberException, IssueUpdateContentException, IssueInsertVersionHistoryException, IssueInsertMemberHistoryException {
		
		MemberDTO changeMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		String title = request.getParameter("issueModifyTitle");
		String body = request.getParameter("issueModifyBody");
		String[] memberInfo = request.getParameterValues("issueMemberBody");
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		int issueCode = Integer.parseInt(request.getParameter("issueCode"));
		
		List<IssueMembersDTO> issueMemberList = new ArrayList<>();
		
		for(int i = 0; i < memberInfo.length; i++) {
			
			IssueMembersDTO member = new IssueMembersDTO();
			
			String[] memberInfoSplit = memberInfo[i].split(" ");
			
			member.setId(memberInfoSplit[0]);
			member.setName(memberInfoSplit[1]);
			member.setNo(Integer.parseInt(memberInfoSplit[2]));
			member.setCode(issueCode);
			member.setChangeMemberNo(changeMember.getNo());
			
			issueMemberList.add(i, member);
			
		}
		
		IssueDTO modifyIssue = new IssueDTO();
		modifyIssue.setTitle(title);
		modifyIssue.setBody(body);
		modifyIssue.setCode(issueCode);
		modifyIssue.setIssueMemberList(issueMemberList);
		modifyIssue.setProjectCode(projectCode);
		modifyIssue.setSprintCode(sprintCode);
		modifyIssue.setWriter(changeMember.getNo());
		
		issueService.updateIssue(modifyIssue);
		
		rttr.addFlashAttribute("message", "이슈 수정 및 담당자 변경 성공!");
		
		return "redirect:/issue/list?code=" + projectCode;
	}
	
	@PostMapping("removeissuemember")
	public ModelAndView removeIssueMember(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IssueRemoveMemberException, IssueInsertMemberHistoryException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		MemberDTO changeMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		String[] memberInfo = request.getParameter("info").split(" ");
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		int issueCode = Integer.parseInt(request.getParameter("issueCode"));
		
		IssueMembersDTO removeMember = new IssueMembersDTO();
		removeMember.setCode(issueCode);
		removeMember.setNo(Integer.parseInt(memberInfo[2]));
		removeMember.setSprintCode(sprintCode);
		removeMember.setParticipationYn("N");
		removeMember.setChangeMemberNo(changeMember.getNo());
		
		int result = issueService.deleteIssueMember(removeMember);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@GetMapping("remove")
	public String removeIssue(HttpServletRequest request, RedirectAttributes rttr) throws IssueRemoveException, IssueInsertVersionHistoryException {
		
		MemberDTO changeMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		int projectCode = issueService.deleteIssue(code, changeMember.getNo());
		
		rttr.addFlashAttribute("message", "이슈 삭제 성공!");
		
		return "redirect:/issue/list?code=" + projectCode;
	}
}
