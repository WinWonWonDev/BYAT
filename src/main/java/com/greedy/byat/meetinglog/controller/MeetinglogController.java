package com.greedy.byat.meetinglog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;
import com.greedy.byat.meetinglog.model.service.MeetinglogService;
import com.greedy.byat.member.model.dto.MemberDTO;
/** 
* <pre>
* Class : MeetinglogController
* Comment : 회의록  컨트롤러
* History
* 2022/02/17  (박수빈) 초기 생성 세팅
* 2022/03/02  (박수빈) selectMeetinglogList() 회의록 목록 조회 , 회의록 상세조회, 회의록 수정
* 2022/03/03  (박수빈) 회의록 상세조회, 회의록 수정
* 2022/03/04  (박수빈) 회의록 상세 조회, 회의록 수정
* 2022/03/05  (박수빈) 회의록 상세 조회, 회의록 수정
* 2022/03/06  (박수빈) 회의록 수정,삭제
* 2022/03/07  (박수빈) 회의록 수정,삭제
* 2022/03/08  (박수빈) 회의록 수정 ,삭제
* 2022/03/09  (박수빈) 회의록 수정,삭제
* 2022/03/10  (박수빈) 알림 생성 수정 삭제
* </pre>
* @version 1.0.0
* @author 박수빈(최초 작성자 명)
* @see MeetinglogMapper, MeetinglogDTO, MeetinglogService, MeetinglogServiceImpl
*
*/
@Controller
@RequestMapping("/meetinglog")
public class MeetinglogController {

	private final MeetinglogService meetinglogService;
	
	@Autowired
	public MeetinglogController(MeetinglogService meetinglogService) {
		
		this.meetinglogService = meetinglogService;
	}
	
	
	/**
	 * 메소드 selectMytask에 관한 문서화 주석
     * 회의록 목록 조회 요청 매서드입니다..
	 * @param request : 프로젝트 코드 변수를 가져오기 위함입니다.
	 * @param model : 뷰에  조회한 프로젝트 코드. 프로젝트 제목, 회의록 배열의값을 넘겨줍니다.
	 * @return : /meetinglog/list.jsp 주소로 이동하기 위해 값을 넘겨줍니다.
	 * @author 박수빈
	 */
	@GetMapping("/list") 
	  public String selectMytask(HttpServletRequest request, Model model) {
		  
		  int projectCode = Integer.parseInt(request.getParameter("code"));

		  String projectName = meetinglogService.selectProjectName(projectCode);
		  
		  List<MeetinglogDTO> meetinglogList = meetinglogService.selectMeetinglogList(projectCode);
				  
		  model.addAttribute("code", projectCode);
		  model.addAttribute("projectName", projectName);
		  model.addAttribute("meetinglogList", meetinglogList);
		  
		  System.out.println(meetinglogList+" : meetinglogList selectMytask");
		  
		  return "/meetinglog/list";
	  }


