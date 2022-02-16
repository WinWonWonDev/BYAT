package com.greedy.byat.management.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.management.model.dao.ManagementMapper;

@Service
public class ManagementServiceImpl implements ManagementService {
 
	private final ManagementMapper mapper;

	@Autowired
	public ManagementServiceImpl(ManagementMapper mapper) {
		this.mapper = mapper;
	}

	







}


