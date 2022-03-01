package com.greedy.byat.issue.model.service;

import java.util.List;

import com.greedy.byat.common.exception.issue.IssueInsertMemberHistoryException;
import com.greedy.byat.common.exception.issue.IssueInsertVersionHistoryException;
import com.greedy.byat.common.exception.issue.IssueModifyMemberException;
import com.greedy.byat.common.exception.issue.IssueModifyStatusException;
import com.greedy.byat.common.exception.issue.IssueRegistStatusHistoryException;
import com.greedy.byat.common.exception.issue.IssueUpdateContentException;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.dto.SprintMembersDTO;

public interface IssueService {

	List<SprintDTO> selectSprintList(int projectCode);

	int updateIssueStatus(IssueDTO issue) throws IssueModifyStatusException, IssueRegistStatusHistoryException;

	List<IssueDTO> selectIssueList(int code);

	List<SprintMembersDTO> selectSprintMembers(int code);

	void updateIssue(IssueDTO modifyIssue) throws IssueModifyMemberException, IssueUpdateContentException, IssueInsertVersionHistoryException, IssueInsertMemberHistoryException;

}
