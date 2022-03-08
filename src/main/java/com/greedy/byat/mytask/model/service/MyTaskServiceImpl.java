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


@Service
public class MyTaskServiceImpl implements MyTaskService {
 
	private final MyTaskMapper mapper;

	@Autowired
	public MyTaskServiceImpl(MyTaskMapper mapper) {
		this.mapper = mapper;
	}

	
	@Override
	public MyTaskDTO selectmyTaskList(MemberDTO member) {
		
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
	
	@Override
	public List<TaskDTO> selectTaskList(int memberNo) {
		
		List<TaskDTO> taskList = mapper.selectTaskList(memberNo);

		return taskList;
	}


	@Override
	public void registToDoList(int memberNumber) {
	
		mapper.registToDoList(memberNumber);
		
		System.out.println("registToDoList");

	}

	@Override
	public int removeToDoList(int todoListNumber) {
		int result = mapper.removeToDoList(todoListNumber);
		
		System.out.println( result + " : removeToDoList");

		return result;
	}

	@Override
	public int modifyToDoListStatus(ToDoListDTO toDoList) {
		
		System.out.println("modifyToDoListStatus" + toDoList);
		
		int result = mapper.modifyToDoListStatus(toDoList);
		
		System.out.println("modifyToDoListStatus : " + result);
		return result;
	}

	@Override
	public List<ProjectMembersDTO> selectMemberModal(int code) {

		List<ProjectMembersDTO> projectMembers= mapper.selectMemberModal(code);
		
		System.out.println("selectMemberModal : " + projectMembers);
		
		return projectMembers;
	}

	@Override 
	public int modifyToDoList(ToDoListDTO todoList) {

		
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		todoList.setWritingTime(sqlDate);
		
		int result =mapper.modifyToDoList(todoList);
		
		System.out.println("modifyToDoList : " + result);

		
		return result;
	}


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


