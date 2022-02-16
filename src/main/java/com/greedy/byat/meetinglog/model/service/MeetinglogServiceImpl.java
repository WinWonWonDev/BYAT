package com.greedy.byat.meetinglog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.meetinglog.model.dao.MeetinglogMapper;

@Service
public class MeetinglogServiceImpl implements MeetinglogService {
 
	private final MeetinglogMapper mapper;

	@Autowired
	public MeetinglogServiceImpl(MeetinglogMapper mapper) {
		this.mapper = mapper;
	}

	







}


