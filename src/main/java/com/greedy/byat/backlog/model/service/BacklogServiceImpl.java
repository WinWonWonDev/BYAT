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
	public List<BacklogDTO> selectAllBacklogList(int projectCode) {
		
		List<BacklogDTO> backlogList = mapper.selectAllBacklogList(projectCode);
		
		return backlogList;
	}
	
	/* Backlog 생성용 메서드 */
	@Override
	public void registBacklog(BacklogDTO backlog) throws BacklogRegistException {
		
		int result = mapper.insertBacklog(backlog);
		
		if(!(result > 0)) {
			throw new BacklogRegistException("Backlog 생성에 실패하셨습니다.");
		}
	}
	
	/* Backlog 상세 조회용 메서드 */
	@Override
	public BacklogDTO selectBacklogDetail(int code) {
		
		BacklogDTO backlogDetail = mapper.selectBacklogDetail(code);
		
		return backlogDetail;
	}
	
	/* Backlog 수정용 메서드 */
	@Override
	public void modifyBacklog(BacklogDTO backlog) throws BacklogModifyException {
		
		int result = mapper.updateBacklog(backlog);
		
		if(!(result > 0)) {
			throw new BacklogModifyException("Backlog 수정에 실패하셨습니다.");
		}
	}
	
	/* Backlog 삭제용 메서드 */
	@Override
	public void removeBacklog(int code) throws BacklogRemoveException {
		
		int result = mapper.deleteBacklog(code);
		
		if(!(result > 0)) {
			throw new BacklogRemoveException("Backlog 삭제에 실패하셨습니다.");
		}
		
	}

	







}


