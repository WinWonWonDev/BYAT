package com.greedy.byat.sprint.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String startSprint(Map<String, Integer> map) {
		
		String message = null;
		
		int projectProgressResult = mapper.checkProjectProgress(map);
		
		/* 프로젝트가 진행중인지 확인하는 if */
		if(projectProgressResult > 0) {
			
			int sprintProgressResult = mapper.checkSprintProgress3(map);
			
			/* 스프린트가 진행전인 상태인지 확인하는 if*/
			if(sprintProgressResult > 0) {
				
				SprintDTO sprint = mapper.selectSprint2(map);
				
				/* 스프린트의 미기입된 정보가 있는지 확인하는 if*/
				if(sprint.getTitle() != null && sprint.getStartDate() != null && sprint.getEndDate() != null && sprint.getBody() != null) {
					
					/* 진행전인 스프린트의 태스크리스트*/
					List<TaskDTO> taskList = mapper.selectTaskList2(map);
					int cnt = 0;
					System.out.println("taskList@@@@@@@@@@@" + taskList);
					
					for(int i = 0; i < taskList.size(); i++) {
						
						TaskDTO task = taskList.get(i);
						task.setMemberNo(map.get("memberNo"));
						
						/* 태스크들이  미기입된 항목이 있는지 체크한다.*/
						if(task.getTitle() != null && task.getStartDate() != null && task.getEndDate() != null && task.getBody() != null) {
							
							/* 백로그코드가 있는 태스크면 태스크의 진행 상태를 진행중으로 바꾸고 백로그의 진행상태도 진행중으로 바꾼다.*/
							if(task.getBacklogCode() > 0) {
								
								/* 태스크의 진행상태를 진행중으로 바꾼다. */
								int result1 = mapper.updateTaskProgress(task);
								
								/* 태스크의 진행상태 변경 이력 추가 */
								int result2 = mapper.insertTaskProgressHistory(task);
								
								/* 백로그의 진행상태도 진행중으로 바꿔준다. */
								int result3 = mapper.updateBacklogProgress(task);
								
								/* 백로그의 진행상태 변경 이력 추가 */
								int result4 = mapper.insertBacklogProgressHistory(task);
								
								if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0) {
									
									cnt++;
								}
							
							/* 백로그코드가 없는 태스크면 태스크의 진행 상태를 진행중으로 바꾼다.*/
							} else {
								
								int result1 = mapper.updateTaskProgress(task);
								
								int result2 = mapper.insertTaskProgressHistory(task);
								
								if(result1 > 0 && result2 > 0) {
									
									cnt++;
								}
							}
						} else {
							
							message = "필수항목이 미기입된 태스크가 존재합니다.";
							break;
						}
					}
					
					if(taskList.size() == cnt) {
						
						int result1 = mapper.updatesprintProgress(map);
						
						int result2 = mapper.insertSprintProgressHistory3(map);
						
						if(result1 > 0 && result2 > 0) {
							
							message = "스프린트를 시작합니다.";
						}
					}
					
				} else {
					
					message = "스프린트에 미기입된 항목이 있습니다.";
				}
			} else {
				
				message = "진행전인 스프린트가 아닙니다.";
			}
		} else {
			
			message = "프로젝트가 진행중이 아닙니다.";
		}
		
		
		
		return message; 		
	}

	@Override
	public String endSprint(Map<String, Integer> map) {
		
		String message = null;
		
		int sprintProgressResult = mapper.checkSprintProgress2(map);
		
		/* 스프린트가 진행중인지 확인하는 if*/
		if(sprintProgressResult > 0) {
			
			/* 스프린트의 모든 태스크 코드를 가져온다.*/
			List<TaskDTO> taskCodeList = mapper.selectSprintTaskCodeList(map);
			
			TaskDTO task = new TaskDTO();
			int cnt = 0;
			
			for(int i = 0; i < taskCodeList.size(); i++) {
				
				task = taskCodeList.get(i);
				task.setMemberNo(map.get("memberNo"));
				
				/* 백로그로 생성된 태스크이면서 진행상태가 진행중이라면 태스크의 진행상태를 미완료로 바꾸고 백로그의 진행상태도 미완료로 바꾼다.*/
				if("진행중".equals(task.getProgress()) && task.getBacklogCode() > 0) {
					
					int result1 = mapper.updateTaskProgress2(task);
					
					int result2 = mapper.updateBacklogProgress3(task);
					
					if(result1 > 0 && result2 > 0) {
						
						cnt++;
					}
					
				/* 백로그로 생성된 태스크이면서 진행상태가 완료라면 백로그의 진행상태도 완료로 바꿔준다.*/
				} else if("완료".equals(task.getProgress()) && task.getBacklogCode() > 0) {
					
					int result1 = mapper.updateBacklogProgress2(task);
					
					int result2 = mapper.insertBacklogProgressHistory3(task);
					
					if(result1 > 0 && result2 > 0) {
						
						cnt++;
					}
					
				/* 긴급 태스크로 생성된 태스크이면서 진행상태가 진행중이라면 태스크는 미완료로 바꾸고 백로그는 진행상태가 미완료인채로 다시 생성된다.*/
				} else if("진행중".equals(task.getProgress()) && 0 >= task.getBacklogCode()) {
					
					int result1 = mapper.updateTaskProgress3(task);
					
					int result2 = mapper.insertTaskProgressHistory2(task);
					
					int result3 = mapper.insertBacklog(task);
					
					int result4 = mapper.insertBacklogProgressHistory2(task);
					
					int result5 = mapper.insertBacklogVersionHistory(task);
					
					if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0) {
						
						cnt++;
					}
					
				/* 긴급 태스크로 생성된 태스크이면서 진행상태가 완료라면 이상없음*/
				} else {
					
					cnt++;
				}
			}
			
			System.out.println("taskCodeList : " + taskCodeList.size());
			System.out.println("cnt : "+cnt);
			
			if(cnt == taskCodeList.size()) {
				
				/* 스프린트 코드를 가져온다.*/
				int sprintCode = mapper.selectSprintCode(map);
				
				int result1 = mapper.updateSprintProgress2(sprintCode);
				
				map.put("sprintCode", sprintCode);
				
				int result2 = mapper.insertSprintProgressHistory4(map);
				
				System.out.println(result1 + "aaaaa" + result2);
				
				if(result1 > 0 && result2 > 0) {
					
					int result = mapper.insertRetrospective(sprintCode);
					
					if(result > 0) {
						
						message = "스프린트를 종료합니다.";
					} else {
						
						message = "스프린트 좆ㅇ료 실패3";
					}
				} else {
					
					message = "스프린트 종료 실패2";
				}
			} else {
				message = "스프린트 종료 실패1";
			}
		} else {
			
			message = "스프린트가 진행중이 아닙니다.";
		}
		
		return message;
	}

	


	







}


