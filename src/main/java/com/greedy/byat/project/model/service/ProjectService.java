package com.greedy.byat.project.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.exception.project.ProjectMemberModifyRoleException;
import com.greedy.byat.common.exception.project.ProjectMemberRemoveException;
import com.greedy.byat.common.exception.project.ProjectModifyException;
import com.greedy.byat.common.exception.project.ProjectRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistMemberException;
import com.greedy.byat.common.exception.project.ProjectRemoveException;
import com.greedy.byat.common.exception.project.ProjectWriterChangeException;
import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.project.model.dto.ProjectPagingDTO;

public interface ProjectService {

	List<ProjectDTO> selectProjectList(MemberDTO member);

	List<ProjectMembersDTO> selectProjectMembers(int code);

	void registProject(ProjectDTO project) throws ProjectRegistException;

	void removeProject(int code) throws ProjectRemoveException;

	ProjectDTO selectProjectDetail(int code);

	void modifyProject(ProjectDTO project) throws ProjectModifyException;

	List<MemberDTO> searchAddMemberList(String searchMember, String[] projectMembersList, String[] selectMembers);

	void registProjectMember(ProjectMembersDTO registMember) throws ProjectRegistMemberException;

	List<ProjectMembersDTO> selectProjectMemberList(int code);

	void removeProjectMembers(ProjectMembersDTO removeMember) throws ProjectMemberRemoveException;

	void modifyProjectMemberRole(List<ProjectMembersDTO> members) throws ProjectMemberModifyRoleException, ProjectWriterChangeException;

	int selectTotalCount(Map<String, String> searchMap);

}
