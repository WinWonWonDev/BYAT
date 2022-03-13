package com.greedy.byat.project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.common.exception.notice.NoticeInsertException;
import com.greedy.byat.common.exception.project.CalendatRegistProjectScheduleException;
import com.greedy.byat.common.exception.project.ProjectMemberHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectMemberModifyRoleException;
import com.greedy.byat.common.exception.project.ProjectMemberRemoveException;
import com.greedy.byat.common.exception.project.ProjectModifyCalendarException;
import com.greedy.byat.common.exception.project.ProjectModifyException;
import com.greedy.byat.common.exception.project.ProjectProgressHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistMemberException;
import com.greedy.byat.common.exception.project.ProjectRemoveException;
import com.greedy.byat.common.exception.project.ProjectVersionHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectWriterChangeException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.project.model.service.ProjectService;
import com.greedy.byat.sprint.model.dto.SprintDTO;



/**
 * <pre>
 * Class : ProjectController
 * Comment : Project 관련 메소드를 모아놓은 Controller입니다.
 * History
 * 2022/02/17 (박인근) 처음 작성
 * 2022/02/18 (박인근) 프로젝트 목록조회, 생성 관련 메소드 추가 
 * 2022/02/19 (박인근) 프로젝트 조회, 수정 관련 메소드 추가
 * 2022/02/20 (박인근) 프로젝트 조회, 수정 관련 메소드 수정
 * 2022/02/21 (박인근) 프로젝트 구성원 추가 관련 메소드 추가
 * 2022/02/22 (박인근) 프로젝트 구성원 목록 조회 관련 메소드 추가
 * 2022/02/23 (박인근) 프로젝트 구성원 추가 취소 메소드, 구성원 제외 관련 메소드 추가
 * 2022/02/24 (박인근) 프로젝트 구성원 추가 시 검색 오류 코드 수정
 * 2022/02/25 (박인근) 프로젝트 버전 히스토리, 변경 이력 관련 코드 추가
 * 2022/02/28 (박인근) 프로젝트 일정 생성 관련 코드 추가
 * 2022/03/03 (박인근) 프로젝트 구성원 추가시 탈퇴 여부 체크, 구성원 제외 시 진행중이 스프린트 여부 체크 관련 코드 추가
 * 2022/03/04 (박인근) 프로젝트 진행상태 자동 변경 코드 추가
 * 2022/03/05 (박인근) 프로젝트 생성 알림 관련 코드 추가
 * </pre>
 * @version 13
 * @author 박인근
 * see ProjectDTO, ProjectMembersDTO, ProjectService, ProjectServiceImpl, ProjectMapper.java, ProjectMapper.xml, NoticeDTO, MemberDTO, RoleDTO
 * */
@Controller
@RequestMapping("/project")
public class ProjectController {

