package com.greedy.byat.sprint.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
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

	int insertSprintVersionHistory2(SprintDTO sprint);

	int insertSprintProgressHistory2(SprintDTO sprint);

	List<BacklogDTO> selectBacklogList(int projectCode);

	int checkSprintProgress2(Map<String, Integer> map);

	List<TaskDTO> selectSprintTaskCodeList(Map<String, Integer> map);

	int insertTaskProgressHistory2(TaskDTO task);

	int insertBacklog(TaskDTO task);

	int insertBacklogProgressHistory2(TaskDTO task);

	int insertBacklogVersionHistory(TaskDTO task);

	int updateBacklogProgress2(TaskDTO task);

	int insertBacklogProgressHistory3(TaskDTO task);

	int updateTaskProgress2(TaskDTO task);

	int updateBacklogProgress3(TaskDTO task);

	int updateSprintProgress2(int sprintCode);

	int insertSprintProgressHistory4(Map<String, Integer> map);

	int checkProjectProgress(Map<String, Integer> map);

	int checkSprintProgress3(Map<String, Integer> map);

	SprintDTO selectSprint2(Map<String, Integer> map);

	List<TaskDTO> selectTaskList2(Map<String, Integer> map);

	int updateTaskProgress(TaskDTO task);

	int insertTaskProgressHistory(TaskDTO task);

	int updateBacklogProgress(TaskDTO task);

	int insertBacklogProgressHistory(TaskDTO task);

	int insertRetrospective(int sprintCode);

	int selectSprintCode(Map<String, Integer> map);

	int updatesprintProgress(Map<String, Integer> map);

	int insertSprintProgressHistory3(Map<String, Integer> map);

	int updateTaskProgress3(TaskDTO task);


}
