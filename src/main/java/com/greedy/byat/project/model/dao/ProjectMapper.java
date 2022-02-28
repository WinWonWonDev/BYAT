package com.greedy.byat.project.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

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


}
