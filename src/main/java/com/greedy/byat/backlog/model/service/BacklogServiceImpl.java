package com.greedy.byat.backlog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.backlog.model.dao.BacklogMapper;

@Service
public class BacklogServiceImpl implements BacklogService {
 
	private final BacklogMapper mapper;

	@Autowired
	public BacklogServiceImpl(BacklogMapper mapper) {
		this.mapper = mapper;
	}

	







}


