package com.greedy.byat.task.model.dao;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

public interface TaskMapper {

	int insertTask(TaskDTO task);

	TaskDTO selectTask(int taskCode);

	int insertTaskVersionHistory(TaskDTO task);

	int insertTaskProgressHistory(TaskDTO task);

	int checkSprintProgress(int projectCode);

	List<MemberDTO> selectProjectMembers(int projectCode);

	int insertTaskMembers(TaskDTO task);

	int insertSprintMembers(TaskDTO task);

	int selectSprintCode(int projectCode);

	String selectSprintTitle(TaskDTO task);

}
