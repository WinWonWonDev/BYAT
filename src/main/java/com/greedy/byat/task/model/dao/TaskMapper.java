package com.greedy.byat.task.model.dao;

import com.greedy.byat.task.model.dto.TaskDTO;

public interface TaskMapper {

	int insertTask(TaskDTO task);

	TaskDTO selectTask(int taskCode);

	int insertTaskVersionHistory(TaskDTO task);

	int insertTaskProgressHistory(TaskDTO task);

	int checkSprintProgress(int projectCode);

}
