package com.greedy.byat.project.model.service;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;

public interface ProjectService {

	List<ProjectDTO> selectProjectList(MemberDTO member);

}
