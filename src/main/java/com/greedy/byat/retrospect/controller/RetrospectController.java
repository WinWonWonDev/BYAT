package com.greedy.byat.retrospect.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectCommentDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectDTO;
import com.greedy.byat.retrospect.model.service.RetrospectService;
import com.greedy.byat.sprint.model.dto.SprintDTO;

@Controller
@RequestMapping("/retrospect")
public class RetrospectController {

	private final RetrospectService retrospectService;
	
	@Autowired
	public RetrospectController(RetrospectService retrospectService) {
		
		this.retrospectService = retrospectService;
	}
	
	@GetMapping("/list")
	public ModelAndView selectRetrospectList(ModelAndView mv, HttpServletRequest request) {
		
		int projectCode = Integer.parseInt(request.getParameter("code"));
		
		List<RetrospectDTO> retrospectList = retrospectService.selectRetrospectList(projectCode);
		
		mv.addObject("retrospectList", retrospectList);
		mv.addObject("code", projectCode);
		mv.setViewName("/retrospect/list");
		
		return mv;
	}
	
	@PostMapping("/regist")
	public String registRetrospectComment(HttpServletRequest request) {
		
		int retrospectCode = Integer.parseInt(request.getParameter("retrospectCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		String body = request.getParameter("body");
		
		RetrospectCommentDTO retrospectComment = new RetrospectCommentDTO();
		retrospectComment.setCode(retrospectCode);
		retrospectComment.setMemberNo(memberNo);
		retrospectComment.setBody(body);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		List<RetrospectCommentDTO> retrospectCommentList = retrospectService.registRetrospectComment(retrospectComment);
		
		return gson.toJson(retrospectCommentList);
	}
}
