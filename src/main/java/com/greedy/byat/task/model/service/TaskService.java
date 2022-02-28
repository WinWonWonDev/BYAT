package com.greedy.byat.task.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

public interface TaskService {

	String registTask(TaskDTO task);

	TaskDTO selectTaskDetail(int taskCode);

	List<MemberDTO> selectProjectMembers(int projectCode);

	String selectTaskParticipation(Map<String, Integer> taskParticipation);

	int registTaskMembers(Map<String, Integer> taskParticipation);

}
