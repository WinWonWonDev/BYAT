package com.greedy.byat.sprint.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.sprint.RegistSprintException;
import com.greedy.byat.sprint.model.dao.SprintMapper;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

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
		
		int result1 = mapper.insertSprint(sprint);
		
		int result2 = mapper.insertSprintVersionHistory(sprint);
		
		int result3 = mapper.insertSprintProgressHistory(sprint);
		
		if (!(result1 > 0) && !(result2 > 0) && !(result3 > 0)) {
			System.out.println("스프린트 생성 실패");
		}
	}

	@Override
	public void modifySprint(SprintDTO sprint) {
		
		int result1 = mapper.updateSprint(sprint);
		
		int result2 = mapper.insertSprintVersionHistory(sprint);
		
		if(!(result1 > 0) && !(result2 > 0)) {
			System.out.println("스프린트 수정 실패");
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

	@Override
	public String selectProjectProgress(int projectCode) {
		
		String projectProgress = mapper.selectProjectProgress(projectCode);
		
		return projectProgress;
	}

	@Override
	public List<TaskDTO> selectTaskList(int sprintCode) {
		
		List<TaskDTO> taskList = mapper.selectTaskList(sprintCode);
		
		System.out.println("asdf"+taskList);
		
		return taskList;
	}


	







}


