package com.greedy.byat.project.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.project.ProjectModifyException;
import com.greedy.byat.common.exception.project.ProjectRegistException;
import com.greedy.byat.common.exception.project.ProjectRemoveException;
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
		
		List<ProjectMembersDTO> orderProjectMembers = new ArrayList<>();
		
		int memberCount = 0;
		
		for(int i = 0; i < projectMembers.size(); i++) {
			projectMembers.get(i).setName(projectMembers.get(i).getName().substring(1, 3));
			
			if("PM".equals(projectMembers.get(i).getRoleName())) {
				
				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}
			
		}
		
		for(int i = 0; i < projectMembers.size(); i++) {
			
			if("부PM".equals(projectMembers.get(i).getRoleName())) {
				
				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}
			
		}
		
		for(int i = 0; i < projectMembers.size(); i++) {
			
			if("일반 멤버".equals(projectMembers.get(i).getRoleName())) {
				
				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}
			
		}
		
		return orderProjectMembers;
	}

	@Override
	public void registProject(ProjectDTO project) throws ProjectRegistException {
		
		int result = mapper.registProject(project);
		
		ProjectMembersDTO projectMembers = new ProjectMembersDTO();
		projectMembers.setName(project.getWriter());
		projectMembers.setNo(project.getWriterMember().getNo());
		
		int projectMembersRegistResult = 0;
		int projectRoleRegistResult = 0;
		
		if(result > 0) {
			projectMembersRegistResult = mapper.registProjectMembers(projectMembers);			
		}
		
		if(projectMembersRegistResult > 0) {
			projectRoleRegistResult = mapper.registProjectMemberRole(projectMembers.getNo());
		}
		
		if(!(result > 0 && projectMembersRegistResult > 0 && projectRoleRegistResult > 0)) {
			throw new ProjectRegistException("프로젝트 생성 실패!");
		}
		
	}

	@Override
	public void removeProject(int code) throws ProjectRemoveException {
		
		int result = mapper.removeProject(code);
		
		if(!(result > 0)) {
			throw new ProjectRemoveException("프로젝트 삭제 실패");
		}
		
	}

	@Override
	public ProjectDTO selectProjectDetail(int code) {
		
		ProjectDTO projectDetail = mapper.selectProjectDetail(code);
		
		return projectDetail;
	}

	@Override
	public void modifyProject(ProjectDTO project) throws ProjectModifyException {
		
		int result = mapper.modifyProject(project);
		
		if(!(result > 0)) {
			throw new ProjectModifyException("프로젝트 수정 실패");
		}
		
	}

	@Override
	public List<MemberDTO> searchAddMemberList(String searchMember) {
		
		System.out.println("impl : " + searchMember);
		
		List<MemberDTO> searchMemberList = mapper.searchAddMemberList(searchMember);
		
		System.out.println("여기오냐?");
		
		return searchMemberList;
	}



}


