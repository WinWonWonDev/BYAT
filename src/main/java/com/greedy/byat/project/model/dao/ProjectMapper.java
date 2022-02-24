package com.greedy.byat.project.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.project.model.dto.ProjectPagingDTO;

public interface ProjectMapper {

	List<ProjectDTO> selectProjectList(MemberDTO member);

	List<ProjectMembersDTO> selectProjectMembers(int code);

	int registProject(ProjectDTO project);

	int registProjectWriteMember(ProjectMembersDTO projectMembers);

	int registProjectFirstMemberRole(ProjectMembersDTO projectMembers);

	int removeProject(int code);

	ProjectDTO selectProjectDetail(int code);

	int modifyProject(ProjectDTO project);

	List<MemberDTO> searchAddMemberList(String searchMember);

	int registProjectMembers(ProjectMembersDTO registMember);

	int registProjectMemberRole(ProjectMembersDTO registMember);

	List<ProjectMembersDTO> selectProjectMemberList(int code);

	int removeProjectMembers(ProjectMembersDTO removeMember);

	int modifyProjectMemberRole(ProjectMembersDTO projectMembersDTO);

	int modifyProjectWriter(ProjectMembersDTO projectMembersDTO);

	List<ProjectMembersDTO> selectProjectMemberNonParticipationList(int code);

	int updateMemberParticipation(ProjectMembersDTO registMember);

	int updateMemberRole(ProjectMembersDTO registMember);

	int selectTotalCount(Map<String, String> searchMap);


}
