package com.greedy.byat.mytask.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dao.MyTaskMapper;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;

@Service
public class MyTaskServiceImpl implements MyTaskService {
 
	private final MyTaskMapper mapper;

	@Autowired
	public MyTaskServiceImpl(MyTaskMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public MyTaskDTO selectMyTask(MemberDTO member) {
		int memberNo = member.getNo();
		
		MyTaskDTO myTask = mapper.selectMyTask(member);
		
		return myTask;
	}








}


