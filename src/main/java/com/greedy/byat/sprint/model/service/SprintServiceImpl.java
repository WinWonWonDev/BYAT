package com.greedy.byat.sprint.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.sprint.model.dao.SprintMapper;

@Service
public class SprintServiceImpl implements SprintService {
 
	private final SprintMapper mapper;

	@Autowired
	public SprintServiceImpl(SprintMapper mapper) {
		this.mapper = mapper;
	}

	







}


