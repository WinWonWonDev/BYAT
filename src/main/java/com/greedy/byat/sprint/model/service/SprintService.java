package com.greedy.byat.sprint.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.common.exception.sprint.RegistSprintException;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

/**
 * <pre>
 * Class : SprintService
 * Comment : SprintController의 요청을 처리하는 Interface
 * History
 * 2022/02/17 (박상범) 스프린트 목록 조회 관련 메소드 추가
 * 2022/02/18 (박상범) 스프린트 목록 조회, 스프린트 생성 관련 메소드 추가
 * 2022/02/19 (박상범) 스프린트 생성, 스프린트 상세 조회 / 수정 관련 메소드 추가
 * 2022/02/20 (박상범) 스프린트 상세 조회 / 수정, 스프린트 삭제 관련 메소드 추가
 * 2022/02/21 (박상범) 스프린트 수정, 스프린트 생성 관련 메소드 추가
 * 2022/03/02 (박상범) 스프린트 시작 관련 메소드 추가
 * 2022/03/03 (박상범) 스프린트 시작 관련 메소드 추가
 * 2022/03/04 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
 * 2022/03/05 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
 * 2022/03/06 (박상범) 스프린트 생성, 스프린트 목록 조회 관련 메소드 추가
 * 2022/03/07 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
 * 2022/03/07 (박상범) 스프린트 생성, 스프린트 수정 관련 메소드 추가
 * </pre>
 * @version 12
 * @author 박상범
 * @see SprintController.java, SprintServiceImpl.java, SprintMapper.java
 * */
public interface SprintService {

	List<SprintDTO> selectSprintList(int projectNo);

	String registSprint(SprintDTO sprint);

	SprintDTO selectSprint(int sprintCode);

	void removeSprint(Map<String, Integer> map);

	String selectProjectProgress(int projectCode);

	void modifySprint(SprintDTO sprint);

	String startSprint(Map<String, Integer> map);

	List<BacklogDTO> selectBacklogList(int projectCode);

	String endSprint(Map<String, Integer> map);

	String selectMemberRoleName(Map<String, Integer> map);

}
