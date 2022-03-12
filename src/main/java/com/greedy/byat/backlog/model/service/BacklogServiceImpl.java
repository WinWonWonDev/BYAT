package com.greedy.byat.backlog.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.backlog.model.dao.BacklogMapper;
import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

/**
 * <pre>
 * Class : BacklogServiceImpl
 * Comment : BacklogService를 상속받아 method들을 재정의한 Class
 * History
 * 2021/02/17 (황인수) 처음 작성함
 * </pre>
 * @version 1.0.0
 * @author 황인수
 * @see BacklogController, BacklogService, BacklogMapper
 * */
@Service
public class BacklogServiceImpl implements BacklogService {
 
	private final BacklogMapper mapper;

	@Autowired
	public BacklogServiceImpl(BacklogMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * Backlog 생성용 메서드
	 * 
	 * @method registBacklog
	 * @param backlog 생성하려는 백로그의 정보
	 * @return message mapper 수행 결과에 따른 message
	 * */
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
	
	/**
	 * Backlog 상세 조회용 메서드
	 * 
	 * @method selectBacklogDetail
	 * @param code 조회하려는 backlog의 code
	 * @return backlog 조회하려는 backlog의 정보
	 * */
	@Override
	public BacklogDTO selectBacklogDetail(int code) {
		
		BacklogDTO backlog = mapper.selectBacklogDetail(code);
		
		return backlog;
	}
	
	/**
	 * Backlog 수정용 메서드
	 * 
	 * @method modifyBacklog
	 * @param backlog 수정하려는 backlog의 정보
	 * @return message mapper 수행 결과에 따른 message
	 * */
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
	
	/**
	 * Backlog 삭제용 메서드
	 * 
	 * @method removeBacklog
	 * @param code 삭제하려는 backlog의 code
	 * @return message mapper 수행 결과에 따른 message
	 * */
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
	
	/**
	 * Backlog task화 메서드
	 * 
	 * @method registBacklogTasklize
	 * @param infoMap mapper를 수행하기 위한 변수들이 담겨 있는 HashMap
	 * @return message mapper 수행 결과에 따른 message
	 * */
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