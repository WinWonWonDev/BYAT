package com.greedy.byat.backlog.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.backlog.model.dao.BacklogMapper;
import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

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
		
		BacklogDTO backlog = mapper.selectBacklogDetail(code);
		
		return backlog;
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

	@Override
	public String registBacklogTasklize(HashMap<String, Integer> infoMap) {
		
		String message = null;
		int checkSprintProgressResult = mapper.checkSprintProgress(infoMap);
		int result = mapper.insertBacklogTasklize(infoMap);
		
		if(checkSprintProgressResult > 0) {
			if(result > 0) {
				message = "백로그가 스프린트에 담겼습니다 !!!";
			} else {
				message = "백로그를 담는 데에 실패했습니다 ...";
			}	
		} else {
			message = "진행중인 스프린트가 없습니다.";
		}
		
		
		return message;
	}

	







}


