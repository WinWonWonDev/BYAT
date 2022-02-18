package com.greedy.byat.project.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dao.ProjectMapper;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

@Service
public class ProjectServiceImpl implements ProjectService {
 
	private final ProjectMapper mapper;

	@Autowired
	public ProjectServiceImpl(ProjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<ProjectDTO> selectProjectList(MemberDTO member) {
		
		List<ProjectDTO> projectList = mapper.selectProjectList(member);
		
		return projectList;
	}

	@Override
	public List<ProjectMembersDTO> selectProjectMembers(int code) {
		
		List<ProjectMembersDTO> projectMembers = mapper.selectProjectMembers(code);
		
		for(int i = 0; i < projectMembers.size(); i++) {
			projectMembers.get(i).setName(projectMembers.get(i).getName().substring(1, 3));
		}
		
		return projectMembers;
	}



}


