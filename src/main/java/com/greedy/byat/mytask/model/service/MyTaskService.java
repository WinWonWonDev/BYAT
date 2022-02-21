package com.greedy.byat.mytask.model.service;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;

public interface MyTaskService {

	List<ProjectDTO> selectMyTaskProjectList(MemberDTO member);
	List<ProjectMembersDTO> selectMyTaskProjectMembers(int code);
}
