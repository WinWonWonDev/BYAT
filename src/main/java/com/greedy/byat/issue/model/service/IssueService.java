package com.greedy.byat.issue.model.service;

import java.util.List;

import com.greedy.byat.common.exception.issue.IssueDeleteNoticeException;
import com.greedy.byat.common.exception.issue.IssueInsertMemberHistoryException;
import com.greedy.byat.common.exception.issue.IssueInsertVersionHistoryException;
import com.greedy.byat.common.exception.issue.IssueModifyMemberException;
import com.greedy.byat.common.exception.issue.IssueModifyNoticeException;
import com.greedy.byat.common.exception.issue.IssueModifyStatusException;
import com.greedy.byat.common.exception.issue.IssueRegistException;
import com.greedy.byat.common.exception.issue.IssueRegistMemberException;
import com.greedy.byat.common.exception.issue.IssueRegistNoticeException;
import com.greedy.byat.common.exception.issue.IssueRegistStatusHistoryException;
import com.greedy.byat.common.exception.issue.IssueRemoveException;
import com.greedy.byat.common.exception.issue.IssueRemoveMemberException;
import com.greedy.byat.common.exception.issue.IssueRemoveMemberNoticeException;
import com.greedy.byat.common.exception.issue.IssueStatusModifyNoticeException;
import com.greedy.byat.common.exception.issue.IssueUpdateContentException;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.dto.SprintMembersDTO;

public interface IssueService {

	List<SprintDTO> selectSprintList(int projectCode);

	int updateIssueStatus(IssueDTO issue) throws IssueModifyStatusException, IssueRegistStatusHistoryException, IssueStatusModifyNoticeException;

	List<IssueDTO> selectIssueList(int code);

	List<SprintMembersDTO> selectSprintMembers(int code);

	void updateIssue(IssueDTO modifyIssue) throws IssueModifyMemberException, IssueUpdateContentException, IssueInsertVersionHistoryException, IssueInsertMemberHistoryException, IssueModifyNoticeException;

	int deleteIssueMember(IssueMembersDTO removeMember) throws IssueRemoveMemberException, IssueInsertMemberHistoryException, IssueRemoveMemberNoticeException;

	int deleteIssue(int code, int changeMemberNo) throws IssueRemoveException, IssueInsertVersionHistoryException, IssueDeleteNoticeException;

	List<SprintMembersDTO> selectSprintMembersList(int projectCode);

	void insertIssue(IssueDTO registIssue) throws IssueRegistMemberException, IssueRegistException, IssueInsertVersionHistoryException, IssueRegistStatusHistoryException, IssueRegistNoticeException, IssueInsertMemberHistoryException;

}
