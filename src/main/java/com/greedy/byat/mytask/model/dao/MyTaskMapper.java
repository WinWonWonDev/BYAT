package com.greedy.byat.mytask.model.dao;

import java.util.List;

import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.task.model.dto.TaskDTO;			

/**
 * <pre>
 * Class : MyTaskMapper
 * Comment : mapper.xml의 id들이 담긴 Class
 * History 
 * 2022/02/17  (박수빈) 초기 생성 세팅
 * 2022/02/19  (박수빈) selectMytask() 마이태스크 프로젝트 목록 조회 요청후 생성요청 후 작성
 * 2022/02/21  (박수빈) selectMytask() 마이태스크  태스크 조회, ToDoList 조회 요청후 생성 
 * 2022/02/22  (박수빈) registToDoList() ToDoList 생성요청 작성, removeToDoList() ToDoList 삭제요청 작성, modifyToDoList() ToDoList 수정요청 작성
 * 2022/02/23  (박수빈) modifyToDoListStatus() 상태 변경요청 작성
 * 2022/02/24  (박수빈) selectMytask() 프로젝트 구성원 상세 조회 요청 작성
 * 2022/02/26  (박수빈) selectMytask() 마이태스크  태스크 조회 요청 작성
 * </pre>
 * @version 1.0.0
 * @author 박수빈
 * @see MyTaksController, MyTaskService, MyTaskServiceImpl, ToDoListDTO, ProjectDTO, ProjectMembersDTO, TaskDTO
 * */
public interface MyTaskMapper {

	List<ProjectDTO> selectMyTaskProjectList(int memberNumber);
	
	List<ProjectMembersDTO> selectMyTaskProjectMembers(int projectCode);

	List<TaskDTO> selectTaskList(int memberNo);

	List<ToDoListDTO> selectMyTaskToDoList(int memberNumber);

	int registToDoList(int memberNumber);

	int removeToDoList(int todoListNumber);
	
	int modifyToDoListStatus(ToDoListDTO toDoList);

	List<ProjectMembersDTO> selectMemberModal(int projectNum);

	int modifyToDoList(ToDoListDTO todoList);

	List<TaskDTO> selectTaskListForProject(TaskDTO task);
}