	/**
     * 메소드 selectMeetinglogDetail에 관한 문서화 주석
     * 회의록 상세 조회 요청 매서드입니다.
	 * @param request : 프로젝트 코드 변수를 가져오기 위함입니다.
	 * @return meetinglog : 클릭한 회의록의 정보 MeetinglogDTO
	 * @author 박수빈
	 */
	  @PostMapping(value="/detail", produces="application/json; charset=UTF-8" )
	  @ResponseBody
	  public String selectMeetinglogDetail(HttpServletRequest request) {

		int meetinglogCode = Integer.parseInt(request.getParameter("meetinglogCode"));
		System.out.println("[selectMeetinglogDetail] meetinglogCode : " + meetinglogCode);
		
		MeetinglogDTO meetinglog = meetinglogService.selectMeetinglogDetail(meetinglogCode);
		  
		System.out.println(meetinglog+ "dgdgdg");
	  
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
			
		return gson.toJson(meetinglog);
	  }
	  
	  
	  /**
       * 메소드 registMeetinglog에 관한 문서화 주석
       * 회의록 생성 요청 매서드 입니다.
       * @param request : 뷰에서  Session에 담겨있는 MemberDTO, projectCode, title, body 값을 받아오기 위해 사용합니다.
       * @param model : Model을 통하여 뷰에게 projectCode 값을 넘겨주기위해 사용합니다. 
       * @param rttr : 1회성으로  message값을 전해주기위해 사용합니다.
       * @return : /meetinglog/list?code="+projectCode; 주소로 이동하기 위해 값을 넘겨줍니다. 
       */
  	  @PostMapping("/regist") 
	  public String registMeetinglog(HttpServletRequest request, Model model, RedirectAttributes rttr) {
		
		  System.out.println("regist");
		  
		  java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
 
		  MeetinglogDTO meetinglog = new MeetinglogDTO();
		  MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		  
		  int projectCode = Integer.parseInt(request.getParameter("code"));
		  String title = request.getParameter("meetingLogTitle");
		  String body = request.getParameter("meetingLogDescription");
		  
		  meetinglog.setWritingDate(sqlDate);
		  meetinglog.setDeleteStatus("N");
		  meetinglog.setVersion('1');
		  meetinglog.setTitle(title);
		  meetinglog.setBody(body);
		  meetinglog.setProjectCode(projectCode);
		  meetinglog.setMemberNo(member.getNo());
		  meetinglog.setMemberName(member.getName());
		  
		  System.out.println("regist:meetinglog"  +meetinglog);
		  String message = meetinglogService.registMeetinglog(meetinglog);

		  
		  model.addAttribute("code", projectCode);
		  
		  rttr.addFlashAttribute("message",message);
		  
		  return "redirect:/meetinglog/list?code="+projectCode; 
		  
	  }
	  /**
       * 메소드 modifyMeetinglog에 관한 문서화 주석
       * 회의록 수정 요청 매서드 입니다.
       * @param request : 뷰에서  Session에 담겨있는   값을 받아오기 위해 사용합니다.
       * @param model : Model을 통하여 뷰에게 projectCode 값을 넘겨주기위해 사용합니다. 
       * @param rttr : 1회성으로  message값을 전해주기위해 사용합니다.
       * @return : /meetinglog/list?code="+projectCode; 주소로 이동하기 위해 값을 넘겨줍니다. 
       */
	  @PostMapping("/modify")
	  public String modifyMeetinglog(HttpServletRequest request, Model model, RedirectAttributes rttr) {
		
		  MeetinglogDTO meetinglog = new MeetinglogDTO();
		  MemberDTO member =  ((MemberDTO) request.getSession().getAttribute("loginMember"));

		  
		  int projectCode = Integer.parseInt(request.getParameter("code"));
		  
		  String title = request.getParameter("meetingLogDetailTitle");
		  String body = request.getParameter("meetingLogDetailBody");
		  int code = Integer.parseInt(request.getParameter("meetingLogDetailCode"));
		  
		  meetinglog.setBody(body);
		  meetinglog.setCode(code);
		  meetinglog.setTitle(title);
		  meetinglog.setProjectCode(projectCode);
		  meetinglog.setMemberNo(member.getNo());
		  meetinglog.setMemberName(member.getName());
		  System.out.println("modify meetinglog : " + meetinglog);
		  
		  String message= meetinglogService.modifyMeetinglog(meetinglog);
		  
		  System.out.println("modify meetinglog :" + meetinglog);
		  
		  model.addAttribute("code", projectCode);
		  
		  rttr.addFlashAttribute("message",message);
		  
		  return "redirect:/meetinglog/list?code=" + projectCode; 
		  
	  }
	  /**
       * 메소드 removeMeetinglog에 관한 문서화 주석
       * 회의록 삭제 요청 매서드 입니다.
       * @param request : 뷰에서  Session에 담겨있는  값을 받아오기 위해 사용합니다.
       * @param model : Model을 통하여 뷰에게 값을 넘겨주기위해 사용합니다. 
       * @param rttr : 1회성으로  message값을 전해주기위해 사용합니다.
       * @return : /meetinglog/list?code="+projectCode; 주소로 이동하기 위해 값을 넘겨줍니다. 
       */
	  @PostMapping("/remove")
	  public String removeMeetinglog(HttpServletRequest request, Model model, RedirectAttributes rttr) {
		 
		  int projectCode = Integer.parseInt(request.getParameter("code"));
		  
		  int meetingCode = Integer.parseInt(request.getParameter("meetingCode"));

		  String message  = meetinglogService.removeMeetinglog(meetingCode);
		
		  model.addAttribute("code", projectCode);
		  
		  rttr.addFlashAttribute("message",message);
		  
		  return "redirect:/meetinglog/list?code=" + projectCode; 
		  
	  }
		  
}
