package com.greedy.byat.project.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.exception.notice.NoticeInsertException;
import com.greedy.byat.common.exception.project.CalendatRegistProjectScheduleException;
import com.greedy.byat.common.exception.project.ProjectMemberHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectMemberModifyRoleException;
import com.greedy.byat.common.exception.project.ProjectMemberRemoveException;
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

public interface ProjectService {

	List<ProjectDTO> selectProjectList(MemberDTO member) throws ProjectProgressHistoryRegistException;

	void insertProject(ProjectDTO project) throws ProjectRegistException, ProjectVersionHistoryRegistException, ProjectProgressHistoryRegistException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException, NoticeInsertException;

	void deleteProject(int code, MemberDTO member) throws ProjectRemoveException, ProjectVersionHistoryRegistException, NoticeInsertException;

	ProjectDTO selectProjectDetail(int code);

	void updateProject(ProjectDTO project, MemberDTO member) throws ProjectModifyException, ProjectVersionHistoryRegistException, NoticeInsertException;

	List<MemberDTO> searchAddMemberList(String searchMember, String[] projectMembersList, String[] selectMembers);

	String insertProjectMember(ProjectMembersDTO registMember) throws ProjectRegistMemberException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException;

	List<ProjectMembersDTO> selectProjectMemberList(int code);

	void deleteProjectMembers(ProjectMembersDTO removeMember) throws ProjectMemberRemoveException, ProjectMemberHistoryRegistException;

	void updateProjectMemberRole(List<ProjectMembersDTO> members) throws ProjectMemberModifyRoleException, ProjectWriterChangeException, ProjectMemberHistoryRegistException;

	int selectSprintProceedingCount(int code, int no);

}
