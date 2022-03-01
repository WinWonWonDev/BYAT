package com.greedy.byat.issue.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.issue.IssueInsertMemberHistoryException;
import com.greedy.byat.common.exception.issue.IssueInsertVersionHistoryException;
import com.greedy.byat.common.exception.issue.IssueModifyMemberException;
import com.greedy.byat.common.exception.issue.IssueModifyStatusException;
import com.greedy.byat.common.exception.issue.IssueRegistStatusHistoryException;
import com.greedy.byat.common.exception.issue.IssueUpdateContentException;
import com.greedy.byat.issue.model.dao.IssueMapper;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.issue.model.dto.IssueMembersDTO;
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

		//문제 생기면 mapper membersDTO result맵 확인
		
		IssueDTO boforeIssue = mapper.selectIssue(modifyIssue.getCode()); //만들어야함
		
		modifyIssue.setVersion(boforeIssue.getVersion() + 1);
		
		int updateIssueContextResult = mapper.updateIssue(modifyIssue);  //만들어야함
		
		int updateIssueMemberResult = 0;
		
		List<IssueMembersDTO> beforeIssueMemberList = mapper.selectIssueMemberList(modifyIssue.getCode());
		
		List<IssueMembersDTO> newIssueMemberList = new ArrayList<>();
		
		int newMemberCount = 0;
		
		for(int i = 0; i < modifyIssue.getIssueMemberList().size(); i++) {
			
			for(int j = 0; j < beforeIssueMemberList.size(); j++) {
				
				if(modifyIssue.getIssueMemberList().get(i).getNo() == beforeIssueMemberList.get(j).getNo()) {
					continue;
				}
				
				if(j == beforeIssueMemberList.size() - 1) {
					newIssueMemberList.add(newMemberCount, modifyIssue.getIssueMemberList().get(i));
					newMemberCount++;
				}
				
			}
			
		}
		
		System.out.println("newMember : " + newIssueMemberList);
		
		if(!(updateIssueContextResult > 0)) {
			
			throw new IssueUpdateContentException("이슈 내용 변경 실패!");
		} else {
			
			int issueVersionHistoryResult = mapper.insertIssueVersionHistory(modifyIssue); //만들어야함
			
			if(!(issueVersionHistoryResult > 0)) {
				
				throw new IssueInsertVersionHistoryException("이슈 버전 히스토리 생성 실패!");
			}
			
			if(newIssueMemberList != null) {
				
				for(int i = 0; i < newIssueMemberList.size(); i++) {
					
					updateIssueMemberResult = mapper.updateIssueMember(newIssueMemberList); //만들어야함
					
					if(!(updateIssueMemberResult > 0)) {
						
						throw new IssueModifyMemberException("이슈 담당자 변경 실패!");
					} else {
						
						int issueMembersHistoryResult = mapper.insertIssueMembersHistory(newIssueMemberList); //만들어야함
						
						if(!(issueMembersHistoryResult > 0)) {
							
							throw new IssueInsertMemberHistoryException("이슈 담당자 변경 히스토리 생성 실패!");
						}
						
					}
					
				}
				
			}
			
		}
		
		
		
	}

	







}


