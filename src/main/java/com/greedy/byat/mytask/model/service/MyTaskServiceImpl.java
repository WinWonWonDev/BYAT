package com.greedy.byat.mytask.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dao.MyTaskMapper;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;
import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.task.model.dto.TaskDTO;


/**
 * <pre>
 * Class : MyTaskServiceImpl
 * Comment : MyTaskController의 요청을 처리하는 Interface
 * History
 * 2021/02/17 (박수빈) 처음 작성
 * </pre>
 * @version 1.0.0
 * @author 박수빈
 * @see MyTaskMapper, MyTaskServiceImpl, MyTaskService
 * */
@Service
public class MyTaskServiceImpl implements MyTaskService {
 
	private final MyTaskMapper mapper;		//MyTaskMaaper를 사용하기위한 전역변수

	@Autowired
	public MyTaskServiceImpl(MyTaskMapper mapper) {
		this.mapper = mapper;
	}
	

	/**
	 * 메소드 selectmyTaskList에 관한 문서화 주석
	 * MyTask 정보를 조회한다.
	 * @param member : 로그인한 사용자의 정보 
	 * @return mytask : MyTaskDTO정보 리턴
	 * 
	 * @author 박수빈
	 */
	@Override
	public MyTaskDTO selectmyTaskList(MemberDTO member) {
		
		System.out.println("selectmyTaskList MemberDTO: " + member);
		
		MyTaskDTO myTask= new MyTaskDTO();
		List<ProjectMembersDTO> projectMembers = new ArrayList<>();
		
		List<ProjectDTO> projectList = mapper.selectMyTaskProjectList(member.getNo());
		List<ToDoListDTO> toDoList = mapper.selectMyTaskToDoList(member.getNo());
		List<TaskDTO>taskList= mapper.selectTaskList(member.getNo());

        System.out.println("서비스자식클래스"+toDoList);

	    for(int i=0; i < projectList .size(); i++) {
			projectList.get(i).setWriter(projectList.get(i).getWriter().substring(1, 3));
			
			projectMembers = mapper.selectMyTaskProjectMembers(projectList.get(i).getCode());
			
			for(int j = 0; j < projectMembers.size(); j++) {
		         projectMembers.get(j).setName(projectMembers.get(j).getName().substring(1, 3));
		      }
			
			projectList.get(i).setProjectMembers(projectMembers);
			
		}
		myTask.setProjectDTO(projectList);
		myTask.setTodolistDTO(toDoList);
		myTask.setTaskDTO(taskList);

		return myTask;
	}
	

	/**
	 * 메소드 registToDoList에 관한 문서화 주석
	 * @param memberNumber: 로그인 한 멤버 고유 번호
	 * @return 결과 확인용
	 * 
	 * @author 박수빈
	 */
	@Override
	public int registToDoList(int memberNo) {
	
		int result = mapper.registToDoList(memberNo);
		
		return result;

	}
	
	
	/**
	 * 메소드 removeToDoList에 관한 문서화 주석
	 * @param todoListNumber: 로그인 한 멤버 고유 번호
	 * @return 결과 확인용
	 * 
	 * @author 박수빈
	 */
	@Override
	public int removeToDoList(int todoListNumber) {
		
		int result = mapper.removeToDoList(todoListNumber);
		

		return result;
	}

	/**
	 * 메소드 modifyToDoListStatus에 관한 문서화 주석
	 * @param ToDoListDTO: 수정될 ToDoListDTO 정보
	 * @return 결과 확인용
	 * 
	 * @author 박수빈
	 */
	@Override
	public int modifyToDoListStatus(ToDoListDTO toDoList) {
		
		int result = mapper.modifyToDoListStatus(toDoList);
		
		System.out.println("modifyToDoListStatus : " + result);
		return result;
	}

	/**
	 * 메소드 selectMemberModal에 관한 문서화 주석
	 * @param code: 클릭한 프로젝트의 코드
	 * @return projectMembers: 클릭한 프로제트의 구성원 정보 
	 * 
	 * @author 박수빈
	 */
	@Override
	public List<ProjectMembersDTO> selectMemberModal(int code) {

		List<ProjectMembersDTO> projectMembers= mapper.selectMemberModal(code);
		
		System.out.println("selectMemberModal : " + projectMembers);
		
		return projectMembers;
	}

	/**
	 * 메소드 selectMemberModal에 관한 문서화 주석
	 * @param todoList: 수정할 ToDoListDTO 정보
	 * @return : 결과 확인
	 * 
	 * @author 박수빈
	 */
	@Override 
	public int modifyToDoList(ToDoListDTO todoList) {

		
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		todoList.setWritingTime(sqlDate);
		
		int result =mapper.modifyToDoList(todoList);
		
		return result;
	}

	/**
	 * 메소드 selectTaskListForProject에 관한 문서화 주석
	 * 로그인한 멤버 번호와 프로젝트 코드를 mapper로 전해준다.
	 * @param memberNo: 수정하는 멤버 번호
	 * @param projectCode: 클릭한 project의 코드
	 * @return taskList: 조회한 태스크 들을 리턴한다
	 * 
	 * @author 박수빈
	 */
	@Override
	public List<TaskDTO> selectTaskListForProject(int memberNo, int projectCode) {
		
		System.out.println("serviceImpl :  selectTaskLsitForProject : in");
		
		TaskDTO task= new TaskDTO();
		
		task.setMemberNo(memberNo);
		task.setProjectCode(projectCode);
		
		List<TaskDTO> taskList = mapper.selectTaskListForProject(task);

		System.out.println("serviceImpl :  selectTaskLsitForProject : out : taskList : " + taskList);
		return taskList;
	}




}


