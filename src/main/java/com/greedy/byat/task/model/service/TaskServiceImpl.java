package com.greedy.byat.task.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.greedy.byat.task.model.dao.TaskMapper;
import com.greedy.byat.task.model.dto.TaskDTO;

@Service
public class TaskServiceImpl implements TaskService {
 
	private final TaskMapper mapper;

	@Autowired
	public TaskServiceImpl(TaskMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void registTask(TaskDTO task) {
		
		int result = mapper.insertTask(task);
		
		if(!(result > 0)) {
			System.out.println("태스크 생성 실패");
		}
	}

	







}


