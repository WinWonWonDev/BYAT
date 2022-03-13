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
import com.greedy.byat.task.model.dto.TaskMembersDTO;
import com.greedy.byat.task.model.service.TaskService;

/** 
* <pre>
* Class : TaskController
* Comment : Task관련 메소드를 모아놓은 Controller입니다.
* History
* 2022/02/17 박상범  처음 작성
* </pre>
* @version 1.0.0
* @author 박상범
* @see TaskDTO.java, TaskService.java, TaskServiceImpl.java, TaskMapper.java 
*/
@Controller
@RequestMapping("/task")
public class TaskController {

	private final TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	/**
	* 메소드 selectTaskList에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @return : 비동기 방식으로 Json형태로 taskService메소드의 결과 값을 리턴합니다.
	*/
	@GetMapping(value = "/list", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectTaskList(HttpServletRequest request) {
		
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		List<TaskDTO> taskList = taskService.selectTaskList(sprintCode);
		
		return gson.toJson(taskList);
	}
	
	/**
	* 메소드 registTask에 관한 문서화 주석
	* @param TaskDTO task : TaskDTO 자체를 파라미터로 사용하기 위함입니다.
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
	* @return : request로 받은 projectCode를 사용하여 /sprint/list로 redirect 합니다.
	*/
	@PostMapping("/regist")
	public String registTask(@ModelAttribute TaskDTO task, RedirectAttributes rttr) {
		
		String message = taskService.registTask(task);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + task.getProjectCode();
	}
	
	/**
	* 메소드 selectTaskDetail에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @return : 비동기 방식으로 Json형태로 taskService메소드의 결과 값을 리턴합니다.
	*/
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
		
		return gson.toJson(task);
	}
	
	/**
	* 메소드 selectTaskParticipation에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @return : 비동기 방식으로 Json형태로 taskService메소드의 결과 값을 리턴합니다.
	*/
	@GetMapping(value = "/selectparticipation", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectTaskParticipation(HttpServletRequest request) {
		
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
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
		
		return gson.toJson(result);
	}
	
	/**
	* 메소드 registTask에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
	* @return : request로 받은 projectCode를 사용하여 /sprint/list로 redirect 합니다.
	*/
	@GetMapping("/participation")
	public String registTaskMembers(HttpServletRequest request, RedirectAttributes rttr) {
		
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		System.out.println(taskCode);
		System.out.println(memberNo);
		
		Map<String, Integer> taskParticipation = new HashMap<String, Integer>();
		taskParticipation.put("taskCode", taskCode);
		taskParticipation.put("memberNo", memberNo);
		
		String message = taskService.registTaskMembers(taskParticipation);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
	
	/**
	* 메소드 selectTaskMembersList에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @return : 비동기 방식으로 Json형태로 taskService메소드의 결과 값을 리턴합니다.
	*/
	@GetMapping(value = "/members", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectTaskMembersList(HttpServletRequest request) {
		
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		List<TaskMembersDTO> taskMembers = taskService.selectTaskMembers(taskCode);
		
		return gson.toJson(taskMembers);
	}
	
	/**
	* 메소드 modifySprint에 관한 문서화 주석
	* @param TaskDTO task : TaskDTO 자체를 파라미터로 사용하기 위함입니다.
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
	* @return : @ModelAttribute TaskDTO로 받은 task에서  getProjectCode()를 사용하여 /sprint/list로 redirect 합니다.
	*/
	@PostMapping("/modify")
	public String modifyTask(@ModelAttribute TaskDTO task, HttpServletRequest request, RedirectAttributes rttr) {
		
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		task.setMemberNo(memberNo);
		
		String message = taskService.modifyTask(task);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + task.getProjectCode();
	}
	
	/**
	* 메소드 removeTask에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
	* @return : request로 받은 projectCode를 사용하여 /sprint/list로 redirect 합니다.
	*/
	@GetMapping("/remove")
	public String removeTask(HttpServletRequest request, RedirectAttributes rttr) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		System.out.println(projectCode);
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("taskCode", taskCode);
		map.put("updateMemberNo", memberNo);
		
		String message = taskService.removeTask(map);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode; 
	}
	
	/**
	* 메소드 removeTask에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
	* @return : request로 받은 projectCode를 사용하여 /sprint/list로 redirect 합니다.
	*/
	@GetMapping("/giveup")
	public String removeTaskMembers(HttpServletRequest request, RedirectAttributes rttr) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("taskCode", taskCode);
		map.put("memberNo", memberNo);
		
		String message = taskService.removeTaskMembers(map);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode; 
	}
	
	/**
	* 메소드 selectTaskMembersList에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터를 전달받기 위함입니다.
	* @return : 비동기 방식으로 Json형태로 taskService메소드의 결과 값을 리턴합니다.
	*/
	@GetMapping("/progress")
	@ResponseBody
	public String updateTaskProgress(HttpServletRequest request) {
		
		int taskCode = Integer.parseInt(request.getParameter("taskCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		int taskProgressCode = 0;
		
		if("진행중".equals(request.getParameter("taskProgress"))) {
			
			taskProgressCode = 1;
		} else if("완료".equals(request.getParameter("taskProgress"))) {
			
			taskProgressCode = 2;
		}
		
		Map<String, Integer> map = new HashMap<>();
		map.put("taskCode", taskCode);
		map.put("taskProgressCode", taskProgressCode);
		map.put("memberNo", memberNo);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		boolean result = taskService.updateTaskProgress(map);
		
		return gson.toJson(result);
	}
}
