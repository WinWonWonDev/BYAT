package com.greedy.byat.sprint.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.backlog.model.service.BacklogService;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.service.SprintService;

/** 
* <pre>
* Class : SprintController
* Comment : Sprint관련 메소드를 모아놓은 Controller입니다.
* History
* 2022/02/17 (박상범) 스프린트 목록 조회 관련 메소드 추가
* 2022/02/18 (박상범) 스프린트 목록 조회, 스프린트 생성 관련 메소드 추가
* 2022/02/19 (박상범) 스프린트 생성, 스프린트 상세 조회 / 수정 관련 메소드 추가
* 2022/02/20 (박상범) 스프린트 상세 조회 / 수정, 스프린트 삭제 관련 메소드 추가
* 2022/02/21 (박상범) 스프린트 수정, 스프린트 생성 관련 메소드 추가
* 2022/03/02 (박상범) 스프린트 시작 관련 메소드 추가
* 2022/03/03 (박상범) 스프린트 시작 관련 메소드 추가
* 2022/03/04 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
* 2022/03/05 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
* 2022/03/06 (박상범) 스프린트 생성, 스프린트 목록 조회 관련 메소드 추가
* 2022/03/07 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
* 2022/03/07 (박상범) 스프린트 생성, 스프린트 수정 관련 메소드 추가
* </pre>
* @version 12
* @author 박상범
* @see SprintDTO.java, SprintService.java, SprintServiceImpl.java, SprintMapper.java 
*/
@Controller
@RequestMapping("/sprint")
public class SprintController {

	private final SprintService sprintService;
	
	@Autowired
	public SprintController(SprintService sprintService, BacklogService backlogService) {
		this.sprintService = sprintService;
		
	}
	
   /**
	* 메소드 selectAllList에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터
	* @param ModelAndView mv : sprintService의 메소드의 결과 값과 반환 경로를 담기 위한 파라미터
	* @return : sprintService메소드의 결과 값과 반환경로를 담은 mv를 return
	*/
	@GetMapping("/list")
	public ModelAndView selectAllList(HttpServletRequest request, ModelAndView mv) {

		int projectCode = Integer.parseInt(request.getParameter("code"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		Map<String, Integer> map = new HashMap<>();
		map.put("projectCode", projectCode);
		map.put("memberNo", memberNo);
		
		String roleName = sprintService.selectMemberRoleName(map);
		String projectProgress = sprintService.selectProjectProgress(projectCode);
		List<SprintDTO> sprintList = sprintService.selectSprintList(projectCode);
		List<BacklogDTO> backlogList = sprintService.selectBacklogList(projectCode);
		
		mv.addObject("roleName", roleName);
		mv.addObject("backlogList", backlogList);
		mv.addObject("sprintList", sprintList);
		mv.addObject("projectProgress", projectProgress);
		mv.addObject("code", projectCode);
		mv.setViewName("/sprint/list");
		
		return mv;
	}

   /**
	* 메소드 registSprint에 관한 문서화 주석
	* @param SprintDTO sprint : SprintDTO 자체를 사용하기 위한 파라미터로
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
	* @return : request로 받은 projectCode를 사용하여 /sprint/list로 redirect
	*/
	@PostMapping("/regist")
	public String registSprint(@ModelAttribute SprintDTO sprint, HttpServletRequest request, RedirectAttributes rttr) {
		
		MemberDTO writer = (MemberDTO) request.getSession().getAttribute("loginMember");
		int projectCode = Integer.parseInt(request.getParameter("code"));

		sprint.setProjectCode(projectCode);
		sprint.setWriter(writer.getName());
		sprint.setMemberNo(writer.getNo());
		
		String message = sprintService.registSprint(sprint);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
	
   /**
	* 메소드 selectSprintDetail에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터
	* @return : 비동기 방식으로 Json형태로 sprintService메소드의 결과 값을 리턴
	*/
	@GetMapping(value = "/detail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectSprintDetail(HttpServletRequest request) {
		
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		SprintDTO sprint = sprintService.selectSprint(sprintCode);
		
		return gson.toJson(sprint);
	}
	
   /**
	* 메소드 modifySprint에 관한 문서화 주석
	* @param SprintDTO sprint : SprintDTO 자체를 사용하기 위한 파라미터
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
	* @return : @ModelAttribute SprintDTO로 받은 sprint에서  getProjectCode()를 사용하여 /sprint/list로 redirect
	*/
	@PostMapping("/modify")
	public String modifySprint(@ModelAttribute SprintDTO sprint, RedirectAttributes rttr) {
		
		sprintService.modifySprint(sprint);
		
		rttr.addFlashAttribute("message", "스프린트를 수정하였습니다.");
		
		return "redirect:/sprint/list?code=" + sprint.getProjectCode();
	}
	
   /**
	* 메소드 removeSprint에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
	* @return : request로 받은 projectCode를 사용하여 /sprint/list로 redirect
	*/
	@GetMapping("/remove")
	public String removeSprint(HttpServletRequest request, RedirectAttributes rttr) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		Map<String, Integer> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("sprintCode", sprintCode);
		
		sprintService.removeSprint(map);
		
		rttr.addFlashAttribute("message", "스프린트 삭제 완료");
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
	
   /**
	* 메소드 startSprint에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
	* @return : request로 받은 projectCode를 사용하여 /sprint/list로 redirect
	*/
	@GetMapping("/start")
	public String startSprint(HttpServletRequest request, RedirectAttributes rttr) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		Map<String, Integer> map = new HashMap<>();
		map.put("projectCode", projectCode);
		map.put("memberNo", memberNo);
		
		String message = sprintService.startSprint(map);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
	
   /**
	* 메소드 endSprint에 관한 문서화 주석
	* @param HttpServletRequest request : 요청에 의해 넘어온 파라미터
	* @param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
	* @return : request로 받은 projectCode를 사용하여 /sprint/list로 redirect
	*/
	@GetMapping("/end")
	public String endSprint(HttpServletRequest request, RedirectAttributes rttr) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		Map<String, Integer> map = new HashMap<>();
		map.put("projectCode", projectCode);
		map.put("memberNo", memberNo);
		
		String message = sprintService.endSprint(map);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
}
