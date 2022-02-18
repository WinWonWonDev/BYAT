package com.greedy.byat.mytask.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;
import com.greedy.byat.mytask.model.service.MyTaskService;

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
		
		MyTaskDTO mytask = mytaskService.selectMyTask(member);
		
		model.addAttribute("mytask", mytask);
		return "/mytask/mytask";
	}
	

}
