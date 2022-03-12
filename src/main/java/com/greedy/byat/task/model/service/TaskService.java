package com.greedy.byat.task.model.service;

import java.util.List;

import java.util.Map;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dto.TaskDTO;
import com.greedy.byat.task.model.dto.TaskMembersDTO;

/**
 * <pre>
 * Class : TaskService
 * Comment : TaskController의 요청을 처리하는 Interface
 * History
 * 2021/02/17 (박상범) 처음 작성
 * </pre>
 * @version 1.0.0
 * @author 박상범
 * @see TaskController.java, TaskServiceImpl.java, TaskMapper.java
 * */
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

	boolean updateTaskProgress(Map<String, Integer> map);

	List<TaskDTO> selectTaskList(int sprintCode);

}
