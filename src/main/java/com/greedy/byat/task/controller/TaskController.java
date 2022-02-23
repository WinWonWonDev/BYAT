package com.greedy.byat.task.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		int projectCode = Integer.parseInt(request.getParameter("code"));
		
		System.out.println(projectCode);
		
		taskService.registTask(task);
		
		rttr.addFlashAttribute("message", "태스크를 생성하였습니다.");
		
		return "redirect:/sprint/list?=" + projectCode;
	}
}
