package com.greedy.byat.mytask.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	//전체 조회 
	@GetMapping("/list")
	public String selectMytask(HttpServletRequest request, Model model) throws ParseException {

		int progressIng = 0;
		int progressDone = 0;
		int progressNotDone = 0;
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		MyTaskDTO mytask = new MyTaskDTO();
		
		mytask = mytaskService.selectmyTaskList(member);
		
		for(int i = 0; i < mytask.getProjectDTO().size(); i++) {
			
			if(mytask.getProjectDTO().get(i).getProgress().equals("진행중")) {
				progressIng++;
			} else if (mytask.getProjectDTO().get(i).getProgress().equals("완료")) {
	            progressDone++;
	         } else {
	            progressNotDone++;
	         }
		}
		
		int[] projectProgressArr = { progressIng, progressDone, progressNotDone };
		
		
		model.addAttribute("projectProgress", projectProgressArr);
		model.addAttribute("projectList", mytask.getProjectDTO());
		model.addAttribute("todoList", mytask.getTodolistDTO());
		model.addAttribute("taskList", mytask.getTaskDTO());

		return "/mytask/mytask";
	}
	
	/*
	 * @PostMapping("/selecttasklist")
	 * 
	 * @ResponseBody public List<TaskDTO> selectTaskList(int loginMember ,
	 * HttpServletResponse response) {
	 * 
	 * System.out.println(loginMember + "@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	 * response.setContentType("application/json; charset=UTF-8");
	 * 
	 * List<TaskDTO> taskList = mytaskService.selectTaskList(loginMember);
	 * System.out.println(taskList + "@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	 * 
	 * return taskList; }
	 */
	
	@GetMapping("/selecttasklistforproject")
	@ResponseBody 
	public List<TaskDTO> selectTaskListForProject(int projectCode, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		response.setContentType("application/json; charset=UTF-8");
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		List<TaskDTO> taskList = mytaskService.selectTaskListForProject(member.getNo(), projectCode);

		

		return taskList;
	}
	
	@GetMapping("/regist")
	public String registToDoList(HttpServletRequest request) {

		int memberNumber = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();

		mytaskService.registToDoList(memberNumber);

		return "redirect:/mytask/list";
	}

	@GetMapping("/remove")
	public int removeToDoList(int toDoListNumber, HttpServletResponse response) {

		response.setContentType("application/json; charset=UTF-8");

		int result = mytaskService.removeToDoList(toDoListNumber);

		return result;
	}
	
	@PostMapping("/modify")
	public int modifyToDoList(@ModelAttribute ToDoListDTO todoList, HttpServletResponse response) {

		response.setContentType("application/json; charset=UTF-8");

		int result= mytaskService.modifyToDoList(todoList);

		return result;
	}
	
	@GetMapping("/modifytodoliststatus")
	public int modifyToDoListStatus(@ModelAttribute ToDoListDTO todoList, HttpServletResponse response) {
		
		response.setContentType("application/json; charset=UTF-8");

		int result = mytaskService.modifyToDoListStatus(todoList);
		
		return result;
	}

	@PostMapping("/selectmembermodal")
	@ResponseBody
	public List<ProjectMembersDTO> selectMembersModal(int projectCode, HttpServletResponse response) {
		
		response.setContentType("application/json; charset=UTF-8");

		List<ProjectMembersDTO> projectMembers = mytaskService.selectMemberModal(projectCode);
		
		return projectMembers;
		
	}
}














