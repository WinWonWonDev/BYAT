package com.greedy.byat.issue.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.issue.model.dao.IssueMapper;

@Service
public class IssueServiceImpl implements IssueService {
 
	private final IssueMapper mapper;

	@Autowired
	public IssueServiceImpl(IssueMapper mapper) {
		this.mapper = mapper;
	}

	







}


