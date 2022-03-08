package com.greedy.byat.issue.model.dao;

import java.util.List;

import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;
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

	int insertIssueMembersHistory(IssueMembersDTO issueMembersDTO);

	int insertIssueVersionHistory(IssueDTO modifyIssue);

	IssueDTO selectIssue(int code);

	int insertIssueMember(IssueMembersDTO issueMembersDTO);

	int checkBeforeIssueMember(IssueMembersDTO issueMembersDTO);

	int updateIssueMember(IssueMembersDTO issueMembersDTO);

	int deleteIssueMember(IssueMembersDTO removeMember);

	int deleteIssue(int code);

	String selectProjectTitle(int projectCode);

	int insertNoticeIssue(NoticeDTO notice);

	int selectSprintCode(int projectCode);

	int registIssue(IssueDTO registIssue);

	int insertFirstIssueMember(IssueMembersDTO issueMembersDTO);

	int insertIssueFirstVersionHistory(IssueDTO registIssue);

	int insertIssueFirstProgressHistory(IssueDTO registIssue);

	int insertNoticeIssueFirst(NoticeDTO notice);

}
