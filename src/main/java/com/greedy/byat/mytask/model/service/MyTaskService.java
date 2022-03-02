package com.greedy.byat.mytask.model.service;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;
import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

public interface MyTaskService {

	MyTaskDTO selectmyTaskList(MemberDTO member);
	void registToDoList(int memberNo);
	int removeToDoList(int toDoListNumber);
	int modifyToDoListStatus(ToDoListDTO todoList);
	List<ProjectMembersDTO> selectMemberModal(int projectNum);
	int modifyToDoList(ToDoListDTO todoList);
	List<TaskDTO> selectTaskList(int memberNo);
	List<TaskDTO> selectTaskListForProject(int memberNo, int ProjectCode);
	
}
