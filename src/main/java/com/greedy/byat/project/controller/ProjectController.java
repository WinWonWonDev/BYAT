package com.greedy.byat.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("list")
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
	
	
}
