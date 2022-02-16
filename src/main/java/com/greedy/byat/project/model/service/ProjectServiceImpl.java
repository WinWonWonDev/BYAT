package com.greedy.byat.project.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.project.model.dao.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService {
 
	private final ProjectMapper mapper;

	@Autowired
	public ProjectServiceImpl(ProjectMapper mapper) {
		this.mapper = mapper;
	}

	







}


