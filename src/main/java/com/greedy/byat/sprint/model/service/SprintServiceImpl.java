package com.greedy.byat.sprint.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.common.exception.sprint.RegistSprintException;
import com.greedy.byat.sprint.model.dao.SprintMapper;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

@Service
public class SprintServiceImpl implements SprintService {
 
	private final SprintMapper mapper;

	@Autowired
	public SprintServiceImpl(SprintMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<SprintDTO> selectSprintList(int projectNo) {

		List<SprintDTO> sprintList = mapper.selectSprintList(projectNo);
		
		return sprintList;
	}
	
	@Override
	public List<BacklogDTO> selectBacklogList(int projectCode) {
		
		List<BacklogDTO> backlogList = mapper.selectBacklogList(projectCode);
		
		return backlogList;
	}

	@Override
	public String registSprint(SprintDTO sprint) {
		
		int projectCode = sprint.getProjectCode();
		
		/*진행중인 스프린트가 있으면 스프린트를 생성할 수 없다.*/
		int checkResult = mapper.checkSprintProgress(projectCode);
		
		String result = null;
		
		if(!(checkResult > 0)) {
			
			int result1 = mapper.insertSprint(sprint);
		
			int result2 = mapper.insertSprintVersionHistory(sprint);
		
			int result3 = mapper.insertSprintProgressHistory(sprint);
		
			if (!(result1 > 0) && !(result2 > 0) && !(result3 > 0)) {
				result = "스프린트 생성 실패";
			} else {
				result = "스프린트를 생성하였습니다.";
			}
		} else {
			result = "완료되지 않은 스프린트가 존재합니다.";
		}
		
		return result;
	}

	@Override
	public void modifySprint(SprintDTO sprint) {
		
		int result1 = mapper.updateSprint(sprint);
		
		int result2 = mapper.insertSprintVersionHistory2(sprint);
		
		if(!(result1 > 0) && !(result2 > 0)) {
			System.out.println("스프린트 수정 실패");
		}
	}
	
	@Override
	public SprintDTO selectSprint(int sprintCode) {
		
		SprintDTO sprint = mapper.selectSprint(sprintCode);
		
		return sprint;
	}
	
	@Override
	public void removeSprint(int sprintCode) {
		
		int result = mapper.deleteSprint(sprintCode);
		
		if(!(result > 0)) {
			System.out.println("스프린트 삭제 실패");
		}
	}

	@Override
	public String selectProjectProgress(int projectCode) {
		
		String projectProgress = mapper.selectProjectProgress(projectCode);
		
		return projectProgress;
	}

	@Override
	public List<TaskDTO> selectTaskList(int sprintCode) {
		
		List<TaskDTO> taskList = mapper.selectTaskList(sprintCode);
		
		return taskList;
	}

	@Override
	public void startSprint(int projectCode) {
		
//		/* 스프린트 상태 진행중으로 변경 */
//		int result1 = mapper.updateSprintProgress(projectCode);
//		
//		/* 스프린트 상태 변경 이력 추가 */
//		int result2 = mapper.insertSprintProgressHistory3(projectCode);
//		
//		/* 스프린트의 태스크 중에 백로그 코드를 가진 태스크가 있으면 백로그 코드를 가진 백로그의 상태도 변경해줘야 하기 때문에 태스크의 백로그 코드들을 가져온다.*/
//		List<Integer> backlogCodeList = mapper.selectTaskBacklogCode(projectCode);
//		
//		for(int i = 0; i < backlogCodeList.size; i++){
//			
//		}
//		
//		int result3 = mapper.backlogProgressHistroy(projectCode);
//				
//		/* 태스크 상태 진행중으로 변경 */
//		int result4 = mapper.updateTaskProgress(projectCode);
//		
//		/* 태스크 상태 변경 이력 추가*/
//		int result5 = mapper.insertTaskProgressHistory(projectCode);
//		
//				
//		if(!(result1 > 0)) {
//			System.out.println("스프린트 시작 실패");
//		}
	}


	







}


