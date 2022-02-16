package com.greedy.byat.retrospect.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.retrospect.model.dao.RetrospectMapper;

@Service
public class RetrospectServiceImpl implements RetrospectService {
 
	private final RetrospectMapper mapper;

	@Autowired
	public RetrospectServiceImpl(RetrospectMapper mapper) {
		this.mapper = mapper;
	}

	







}


