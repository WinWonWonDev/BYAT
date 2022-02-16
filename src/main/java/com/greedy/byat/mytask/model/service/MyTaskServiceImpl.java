package com.greedy.byat.mytask.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.mytask.model.dao.MyTaskMapper;

@Service
public class MyTaskServiceImpl implements MyTaskService {
 
	private final MyTaskMapper mapper;

	@Autowired
	public MyTaskServiceImpl(MyTaskMapper mapper) {
		this.mapper = mapper;
	}

	







}


