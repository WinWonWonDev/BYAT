package com.greedy.byat.project.model.dao;

import java.util.List;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dto.ProjectDTO;

public interface ProjectMapper {

	List<ProjectDTO> selectProjectList(MemberDTO member);

}
