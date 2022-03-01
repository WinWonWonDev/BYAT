package com.greedy.byat.issue.model.dao;

import java.util.List;

import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.dto.SprintMembersDTO;

public interface IssueMapper {

	List<SprintDTO> selectSprintList(int projectCode);

	List<IssueDTO> selectIssueList(SprintDTO sprintDTO);

	int updateIssueStatus(IssueDTO issue);

	int insertIssueProgressHistory(IssueDTO issue);

	List<IssueMembersDTO> selectIssueMemberList(int code);

	List<SprintMembersDTO> selectSprintMembers(int code);

	int updateIssue(IssueDTO modifyIssue);

	int updateIssueMember(List<IssueMembersDTO> newIssueMemberList);

	int insertIssueMembersHistory(List<IssueMembersDTO> newIssueMemberList);

	int insertIssueVersionHistory(IssueDTO modifyIssue);

	IssueDTO selectIssue(int code);

}
