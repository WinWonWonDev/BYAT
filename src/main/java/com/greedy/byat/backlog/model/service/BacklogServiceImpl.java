package com.greedy.byat.backlog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.backlog.model.dao.BacklogMapper;
import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.common.exception.backlog.BacklogModifyException;
import com.greedy.byat.common.exception.backlog.BacklogRegistException;
import com.greedy.byat.common.exception.backlog.BacklogRemoveException;

@Service
public class BacklogServiceImpl implements BacklogService {
 
	private final BacklogMapper mapper;

	@Autowired
	public BacklogServiceImpl(BacklogMapper mapper) {
		this.mapper = mapper;
	}
	
	/* Backlog 전체 조회용 메서드 */
	@Override
	public List<BacklogDTO> selectBacklogList(int projectCode) {
		
		List<BacklogDTO> backlogList = mapper.selectBacklogList(projectCode);
		
		return backlogList;
	}
	
	/* Backlog 생성용 메서드 */
	@Override
	public String registBacklog(BacklogDTO backlog) {
		
		int result = mapper.insertBacklog(backlog);
		
		String message = null;
		
		if(!(result > 0)) {
			message = "백로그 생성 실패 ...";
		} else {
			message = "백로그 생성 성공 !!!"; 
		}
		
		return message;
	}
	
	/* Backlog 상세 조회용 메서드 */
	@Override
	public BacklogDTO selectBacklogDetail(int code) {
		
		BacklogDTO backlogDetail = mapper.selectBacklogDetail(code);
		
		return backlogDetail;
	}
	
	/* Backlog 수정용 메서드 */
	@Override
	public String modifyBacklog(BacklogDTO backlog) {
		
		int result = mapper.updateBacklog(backlog);
		String message = null;
		
		if(!(result > 0)) {
			message = "백로그 수정 실패 ...";
		} else {
			message = "백로그 수정 성공 !!!";
		}
		
		return message;
	}
	
	/* Backlog 삭제용 메서드 */
	@Override
	public String removeBacklog(int code) {
		
		int result = mapper.deleteBacklog(code);
		String message = null;
		
		if(!(result > 0)) {
			message = "백로그 삭제 실패 ...";
		} else {
			message = "백로그 삭제 성공 !!!";
		}
		
		return message;
	}

	







}


