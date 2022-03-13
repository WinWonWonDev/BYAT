package com.greedy.byat.issue.model.dao;

import java.util.List;

import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.dto.SprintMembersDTO;

/**
 * <pre>
 * Class : IssueMapper
 * Comment : mapper.xml의 id들이 담긴 class
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
 * @see IssueController, IssueService, IssueServiceImpl, MemberDTO, ProjectDTO, IssueDTO, IssueMembersDTO, NoticeDTO, SprintDTO, SprintMembersDTO, ProjectMembersDTO
 * */
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

	int insertIssueFirstMembersHistory(IssueMembersDTO issueMembersDTO);

}
