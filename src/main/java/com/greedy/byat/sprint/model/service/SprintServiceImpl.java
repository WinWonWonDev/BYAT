package com.greedy.byat.sprint.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.sprint.model.dao.SprintMapper;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

/**
 * <pre>
 * Class : SprintServiceImpl
 * Comment : SprintService를 상속바아 메소드들을 재정의한 클래스
 * History
 * 2022/02/17 (박상범) 스프린트 목록 조회 관련 메소드 추가
 * 2022/02/18 (박상범) 스프린트 목록 조회, 스프린트 생성 관련 메소드 추가
 * 2022/02/19 (박상범) 스프린트 생성, 스프린트 상세 조회 / 수정 관련 메소드 추가
 * 2022/02/20 (박상범) 스프린트 상세 조회 / 수정, 스프린트 삭제 관련 메소드 추가
 * 2022/02/21 (박상범) 스프린트 수정, 스프린트 생성 관련 메소드 추가
 * 2022/03/02 (박상범) 스프린트 시작 관련 메소드 추가
 * 2022/03/03 (박상범) 스프린트 시작 관련 메소드 추가
 * 2022/03/04 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
 * 2022/03/05 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
 * 2022/03/06 (박상범) 스프린트 생성, 스프린트 목록 조회 관련 메소드 추가
 * 2022/03/07 (박상범) 스프린트 시작, 스프린트 종료 관련 메소드 추가
 * 2022/03/07 (박상범) 스프린트 생성, 스프린트 수정 관련 메소드 추가
 * </pre>
 * @version 12
 * @author 박상범
 * @see SprintController.java, SprintServiceImpl.java, SprintMapper.java
 * */
@Service
public class SprintServiceImpl implements SprintService {
 
	private final SprintMapper mapper;

	@Autowired
	public SprintServiceImpl(SprintMapper mapper) {
		this.mapper = mapper;
	}

   /**
	* 메소드 selectSprintList에 관한 문서화 주석
	* @param int projectCode : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : selectSprintList메소드의 결과값을 리턴
	*/
	@Override
	public List<SprintDTO> selectSprintList(int projectNo) {

		List<SprintDTO> sprintList = mapper.selectSprintList(projectNo);
		
		return sprintList;
	}
	
	/**
	* 메소드 selectBacklogList에 관한 문서화 주석
	* @param int projectCode : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : selectBacklogList메소드의 결과값을 리턴
	*/
	@Override
	public List<BacklogDTO> selectBacklogList(int projectCode) {
		
		List<BacklogDTO> backlogList = mapper.selectBacklogList(projectCode);
		
		return backlogList;
	}

	/**
	* 메소드 selectMemberRoleName에 관한 문서화 주석
	* @param Map<String, Integer> map : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : selectProjectMembersRoleNamee메소드의 결과값을 리턴
	*/
	@Override
	public String selectMemberRoleName(Map<String, Integer> map) {
		
		String roleName = mapper.selectProjectMembersRoleNamee(map);
		
		return roleName;
	}
	
	
	/**
	* 메소드 registSprint에 관한 문서화 주석
	* @param SprintDTO sprint : SprintDTO 자체를 사용하기 위한 파라미터
	* @return : SprintController에서 addFlashAttribute할 message를 리턴
	*/
	@Override
	public String registSprint(SprintDTO sprint) {
		
		String message = null;
		
		int projectCode = sprint.getProjectCode();
		
		/* 진행중이거나 진행전인 스프린트가 있으면 스프린트를 생성할 수 없습니다. */
		int checkResult = mapper.checkSprintProgress(projectCode);
		
		if(!(checkResult > 0)) {
			
			int result1 = mapper.insertSprint(sprint);
		
			int result2 = mapper.insertSprintVersionHistory(sprint);
		
			int result3 = mapper.insertSprintProgressHistory(sprint);
			
			if (!(result1 > 0) && !(result2 > 0) && !(result3 > 0)) {
				message = "스프린트 생성 실패";
			} else {
				message = "스프린트를 생성하였습니다.";
			}
		} else {
			message = "완료되지 않은 스프린트가 존재합니다.";
		}
		return message;
	}

