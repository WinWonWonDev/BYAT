package com.greedy.byat.history.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.history.model.dao.HistoryMapper;

@Service
public class HistoryServiceImpl implements HistoryService {
 
	private final HistoryMapper mapper;

	@Autowired
	public HistoryServiceImpl(HistoryMapper mapper) {
		this.mapper = mapper;
	}

	







}


