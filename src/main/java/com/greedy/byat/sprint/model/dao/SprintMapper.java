package com.greedy.byat.sprint.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

/**
 * <pre>
 * Class : SprintMapper
 * Comment : mapper.xml의 id들이 담긴 Class
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
 * @see SprintController.java, SprintService.java, SprintServiceImpl.java
 * */
public interface SprintMapper {

	List<SprintDTO> selectSprintList(int projectNo);

	int insertSprint(SprintDTO sprint);

	SprintDTO selectSprint(int sprintCode);

	int deleteSprint(int sprintCode);

	int insertSprintVersionHistory(SprintDTO sprint);

	int insertSprintProgressHistory(SprintDTO sprint);

	String selectProjectProgress(int projectCode);

	int updateSprint(SprintDTO sprint);

	List<TaskDTO> selectTaskList(int sprintCode);

	int checkSprintProgress(int projectCode);

	int insertSprintVersionHistory2(SprintDTO sprint);

	int insertSprintProgressHistory2(SprintDTO sprint);

	List<BacklogDTO> selectBacklogList(int projectCode);

	int checkSprintProgress2(Map<String, Integer> map);

	List<TaskDTO> selectSprintTaskCodeList(Map<String, Integer> map);

	int insertTaskProgressHistory2(TaskDTO task);

	int insertBacklog(TaskDTO task);

	int insertBacklogProgressHistory2(TaskDTO task);

	int insertBacklogVersionHistory(TaskDTO task);

	int updateBacklogProgress2(TaskDTO task);

	int insertBacklogProgressHistory3(TaskDTO task);

	int updateTaskProgress2(TaskDTO task);

	int updateBacklogProgress3(TaskDTO task);

	int updateSprintProgress2(int sprintCode);

	int insertSprintProgressHistory4(Map<String, Integer> map);

	int checkProjectProgress(Map<String, Integer> map);

	String checkSprintProgress3(Map<String, Integer> map);

	SprintDTO selectSprint2(Map<String, Integer> map);

	List<TaskDTO> selectTaskList2(Map<String, Integer> map);

	int updateTaskProgress(TaskDTO task);

	int insertTaskProgressHistory(TaskDTO task);

	int updateBacklogProgress(TaskDTO task);

	int insertBacklogProgressHistory(TaskDTO task);

	int insertRetrospective(int sprintCode);

	int selectSprintCode(Map<String, Integer> map);

	int updatesprintProgress(Map<String, Integer> map);

	int insertSprintProgressHistory3(Map<String, Integer> map);

	int updateTaskProgress3(TaskDTO task);

	int insertSprintNotice(Map<String, Integer> map);

	int insertStartSprintNotice(Map<String, Integer> map);

	List<Integer> selectSprintMemberList(Map<String, Integer> map);

	List<Integer> selectSprintMemberList2(Map<String, Integer> map);

	int insertEndSprintNotice(Map<String, Integer> map);

	int insertUpdateSprintNotice(Map<String, Integer> map);

	List<Integer> selectSprintMemberList3(SprintDTO sprint);

	List<Integer> selectIssueList(Map<String, Integer> map);

	int updateIssueProgress(int issueCode);

	int insertIssueProgressHistory(Map<String, Integer> issueMap);

	List<Integer> selectIssueList2(SprintDTO sprint);

	int updateIssueSprintCode(int projectCode);

	int insertIssueProgressHistory2(Map<String, Integer> map);

	int updateIssueMembersParticipation(int issueCode);

	int updateIssueProgress2(int sprintCode);

	List<Integer> selectIssueList3(int sprintCode);

	int insertSprintVersionHistory3(SprintDTO sprint);

	int insertIssueProgressHistory3(int issueCode);

	int updateIssueMembersParticipation2(int issueCode);

	List<Integer> selectSprintMemberList4(int sprintCode);

	int insertRemoveSprintNotice(Map<String, Integer> map);

	String selectProjectMembersRoleNamee(Map<String, Integer> map);

}
