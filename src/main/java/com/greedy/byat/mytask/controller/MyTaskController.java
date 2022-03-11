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
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		
		System.out.println(mytask.getProjectDTO());
		
		model.addAttribute("projectProgress", projectProgressArr);
		model.addAttribute("projectList", mytask.getProjectDTO());
		model.addAttribute("todoList", mytask.getTodolistDTO());
		model.addAttribute("taskList", mytask.getTaskDTO());

		return "/mytask/mytask";
	}
	

	@GetMapping(value="/selecttasklistforproject", produces="application/json; charset=UTF-8")
	@ResponseBody 
	public String selectTaskListForProject(int projectCode, HttpServletRequest request, HttpServletResponse response, Model model) throws JsonProcessingException {

		response.setContentType("application/json; charset=UTF-8");
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		List<TaskDTO> taskList = mytaskService.selectTaskListForProject(member.getNo(), projectCode);

		model.addAttribute("code", projectCode);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();

		return gson.toJson(taskList); 
	}
	
	@GetMapping("/regist")
	public String registToDoList(HttpServletRequest request) {
		
		int memberNumber = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();

		mytaskService.registToDoList(memberNumber);
		
		return "redirect:/mytask/list";
	}

	@GetMapping(value = "/remove", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int removeToDoList(int toDoListNo, HttpServletResponse response) {

		System.out.println("gg");

		int result = mytaskService.removeToDoList(toDoListNo);

		return result;
	}
	
	@PostMapping(value = "/modify", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int modifyToDoList(@ModelAttribute ToDoListDTO todoList) {

		int result= mytaskService.modifyToDoList(todoList);
		
		System.out.println(result + "modify");
		return result;
	}
	
	@GetMapping(value="/modifytodoliststatus", produces="application/json; charset=UTF-8")
	@ResponseBody 
	public int modifyToDoListStatus(@ModelAttribute ToDoListDTO todoList) {
		System.out.println("modifyTODoListStatus : " + todoList);

		int result = mytaskService.modifyToDoListStatus(todoList);
		
		return result;
	}

	@PostMapping("/selectmembermodal")
	@ResponseBody
	public List<ProjectMembersDTO> selectMembersModal(int projectCode){
	

		List<ProjectMembersDTO> projectMembers = mytaskService.selectMemberModal(projectCode);
		
		return projectMembers;
		
	}
}














