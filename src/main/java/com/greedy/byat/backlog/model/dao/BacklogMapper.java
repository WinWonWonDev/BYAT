package com.greedy.byat.backlog.model.dao;

import java.util.HashMap;
import java.util.List;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

/**
 * <pre>
 * Class : BacklogMapper
 * Comment : mapper.xml의 id들이 담긴 Class
 * History
 * 2021/02/17 (황인수) 처음 작성
 * </pre>
 * @version 1.0.0
 * @author 황인수
 * @see BacklogController, BacklogService, BacklogServiceImpl, BacklogDTO
 * */
public interface BacklogMapper {
	
	List<BacklogDTO> selectBacklogList(int projectCode);
	
	BacklogDTO selectBacklogDetail(int code);
	
	int insertBacklog(BacklogDTO backlog);
	
	int updateBacklog(BacklogDTO backlog);
	
	int deleteBacklog(int code);
	
	int insertBacklogTasklize(HashMap<String, Integer> infoMap);
	
	List<TaskDTO> selectTaskList(int sprintCode);

	int checkSprintProgress(HashMap<String, Integer> infoMap);
}
