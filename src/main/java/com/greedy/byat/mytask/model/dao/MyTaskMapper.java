package com.greedy.byat.mytask.model.dao;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

public interface MyTaskMapper {

	List<ProjectDTO> selectMyTaskProjectList(MemberDTO member);
	
	List<ProjectMembersDTO> selectMyTaskProjectMembers(int code);
	
}
