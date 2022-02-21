package com.greedy.byat.mytask.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.greedy.byat.task.model.dto.TaskDTO;

@Controller
@RequestMapping("/mytask")
public class MyTaskController {

	private final MyTaskService mytaskService;

	@Autowired
	public MyTaskController(MyTaskService mytaskService) {

		this.mytaskService = mytaskService;
	}

	@GetMapping("/list")
	public String selectMytask(HttpServletRequest request, Model model) throws ParseException {

		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		
		MyTaskDTO mytask = new MyTaskDTO();

		List<ProjectDTO> projectList = new ArrayList<>();
		List<ProjectMembersDTO> projectMembers = new ArrayList<>();
		List<ToDoListDTO> toDoList = new ArrayList<>();
		List<TaskDTO>taskList = new ArrayList<>(); 

		projectList = mytaskService.selectMyTaskProjectList(member);
		toDoList = mytaskService.selectMyTaskToDoList(member.getNo());
		
		System.out.println(member.getNo());
		
		int progressIng = 0;
		int progressDone = 0;
		int progressNotDone = 0;
		
		for(int i = 0; i < projectList.size(); i++) {
		  
		  projectList.get(i).setWriter(projectList.get(i).getWriter().substring(1, 3));
		  
		  projectMembers = mytaskService.selectMyTaskProjectMembers(projectList.get(i).getCode());

		  projectList.get(i).setProjectMembers(projectMembers);
		  
		  //taskList = mytaskService.selectTaskList(projectList.get(i).getCode(), member.getNo());
		  
		  if(projectList.get(i).getProgress().equals("진행중")) {
			  progressIng++;
		  }else if(projectList.get(i).getProgress().equals("완료")){
			  progressDone++;
		  } else {
			  progressNotDone++;
		  }
		}
		
		// memberDTO를 넣어주어서 멤버에 해당되는 ProjectList를 뽑아 온다.
		
		mytask.setProjectDTO(projectList);
		//mytask.setTaskDTO(taskList);
		mytask.setTodolistDTO(toDoList);
		
		int [] projectProgressArr = {progressIng, progressDone, progressNotDone};
		 
		model.addAttribute("projectList", mytask.getProjectDTO());
		
		model.addAttribute("projectProgress",projectProgressArr);
		
		model.addAttribute("todolist", mytask.getTodolistDTO());
		
		return "/mytask/mytask";
	}

}
