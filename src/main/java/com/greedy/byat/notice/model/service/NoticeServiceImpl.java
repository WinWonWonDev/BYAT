package com.greedy.byat.notice.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.notice.model.dao.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
 
	private final NoticeMapper mapper;

	@Autowired
	public NoticeServiceImpl(NoticeMapper mapper) {
		this.mapper = mapper;
	}

	







}


