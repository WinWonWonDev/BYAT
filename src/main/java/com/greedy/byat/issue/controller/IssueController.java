package com.greedy.byat.issue.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.byat.common.exception.issue.IssueDeleteNoticeException;
import com.greedy.byat.common.exception.issue.IssueInsertMemberHistoryException;
import com.greedy.byat.common.exception.issue.IssueInsertVersionHistoryException;
import com.greedy.byat.common.exception.issue.IssueModifyMemberException;
import com.greedy.byat.common.exception.issue.IssueModifyNoticeException;
import com.greedy.byat.common.exception.issue.IssueModifyStatusException;
import com.greedy.byat.common.exception.issue.IssueRegistException;
import com.greedy.byat.common.exception.issue.IssueRegistMemberException;
import com.greedy.byat.common.exception.issue.IssueRegistNoticeException;
import com.greedy.byat.common.exception.issue.IssueRegistStatusHistoryException;
import com.greedy.byat.common.exception.issue.IssueRemoveException;
import com.greedy.byat.common.exception.issue.IssueRemoveMemberException;
import com.greedy.byat.common.exception.issue.IssueRemoveMemberNoticeException;
import com.greedy.byat.common.exception.issue.IssueStatusModifyNoticeException;
import com.greedy.byat.common.exception.issue.IssueUpdateContentException;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
import com.greedy.byat.issue.model.service.IssueService;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.dto.SprintMembersDTO;

/**
 * <pre>
 * Class : IssueController
 * Comment : Issue 관련 메소드를 모아놓은 Controller입니다.
 * History
 * 2022/02/25 (박인근) 처음 작성
 * 2022/02/26 (박인근) 이슈 목록 조회 관련 코드 추가
 * 2022/02/27 (박인근) 이슈 목록 조회, 이슈 상세 조회 관련 코드 추가
 * 2022/02/28 (박인근) 이슈 상태 변경 관려 코드 추가
 * 2022/03/01 (박인근) 이슈 수정 관련 코드 추가
 * 2022/03/02 (박인근) 이슈 수정(담당자 변경), 이슈 담당자 제외 관련 코드 추가
 * 2022/03/03 (박인근) 이슈 삭제 관련 코드 추가
 * 2022/03/07 (박인근) 이슈 관련 알림 생성 코드 추가
 * </pre>
 * @version 8
 * @author 박인근
 * see IssueDTO, IssueMembersDTO, IssueService, IssueServiceImpl, IssueMapper.java, IssueMapper.xml, NoticeDTO, MemberDTO, SprintDTO, SprintMembersDTO
 * */
@Controller
@RequestMapping("/issue")
public class IssueController {

	/** IssueService의 메소드를 사용하기 위한 전역 변수로 변경 불가능 하도록 final로 선언 */
	private final IssueService issueService;
	
	@Autowired
	public IssueController(IssueService issueService) {
		
		this.issueService = issueService;
	}
	
	/**
	 * 메소드 selectIssueList에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 현재 session에 담겨져 있는 값을 사용하기 위한 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * */
	@GetMapping("list")
	public ModelAndView selectIssueList(ModelAndView mv, HttpServletRequest request) {
		
		int projectCode = Integer.parseInt(request.getParameter("code"));
		
		List<SprintDTO> sprintList = issueService.selectSprintList(projectCode);

		mv.addObject("sprintList", sprintList);
		mv.addObject("pjCode", projectCode);
		mv.setViewName("/issue/list");

		return mv;
	}
	
	/**
	 * 메소드 modifyIssueStatus에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception ModelAndView에 담아 보내줄 값의 자료형 변경 시 처리하는 예외, 이슈 상태 변경 실패 예외, 이슈 상태 변경 이력 생성 실패 예외, 이슈 상태 변경 알림 생성 실패 예외 처리
	 * */
	@GetMapping("modifyissuestatus")
	public ModelAndView modifyIssueStatus(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IssueModifyStatusException, JsonProcessingException, IssueRegistStatusHistoryException, IssueStatusModifyNoticeException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("issueCode"));
		String progress = request.getParameter("progress");
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		IssueDTO issue = new IssueDTO();
		issue.setCode(code);
		issue.setProgress(progress);
		issue.setName(member.getName());
		
