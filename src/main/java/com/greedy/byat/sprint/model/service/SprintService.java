package com.greedy.byat.sprint.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.common.exception.sprint.RegistSprintException;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

public interface SprintService {

	List<SprintDTO> selectSprintList(int projectNo);

	String registSprint(SprintDTO sprint);

	SprintDTO selectSprint(int sprintCode);

	void removeSprint(Map<String, Integer> map);

	String selectProjectProgress(int projectCode);

	void modifySprint(SprintDTO sprint);

	List<TaskDTO> selectTaskList(int sprintCode);

	String startSprint(Map<String, Integer> map);

	List<BacklogDTO> selectBacklogList(int projectCode);

	String endSprint(Map<String, Integer> map);

	String selectMemberRoleName(Map<String, Integer> map);

}
