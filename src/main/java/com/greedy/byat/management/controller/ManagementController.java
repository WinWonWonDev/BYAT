package com.greedy.byat.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.management.model.service.ManagementService;
import com.greedy.byat.member.model.dto.MemberDTO;

/* 
* <pre>
* Class : ManagementController
* Comment : management관련 메소드들을 모아놓기 위한 Controller입니다.
* History
* 2022/02/24 이소현  처음 작성
* </pre>
* @version 1.1.0
* @author 이소현
* @see ManagementService, ManagementServiceImpl, ManagementDTO, PermitDTO, ManagementMapper  
*
*/
@Controller
@RequestMapping("/management")
public class ManagementController {

	private final ManagementService managementService;
	
	@Autowired
	public ManagementController(ManagementService managementService) {
		
		this.managementService = managementService;
	}
	
   /**
    * 메소드 selectManagementList에 관한 문서화 주석
    * @ param ModelAndView : addObject를 통해 managementList값을 담고, 그 담은 값을 setViewName을 통해 management.jsp로 보내주기 위한 파라미터입니다.
    * @ return : view에서 사용해야하는 값에 managementList를 담아 mv를 반환합니다.
    */
	@GetMapping("list")
	public ModelAndView selectManagementList(ModelAndView mv) {
		
		List<ManagementDTO> managementList = managementService.selectManagementList();

		mv.addObject("managementList", managementList);
		mv.setViewName("/management/management");
		
		return mv;
	}
	
   /**
    * 메소드 registManagement에 관한 문서화 주석
    * @ param MemberDTO member : jsp에서 넘기는 멤버 이름,사번  MemberDTO형식에 담아 전달하기 위한 파라미터입니다. 
    * @ param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ param String managementRoleforCreate : 멤버 권한의 값을 String으로 담은 파라미터입니다.
    * @ return : /management/list로 재요청하기 위한 return 입니다.
    */
	@PostMapping("regist")
	public String registManagement(MemberDTO member, RedirectAttributes rttr, String managementRoleforCreate) {
			
		int result = managementService.registManagement(member, managementRoleforCreate);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "회원 계정 등록 성공!");
		} else {
			rttr.addFlashAttribute("message", "회원 계정 등록 실패! 다시 시도해주세요!");
		}
		
		return "redirect:/management/list";
		
	}
	
   /**
    * 메소드 modifyManagement에 관한 문서화 주석
    * @ param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ param ManagementDTO manegement : jsp에서 넘기는 멤버 이름,사번, 권한을  ManagementDTO형식에 담아 전달하기 위한 파라미터입니다. 
    * @ return : /management/list로 재요청하기 위한 return 입니다.
    */
	@PostMapping("modify")
	public String modifyManagement(RedirectAttributes rttr, ManagementDTO management) {

		int result = managementService.modifyManagement(management);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "회원 계정 수정 성공!");
		} else {
			rttr.addFlashAttribute("message", "회원 계정 수정 실패! 다시 시도해주세요!");
		}
		
		return "redirect:/management/list";
	}

   /**
    * 메소드 removeManagement에 관한 문서화 주석
    * @ param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ param HttpServletRequest request : 멤버 넘버를 jsp에서 getParameter로 가져오기 위한 파라미터입니다. 
    * @ return : /management/list로 재요청하기 위한 return 입니다.
    */
	@GetMapping("remove") 
	public String removeManagement(RedirectAttributes rttr, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = managementService.removeManagement(no);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "회원 계정 삭제 성공!");
		} else {
			rttr.addFlashAttribute("message", "회원 계정 삭제 실패! 담당중인 프로젝트가 있는 것 같습니다. 조치 후 다시 시도해주세요!");
		}
		
		return "redirect:/management/list";
		
	}
	
   /**
    * 메소드 selectManagementRemovedList에 관한 문서화 주석
    * @ param ModelAndView : addObject를 통해 deletedManagementList의 값을 담고, 그 담은 값을 setViewName을 통해 deletedMangementList.jsp로 보내주기 위한 파라미터입니다.
    * @ return : view에서 사용해야하는 값에 deletedManagementList를 담아 mv를 반환합니다.
    */
	@GetMapping("removedlist")
	public ModelAndView selectManagementRemovedList(ModelAndView mv) {
		
		List<ManagementDTO> deletedManagementList = managementService.selectManagementRemovedList();
		
		mv.addObject("deletedManagementList", deletedManagementList );
		mv.setViewName("/management/deletedManagementList");
		
		return mv; 
	}
	
   /**
    * 메소드 restoreManagement에 관한 문서화 주석
    * @ param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ param HttpServletRequest request : 멤버 넘버를 jsp에서 getParameter로 가져오기 위한 파라미터입니다. 
    * @ return : /management/removedlist로 재요청하기 위한 return 입니다.
    */
	@GetMapping("restore")
	public String restoreManagement(RedirectAttributes rttr, HttpServletRequest request) {
	
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = managementService.restoreManagement(no);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "회원 계정 복구 성공!");
		} else {
			rttr.addFlashAttribute("message", "회원계정 복구 실패! 다시 시도해주세요!");
		}
		
		return "redirect:/management/removedlist";
	}

}
