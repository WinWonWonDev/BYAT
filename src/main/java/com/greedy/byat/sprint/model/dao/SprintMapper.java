package com.greedy.byat.sprint.model.dao;

import java.util.List;

import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

public interface SprintMapper {

	List<SprintDTO> selectSprintList(int projectNo);

	int insertSprint(SprintDTO sprint);

	SprintDTO selectSprint(int sprintCode);

	int deleteSprint(int sprintCode);

	int insertSprintVersionHistory(SprintDTO sprint);

	int insertSprintProgressHistory(SprintDTO sprint);

	String selectProjectProgress(int projectCode);

	int updateSprint(SprintDTO sprint);

	List<TaskDTO> selectTaskList(int sprintCode);

	int checkSprintProgress(int projectCode);
	
}
