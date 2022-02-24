package com.greedy.byat.task.model.service;

import java.util.List;

import com.greedy.byat.task.model.dto.TaskDTO;

public interface TaskService {

	String registTask(TaskDTO task);

	TaskDTO selectTaskDetail(int taskCode);

	List<String> selectProjectMembers(int projectCode);

}
