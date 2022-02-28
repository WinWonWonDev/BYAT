package com.greedy.byat.issue.model.dao;

import java.util.List;

import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;

public interface IssueMapper {

	List<SprintDTO> selectSprintList(int projectCode);

	List<IssueDTO> selectIssueList(SprintDTO sprintDTO);

	int updateIssueStatus(IssueDTO issue);

	int insertIssueProgressHistory(IssueDTO issue);

}
