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

/**
 * <pre>
 * Class : IssueService
 * Comment : IssueController의 요청을 처리하는 Interface 입니다.
 * History
 * 2022/02/25 (박인근) 처음 작성
 * 2022/02/26 (박인근) 이슈 목록 조회 관련 코드 추가
 * 2022/02/27 (박인근) 이슈 목록 조회, 이슈 상세 조회 관련 코드 추가
 * 2022/02/28 (박인근) 이슈 상태 변경 관려 코드 추가
 * 2022/03/01 (박인근) 이슈 수정 관련 코드 추가
 * 2022/03/02 (박인근) 이슈 수정(담당자 변경), 이슈 담당자 제외 관련 코드 추가
 * 2022/03/03 (박인근) 이슈 삭제 관련 코드 추가
 * 2022/03/07 (박인근) 이슈 관련 알림 생성 코드 추가
 * </pre>
 * @version 8
 * @author 박인근
 * see IssueDTO, IssueMembersDTO, IssueController, IssueServiceImpl, IssueMapper.java, IssueMapper.xml, NoticeDTO, MemberDTO, SprintDTO, SprintMembersDTO
 * */
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
