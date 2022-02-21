package com.greedy.byat.mytask.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dao.MyTaskMapper;
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
	public List<ProjectDTO> selectMyTaskProjectList(MemberDTO member) {
		 
		List<ProjectDTO> project = mapper.selectMyTaskProjectList(member);
		
		return project;
	}
	
	@Override
	public List<ProjectMembersDTO>selectMyTaskProjectMembers(int projectCode){
		
		List<ProjectMembersDTO> projectMembers = mapper.selectMyTaskProjectMembers(projectCode);
		
		List<ProjectMembersDTO> orderProjectMembers = new ArrayList<>();
		
		int memberCount = 0;
		
		for(int i = 0; i < projectMembers.size(); i++) {
			projectMembers.get(i).setName(projectMembers.get(i).getName().substring(1, 3));
			
			if("PM".equals(projectMembers.get(i).getRoleName())) {
				
				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}
			
		}
		
		for(int i = 0; i < projectMembers.size(); i++) {
			
			if("부PM".equals(projectMembers.get(i).getRoleName())) {
				
				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}
			
		}
		
		for(int i = 0; i < projectMembers.size(); i++) {
			
			if("일반멤버".equals(projectMembers.get(i).getRoleName())) {
				
				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}
			
		}
		
		return orderProjectMembers;
	}

	@Override
	public List<TaskDTO> selectTaskList(int projectCode, int memberNo) {
		
		List<TaskDTO> task = mapper.selectTaskList(projectCode, memberNo);

		
		return task;
	}

	@Override
	public List<ToDoListDTO> selectMyTaskToDoList(int memberNo) {

		List<ToDoListDTO> todoList = mapper.selectMyTaskToDoList(memberNo);

		return todoList;
	}






}


