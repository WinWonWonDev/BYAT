package com.greedy.byat.backlog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.backlog.model.dao.BacklogMapper;
import com.greedy.byat.backlog.model.dto.BacklogDTO;

@Service
public class BacklogServiceImpl implements BacklogService {
 
	private final BacklogMapper mapper;

	@Autowired
	public BacklogServiceImpl(BacklogMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<BacklogDTO> selectBacklogList(int projectCode) {

		
		
		return null;
	}

	







}


