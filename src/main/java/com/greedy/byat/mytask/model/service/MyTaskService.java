package com.greedy.byat.mytask.model.service;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;
import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

public interface MyTaskService {

	List<ProjectDTO> selectMyTaskProjectList(MemberDTO member);
	List<ProjectMembersDTO> selectMyTaskProjectMembers(int projectCode);
	List<TaskDTO> selectTaskList(int projectCode, int i);
	List<ToDoListDTO> selectMyTaskToDoList(int memberNo);
	void registToDoList(int memberNo);
	int removeToDoList(int todolistNo);
	int modifyToDoListStatus(ToDoListDTO todolist);
	

}
