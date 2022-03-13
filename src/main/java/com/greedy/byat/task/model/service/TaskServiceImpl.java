package com.greedy.byat.task.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.task.model.dao.TaskMapper;
import com.greedy.byat.task.model.dto.TaskDTO;
import com.greedy.byat.task.model.dto.TaskMembersDTO;

/**
 * <pre>
 * Class : TaskServiceImpl
 * Comment : TaskService를 상속바아 메소드들을 재정의한 클래스
 * History
 * 2022/02/22 (박상범) 태스크 목록 조회 메소드 추가
 * 2022/02/23 (박상범) 태스크 목록 조회, 태스크 생성 관련 메소드 추가
 * 2022/02/24 (박상범) 태스크 생성, 태스크 상세 조회 관련 메소드 추가
 * 2022/02/25 (박상범) 태스크 생성 관련 메소드 추가
 * 2022/02/26 (박상범) 태스크 상세 조회, 태스크 참여 여부 조회, 태스크 참여자 목록 조회 관련 메소드 추가
 * 2022/02/27 (박상범) 태스크 참가 여부 조회, 태스크 상세 조회, 태스크 참여자 목록 조회 관련 메소드 추가
 * 2022/02/28 (박상범) 태스크 참가, 태스크 참여자 목록, 태스크 상태 변경 관련 메소드 추가
 * 2022/03/01 (박상범) 태스크 삭제, 태스크 수정, 태스크 참가 포기 관련 메소드 추가
 * 2022/03/02 (박상범) 태스크 참가 포기, 태스크 참가, 태스크 참여자 목록 조회 관련 메소드 추가
 * 2022/03/08 (박상범) 태스크 상태 변경 태스크 참여자 목록 조회 관련 메소드 추가
 * </pre>
 * @version 10
 * @author 박상범
 * @see TaskController.java, TaskServiceImpl.java, TaskMapper.java
 * */
@Service
public class TaskServiceImpl implements TaskService {
 
	private final TaskMapper mapper;