	/**
	* 메소드 modifySprint에 관한 문서화 주석
	* @param SprintDTO sprint : SprintDTO 자체를 사용하기 위한 파라미터
	*/
	@Override
	public void modifySprint(SprintDTO sprint) {
		
		int result1 = mapper.updateSprint(sprint);
		
		int result2 = mapper.insertSprintVersionHistory2(sprint);
		
		List<Integer> sprintMemberList = mapper.selectSprintMemberList3(sprint);
		
		int result3 = 0;
		
		Map<String, Integer> map = new HashMap<>();
		map.put("sprintCode", sprint.getCode());
		
		for(int i = 0; i < sprintMemberList.size(); i++) {
			
			map.put("memberNo", sprintMemberList.get(i));
			
			result3 += mapper.insertUpdateSprintNotice(map);
		}
		
		if(!(result1 > 0) && !(result2 > 0) && !(result3 != sprintMemberList.size())) {
			System.out.println("스프린트 수정 실패");
		}
	}
	
	/**
	* 메소드 selectSprint에 관한 문서화 주석
	* @param int sprintCode : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : selectSprint메소드의 결과값을 리턴
	*/
	@Override
	public SprintDTO selectSprint(int sprintCode) {
		
		SprintDTO sprint = mapper.selectSprint(sprintCode);
		
		return sprint;
	}
	
	/**
	* 메소드 removeSprint에 관한 문서화 주석
	* @param Map<String, Integer> map : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	*/
	@Override
	public void removeSprint(Map<String, Integer> map) {
		
		int sprintCode = map.get("sprintCode");
		
		SprintDTO sprint = mapper.selectSprint(sprintCode);
		sprint.setMemberNo(map.get("memberNo"));
		
		int result1 = mapper.deleteSprint(sprintCode);
		
		int result2 = mapper.insertSprintVersionHistory3(sprint);//
		
		/* 스프린트의 이슈들의 상태를 보류로 바꿔준다. */
		int result3 = mapper.updateIssueProgress2(sprintCode);
		
		/* 스프린트의 이슈코드 들을 가져온다. */
		List<Integer> issueList = mapper.selectIssueList3(sprintCode);
		
		int result4 = 0;
		
		for(int i = 0; i < issueList.size(); i++) {
			
			int issueCode = issueList.get(i);
			
			/* 이슈 구성원도 참가 여부를 N으로 바꿔준다.*/
			result4 += mapper.updateIssueMembersParticipation2(issueCode);
		}
		
		/* 스프린트의 구성원들을 가져온다. */
		List<Integer> sprintMembers = mapper.selectSprintMemberList4(sprintCode);
		
		int result5 = 0;
		
		for(int i = 0; i < sprintMembers.size(); i++) {
			
			map.put("memberNo", sprintMembers.get(i));
			
			/* 멤버 구성원 별로 스프린트가 삭제되었다는 알림이 생성된다. */
			result5 += mapper.insertRemoveSprintNotice(map);
		}
		
		if(!(result1 > 0) && !(result2 > 0) && !(result3 > 0) && !(result4 == issueList.size()) && !(result5 == sprintMembers.size())) {
			System.out.println("스프린트 삭제 실패");
		}
	}

	/**
	* 메소드 selectProjectProgress에 관한 문서화 주석
	* @param int projectCode : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : selectProjectProgress메소드의 결과값을 리턴
	*/
	@Override
	public String selectProjectProgress(int projectCode) {
		
		String projectProgress = mapper.selectProjectProgress(projectCode);
		
		return projectProgress;
	}

