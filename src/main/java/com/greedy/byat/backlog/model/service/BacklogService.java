package com.greedy.byat.backlog.model.service;

import java.util.List;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.common.exception.backlog.BacklogModifyException;
import com.greedy.byat.common.exception.backlog.BacklogRegistException;
import com.greedy.byat.common.exception.backlog.BacklogRemoveException;

public interface BacklogService {

	List<BacklogDTO> selectBacklogList(int projectCode);
	
	String registBacklog(BacklogDTO backlog);
	
	BacklogDTO selectBacklogDetail(int code);
	
	String modifyBacklog(BacklogDTO backlog);
	
	String removeBacklog(int code);
}