		int result = issueService.updateIssueStatus(issue);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		mv.addObject("result", objectMapper.writeValueAsString(result));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 selectSprintIssueList에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception ModelAndView에 담아 보내줄 값의 자료형 변경 시 처리하는 예외
	 * */
	@GetMapping("issuelist")
	public ModelAndView selectSprintIssueList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("sprintCode"));
		
		List<IssueDTO> issueList = issueService.selectIssueList(code);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		
		mv.addObject("issueList", objectMapper.writeValueAsString(issueList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 selectSprintMembers에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception ModelAndView에 담아 보내줄 값의 자료형 변경 시 처리하는 예외
	 * */
	@PostMapping("sprintmemberlist")
	public ModelAndView selectSprintMembers(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("sprintCode"));

		List<SprintMembersDTO> sprintMemberList = issueService.selectSprintMembers(code);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		System.out.println(sprintMemberList);
		
		mv.addObject("sprintMemberList", objectMapper.writeValueAsString(sprintMemberList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 modifyIssue에 관한 문서화 주석
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param rttr redirect시 사용할 속성과 값을 담아 전달하기 위한 파라미터
	 * @ return view를 redirect 하기 위해 url과 어떤 project인지 확인하기 위한 project의 식별 번호를 함께 반환
	 * @ exception 이슈 담당자 변경 실패 예외, 이슈 내용 변경 실패 예외, 이슈 버전 히스토리 생성 실패 예외, 이슈 담당자 변경 이력 생성 실패, 이슈 수정 알림 생성 실패 예외 처리
	 * */
	@PostMapping("modify")
	public String modifyIssue(HttpServletRequest request, RedirectAttributes rttr) throws IssueModifyMemberException, IssueUpdateContentException, IssueInsertVersionHistoryException, IssueInsertMemberHistoryException, IssueModifyNoticeException {
		
		MemberDTO changeMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		String title = request.getParameter("issueModifyTitle");
		String body = request.getParameter("issueModifyBody");
		String[] memberInfo = request.getParameterValues("issueMemberBody");
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		int issueCode = Integer.parseInt(request.getParameter("issueCode"));
		
		List<IssueMembersDTO> issueMemberList = new ArrayList<>();
		
		for(int i = 0; i < memberInfo.length; i++) {
			
			IssueMembersDTO member = new IssueMembersDTO();
			
			String[] memberInfoSplit = memberInfo[i].split(" ");
			
			member.setId(memberInfoSplit[0]);
			member.setName(memberInfoSplit[1]);
			member.setNo(Integer.parseInt(memberInfoSplit[2]));
			member.setCode(issueCode);
			member.setChangeMemberNo(changeMember.getNo());
			
			issueMemberList.add(i, member);
			
		}
		
		IssueDTO modifyIssue = new IssueDTO();
		modifyIssue.setTitle(title);
		modifyIssue.setBody(body);
		modifyIssue.setCode(issueCode);
		modifyIssue.setIssueMemberList(issueMemberList);
		modifyIssue.setProjectCode(projectCode);
		modifyIssue.setSprintCode(sprintCode);
		modifyIssue.setWriter(changeMember.getNo());
		
		issueService.updateIssue(modifyIssue);
		
		rttr.addFlashAttribute("message", "이슈 수정 및 담당자 변경 성공!");
		
		return "redirect:/issue/list?code=" + projectCode;
	}
	
	/**
	 * 메소드 removeIssueMember에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값과 session에 담긴 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return 요청한 view페이지로 비동기 방식의 jsonView 반환
	 * @ exception 이슈 담당자 제외 실패 예외, 이슈 담당자 변경 이력 생성 실패 예외, 이슈 담당자 제외 알림 생성 실패 예외 처리
	 * */
	@PostMapping("removeissuemember")
	public ModelAndView removeIssueMember(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IssueRemoveMemberException, IssueInsertMemberHistoryException, IssueRemoveMemberNoticeException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		MemberDTO changeMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		String[] memberInfo = request.getParameter("info").split(" ");
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		int issueCode = Integer.parseInt(request.getParameter("issueCode"));
		
		IssueMembersDTO removeMember = new IssueMembersDTO();
		removeMember.setCode(issueCode);
		removeMember.setNo(Integer.parseInt(memberInfo[2]));
		removeMember.setSprintCode(sprintCode);
		removeMember.setParticipationYn("N");
		removeMember.setChangeMemberNo(changeMember.getNo());
		
		int result = issueService.deleteIssueMember(removeMember);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 removeIssue에 관한 문서화 주석
	 * @ param request 요청한 view에서 전달한 값과 session에 담긴 값을 사용하기 위한 파라미터
	 * @ param rttr redirect시 사용할 속성과 값을 담아 전달하기 위한 파라미터
	 * @ return view를 redirect 하기 위해 url과 어떤 project인지 확인하기 위한 project의 식별 번호를 함께 반환
	 * @ exception 이슈 삭제 실패 예외, 이슈 버전 히스토리 생성 실패 예외, 이슈 삭제 알림 생성 실패 예외 처리
	 * */
	@GetMapping("remove")
	public String removeIssue(HttpServletRequest request, RedirectAttributes rttr) throws IssueRemoveException, IssueInsertVersionHistoryException, IssueDeleteNoticeException {
		
		MemberDTO changeMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		int projectCode = issueService.deleteIssue(code, changeMember.getNo());
		
		rttr.addFlashAttribute("message", "이슈 삭제 성공!");
		
		return "redirect:/issue/list?code=" + projectCode;
	}
	
	/**
	 * 메소드 selectSprintMemberList에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception ModelAndView에 담아 보내줄 값의 자료형 변경 시 처리하는 예외
	 * */
	@PostMapping("selectsprintmemberlist")
	public ModelAndView selectSprintMemberList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		List<SprintMembersDTO> selectsprintMemberList = issueService.selectSprintMembersList(projectCode);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		mv.addObject("selectsprintMemberList", objectMapper.writeValueAsString(selectsprintMemberList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 registIssue에 관한 문서화 주석
	 * @ param request 요청한 view에서 전달한 값과 session에 담긴 값을 사용하기 위한 파라미터
	 * @ param rttr redirect시 사용할 속성과 값을 담아 전달하기 위한 파라미터
	 * @ return view를 redirect 하기 위해 url과 어떤 project인지 확인하기 위한 project의 식별 번호를 함께 반환
	 * @ exception 이슈 담당자 지정 실패 예외, 이슈 생성 실패 예외, 이슈 버전 히스토리 생성 실패 예외, 이슈 상태 변경 이력 생성 실패 예외, 이슈 생성 알림 생성 실패 예외, 이슈 담당자 변경 이력 생성 실패 예외 처리
	 * */
	@PostMapping("regist")
	public String registIssue(HttpServletRequest request, RedirectAttributes rttr) throws IssueRegistMemberException, IssueRegistException, IssueInsertVersionHistoryException, IssueRegistStatusHistoryException, IssueRegistNoticeException, IssueInsertMemberHistoryException {
		
		MemberDTO writer = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		String title = request.getParameter("issueCreateTitle");
		String body = request.getParameter("issueCreateBody");
		String[] memberInfo = request.getParameterValues("issueMemberBody");
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		
		List<IssueMembersDTO> issueMemberList = new ArrayList<>();
		
		for(int i = 0; i < memberInfo.length; i++) {
			
			IssueMembersDTO member = new IssueMembersDTO();
			
			String[] memberInfoSplit = memberInfo[i].split(" ");
			
			member.setId(memberInfoSplit[0]);
			member.setName(memberInfoSplit[1]);
			member.setNo(Integer.parseInt(memberInfoSplit[2]));
			member.setChangeMemberNo(writer.getNo());
			member.setSprintCode(sprintCode);
			
			issueMemberList.add(i, member);
			
		}
		
		IssueDTO registIssue = new IssueDTO();
		registIssue.setTitle(title);
		registIssue.setBody(body);
		registIssue.setIssueMemberList(issueMemberList);
		registIssue.setProjectCode(projectCode);
		registIssue.setSprintCode(sprintCode);
		registIssue.setWriter(writer.getNo());
		registIssue.setName(writer.getName());
		
		System.out.println(registIssue);
		
		issueService.insertIssue(registIssue);
		
		rttr.addFlashAttribute("message", "이슈 생성 성공!");
		
		return "redirect:/sprint/list?code=" + projectCode;
	}

	
}
