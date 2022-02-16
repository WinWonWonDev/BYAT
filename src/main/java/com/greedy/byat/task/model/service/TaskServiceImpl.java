package com.greedy.byat.task.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.task.model.dao.TaskMapper;

@Service
public class TaskServiceImpl implements TaskService {
 
	private final TaskMapper mapper;

	@Autowired
	public TaskServiceImpl(TaskMapper mapper) {
		this.mapper = mapper;
	}

	







}


