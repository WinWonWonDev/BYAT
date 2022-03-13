package com.greedy.byat.mytask.model.service;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;
import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.task.model.dto.TaskDTO;


/**
 * <pre>
 * Class : MyTaskService
 * Comment : MyTaskController의 요청을 처리하는 Interface
 * History 
 * 2022/02/17  (박수빈) 초기 생성 세팅
 * 2022/02/19  (박수빈) selectmyTaskList() 마이태스크 프로젝트 목록 조회 요청후 생성요청 후 작성
 * 2022/02/21  (박수빈) selectmyTaskList() 마이태스크  태스크 조회, ToDoList 조회 요청후 생성 
 * 2022/02/22  (박수빈) registToDoList() ToDoList 생성요청 작성, removeToDoList() ToDoList 삭제요청 작성, modifyToDoList() ToDoList 수정요청 작성
 * 2022/02/23  (박수빈) modifyToDoListStatus() 상태 변경요청 작성
 * 2022/02/24  (박수빈) selectMytask() 프로젝트 구성원 상세 조회 요청 작성
 * 2022/02/26  (박수빈) selectMytask() 마이태스크  태스크 조회 요청 작성
 * </pre>
 * @version 1.0.0
 * @author 박수빈
 * @see MyTaskMapper, MyTaskServiceImpl, MyTaskService
 * */
public interface MyTaskService {

	MyTaskDTO selectmyTaskList(MemberDTO member);
	
	int registToDoList(int memberNo);
	
	int removeToDoList(int toDoListNumber);
	
	int modifyToDoListStatus(ToDoListDTO todoList);
	
	List<ProjectMembersDTO> selectMemberModal(int projectNum);
	
	int modifyToDoList(ToDoListDTO todoList);
	
	List<TaskDTO> selectTaskListForProject(int memberNo, int ProjectCode);
	
}
