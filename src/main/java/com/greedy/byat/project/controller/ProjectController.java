package com.greedy.byat.project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.byat.common.exception.project.ProjectModifyException;
import com.greedy.byat.common.exception.project.ProjectRegistException;
import com.greedy.byat.common.exception.project.ProjectRemoveException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.project.model.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		
		this.projectService = projectService;
	}
	
	@GetMapping("/list")
	public ModelAndView selectProjectList(ModelAndView mv, HttpServletRequest request) {
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		List<ProjectDTO> projectList = projectService.selectProjectList(member);
		
		List<ProjectMembersDTO> projectMembers = new ArrayList<>();
		
		String name = "";
		
		for(int i = 0; i < projectList.size(); i++) {
			
			projectList.get(i).setWriter(projectList.get(i).getWriter().substring(1, 3));
			
			projectMembers = projectService.selectProjectMembers(projectList.get(i).getCode());
			
			projectList.get(i).setProjectMembers(projectMembers);
		}
		
		System.out.println(projectList);
		
		mv.addObject("projectList", projectList);
		mv.setViewName("/project/list");
		
		return mv;
	}
	
	@PostMapping("/regist")
	public String registProject(@ModelAttribute ProjectDTO project, HttpServletRequest request, RedirectAttributes rttr) throws ProjectRegistException {
		
		String memberName = ((MemberDTO) request.getSession().getAttribute("loginMember")).getName();
		
		project.setWriterMember((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		project.setWriter(memberName);

		projectService.registProject(project);
		
		rttr.addFlashAttribute("message", "프로젝트 생성 성공!");
		
		return "redirect:/project/list";
	}
	
	@GetMapping("/remove")
	public String removeProject(HttpServletRequest request, RedirectAttributes rttr) throws ProjectRemoveException {
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		projectService.removeProject(code);
		
		rttr.addFlashAttribute("message", "프로젝트 삭제 성공!");
		
		return "redirect:/project/list";
	}
	
	@GetMapping("/detail")
	public ModelAndView selectProjectDetail(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		ProjectDTO projectDetail = projectService.selectProjectDetail(code);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		
		mv.addObject("projectDetail", objectMapper.writeValueAsString(projectDetail));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("/modify")
	public String modifyProject(@ModelAttribute ProjectDTO project, HttpServletRequest request, RedirectAttributes rttr) throws ProjectModifyException {

		int code = Integer.parseInt(request.getParameter("code"));
		
		project.setCode(code);
		
		System.out.println(project);
		
		projectService.modifyProject(project);
		
		rttr.addFlashAttribute("message", "프로젝트 수정 성공!");
		
		return "redirect:/project/list";
	}
	
}
