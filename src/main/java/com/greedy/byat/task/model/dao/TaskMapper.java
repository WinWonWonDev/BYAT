package com.greedy.byat.task.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

public interface TaskMapper {

	int insertTask(TaskDTO task);

	TaskDTO selectTask(int taskCode);

	int insertTaskVersionHistory(TaskDTO task);

	int insertTaskProgressHistory(TaskDTO task);

	int checkSprintProgress(int projectCode);

	List<MemberDTO> selectProjectMembers(int projectCode);

	int selectSprintCode(int projectCode);

	String selectSprintTitle(TaskDTO task);

	int selectTaskParticipation(Map<String, Integer> taskParticipation);

	int insertTaskMembers(Map<String, Integer> taskParticipation);

	int checkWasTaskMembers(Map<String, Integer> taskParticipation);

	int changeTaskMembersParticipation(Map<String, Integer> taskParticipation);

	int checkWasSprintMembers(Map<String, Integer> taskParticipation);

	int changeSprintMembersParticipation(Map<String, Integer> taskParticipation);

	int insertSprintMembers(Map<String, Integer> taskParticipation);

	int insertTaskMembersHistory(Map<String, Integer> taskParticipation);

	int insertSprintMembersHistory(Map<String, Integer> taskParticipation);
}
