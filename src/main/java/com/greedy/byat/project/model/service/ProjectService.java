package com.greedy.byat.project.model.service;

import java.util.List;

import com.greedy.byat.common.exception.project.ProjectModifyException;
import com.greedy.byat.common.exception.project.ProjectRegistException;
import com.greedy.byat.common.exception.project.ProjectRemoveException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

public interface ProjectService {

	List<ProjectDTO> selectProjectList(MemberDTO member);

	List<ProjectMembersDTO> selectProjectMembers(int code);

	void registProject(ProjectDTO project) throws ProjectRegistException;

	void removeProject(int code) throws ProjectRemoveException;

	ProjectDTO selectProjectDetail(int code);

	void modifyProject(ProjectDTO project) throws ProjectModifyException;

}
