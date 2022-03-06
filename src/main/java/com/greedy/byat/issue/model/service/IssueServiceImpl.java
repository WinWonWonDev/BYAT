package com.greedy.byat.issue.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.issue.IssueInsertMemberHistoryException;
import com.greedy.byat.common.exception.issue.IssueInsertVersionHistoryException;
import com.greedy.byat.common.exception.issue.IssueModifyMemberException;
import com.greedy.byat.common.exception.issue.IssueModifyStatusException;
import com.greedy.byat.common.exception.issue.IssueRegistStatusHistoryException;
import com.greedy.byat.common.exception.issue.IssueRemoveException;
import com.greedy.byat.common.exception.issue.IssueRemoveMemberException;
import com.greedy.byat.common.exception.issue.IssueUpdateContentException;
import com.greedy.byat.issue.model.dao.IssueMapper;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
import com.greedy.byat.member.model.dto.MemberDTO;
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
	public int updateIssueStatus(IssueDTO issue) throws IssueModifyStatusException, IssueRegistStatusHistoryException {
		
		System.out.println("!! : " + issue);

		int result = mapper.updateIssueStatus(issue);
		
		System.out.println("?? : " + issue);
		
		int historyResult = mapper.insertIssueProgressHistory(issue);
		
		System.out.println(historyResult);
		
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
	public void updateIssue(IssueDTO modifyIssue) throws IssueModifyMemberException, IssueUpdateContentException, IssueInsertVersionHistoryException, IssueInsertMemberHistoryException {

		IssueDTO boforeIssue = mapper.selectIssue(modifyIssue.getCode());
		
		modifyIssue.setVersion(boforeIssue.getVersion() + 1);
		
		int updateIssueContentResult = mapper.updateIssue(modifyIssue);
		
		int updateIssueMemberResult = 0;
		
		List<IssueMembersDTO> beforeIssueMemberList = mapper.selectIssueMemberList(modifyIssue.getCode());
		
		List<IssueMembersDTO> newIssueMemberList = new ArrayList<>();
		
		for(int i = 0; i < modifyIssue.getIssueMemberList().size(); i++) {
			
			for(int j = 0; j < beforeIssueMemberList.size(); j++) {
				
				if(modifyIssue.getIssueMemberList().get(i).getNo() == beforeIssueMemberList.get(j).getNo()) {
					break;
				}
				
				if(j == beforeIssueMemberList.size() - 1) {
					modifyIssue.getIssueMemberList().get(i).setParticipationYn("Y");
					newIssueMemberList.add(modifyIssue.getIssueMemberList().get(i));
				}
				
			}
			
		}
		
		System.out.println("newMember : " + newIssueMemberList);
		
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
				
					
				for(int i = 0; i < newIssueMemberList.size(); i++) {

					int checkBeforeMember = mapper.checkBeforeIssueMember(newIssueMemberList.get(i));
					
					if(checkBeforeMember > 0) {
						
						updateIssueMemberResult = mapper.updateIssueMember(newIssueMemberList.get(i));
						
						if(!(updateIssueMemberResult > 0)) {
							
							throw new IssueModifyMemberException("이슈 담당자 변경 실패!");
						} else {
							
							int issueMembersHistoryResult = mapper.insertIssueMembersHistory(newIssueMemberList.get(i));
							
							if(!(issueMembersHistoryResult > 0)) {
								
								throw new IssueInsertMemberHistoryException("이슈 담당자 변경 히스토리 생성 실패!");
							}
							
						}
						
					} else {
						
						updateIssueMemberResult = mapper.insertIssueMember(newIssueMemberList.get(i));
						
						if(!(updateIssueMemberResult > 0)) {
							
							throw new IssueModifyMemberException("이슈 담당자 변경 실패!");
						} else {
							
							int issueMembersHistoryResult = mapper.insertIssueMembersHistory(newIssueMemberList.get(i));
							
							if(!(issueMembersHistoryResult > 0)) {
								
								throw new IssueInsertMemberHistoryException("이슈 담당자 변경 히스토리 생성 실패!");
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
		
		
	}

	@Override
	public int deleteIssueMember(IssueMembersDTO removeMember) throws IssueRemoveMemberException, IssueInsertMemberHistoryException {

		int result = mapper.deleteIssueMember(removeMember);
		
		if(!(result > 0)) {
			
			throw new IssueRemoveMemberException("이슈 담당자 제외 실패!");
		} else {
			
			int insertMemberHistoryResult = mapper.insertIssueMembersHistory(removeMember);
			
			if(!(insertMemberHistoryResult > 0)) {
				
				throw new IssueInsertMemberHistoryException("이슈 담당자 변경 히스토리 생성 실패!");
			}
		}
		
		return result;
	}

	@Override
	public int deleteIssue(int code, int changeMemberNo) throws IssueRemoveException, IssueInsertVersionHistoryException {
		
		int result = mapper.deleteIssue(code);
		
		IssueDTO issue = new IssueDTO();
		
		if(!(result > 0)) {
			
			throw new IssueRemoveException("이슈 삭제 실패!");
		} else {
			
			issue = mapper.selectIssue(code);
			issue.setWriter(changeMemberNo);
			issue.setVersion(issue.getVersion() + 1);
			
			System.out.println("removeissue : " + issue);
			
			int versionHistoryInsertResult = mapper.insertIssueVersionHistory(issue);
			
			if(!(versionHistoryInsertResult > 0)) {
				
				throw new IssueInsertVersionHistoryException("이슈 삭제 버전 히스토리 생성 실패!");
			}
		}
		
		return issue.getProjectCode();
	}

}


