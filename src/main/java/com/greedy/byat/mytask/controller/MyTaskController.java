package com.greedy.byat.mytask.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;
import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.mytask.model.service.MyTaskService;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

@Controller
@RequestMapping("/mytask")
public class MyTaskController {

	private final MyTaskService mytaskService;

	@Autowired
	public MyTaskController(MyTaskService mytaskService) {

		this.mytaskService = mytaskService;
	}

	@GetMapping("/list")
	public String selectMytask(HttpServletRequest request, Model model) {

		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		
		MyTaskDTO mytask = new MyTaskDTO();

		List<ProjectDTO> projectList = new ArrayList<>();
		List<ProjectMembersDTO> projectMembers = new ArrayList<>();
		List<ToDoListDTO> toDoList = new ArrayList<>();

		// memberDTO를 넣어주어서 멤버에 해당되는 ProjectList를 뽑아 온다.
		projectList = mytaskService.selectMyTaskProjectList(member);
		mytask.setProjectDTO(projectList);
		
		
		for(int i = 0; i < projectList.size(); i++) {
		  
		  projectList.get(i).setWriter(projectList.get(i).getWriter().substring(1, 3));
		  
		  projectMembers = mytaskService.selectMyTaskProjectMembers(projectList.get(i).getCode());
		  
		  projectList.get(i).setProjectMembers(projectMembers);
		  
		  System.out.println(projectList.get(i).getTitle());
		  
		}
		 
		model.addAttribute("projectList", mytask.getProjectDTO());
		
		
		
		return "/mytask/mytask";
	}

}
