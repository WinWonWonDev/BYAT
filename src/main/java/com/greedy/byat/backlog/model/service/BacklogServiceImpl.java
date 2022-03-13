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
 * 2021/02/21 (황인수) 프론트 연결 시 오류 부분 수정
 * 2021/03/04 (황인수) 백로그 태스크화 구현 메서드 작성
 * 2021/03/06 (황인수) 백로그 태스크화  조건 메서드 보완
 * </pre>
 * @version 4
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
	 * 메소드 registBacklog에 관한 문서화 주석
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
	 * 메소드 selectBacklogDetail에 관한 문서화 주석
	 * @param code 조회하려는 backlog의 code
	 * @return backlog 조회하려는 backlog의 정보
	 * */
	@Override
	public BacklogDTO selectBacklogDetail(int code) {
		
		BacklogDTO backlog = mapper.selectBacklogDetail(code);
		
		return backlog;
	}
	
	/**
	 * 메소드 modifyBacklog에 관한 문서화 주석
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
	 * 메소드 removeBacklog에 관한 문서화 주석
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
	 * 메소드 registBacklogTasklize에 관한 문서화 주석
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