	@Autowired
	public TaskServiceImpl(TaskMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	* 메소드 selectTaskList에 관한 문서화 주석
	* @param int sprintCode : TaskController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : selectTaskList2메소드의 결과값을 리턴
	*/
	@Override
	public List<TaskDTO> selectTaskList(int sprintCode) {
		
		List<TaskDTO> taskList = mapper.selectTaskList2(sprintCode);
		
		return taskList;
	}
	
	/**
	* 메소드 endSprint에 관한 문서화 주석
	* @param int projectCode : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : SprintController에서 addFlashAttribute할 message를 리턴
	*/
	@Override
	public String registTask(TaskDTO task) {
		
		String message = null;
		
		int projectCode = task.getProjectCode();
		
		/* 진행중이거나 진행전인 스프린트가 있는지 확인 결과 */
		int progressSprintResult = mapper.checkSprintProgress(projectCode);
		
		/* 진행중인 스프린트가 있다면 */
		if(progressSprintResult > 0) {
			
			/* 태스크에 스프린트 코드를 넣어줍니다. */
			task.setSprintCode(mapper.selectSprintCode(projectCode));
			
			/* 태스크 생성 결과 */
			int result1 = mapper.insertTask(task);
			
			String sprintTitle = mapper.selectSprintTitle(task);
			
			task.setTitle("\'" + task.getTitle() + "\'" + "태스크 생성(" + sprintTitle + ")");
			
			/* 태스크 버전 이력 생성 결과 */
			int result2 = mapper.insertTaskVersionHistory(task);
			
			/* 태스크 상태변경 이력 생성 결과 */
			int result3 = mapper.insertTaskProgressHistory(task);
			
			if((result1 > 0) && (result2 > 0) && (result3 > 0)) {
				message = "태스크를 생성하였습니다.";
			} else {
				message = "태스크 생성 실패";
			}
			
		} else {
			message = "진행중인 스프린트가 없습니다.";
		}
		
		return message;
	}

	/**
	* 메소드 selectTaskDetail에 관한 문서화 주석
	* @param int taskCode : TaskController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : selectTask메소드의 결과값을 리턴
	*/
	@Override
	public TaskDTO selectTaskDetail(int taskCode) {

		TaskDTO task = mapper.selectTask(taskCode);
		
		return task;
	}

	/**
	* 메소드 selectProjectMembers에 관한 문서화 주석
	* @param int projectCode TaskController에서 넘어온 값을 사용하기 위한 파라미터
	* @return selectProjectMembers메소드의 결과값을 리턴
	*/
	@Override
	public List<MemberDTO> selectProjectMembers(int projectCode) {
		
		List<MemberDTO> projectMembers = mapper.selectProjectMembers(projectCode);
		
		return projectMembers;
	}

	/**
	* 메소드 selectTaskParticipation에 관한 문서화 주석
	* @param Map<String, Integer> taskParticipation TaskController에서 넘어온 값을 사용하기 위한 파라미터
	* @return selectTaskParticipation메소드의 결과값에 따라 문자열을 리턴
	*/
	@Override
	public String selectTaskParticipation(Map<String, Integer> taskParticipation) {

		int result = mapper.selectTaskParticipation(taskParticipation);
		
		return (result > 0)? "Y" : "N";
	}

	/**
	* 메소드 registTaskMembers에 관한 문서화 주석
	* @param Map<String, Integer> taskParticipation SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return SprintController에서 addFlashAttribute할 message를 리턴
	*/
	@Override
	public String registTaskMembers(Map<String, Integer> taskParticipation) {

		String message = null;
		
		int checkResult1 = mapper.checkWasTaskMembers(taskParticipation);
		
		int checkResult3 = mapper.checkIsSprintMembers(taskParticipation);
		
		int taskMemberResult = 0;

		int sprintMembersResult = 0;
		
		if(checkResult3 == 0) {			 //1														//처음 참가 하는 거라면
			
			sprintMembersResult = mapper.insertSprintMembers(taskParticipation);
		} else {
			
			int checkResult2 = mapper.checkWasSprintMembers(taskParticipation);
			
			if(checkResult2 > 0) {																	
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

		if(sprintMembersResult > 0 && taskMemberResult > 0 && historyResult1 > 0 && historyResult2 > 0) {
			message = "태스크에 참가하셨습니다.";
		} else {
			message = "참가 실패";
		}
		
		return message;
	}

	/**
	* 메소드 selectTaskMembers에 관한 문서화 주석
	* @param int taskCode TaskController에서 넘어온 값을 사용하기 위한 파라미터
	* @return selectTaskMembers메소드의 결과값을 리턴
	*/
	@Override
	public List<TaskMembersDTO> selectTaskMembers(int taskCode) {
		
		List<TaskMembersDTO> taskMembers = mapper.selectTaskMembers(taskCode);
		
		return taskMembers;
	}

	/**
	* 메소드 modifyTask에 관한 문서화 주석
	* @param TaskDTO task SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return SprintController에서 addFlashAttribute할 message를 리턴
	*/
	@Override
	public String modifyTask(TaskDTO task) {
		
		String message = null;
		
		int result1 = mapper.updateTask(task);
		
		int result2 = mapper.insertTaskVersionHistory2(task);
		
		if(result1 > 0 && result2 > 0) {
			message = "태스크를 수정하셨습니다.";
		} else {
			message = "태스크 수정 실패";
		}
		
		return message;
	}

	/**
	* 메소드 modifyTask에 관한 문서화 주석
	* @param Map<String, Integer> map SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return SprintController에서 addFlashAttribute할 message를 리턴
	*/
	@Override
	public String removeTask(Map<String, Integer> map) {
		
		String message = null;
		
		int result1 = mapper.deleteTask(map);
		
		int taskCode = map.get("taskCode");
		
		TaskDTO task = mapper.selectTask(taskCode);
		
		task.setUpdateMemberNo(map.get("updateMemberNo"));
		
		int result2 = mapper.insertTaskVersionHistory3(task);
		
		if(result1 > 0 && result2 > 0) {
			message = "태스크를 삭제 하였습니다.";
		} else {
			message = "태스크 삭제 실패";
		}
		
		return message;
	}

	/**
	* 메소드 removeTaskMembers에 관한 문서화 주석
	* @param Map<String, Integer> map SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return SprintController에서 addFlashAttribute할 message를 리턴
	*/
	@Override
	public String removeTaskMembers(Map<String, Integer> map) {
		
		String message = null;
		
		int result1 = mapper.deleteTaskMembers2(map);
		
		int result4 = mapper.insertTaskMembersHistory3(map);
		
		int result2 = mapper.checkOtherTaskMembers(map);
		
		if(result1 > 0 && result4 > 0) {
			
			if(result2 > 0) {
				message = "해당 태스크에서 제외되었습니다.";
			} else {
				
				int result3 = mapper.deleteSprintMembers(map);
				
				int result5 = mapper.insertSprintMembersHistory2(map);
				
				if(result3 > 0 && result5 > 0) {
					message = "담당하고 있는 태스크가 없기 때문에 스프린트 구성원에서도 제외 됩니다.";
				}
			}
		} else {
			message = "태스크 참가 포기 실패";
		}
		
		return message;
	}

	/**
	* 메소드 updateTaskProgress에 관한 문서화 주석
	* @param Map<String, Integer> map TaskController에서 넘어온 값을 사용하기 위한 파라미터
	* @return mapper메소드의 동작을 확인한 후 결과에 따라 boolean타입의 result를 리턴
	*/
	@Override
	public boolean updateTaskProgress(Map<String, Integer> map) {

		boolean result = false;
		
		int result1 = mapper.updateTaskProgress(map);
		
		int result2 = mapper.insertTaskProgressHistory2(map);
		
		if(result1 > 0 && result2 > 0) {
			result = true;
		}
		
		return result;
	}

}