	/**
	* 메소드 startSprint에 관한 문서화 주석
	* @param int projectCode : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : SprintController에서 addFlashAttribute할 message를 리턴
	*/
	@Override
	public String startSprint(Map<String, Integer> map) {
		
		String message = null;
		
		int projectProgressResult = mapper.checkProjectProgress(map);
		
		/* 프로젝트가 진행중인지 확인하는 if */
		if(projectProgressResult > 0) {
			
			String sprintProgressResult = mapper.checkSprintProgress3(map);
			
			/* 스프린트가 진행전인 상태인지 확인하는 if*/
			if("진행전".equals(sprintProgressResult)) {
				
				SprintDTO sprint = mapper.selectSprint2(map);
				
				/* 스프린트의 미기입된 정보가 있는지 확인하는 if*/
				if(sprint.getTitle() != null && sprint.getStartDate() != null && sprint.getEndDate() != null && sprint.getBody() != null) {
					
					/* 진행전인 스프린트의 태스크리스트*/
					List<TaskDTO> taskList = mapper.selectTaskList2(map);
					int cnt = 0;
					
					/* 태스크가 존재하는지 확인하는 if */
					if(taskList.size() != 0) {
					
						for(int i = 0; i < taskList.size(); i++) {
							
							TaskDTO task = taskList.get(i);
							task.setMemberNo(map.get("memberNo"));
							
							/* 태스크들이  미기입된 항목이 있는지 체크힙니다.*/
							if(task.getTitle() != null && task.getStartDate() != null && task.getEndDate() != null && task.getBody() != null) {
								
								/* 백로그코드가 있는 태스크면 태스크의 진행 상태를 진행중으로 바꾸고 백로그의 진행상태도 진행중으로 바꿉니다.*/
								if(task.getBacklogCode() > 0) {
									
									/* 태스크의 진행상태를 진행중으로 바꿉니다. */
									int result1 = mapper.updateTaskProgress(task);
									
									/* 태스크의 진행상태 변경 이력 추가 */
									int result2 = mapper.insertTaskProgressHistory(task);
									
									/* 백로그의 진행상태도 진행중으로 바꿔줍니다. */
									int result3 = mapper.updateBacklogProgress(task);
									
									/* 백로그의 진행상태 변경 이력 추가 */
									int result4 = mapper.insertBacklogProgressHistory(task);
									
									if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0) {
										cnt++;
									}
									
									/* 백로그코드가 없는 태스크면 태스크의 진행 상태를 진행중으로 바꿉니다. */
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
							
							List<Integer> sprintMemberList = mapper.selectSprintMemberList(map);
							
							int result3 = 0;
							
							for(int j = 0; j < sprintMemberList.size(); j++) {
								
								map.put("memberNo", sprintMemberList.get(j));
								result3 += mapper.insertStartSprintNotice(map);
							}
							
							if(result1 > 0 && result2 > 0 && result3 == sprintMemberList.size()) {
								message = "스프린트를 시작합니다.";
							}
						}
						
					} else {
						message = "태스크가 없습니다. 태스크를 생성해주세요.";
					}
					
				} else {
					message = "스프린트에 미기입된 항목이 있습니다.";
				}
				
			} else if("진행중".equals(sprintProgressResult)) {
				message = "진행중인 스프린트가 존재합니다.";
			} else {
				message = "시작할 스프린트가 존재하지 않습니다.";
			}
		} else {
			message = "프로젝트가 진행중이 아닙니다.";
		}
		return message; 		
	}

	/**
	* 메소드 endSprint에 관한 문서화 주석
	* @param int projectCode : SprintController에서 넘어온 값을 사용하기 위한 파라미터
	* @return : SprintController에서 addFlashAttribute할 message를 리턴
	*/
	@Override
	public String endSprint(Map<String, Integer> map) {
		
		String message = null;
		
		int loginMemberNo = map.get("memberNo");
		
		int sprintProgressResult = mapper.checkSprintProgress2(map);
		
		/* 스프린트가 진행중인지 확인하는 if*/
		if(sprintProgressResult > 0) {
			
			/* 스프린트의 모든 태스크 코드를 가져옵니다.*/
			List<TaskDTO> taskCodeList = mapper.selectSprintTaskCodeList(map);
			
			TaskDTO task = new TaskDTO();
			int cnt = 0;
			
			for(int i = 0; i < taskCodeList.size(); i++) {
				
				task = taskCodeList.get(i);
				task.setMemberNo(map.get("memberNo"));
				
				/* 백로그로 생성된 태스크이면서 진행상태가 진행중이라면 태스크의 진행상태를 미완료로 바꾸고 백로그의 진행상태도 미완료로 바꿉니다.*/
				if("진행중".equals(task.getProgress()) && task.getBacklogCode() > 0) {
					
					int result1 = mapper.updateTaskProgress2(task);
					
					int result2 = mapper.updateBacklogProgress3(task);
					
					if(result1 > 0 && result2 > 0) {
						cnt++;
					}
					
				/* 백로그로 생성된 태스크이면서 진행상태가 완료라면 백로그의 진행상태도 완료로 바꿔줍니다.*/
				} else if("완료".equals(task.getProgress()) && task.getBacklogCode() > 0) {
					
					int result1 = mapper.updateBacklogProgress2(task);
					
					int result2 = mapper.insertBacklogProgressHistory3(task);
					
					if(result1 > 0 && result2 > 0) {
						cnt++;
					}
					
				/* 긴급 태스크로 생성된 태스크이면서 진행상태가 진행중이라면 태스크는 미완료로 바꾸고 백로그는 진행상태가 미완료인채로 다시 생성됩니다.*/
				} else if("진행중".equals(task.getProgress()) && 0 >= task.getBacklogCode()) {
					
					int result1 = mapper.updateTaskProgress3(task);
					
					int result2 = mapper.insertTaskProgressHistory2(task);
					
					int result3 = mapper.insertBacklog(task);
					
					int result4 = mapper.insertBacklogProgressHistory2(task);
					
					int result5 = mapper.insertBacklogVersionHistory(task);
					
					if (result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0  && result5 > 0 ) {
						cnt++;
					}
					
				/* 긴급 태스크로 생성된 태스크이면서 진행상태가 완료라면 변경사항없음*/
				} else if("완료".equals(task.getProgress()) && 0 >= task.getBacklogCode()){
					cnt++;
				}
			}
			
			if(cnt == taskCodeList.size()) {
				
				/* 스프린트 코드를 가져옵니다.*/
				int sprintCode = mapper.selectSprintCode(map);
				
				map.put("sprintCode", sprintCode);
				
				/* 회고록을 생성합니다. */
				int result1 = mapper.insertRetrospective(sprintCode);
				
				/* 스프린트 알림을 생성해야 하기때문에 스프린트 구성원리스트를 가져옵니다. */
				List<Integer> sprintMemberList = mapper.selectSprintMemberList2(map);
				
				int result2 = 0;
				
				for(int j = 0; j < sprintMemberList.size(); j++ ) {
					
					map.put("memberNo", sprintMemberList.get(j));
					
					/* 스프린트 구성원별로 알림을 생성합니다. */
					result2 += mapper.insertEndSprintNotice(map);
				}
				
				if(result1 > 0 && result2 == sprintMemberList.size()) {
					
					int result6 = mapper.updateSprintProgress2(sprintCode);
					
					map.put("memberNo", loginMemberNo);
					
					int result7 = mapper.insertSprintProgressHistory4(map);
					
					if(result6 > 0 && result7 > 0) {
						message = "스프린트를 종료합니다.";
					} else {
						message = "스프린트 종료 실패";
					}
				} else {
					message = "스프린트 종료 실패";
				}
			} else {
				message = "스프린트 종료 실패";
			}
		} else {
			message = "스프린트가 진행중이지 않습니다.";
		}
		return message;
	}

}