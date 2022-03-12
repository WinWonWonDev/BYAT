package com.greedy.byat.backlog.model.service;

import java.util.HashMap;
import java.util.List;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

/**
 * <pre>
 * Class : BacklogService
 * Comment : BacklogController의 요청을 처리하는 Interface
 * History
 * 2021/02/17 (황인수) 처음 작성
 * </pre>
 * @version 1.0.0
 * @author 황인수
 * @see BacklogController, BacklogServiceImpl, BacklogMapper, BacklogDTO
 * */
public interface BacklogService {
	
	String registBacklog(BacklogDTO backlog);
	
	BacklogDTO selectBacklogDetail(int code);
	
	String modifyBacklog(BacklogDTO backlog);
	
	String removeBacklog(int code);
	
	String registBacklogTasklize(HashMap<String, Integer> infoMap);
}
