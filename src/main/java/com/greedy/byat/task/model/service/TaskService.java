package com.greedy.byat.task.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dto.TaskDTO;
import com.greedy.byat.task.model.dto.TaskMembersDTO;

public interface TaskService {

	String registTask(TaskDTO task);

	TaskDTO selectTaskDetail(int taskCode);

	List<MemberDTO> selectProjectMembers(int projectCode);

	String selectTaskParticipation(Map<String, Integer> taskParticipation);

	String registTaskMembers(Map<String, Integer> taskParticipation);

	List<TaskMembersDTO> selectTaskMembers(int taskCode);

	String modifyTask(TaskDTO task);

	String removeTask(Map<String, Integer> map);

	String removeTaskMembers(Map<String, Integer> map);

	boolean checkTasksContent(int projectCode);

}
