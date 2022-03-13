package com.greedy.byat.project.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

/**
 * <pre>
 * Class : ProjectMapper
 * Comment : mapper.xml의 id들이 담긴 class
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
 * @see ProjectController, ProjectService, ProjectServiceImpl, MemberDTO, ProjectDTO, ProjectMembersDTO, NoticeDTO
 * */
public interface ProjectMapper {

	List<ProjectDTO> selectProjectList(MemberDTO member);

	List<ProjectMembersDTO> selectProjectMembers(int code);

	int insertProject(ProjectDTO project);

	int insertProjectWriteMember(ProjectMembersDTO projectMembers);

	int insertProjectFirstMemberRole(ProjectMembersDTO projectMembers);

	int deleteProject(int code);

	ProjectDTO selectProjectDetail(int code);

	int updateProject(ProjectDTO project);

	List<MemberDTO> searchAddMemberList(String searchMember);

	int insertProjectMembers(ProjectMembersDTO registMember);

	int insertProjectMemberRole(ProjectMembersDTO registMember);

	List<ProjectMembersDTO> selectProjectMemberList(int code);

	int deleteProjectMembers(ProjectMembersDTO removeMember);

	int updateProjectMemberRole(ProjectMembersDTO projectMembersDTO);

	int updateProjectWriter(ProjectMembersDTO projectMembersDTO);

	List<ProjectMembersDTO> selectProjectMemberNonParticipationList(int code);

	int updateMemberParticipation(ProjectMembersDTO registMember);

	int updateMemberRole(ProjectMembersDTO registMember);

	int selectTotalCount(Map<String, String> searchMap);

	int insertFirstVersionHistory(ProjectDTO project);

	int insertFirstProgressHistory(ProjectDTO project);

	int insertFirstMemberHistory(ProjectMembersDTO projectMembers);

	int updateProjectVersion(ProjectDTO project);

	int insertVersionHistory(ProjectDTO project);

	int insertMemberHistory(ProjectMembersDTO registMember);

	ProjectMembersDTO selectProjectMember(ProjectMembersDTO removeMember);

	int insertCalendarProjectSchedule(ProjectDTO project);

	int selectSprintProceedingCount(ProjectMembersDTO projectMembers);

	int updateProjectProgress(ProjectDTO projectDTO);

	int insertProgressHistory(ProjectDTO projectDTO);

	int insertNoticeFisrtProjectRegist(NoticeDTO notice);

	int insertNoticeProjectRegist(NoticeDTO notice);

	int updateCalendarProject(ProjectDTO calendarProject);

	int insertCalendarFirstProjectSchedule(ProjectDTO project);


}
