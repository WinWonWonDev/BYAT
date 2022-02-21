package com.greedy.byat.project.model.dao;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

public interface ProjectMapper {

	List<ProjectDTO> selectProjectList(MemberDTO member);

	List<ProjectMembersDTO> selectProjectMembers(int code);

	int registProject(ProjectDTO project);

	int registProjectMembers(ProjectMembersDTO projectMembers);

	int registProjectMemberRole(int no);

	int removeProject(int code);

	ProjectDTO selectProjectDetail(int code);

	int modifyProject(ProjectDTO project);

	List<MemberDTO> searchAddMemberList(String searchMember);

}
