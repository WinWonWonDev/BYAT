package com.greedy.byat.backlog.model.service;

import java.util.HashMap;
import java.util.List;

import com.greedy.byat.backlog.model.dto.BacklogDTO;

public interface BacklogService {

	List<BacklogDTO> selectBacklogList(int projectCode);
	
	String registBacklog(BacklogDTO backlog);
	
	BacklogDTO selectBacklogDetail(int code);
	
	String modifyBacklog(BacklogDTO backlog);
	
	String removeBacklog(int code);
	
	String registBacklogTasklize(HashMap<String, Object> infoMap);
}
