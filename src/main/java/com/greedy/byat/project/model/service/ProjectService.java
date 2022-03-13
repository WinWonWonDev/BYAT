package com.greedy.byat.project.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.exception.notice.NoticeInsertException;
import com.greedy.byat.common.exception.project.CalendatRegistProjectScheduleException;
import com.greedy.byat.common.exception.project.ProjectMemberHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectMemberModifyRoleException;
import com.greedy.byat.common.exception.project.ProjectMemberRemoveException;
import com.greedy.byat.common.exception.project.ProjectModifyCalendarException;
import com.greedy.byat.common.exception.project.ProjectModifyException;
import com.greedy.byat.common.exception.project.ProjectProgressHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistMemberException;
import com.greedy.byat.common.exception.project.ProjectRemoveException;
import com.greedy.byat.common.exception.project.ProjectVersionHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectWriterChangeException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;

/**
 * <pre>
 * Class : ProjectService
 * Comment : ProjectController의 요청을 처리하는 Interface 입니다.
 * History
 * 2022/02/17 (박인근) 처음 작성
 * 2022/02/18 (박인근) 프로젝트 목록 조회, 생성 관련 메소드 추가
 * 2022/02/19 (박인근) 프로젝트 조회, 수정 관련 메소드 추가
 * 2022/02/20 (박인근) 프로젝트 조회, 수정 관련 메소드 수정
 * 2022/02/21 (박인근) 프로젝트 구성원 추가 관련 메소드 추가
 * 2022/02/22 (박인근) 프로젝트 구성원 목록 조회 관련 메소드 추가
 * 2022/02/23 (박인근) 프로젝트 구성원 추가 취소 메소드, 구성원 제외 관련 메소드 추가
 * 2022/02/24 (박인근) 프로젝트 구성원 추가 시 검색 오류 코드 수정
 * 2022/02/25 (박인근) 프로젝트 버전 히스토리, 변경 이력 관련 코드 추가
 * 2022/02/28 (박인근) 프로젝트 일정 생성 관련 코드 추가
 * 2022/03/03 (박인근) 프로젝트 구성원 추가시 탈퇴 여부 체크, 구성원 제외 시 진행중이 스프린트 여부 체크 관련 코드 추가
 * 2022/03/04 (박인근) 프로젝트 진행상태 자동 변경 코드 추가
 * 2022/03/05 (박인근) 프로젝트 생성 알림 관련 코드 추가
 * </pre>
 * @version 13
 * @author 박인근
 * see ProjectController, ProjectDTO, ProjectMembersDTO, ProjectServiceImpl, ProjectMapper.java, ProjectMapper.xml, NoticeDTO, MemberDTO, RoleDTO
 * */
public interface ProjectService {

	List<ProjectDTO> selectProjectList(MemberDTO member) throws ProjectProgressHistoryRegistException;

	void insertProject(ProjectDTO project) throws ProjectRegistException, ProjectVersionHistoryRegistException, ProjectProgressHistoryRegistException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException, NoticeInsertException;

	void deleteProject(int code, MemberDTO member) throws ProjectRemoveException, ProjectVersionHistoryRegistException, NoticeInsertException;

	ProjectDTO selectProjectDetail(int code);

	void updateProject(ProjectDTO project, MemberDTO member) throws ProjectModifyException, ProjectVersionHistoryRegistException, NoticeInsertException, ProjectModifyCalendarException;

	List<MemberDTO> searchAddMemberList(String searchMember, String[] projectMembersList, String[] selectMembers);

	String insertProjectMember(ProjectMembersDTO registMember) throws ProjectRegistMemberException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException;

	List<ProjectMembersDTO> selectProjectMemberList(int code);

	void deleteProjectMembers(ProjectMembersDTO removeMember) throws ProjectMemberRemoveException, ProjectMemberHistoryRegistException;

	void updateProjectMemberRole(List<ProjectMembersDTO> members) throws ProjectMemberModifyRoleException, ProjectWriterChangeException, ProjectMemberHistoryRegistException;

	int selectSprintProceedingCount(int code, int no);

}
