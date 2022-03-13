package com.greedy.byat.mytask.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;
import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.task.model.dto.TaskDTO;
import com.greedy.byat.mytask.model.service.MyTaskService;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

/** 
* <pre>
* Class : MyTaskController
* Comment : MyTask관련 메소드를 모아놓은 controller
* History
* 2022/02/17  (박수빈) 초기 생성 세팅
* 2022/02/19  (박수빈) selectMytask() 마이태스크 프로젝트 목록 조회 요청후 생성요청 후 작성
* 2022/02/21  (박수빈) selectMytask() 마이태스크  태스크 조회, ToDoList 조회 요청후 생성 
* 2022/02/22  (박수빈) registToDoList() ToDoList 생성요청 작성, removeToDoList() ToDoList 삭제요청 작성, modifyToDoList() ToDoList 수정요청 작성
* 2022/02/23  (박수빈) modifyToDoListStatus() 상태 변경요청 작성
* 2022/02/24  (박수빈) selectMytask() 프로젝트 구성원 상세 조회 요청 작성
* 2022/02/26  (박수빈) selectMytask() 마이태스크  태스크 조회 요청 작성
* </pre>
* @version 1
* @author 박수빈 
* @see MyTaskDTO, MyTaskDTO, ToDoListDTO, TaskDTO, MyTaskMapper, MyTaskService, MyTaskServiceImple
*/
@Controller
@RequestMapping("/mytask")
public class MyTaskController {

	private final MyTaskService mytaskService;

	@Autowired
	public MyTaskController(MyTaskService mytaskService) {

		this.mytaskService = mytaskService;
	}

	
	/**
	 * 메소드 selectMytask에 관한 문서화 주석
	 * @param request 뷰에서  Session에 담겨있는 MemberDTO 값을 받아오기 위해 사용
	 * @param model Model을 통하여 view에게 MyTaskDTO값을 넘겨주기위해 사용
	 * @return : /mytask/mytask URL로 이동
	 */
	@GetMapping("/list")
	public String selectMytask(HttpServletRequest request, Model model) {

		/* 프로젝트 상태 변수*/
		int progressIng = 0;
		int progressDone = 0;
		int progressNotDone = 0;
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		MyTaskDTO mytask = new MyTaskDTO();
		
		mytask = mytaskService.selectmyTaskList(member);
		
		/* 프로젝트 상태 그래프의 개수를 저장한다.*/
		for(int i = 0; i < mytask.getProjectDTO().size(); i++) {
			
			if(mytask.getProjectDTO().get(i).getProgress().equals("진행중")) {
				progressIng++;
			} else if (mytask.getProjectDTO().get(i).getProgress().equals("완료")) {
	            progressDone++;
	        } else {
	            progressNotDone++;
	        }
		}
		
		int[] projectProgressArr = { progressIng, progressDone, progressNotDone };
		
		// 모델을 통해서 값을 넘긴다
		model.addAttribute("projectProgress", projectProgressArr);
		model.addAttribute("projectList", mytask.getProjectDTO());
		model.addAttribute("todoList", mytask.getTodolistDTO());
		model.addAttribute("taskList", mytask.getTaskDTO());

		return "/mytask/mytask";
	}
	
	/**
	 * 메소드 selectTaskListForProject에 관한 문서화 주석
	 * @param projectCode 클릭한 프로젝트 번호입니다.
	 * @param request view 에서 MemberDTO의 값을  가져오기 위해 사용합니다.
	 * @param model projectCode 뷰로 넘겨주기 위해 사용합니다.
	 * @return : gson을통해서  List<TaskDTO> taskList의 값을 view로 넘겨준다.
	 */
	@GetMapping(value="/selecttasklistforproject", produces="application/json; charset=UTF-8")
	@ResponseBody 
	public String selectTaskListForProject(int projectCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		
		MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		List<TaskDTO> taskList = mytaskService.selectTaskListForProject(member.getNo(), projectCode);

		model.addAttribute("code", projectCode);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();

		return gson.toJson(taskList); 
	}
	
	/**
	 * 메소드 registToDoList에 관한 문서화 주석
	 * @param request : view 에서 MemberDTO의 값을 가져오기 위해 사용합니다.
	 * @return : redirect을 통해서 mytask/list URL로 이동합니다.
	 */
	@GetMapping("/regist")
	public String registToDoList(HttpServletRequest request) {
		
		int memberNumber = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();

		int result = mytaskService.registToDoList(memberNumber);
		
		return "redirect:/mytask/list";
	}

	/**
	 * 메소드 removeToDoList에 관한 문서화 주석
	 * @param toDoListNo : 삭제할 ToDoList 번호
	 * @return : 결과값 확인용
	 */
	@GetMapping(value = "/remove", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int removeToDoList(int toDoListNo, HttpServletResponse response) {

		System.out.println("gg");

		int result = mytaskService.removeToDoList(toDoListNo);

		return result;
	}
	
	/**
     * 메소드 modifyToDoList에 관한 문서화 주석
	 * @param toDoListNo : 삭제할 ToDoListno 번호
	 * @return : 결과값 확인용
	 */
	@PostMapping(value = "/modify", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int modifyToDoList(@ModelAttribute ToDoListDTO todoList) {

		int result= mytaskService.modifyToDoList(todoList);
		
		System.out.println(result + "modify");
		return result;
	}
	
	/**
     * 메소드 modifyToDoListStatus에 관한 문서화 주석
	 * @param ToDoListDTO : 수정할 todoListDTO
	 * @return : 결과값 확인용
	 */
	@GetMapping(value="/modifytodoliststatus", produces="application/json; charset=UTF-8")
	@ResponseBody 
	public int modifyToDoListStatus(@ModelAttribute ToDoListDTO todoList) {
		System.out.println("modifyTODoListStatus : " + todoList);

		int result = mytaskService.modifyToDoListStatus(todoList);
		
		return result;
	}
	/**
     * 메소드 selectMembersModal에 관한 문서화 주석
	 * @param projectCode : 구성원 을 조회하기 위해 프로젝트 코드
	 * @return : projectMembers : 조회한 프로젝트 구성원 정
	 */
    @PostMapping("/selectmembermodal")
    @ResponseBody
    public List<ProjectMembersDTO> selectMembersModal(int projectCode){
    	
        List<ProjectMembersDTO> projectMembers = mytaskService.selectMemberModal(projectCode);
        
        return projectMembers;
    }
}














