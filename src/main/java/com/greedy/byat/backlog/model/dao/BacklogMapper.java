package com.greedy.byat.backlog.model.dao;

import java.util.List;

import com.greedy.byat.backlog.model.dto.BacklogDTO;

public interface BacklogMapper {
	
	List<BacklogDTO> selectAllBacklogList(int projectCode);
	
	BacklogDTO selectBacklogDetail(int code);
	
	int insertBacklog(BacklogDTO backlog);
	
	int updateBacklog(BacklogDTO backlog);
	
	int deleteBacklog(int code);
}
