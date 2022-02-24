package com.greedy.byat.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dto.TaskDTO;
import com.greedy.byat.task.model.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

	private final TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping("/regist")
	public String registTask(@ModelAttribute TaskDTO task, HttpServletRequest request, RedirectAttributes rttr) {
		
		MemberDTO writer = (MemberDTO) request.getSession().getAttribute("loginMember");
		int projectCode = Integer.parseInt(request.getParameter("code"));
		int writerMemberNo = writer.getNo();
		String writerName = writer.getName();
		
		System.out.println("프로젝트 코드 : " + projectCode);
		
		task.setProjectCode(projectCode);
		task.setMemberNo(writerMemberNo);
		task.setManager(writerName);
		
		String message = taskService.registTask(task);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?=" + projectCode;
	}
	
	@GetMapping(value = "/detail", produces = "application/json; charset=UTF-8")
	public String selectTaskDetail(HttpServletRequest request) {
		
		int taskCode = Integer.parseInt(request.getParameter("code"));
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		TaskDTO task = taskService.selectTaskDetail(taskCode);
		
		return gson.toJson(task);
	}
	
	@GetMapping(value = "/manager", produces = "application/json; charset=UTF-8")
	public String selectProjectMembers(HttpServletRequest request) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		List<String> projectMembers = taskService.selectProjectMembers(projectCode);
		
		return gson.toJson(projectMembers);
	}
}
