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
 * 2021/02/17 (박수빈) 처음 작성
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
