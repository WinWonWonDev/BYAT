package com.greedy.byat.sprint.model.service;

import java.util.List;

import com.greedy.byat.common.exception.sprint.RegistSprintException;
import com.greedy.byat.sprint.model.dto.SprintDTO;

public interface SprintService {

	List<SprintDTO> selectSprintList(int projectNo);

	void registSprint(SprintDTO sprint);

	SprintDTO selectSprint(int sprintCode);

	void deleteSprint(int sprintCode);

	String selectProjectProgress(int projectCode);

	void modifySprint(SprintDTO sprint);

	
}
