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
 * 2022/02/22 (박상범) 태스크 목록 조회 메소드 추가
 * 2022/02/23 (박상범) 태스크 목록 조회, 태스크 생성 관련 메소드 추가
 * 2022/02/24 (박상범) 태스크 생성, 태스크 상세 조회 관련 메소드 추가
 * 2022/02/25 (박상범) 태스크 생성 관련 메소드 추가
 * 2022/02/26 (박상범) 태스크 상세 조회, 태스크 참여 여부 조회, 태스크 참여자 목록 조회 관련 메소드 추가
 * 2022/02/27 (박상범) 태스크 참가 여부 조회, 태스크 상세 조회, 태스크 참여자 목록 조회 관련 메소드 추가
 * 2022/02/28 (박상범) 태스크 참가, 태스크 참여자 목록, 태스크 상태 변경 관련 메소드 추가
 * 2022/03/01 (박상범) 태스크 삭제, 태스크 수정, 태스크 참가 포기 관련 메소드 추가
 * 2022/03/02 (박상범) 태스크 참가 포기, 태스크 참가, 태스크 참여자 목록 조회 관련 메소드 추가
 * 2022/03/08 (박상범) 태스크 상태 변경 태스크 참여자 목록 조회 관련 메소드 추가
 * </pre>
 * @version 10
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
