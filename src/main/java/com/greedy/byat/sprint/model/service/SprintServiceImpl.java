package com.greedy.byat.sprint.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.sprint.model.dao.SprintMapper;
import com.greedy.byat.sprint.model.dto.SprintDTO;

@Service
public class SprintServiceImpl implements SprintService {
 
	private final SprintMapper mapper;

	@Autowired
	public SprintServiceImpl(SprintMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<SprintDTO> selectSprintList(int projectNo) {

		List<SprintDTO> sprintList = mapper.selectSprintList(projectNo);
		
		return sprintList;
	}

	







}


