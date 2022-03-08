package com.greedy.byat.mytask.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.greedy.byat.mytask.model.dto.ToDoListDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.task.model.dto.TaskDTO;			

public interface MyTaskMapper {

	List<ProjectDTO> selectMyTaskProjectList(int memberNumber);
	
	List<ProjectMembersDTO> selectMyTaskProjectMembers(int projectCode);

	List<TaskDTO> selectTaskList(int memberNo);

	List<ToDoListDTO> selectMyTaskToDoList(int memberNumber);

	void registToDoList(int memberNumber);

	int removeToDoList(int todoListNumber);
	
	int modifyToDoListStatus(ToDoListDTO toDoList);

	List<ProjectMembersDTO> selectMemberModal(int projectNum);

	int modifyToDoList(ToDoListDTO todoList);

	List<TaskDTO> selectTaskListForProject(TaskDTO task);
}
