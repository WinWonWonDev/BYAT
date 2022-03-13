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
 * 2021/02/21 (황인수) 프론트 연결 시 오류 부분 수정
 * 2021/03/04 (황인수) 백로그 태스크화 구현 메서드 작성
 * </pre>
 * @version 3
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
