package com.greedy.byat.mytask.model.dao;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

public interface MyTaskMapper {

	List<ProjectDTO> selectMyTaskProjectList(MemberDTO member);
	
	List<ProjectMembersDTO> selectMyTaskProjectMembers(int code);

	List<TaskDTO> selectTaskList(int projectCode, int memberNo);

	List<ToDoListDTO> selectMyTaskToDoList(int memberNo);

	void reqistToDoList(int memberNo);

	int removeToDoList(int todolistNo);
	
	int modifyToDoListStatus(ToDoListDTO todolist);

	List<ProjectMembersDTO> selectMemberModal(int projectNum);

	int modifyToDoList(ToDoListDTO todolist);
}
