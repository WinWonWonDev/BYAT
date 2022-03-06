package com.greedy.byat.task.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dao.TaskMapper;
import com.greedy.byat.task.model.dto.TaskDTO;
import com.greedy.byat.task.model.dto.TaskMembersDTO;

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
			
			task.setTitle("\'" + task.getTitle() + "\'" + "태스크 생성(" + sprintTitle + ")");
			
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

	@Override
	public String selectTaskParticipation(Map<String, Integer> taskParticipation) {

		int result = mapper.selectTaskParticipation(taskParticipation);
		
		return (result > 0)? "Y" : "N";
	}

	@Override
	public String registTaskMembers(Map<String, Integer> taskParticipation) {

		String message = null;
		
		int checkResult1 = mapper.checkWasTaskMembers(taskParticipation);
		int checkResult2 = mapper.checkWasSprintMembers(taskParticipation);
		int checkResult3 = mapper.checkIsSprintMembers(taskParticipation);
		
		int taskMemberResult = 0;
		int sprintMembersResult = 0;
		
		/* 이미 스프린트 구성원인지 확인한다*/
		if(checkResult3 == 0) {			 //1														//처음 참가 하는 거라면
			
			/* 새로 스프린트 구성원으로 등록한다.*/
			sprintMembersResult = mapper.insertSprintMembers(taskParticipation);
		} else {
			
			if(checkResult2 > 0) {																	//참가를 했던 경험이 있는지
				
				sprintMembersResult = 1;
			} else {
				
				sprintMembersResult = mapper.changeSprintMembersParticipation(taskParticipation);	//참가를 했었던 적이 있으면
			}
		}
		
		if(checkResult1 > 0) {
			
			taskMemberResult = mapper.changeTaskMembersParticipation(taskParticipation);
		} else {
			
			taskMemberResult = mapper.insertTaskMembers(taskParticipation);
		}

		int historyResult2 = mapper.insertSprintMembersHistory(taskParticipation);
		int historyResult1 = mapper.insertTaskMembersHistory(taskParticipation);

		System.out.println(taskMemberResult);
		System.out.println(sprintMembersResult);
		System.out.println(historyResult1);
		System.out.println(historyResult2);
		
		if(sprintMembersResult > 0 && taskMemberResult > 0 && historyResult1 > 0 && historyResult2 > 0) {
			
			message = "태스크에 참가하셨습니다.";
		} else {
			
			message = "참가 실패";
		}
		
		return message;
	}

	@Override
	public List<TaskMembersDTO> selectTaskMembers(int taskCode) {
		
		List<TaskMembersDTO> taskMembers = mapper.selectTaskMembers(taskCode);
		
		return taskMembers;
	}

	@Override
	public String modifyTask(TaskDTO task) {
		
		String message = null;
		
		int result1 = mapper.updateTask(task);
		
		int result2 = mapper.insertTaskVersionHistory2(task);
		
		if(result1 > 0 && result2 > 0) {
			message =  "태스크를 수정하셨습니다.";
		} else {
			message = "태스크 수정 실패";
		}
		
		return message;
	}

	@Override
	public String removeTask(Map<String, Integer> map) {
		
		String message = null;
		
		/* 태스크의 상태를 Y로 바꿔준다. */
		int result1 = mapper.deleteTask(map);
		
		int taskCode = map.get("taskCode");
		/* 삭제할 태스크의 내용를 불러온다*/
		TaskDTO task = mapper.selectTask(taskCode);
		
		/* 태스크를 삭제하는 멤버의 번호를 추가*/
		task.setUpdateMemberNo(map.get("updateMemberNo"));
		
		/* 태스크 버전 이력에 추가한다.*/
		int result2 = mapper.insertTaskVersionHistory3(task);
		
		/* 태스크 구성원의 번호를 모두 불러 온다. */
		List<Integer> taskMembersNo = mapper.selectTaskMembersList(map);
		
		/* 태스크 구성원의 참여 상태를 N으로 바꿔준다.*/
		int result3 = mapper.deleteTaskMembers(map);
		
		int result4 = 0;
		
		for(int i = 0; i < taskMembersNo.size(); i++) {
			
			int memberNo = taskMembersNo.get(i);
			
			map.put("memberNo", memberNo);
			
			/* 태스크 구성원 변경 이력에 불러온 태스크 구성원들을 추가한다. */
			result4 += mapper.insertTaskMembersHistory2(map);
		}
		
		if(result1 > 0 && result2 > 0 && result3 > 0 && (taskMembersNo.size() == result4)) {
			
			message = "태스크를 삭제 하였습니다.";
		} else {
			
			message = "태스크 삭제 실패";
		}
		
		return message;
	}

	@Override
	public String removeTaskMembers(Map<String, Integer> map) {
		
		String message = null;
		
		/* 태스크 구성원에서 제외한다.*/
		int result1 = mapper.deleteTaskMembers2(map);
		
		/*태스크 구성원 변경 이력에 추가한다.*/
		int result4 = mapper.insertTaskMembersHistory3(map);
		
		/* 스프린트 구성원에서 제외 해야할지 여부를 알기위해 같은 스프린트내의 다른 태스크에 참가 중인지 판별한다.*/
		int result2 = mapper.checkOtherTaskMembers(map);
		
		if(result1 > 0 && result4 > 0) {
			
			/* 다른 진행 중인 태스크가 없으면(0이면) 스프린트 구성원에서 제외한다.*/
			if(result2 > 0) {
				
				message = "해당 태스크에서 제외되었습니다.";
			} else {
				
				/* 스프린트 구성원에서 제외 시킨다. */
				int result3 = mapper.deleteSprintMembers(map);
				
				/* 스프린트 구성원 변경 이력에 추가한다.*/
				int result5 = mapper.insertSprintMembersHistory2(map);
				
				
				if(result3 > 0 && result5 > 0) {
					
					message = "담당하고 있는 태스크가 없기 때문에 스프린트 구성원에서 제외 됩니다.";
				}
			}
		} else {
			
			message = "태스크 참가 포기 실패";
		}
		
		return message;
	}

	@Override
	public boolean checkTasksContent(int projectCode) {
		
		boolean result = true;
		
		List<TaskDTO> taskList = mapper.selectTaskList(projectCode);
		
		for(int i  = 0; i < taskList.size(); i++) {
			
			TaskDTO task = taskList.get(i);
			
			if(task.getTitle().isEmpty() || task.getStartDate() == null || task.getEndDate() == null || task.getBody().isEmpty()) {
				
				result = false;
			}
		}
		return result;
	}




	







}