package com.greedy.byat.issue.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.issue.IssueModifyStatusException;
import com.greedy.byat.common.exception.issue.IssueRegistStatusHistoryException;
import com.greedy.byat.issue.model.dao.IssueMapper;
import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;

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
		
		for(int i = 0; i < sprintList.size(); i++) {
			
			sprintList.get(i).setProjectCode(projectCode);
			
			issueList = mapper.selectIssueList(sprintList.get(i));
			
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

	







}


