package com.greedy.byat.backlog.model.service;

import java.util.List;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.common.exception.backlog.BacklogModifyException;
import com.greedy.byat.common.exception.backlog.BacklogRegistException;
import com.greedy.byat.common.exception.backlog.BacklogRemoveException;

public interface BacklogService {

	List<BacklogDTO> selectBacklogList(int projectCode);
	
	void registBacklog(BacklogDTO backlog) throws BacklogRegistException;
	
	BacklogDTO selectBacklogDetail(int code);
	
	void modifyBacklog(BacklogDTO backlog) throws BacklogModifyException;
	
	void removeBacklog(int code) throws BacklogRemoveException;
}
