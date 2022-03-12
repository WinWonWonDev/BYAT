package com.greedy.byat.task.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dto.TaskDTO;
import com.greedy.byat.task.model.dto.TaskMembersDTO;

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

	int checkIsSprintMembers(Map<String, Integer> taskParticipation);

	List<TaskMembersDTO> selectTaskMembers(int taskCode);

	int updateTask(TaskDTO task);

	int insertTaskVersionHistory2(TaskDTO task);

	int deleteTask(Map<String, Integer> map);

	int insertTaskVersionHistory3(TaskDTO task);

	int deleteTaskMembers(Map<String, Integer> map);

	List<Integer> selectTaskMembersList(Map<String, Integer> map);

	int insertTaskMembersHistory2(Map<String, Integer> map);

	String selectTaskBody(Map<String, Object> map);

	TaskDTO selectTask2(Map<String, Integer> map);

	int deleteTaskMembers2(Map<String, Integer> map);

	int checkOtherTaskMembers(Map<String, Integer> map);

	int deleteSprintMembers(Map<String, Integer> map);

	int insertTaskMembersHistory3(Map<String, Integer> map);

	int insertSprintMembersHistory2(Map<String, Integer> map);

	List<Integer> selectTaskMembersList2(TaskDTO task);

	int insertUpdateTaskNotice(Map<String, Integer> map);

	int updateTaskProgress(Map<String, Integer> map);

	int insertTaskProgressHistory2(Map<String, Integer> map);

	List<TaskDTO> selectTaskList2(int sprintCode);
}