	/** ProjectService의 메소드를 사용하기 위한 전역 변수로 변경 불가능 하도록 final로 선언 */
	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		
		this.projectService = projectService;
	}
	
	/**
	 * 메소드 selectProjectList에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 현재 session에 담겨져 있는 값을 사용하기 위한 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception 날짜에 따라 프로젝트의 진행도가 바뀌기 때문에 상태 변경 이력 테이블에 값을 넣을 때 처리하는 예외
	 * */
	@GetMapping("/list")
	public ModelAndView selectProjectList(ModelAndView mv, HttpServletRequest request) throws ProjectProgressHistoryRegistException {
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		List<ProjectDTO> projectList = projectService.selectProjectList(member);

		List<Integer> PmMemberNumber = new ArrayList<>();
		
		for(int i = 0; i < projectList.size(); i++) {
			
			int writerLength = projectList.get(i).getWriter().length();
			
			projectList.get(i).setWriter(projectList.get(i).getWriter().substring(writerLength - 2, writerLength - 0));
			
			for(int j = 0; j < projectList.get(i).getProjectMembers().size(); j++) {

				int nameLength = projectList.get(i).getProjectMembers().get(j).getName().length();
				
				projectList.get(i).getProjectMembers().get(j).setName(projectList.get(i).getProjectMembers().get(j).getName().substring(nameLength - 2, nameLength - 0));
				
				if("PM".equals(projectList.get(i).getProjectMembers().get(j).getRoleName())) {
					
					PmMemberNumber.add(projectList.get(i).getProjectMembers().get(j).getNo());
				}
				
				
			}
			
		}

		mv.addObject("projectList", projectList);
		mv.addObject("PmMemberNumber", PmMemberNumber);
		mv.setViewName("/project/list");
		
		return mv;
	}
	
	/**
	 * 메소드 registProject에 관한 문서화 주석
	 * @ param project 전달받은 ProjectDTO 값을 사용하기 위한 파라미터
	 * @ param request 현재 session에 담겨져 있는 값을 사용하기 위한 파라미터
	 * @ param rttr redirect시 사용할 속성과 값을 담아 전달하기 위한 파라미터
	 * @ return view를 redirect 하기 위해 url과 함께 반환
	 * @ exception 프로젝트 생성에 따라 생성 실패 예외, 버전 히스토리 생성 실패 예외, 상태 변경 이력 생성 실패 예외, 멤버 변경 이력 생성 실패 예외, 멤버 일정 생성 실패 예외, 멤버 알림 생성 실패 예외시 처리하는 예외
	 * */
	@PostMapping("/regist")
	public String registProject(@ModelAttribute ProjectDTO project, HttpServletRequest request, RedirectAttributes rttr) throws ProjectRegistException, ProjectVersionHistoryRegistException, ProjectProgressHistoryRegistException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException, NoticeInsertException {
		
		String memberName = ((MemberDTO) request.getSession().getAttribute("loginMember")).getName();
		
		project.setWriterMember((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		project.setWriter(memberName);

		projectService.insertProject(project);
		
		rttr.addFlashAttribute("message", "프로젝트 생성 성공!");
		
		return "redirect:/project/list";
	}
	
	/**
	 * 메소드 removeProject에 관한 문서화 주석
	 * @ param request 현재 session에 담겨져 있는 값을 사용하기 위한 파라미터
	 * @ param rttr redirect시 사용할 속성과 값을 담아 전달하기 위한 파라미터
	 * @ return view를 redirect 하기 위해 url과 함께 반환
	 * @ exception 프로젝트 삭제 실패 예외, 프로젝트 버전 히스토리 생성 실패 예외, 멤버 알림 생성 실패 예외시 처리하는 예외
	 * */
	@GetMapping("/remove")
	public String removeProject(HttpServletRequest request, RedirectAttributes rttr) throws ProjectRemoveException, ProjectVersionHistoryRegistException, NoticeInsertException {
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		projectService.deleteProject(code, member);
		
		rttr.addFlashAttribute("message", "프로젝트 삭제 성공!");
		
		return "redirect:/project/list";
	}
	
	/**
	 * 메소드 selectProjectDetail에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 현재 session에 담겨져 있는 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception ModelAndView에 담아 보내줄 값의 자료형 변경 시 처리하는 예외
	 * */
	@GetMapping("/detail")
	public ModelAndView selectProjectDetail(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		ProjectDTO projectDetail = projectService.selectProjectDetail(code);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		
		mv.addObject("projectDetail", objectMapper.writeValueAsString(projectDetail));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 modifyProject에 관한 문서화 주석
	 * @ param project 전달받은 값들을 projectDTO에 담아 사용하기 위해 사용하는 파라미터
	 * @ param request 현재 session에 담겨져 있는 값을 사용하기 위한 파라미터
	 * @ param rttr redirect시 사용할 속성과 값을 담아 전달하기 위한 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception 프로젝트 수정 실패 예외, 프로젝트 버전 히스토리 생성 실패 예외, 멤버 알림 생성 실패 예외, 멤버 일정 변경 실패 예외시 처리하는 예외
	 * */
	@PostMapping("/modify")
	public String modifyProject(@ModelAttribute ProjectDTO project, HttpServletRequest request, RedirectAttributes rttr) throws ProjectModifyException, ProjectVersionHistoryRegistException, NoticeInsertException, ProjectModifyCalendarException {

		int code = Integer.parseInt(request.getParameter("code"));
		
		project.setCode(code);
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		projectService.updateProject(project, member);
		
		rttr.addFlashAttribute("message", "프로젝트 수정 성공!");
		
		return "redirect:/project/list";
	}
	
	/**
	 * 메소드 searchMembers에 관한 문서화 주석
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ return 비동기 방식으로 멤버의 정보를 담은 List를 반환
	 * */
	@RequestMapping(value="/searchmembers", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	@ResponseBody
	public String searchMembers(HttpServletRequest request) {
		
		String searchMember = request.getParameter("searchValue");
		
		String[] selectMembers = null;
		
		String[] projectMembersList = request.getParameterValues("projectMembersList");
		
		if(request.getParameter("selectMembers") != null) {
			
			selectMembers = request.getParameterValues("selectMembers");
			
		}
		
		List<MemberDTO> memberList = projectService.searchAddMemberList(searchMember, projectMembersList, selectMembers);
		
		Gson gson = new Gson();
		
		return gson.toJson(memberList);
	}
	
	/**
	 * 메소드 registProjectMembers에 관한 문서화 주석
	 * @ param registMember view에서 전달한 값을 담아 사용하기 위한 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param rttr redirect시 사용할 속성과 값을 담아 전달하기 위한 파라미터
	 * @ return view를 redirect 하기 위해 url과 함께 반환
	 * @ exception 프로젝트의 구성원 추가 실패 예외, 프로젝트 구성원 변경 이력 생성 실패 예외, 프로젝트 구성원 일정 생성 실패 예외
	 * */
	@PostMapping("/registmember")
	public String registProjectMembers(@ModelAttribute ProjectMembersDTO registMember, HttpServletRequest request, RedirectAttributes rttr) throws ProjectRegistMemberException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException {
		
		String[] projectCode = request.getParameterValues("code");
		String[] memberNo = request.getParameterValues("no");
		String[] memberRole = request.getParameterValues("role");
		String[] name = request.getParameterValues("name");
		
		int code = Integer.parseInt(projectCode[0]);
		
		String projectTitle = "";
		
		for(int i = 0; i < memberNo.length; i++) {
		
			registMember.setCode(code);
			registMember.setNo(Integer.parseInt(memberNo[i]));
			registMember.setRoleName(memberRole[i]);
			registMember.setName(name[i]);
			
			projectTitle = projectService.insertProjectMember(registMember);
			
		}
		
		rttr.addFlashAttribute("message", projectTitle + " 프로젝트에 멤버 추가 성공!");
		
		return "redirect:/project/list";
	}
	
	/**
	 * 메소드 projectMemberList에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception ModelAndView에 담아 보내줄 값의 자료형 변경 시 처리하는 예외
	 * */
	@GetMapping("/projectmemberlist")
	public ModelAndView projectMemberList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		List<ProjectMembersDTO> projectMemberList = projectService.selectProjectMemberList(code);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		mv.addObject("memberList", objectMapper.writeValueAsString(projectMemberList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 removeProjectMembers에 관한 문서화 주석
	 * @ param mv 반환하는 url에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return 요청한 view페이지로 비동기 방식의 jsonView 반환
	 * @ exception 프로젝트의 구성원 제외 실패 예외, 프로젝트 구성원 변경 이력 생성 실패 예외
	 * */
	@GetMapping("/removemember")
	public ModelAndView removeProjectMembers(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws ProjectMemberRemoveException, ProjectMemberHistoryRegistException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("code"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		ProjectMembersDTO removeMember = new ProjectMembersDTO();
		removeMember.setCode(code);
		removeMember.setNo(no);
		
		projectService.deleteProjectMembers(removeMember);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 modifyMemberRole에 관한 문서화 주석
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param rttr redirect시 사용할 속성과 값을 담아 전달하기 위한 파라미터
	 * @ return view를 redirect 하기 위해 url과 함께 반환
	 * @ exception 프로젝트 구성원의 역할 변경 실패 예외, 프로젝트의 PM 변경 실패 예외, 프로젝트의 구성원 변경 이력 생성 실패 예외
	 * */
	@PostMapping("/modifyrole")
	public String modifyMemberRole(HttpServletRequest request, RedirectAttributes rttr) throws ProjectMemberModifyRoleException, ProjectWriterChangeException, ProjectMemberHistoryRegistException {
		
		String[] no = request.getParameterValues("no");
		String[] roleName = request.getParameterValues("roleName");
		String[] code = request.getParameterValues("code");
		String[] name = request.getParameterValues("name");
		
		List<ProjectMembersDTO> members = new ArrayList<>();
		
		for(int i = 0; i < no.length; i++) {
			
			System.out.println("name : " + name[i]);
			
			ProjectMembersDTO member = new ProjectMembersDTO();
			
			member.setCode(Integer.parseInt(code[i]));
			member.setNo(Integer.parseInt(no[i]));
			member.setRoleName(roleName[i]);
			member.setName(name[i]);
			members.add(i, member);
		}
		
		
		projectService.updateProjectMemberRole(members);
		
		rttr.addFlashAttribute("message", "구성원 정보 수정 성공!");
		
		return "redirect:/project/list";
	}
	
	/**
	 * 메소드 selectSprintMember에 관한 문서화 주석
	 * @ param mv view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception ModelAndView에 담아 보내줄 값의 자료형 변경 시 처리하는 예외
	 * */
	@PostMapping("/selectsprintmember")
	public ModelAndView selectSprintMember(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int code = Integer.parseInt(request.getParameter("code"));
		int no = Integer.parseInt(request.getParameter("no"));

		int count = projectService.selectSprintProceedingCount(code, no);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		mv.addObject("count", objectMapper.writeValueAsString(count));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
}
