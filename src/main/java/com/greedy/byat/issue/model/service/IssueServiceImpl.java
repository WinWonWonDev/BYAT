package com.greedy.byat.issue.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.greedy.byat.issue.controller.IssueController;
import com.greedy.byat.issue.model.dao.IssueMapper;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.dto.SprintMembersDTO;

@Service
public class IssueServiceImpl implements IssueService {
 
	private final IssueMapper mapper;

	@Autowired
	public IssueServiceImpl(IssueMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<SprintDTO> selectSprintList(int projectCode) {

		List<SprintDTO> sprintList = mapper.selectSprintList(projectCode);
		
		List<IssueDTO> issueList = new ArrayList<>();
		
		List<IssueMembersDTO> issueMembersList = new ArrayList<>();
		
		for(int i = 0; i < sprintList.size(); i++) {
			
			sprintList.get(i).setProjectCode(projectCode);
			
			issueList = mapper.selectIssueList(sprintList.get(i));
			
			for(int j = 0; j < issueList.size(); j++) {
				
				issueMembersList = mapper.selectIssueMemberList(issueList.get(j).getCode());
				issueList.get(j).setIssueMemberList(issueMembersList);
			}
			
			sprintList.get(i).setIssueList(issueList);
		}
		
		return sprintList;
	}

	@Override
	public int updateIssueStatus(IssueDTO issue) throws IssueModifyStatusException, IssueRegistStatusHistoryException, IssueStatusModifyNoticeException {
		
		int result = mapper.updateIssueStatus(issue);
		
		int historyResult = mapper.insertIssueProgressHistory(issue);
		
		List<IssueMembersDTO> issueMemberList = mapper.selectIssueMemberList(issue.getCode());
		
		IssueDTO noticeIssue = mapper.selectIssue(issue.getCode());
		
		NoticeDTO notice = new NoticeDTO();
		notice.setIssueCode(noticeIssue.getCode());
		notice.setBody("\'" + noticeIssue.getTitle() + "\' 이슈의 상태가 " + noticeIssue.getProgress() + "(으)로 변경되었습니다.");
		notice.setCategory(4);
		notice.setUrl("/issue/list?code=" + noticeIssue.getProjectCode());
		notice.setStatus("N");
		
		int noticeInsertIssueStatusChangeResult = 0;
		
		for(int i = 0; i < issueMemberList.size();i ++) {
			
			notice.setNo(issueMemberList.get(i).getNo());
			noticeInsertIssueStatusChangeResult = mapper.insertNoticeIssue(notice);
			
			if(!(noticeInsertIssueStatusChangeResult > 0)) {
				
				throw new IssueStatusModifyNoticeException("이슈 상태 변경 알림 생성 실패!");
			}
		}
		
		if(!(result > 0)) {
			
			throw new IssueModifyStatusException("이슈 상태 변경 실패!");
		}
		
		if(!(historyResult > 0)) {
			
			throw new IssueRegistStatusHistoryException("이슈 상태 변경 이력 생성 실패!");
		}
		
		return result;
	}

	@Override
	public List<IssueDTO> selectIssueList(int code) {

		SprintDTO sprint = new SprintDTO();
		sprint.setCode(code);
		
		List<IssueDTO> issueList = mapper.selectIssueList(sprint);
		
		List<IssueMembersDTO> issueMembersList = new ArrayList<>();
		
		for(int i = 0; i < issueList.size(); i++) {
			
			issueMembersList = mapper.selectIssueMemberList(issueList.get(i).getCode());
			
			issueList.get(i).setIssueMemberList(issueMembersList);
		}
		
		return issueList;
	}

	@Override
	public List<SprintMembersDTO> selectSprintMembers(int code) {
		
		List<SprintMembersDTO> sprintMemberList = mapper.selectSprintMembers(code);
		
		return sprintMemberList;
	}

	@Override
	public void updateIssue(IssueDTO modifyIssue) throws IssueModifyMemberException, IssueUpdateContentException, IssueInsertVersionHistoryException, IssueInsertMemberHistoryException, IssueModifyNoticeException {

		IssueDTO boforeIssue = mapper.selectIssue(modifyIssue.getCode());
		
		modifyIssue.setVersion(boforeIssue.getVersion() + 1);
		
		int updateIssueContentResult = mapper.updateIssue(modifyIssue);
		
		int updateIssueMemberResult = 0;
		
		List<IssueMembersDTO> beforeIssueMemberList = mapper.selectIssueMemberList(modifyIssue.getCode());
		
		List<IssueMembersDTO> newIssueMemberList = new ArrayList<>();
		
		NoticeDTO notice = new NoticeDTO();
		notice.setIssueCode(modifyIssue.getCode());
		notice.setBody("\'" + boforeIssue.getTitle() + "\' 이슈가 수정되었습니다.");
		notice.setCategory(4);
		notice.setUrl("/issue/list?code=" + boforeIssue.getProjectCode());
		notice.setStatus("N");
		
		int noticeInsertIssueUpdateResult = 0;
		
		for(int i = 0; i < modifyIssue.getIssueMemberList().size(); i++) {
			
			for(int j = 0; j < beforeIssueMemberList.size(); j++) {
				
				if(modifyIssue.getIssueMemberList().get(i).getNo() == beforeIssueMemberList.get(j).getNo()) {
					break;
				}
				
				if(j == beforeIssueMemberList.size() - 1) {
					modifyIssue.getIssueMemberList().get(i).setParticipationYn("Y");
					modifyIssue.getIssueMemberList().get(i).setSprintCode(modifyIssue.getSprintCode());
					newIssueMemberList.add(modifyIssue.getIssueMemberList().get(i));
				}
				
			}
			
		}
		
		for(int i = 0; i < beforeIssueMemberList.size(); i++) {
			
			notice.setNo(beforeIssueMemberList.get(i).getNo());
			
			noticeInsertIssueUpdateResult = mapper.insertNoticeIssue(notice);
			
			if(!(noticeInsertIssueUpdateResult > 0)) {
				
				throw new IssueModifyNoticeException("이슈 수정 알림 생성 실패!");
			}
			
		}
		
		if(!(updateIssueContentResult > 0)) {
			
			throw new IssueUpdateContentException("이슈 내용 변경 실패!");
		} else {
			
			String projectTitle = mapper.selectProjectTitle(modifyIssue.getProjectCode());
			
			modifyIssue.setTitle("\'" + modifyIssue.getTitle() + "\' 이슈 수정 (" + projectTitle + ")");
			int issueVersionHistoryResult = mapper.insertIssueVersionHistory(modifyIssue);
			
			if(!(issueVersionHistoryResult > 0)) {
				
				throw new IssueInsertVersionHistoryException("이슈 버전 히스토리 생성 실패!");
			}
			
			if(newIssueMemberList != null) {
				
				notice.setBody("\'" + boforeIssue.getTitle() + "\' 의 담당자로 지정되었습니다.");
					
				for(int i = 0; i < newIssueMemberList.size(); i++) {
					
					int checkBeforeMember = mapper.checkBeforeIssueMember(newIssueMemberList.get(i));
					
					notice.setNo(newIssueMemberList.get(i).getNo());

					if(checkBeforeMember > 0) {
						
						updateIssueMemberResult = mapper.updateIssueMember(newIssueMemberList.get(i));
						
						if(!(updateIssueMemberResult > 0)) {
							
							throw new IssueModifyMemberException("이슈 담당자 변경 실패!");
						} else {
							
							int issueMembersHistoryResult = mapper.insertIssueMembersHistory(newIssueMemberList.get(i));
							
							int issueMembersNoticeResult = mapper.insertNoticeIssue(notice);
							
							if(!(issueMembersHistoryResult > 0)) {
								
								throw new IssueInsertMemberHistoryException("이슈 담당자 변경 히스토리 생성 실패!");
							}
							
							if(!(issueMembersNoticeResult > 0)) {
								
								throw new IssueModifyNoticeException("이슈 담당자 지정 알림 생성 실패!");
							}
							
						}
						
					} else {
						
						updateIssueMemberResult = mapper.insertIssueMember(newIssueMemberList.get(i));
						
						if(!(updateIssueMemberResult > 0)) {
							
							throw new IssueModifyMemberException("이슈 담당자 변경 실패!");
						} else {
							
							int issueMembersHistoryResult = mapper.insertIssueMembersHistory(newIssueMemberList.get(i));
							
							int issueMembersNoticeResult = mapper.insertNoticeIssue(notice);
							
							if(!(issueMembersHistoryResult > 0)) {
								
								throw new IssueInsertMemberHistoryException("이슈 담당자 변경 히스토리 생성 실패!");
							}
							
							if(!(issueMembersNoticeResult > 0)) {
								
								throw new IssueModifyNoticeException("이슈 담당자 지정 알림 생성 실패!");
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}

	@Override
	public int deleteIssueMember(IssueMembersDTO removeMember) throws IssueRemoveMemberException, IssueInsertMemberHistoryException, IssueRemoveMemberNoticeException {

		int result = mapper.deleteIssueMember(removeMember);
		
		int issueRemoveMemberNoticeResult = 0;
		
		IssueDTO issue = mapper.selectIssue(removeMember.getCode());
		
		NoticeDTO notice = new NoticeDTO();
		notice.setIssueCode(issue.getCode());
		notice.setBody("\'" + issue.getTitle() + "\' 이슈의 담당자에서 제외되었습니다.");
		notice.setCategory(4);
		notice.setUrl("/issue/list?code=" + issue.getProjectCode());
		notice.setStatus("N");
		notice.setNo(removeMember.getNo());
		
		if(!(result > 0)) {
			
			throw new IssueRemoveMemberException("이슈 담당자 제외 실패!");
		} else {
			
			int insertMemberHistoryResult = mapper.insertIssueMembersHistory(removeMember);
			
			issueRemoveMemberNoticeResult = mapper.insertNoticeIssue(notice);
			
			if(!(insertMemberHistoryResult > 0)) {
				
				throw new IssueInsertMemberHistoryException("이슈 담당자 변경 히스토리 생성 실패!");
			}
			
			if(!(issueRemoveMemberNoticeResult > 0)) {
				
				throw new IssueRemoveMemberNoticeException("이슈 멤버 제외 알림 생성 실패!");
			}
		}
		
		return result;
	}

	@Override
	public int deleteIssue(int code, int changeMemberNo) throws IssueRemoveException, IssueInsertVersionHistoryException, IssueDeleteNoticeException {
		
		int result = mapper.deleteIssue(code);
		
		int issueDeleteNoticeResult = 0;
		
		IssueDTO issue = new IssueDTO();
		
		List<IssueMembersDTO> issueMemberList = mapper.selectIssueMemberList(code);
		
		NoticeDTO notice = new NoticeDTO();
		
		if(!(result > 0)) {
			
			throw new IssueRemoveException("이슈 삭제 실패!");
		} else {
			
			issue = mapper.selectIssue(code);
			issue.setWriter(changeMemberNo);
			issue.setVersion(issue.getVersion() + 1);
			
			notice.setIssueCode(issue.getCode());
			notice.setBody("\'" + issue.getTitle() + "\' 이슈가 삭제되었습니다.");
			notice.setCategory(4);
			notice.setUrl("/issue/list?code=" + issue.getProjectCode());
			notice.setStatus("N");

			for(int i = 0; i < issueMemberList.size(); i++) {
				
				notice.setNo(issueMemberList.get(i).getNo());
				issueDeleteNoticeResult = mapper.insertNoticeIssue(notice);
				
				if(!(issueDeleteNoticeResult > 0)) {
					
					throw new IssueDeleteNoticeException("이슈 삭제 알림 생성 실패!");
				}
			}
			
			String projectTitle = mapper.selectProjectTitle(issue.getProjectCode());
			
			issue.setTitle("\'" + issue.getTitle() + "\' 이슈 삭제 (" + projectTitle + ")");
			int versionHistoryInsertResult = mapper.insertIssueVersionHistory(issue);
			
			if(!(versionHistoryInsertResult > 0)) {
				
				throw new IssueInsertVersionHistoryException("이슈 삭제 버전 히스토리 생성 실패!");
			}
		}
		
		return issue.getProjectCode();
	}

	@Override
	public List<SprintMembersDTO> selectSprintMembersList(int projectCode) {
	
		int sprintCode = mapper.selectSprintCode(projectCode);
		
		List<SprintMembersDTO> selectSprintMemberList = mapper.selectSprintMembers(sprintCode);
		
		return selectSprintMemberList;
	}

	@Override
	public void insertIssue(IssueDTO registIssue) throws IssueRegistMemberException, IssueRegistException, IssueInsertVersionHistoryException, IssueRegistStatusHistoryException, IssueRegistNoticeException, IssueInsertMemberHistoryException {
		
		int result = mapper.registIssue(registIssue);
		
		int insertIssueMemberResult = 0;
		int insertIssuenoticeResult = 0;
		
		String projectTitle = mapper.selectProjectTitle(registIssue.getProjectCode());
		
		NoticeDTO notice = new NoticeDTO();
		notice.setBody("\'" + registIssue.getTitle() + "\'이슈가 생성되었습니다.");
		notice.setCategory(4);
		notice.setUrl("/issue/list?code=" + registIssue.getProjectCode());
		notice.setStatus("N");
		
		if(!(result > 0)) {
			
			throw new IssueRegistException("이슈 생성 실패!");
			
		} else {
			
			registIssue.setVersion(1);
			
			registIssue.setTitle("\'" + registIssue.getTitle() + "\' 이슈 생성 (" + projectTitle + ")");
			
			int issueRegistHistoryResult = mapper.insertIssueFirstVersionHistory(registIssue);
			int issueRegistRrogressHistoryResult = mapper.insertIssueFirstProgressHistory(registIssue);
			
			for(int i = 0; i < registIssue.getIssueMemberList().size(); i++) {

				notice.setNo(registIssue.getIssueMemberList().get(i).getNo());
				
				insertIssueMemberResult = mapper.insertFirstIssueMember(registIssue.getIssueMemberList().get(i));
				insertIssuenoticeResult = mapper.insertNoticeIssueFirst(notice);
				int insertIssueMemberHistoryResult = mapper.insertIssueFirstMembersHistory(registIssue.getIssueMemberList().get(i));
				
				if(!(insertIssueMemberResult > 0)) {
					
					throw new IssueRegistMemberException("이슈 담당자 지정 실패!");
				} 
				
				if(!(issueRegistHistoryResult > 0)) {
					
					throw new IssueInsertVersionHistoryException("이슈 생성 버전 히스토리 생성 실패!");
				}
				
				if(!(issueRegistRrogressHistoryResult > 0)) {
					
					throw new IssueRegistStatusHistoryException("이슈 생성 상태 변경 이력 생성 실패!");
				}

				if(!(insertIssuenoticeResult > 0)) {
					
					throw new IssueRegistNoticeException("이슈 생성 알림 생성 실패!");
				}
				
				if(!(insertIssueMemberHistoryResult > 0)) {
					
					throw new IssueInsertMemberHistoryException("이슈 생성 담당자 지정 이력 생성 실패!");
				}
				
			}
		}
		
	}

}


