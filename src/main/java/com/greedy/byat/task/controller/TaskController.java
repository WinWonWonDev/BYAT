package com.greedy.byat.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		System.out.println("프로젝트 코드 : " + task.getProjectCode());
		
		String message = taskService.registTask(task);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + task.getProjectCode();
	}
	
	@GetMapping(value = "/detail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectTaskDetail(HttpServletRequest request) {
		
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		TaskDTO task = taskService.selectTaskDetail(taskCode);
		
		System.out.println(task);
		
		return gson.toJson(task);
	}
	
	@GetMapping(value = "/manager", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectProjectMembers(HttpServletRequest request) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		System.out.println("내가 바로 프로젝트 코드 " + projectCode);
		
		
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		List<MemberDTO> projectMembers = taskService.selectProjectMembers(projectCode);
		
		System.out.println("프로젝트 구성원 : " + projectMembers);
		
		return gson.toJson(projectMembers);
	}
	
	@GetMapping(value = "/selectparticipation", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectTaskParticipation(HttpServletRequest request, HttpServletResponse response) {
		
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		System.out.println(taskCode);
		System.out.println(memberNo);
		
		Map<String, Integer> taskParticipation = new HashMap<String, Integer>();
		
		taskParticipation.put("taskCode", taskCode);
		taskParticipation.put("memberNo", memberNo);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		String result = taskService.selectTaskParticipation(taskParticipation);
		
		System.out.println(result);
		
		return gson.toJson(result);
	}
	
	@GetMapping(value = "/participation", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String registTaskMembers(HttpServletRequest request) {
		
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		System.out.println("ㅋㅋ" + taskCode);
		System.out.println(memberNo);
		
		Map<String, Integer> taskParticipation = new HashMap<String, Integer>();
		taskParticipation.put("taskCode", taskCode);
		taskParticipation.put("memberNo", memberNo);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		int result = taskService.registTaskMembers(taskParticipation);
		
		return gson.toJson(result);
	}
	
}
