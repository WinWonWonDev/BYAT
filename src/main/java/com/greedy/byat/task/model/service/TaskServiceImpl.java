package com.greedy.byat.task.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dao.TaskMapper;
import com.greedy.byat.task.model.dto.TaskDTO;

@Service
public class TaskServiceImpl implements TaskService {
 
	private final TaskMapper mapper;

	@Autowired
	public TaskServiceImpl(TaskMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public String registTask(TaskDTO task) {
		
		int projectCode = task.getProjectCode();
		
		String message = null;
		
		/* 진행중이거나 진행전인 스프린트가 있는지 확인 결과 */
		int progressTaskResult = mapper.checkSprintProgress(projectCode);
		
		/* 태스크에 스프린트 코드를 넣어줌 */
		task.setSprintCode(mapper.selectSprintCode(projectCode));
		
		/* 진행중인 태크스가 있다면 */
		if(progressTaskResult > 0) {
			
			/* 태스크 생성 결과 */
			int result1 = mapper.insertTask(task);
			
			String sprintTitle = mapper.selectSprintTitle(task);
			
			task.setTitle("\\'" + task.getTitle() + "\\'" + "태스크 생성(" + sprintTitle + "");
			
			/* 태스크 버전 이력 생성 결과 */
			int result2 = mapper.insertTaskVersionHistory(task);
			
			/* 태스크 상태변경 이력 생성 결과 */
			int result3 = mapper.insertTaskProgressHistory(task);
			
			System.out.println(result1);
			System.out.println(result2);
			System.out.println(result3);
			
			if(!(result1 > 0) && !(result2 > 0) && !(result3 > 0)) {
				
				message = "태스크 생성 실패";
			} else {
				
				message = "태스크를 생성하였습니다.";
			}
			
		} else {
			
			message = "진행중인 스프린트가 없습니다.";
		}
		
		return message;
	}

	@Override
	public TaskDTO selectTaskDetail(int taskCode) {

		TaskDTO task = mapper.selectTask(taskCode);
		
		return task;
	}

	@Override
	public List<MemberDTO> selectProjectMembers(int projectCode) {
		
		List<MemberDTO> projectMembers = mapper.selectProjectMembers(projectCode);
		
		return projectMembers;
	}


	







}


