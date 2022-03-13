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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectCommentDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectDTO;
import com.greedy.byat.retrospect.model.service.RetrospectService;
import com.greedy.byat.sprint.model.dto.SprintDTO;

/**
* <pre>
* Class : RetrospectController
* Comment : retrospect관련 메소드를 모아놓은 Controller
* History
* 2022/03/05 (박상범) 회고록 생성 관련 메소드 추가
* 2022/03/06 (박상범) 회고록 목록 조회 관련 메소드 추가
* 2022/03/09 (박상범) 회고록 목록 조회, 회고록 댓글 목록 조회, 회고록 댓글 작성 관련 메소드 추가
* 2022/03/10 (박상범) 회고록 댓글 삭제 관련 메소드 추가
* </pre>
* @version 4
* @author 박상범
* @see RetrospecctDTO, RetrospectService, RetrospectServiceImpl, RetrospectMapper.java, RetrospectDTO, RetrospectCommentDTO 
*
*/
@Controller
@RequestMapping("/retrospect")
public class RetrospectController {

	private final RetrospectService retrospectService;
	
	@Autowired
	public RetrospectController(RetrospectService retrospectService) {
		
		this.retrospectService = retrospectService;
	}
	
   /**
    * 메소드 selectRetrospectList에 관한 문서화 주석
    * @param ModelAndView mv addObject를 통해 retrospectList값과 projectCode를 담고, 그 담은 값을 setViewName을 통해 list.jsp로 보내주기 위한 파라미터
    * @param HttpServletRequest request jsp의 code를 getParameter로 가져오기 위한 파라미터
    * @return view에서 사용해야하는 값에 retrospectList, projectCode를 담아 mv를 return
    */
	@GetMapping("/list")
	public ModelAndView selectRetrospectList(ModelAndView mv, HttpServletRequest request) {
		
		int projectCode = Integer.parseInt(request.getParameter("code"));
		
		List<RetrospectDTO> retrospectList = retrospectService.selectRetrospectList(projectCode);
		
		System.out.println(retrospectList);
		
		mv.addObject("retrospectList", retrospectList);
		mv.addObject("code", projectCode);
		mv.setViewName("/retrospect/list");
		
		return mv;
	}
	
  /**
    * 메소드 registRetrospectiveComment에 관한 문서화 주석
    * @param HttpServletRequest request jsp의 회고록 코드, 현재 접속 멤버의 세션정보, body값을 getParameter로 가져오기 위한 파라미터
    * @return Service에서 반환 받은 생성된 댓글의 list가 담긴 값을 비동기방식으로 이용하기 위해 가공된 값을 return 
    */
	@PostMapping(value = "/regist", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String registRetrospectiveComment(HttpServletRequest request) {
		
		int retrospectiveCode = Integer.parseInt(request.getParameter("retrospectCode"));
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		String body = request.getParameter("body");
		
		RetrospectCommentDTO retrospectComment = new RetrospectCommentDTO();
		retrospectComment.setCode(retrospectiveCode);
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

  /**
    * 메소드 removeRetrospectiveComment에 관한 문서화 주석
    * @param HttpServletRequest request jsp의 멤버 넘버의 값을 getParameter로 가져오기 위한 파라미터
    * @return Service에서 반환 받은 삭제되고 남은 댓글의 list가 담긴 값을 비동기방식으로 이용하기 위해 가공된 값을 return
    */
	@GetMapping(value = "/remove", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String removeRetrospectiveComment(HttpServletRequest request) {
		
		int retrospectiveCommentNo = Integer.parseInt(request.getParameter("no"));
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		List<RetrospectCommentDTO> retrospectCommentList = retrospectService.removeRetrospectComment(retrospectiveCommentNo);
		
		return gson.toJson(retrospectCommentList);
	}
}
