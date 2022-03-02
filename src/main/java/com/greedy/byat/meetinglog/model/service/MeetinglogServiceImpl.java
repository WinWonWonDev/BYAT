package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.meetinglog.model.dao.MeetinglogMapper;
import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;

@Service
public class MeetinglogServiceImpl implements MeetinglogService {
 
	private final MeetinglogMapper mapper;

	@Autowired
	public MeetinglogServiceImpl(MeetinglogMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<MeetinglogDTO> selectMeetinglogList(int projectCode) {

		List<MeetinglogDTO> meetinglog = mapper.selectMeetinglogList(projectCode);
		
		return meetinglog;
	}


	







}


