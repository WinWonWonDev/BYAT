package com.greedy.byat.sprint.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.sprint.RegistSprintException;
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

	@Override
	public void registSprint(SprintDTO sprint) {
		
		int result = mapper.insertSprint(sprint);
		
		if(!(result > 0)) {
			System.out.println("스픤트 생성 시패");
		}
	}

	@Override
	public SprintDTO selectSprint(int sprintCode) {
		
		SprintDTO sprint = mapper.selectSprint(sprintCode);
		
		return sprint;
	}

	@Override
	public void deleteSprint(int sprintCode) {
		
		int result = mapper.deleteSprint(sprintCode);
		
		if(!(result > 0)) {
			System.out.println("스프린트 삭제 실패");
		}
	}

	







}


