package com.greedy.byat.project.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.notice.NoticeInsertException;
import com.greedy.byat.common.exception.project.CalendatRegistProjectScheduleException;
import com.greedy.byat.common.exception.project.ProjectMemberHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectMemberModifyRoleException;
import com.greedy.byat.common.exception.project.ProjectMemberRemoveException;
import com.greedy.byat.common.exception.project.ProjectModifyCalendarException;
import com.greedy.byat.common.exception.project.ProjectModifyException;
import com.greedy.byat.common.exception.project.ProjectProgressHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistMemberException;
import com.greedy.byat.common.exception.project.ProjectRemoveException;
import com.greedy.byat.common.exception.project.ProjectVersionHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectWriterChangeException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.project.model.dao.ProjectMapper;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;

/**
 * <pre>
 * Class : ProjectServiceImpl
 * Comment : ProjectService의 메소드들을 실제로 처리하는 class 입니다.
 * History
 * 2022/02/17 (박인근) 처음 작성
 * 2022/02/18 (박인근) 프로젝트 목록 조회, 생성 관련 메소드 추가
 * 2022/02/19 (박인근) 프로젝트 조회, 수정 관련 메소드 추가
 * 2022/02/20 (박인근) 프로젝트 조회, 수정 관련 메소드 수정
 * 2022/02/21 (박인근) 프로젝트 구성원 추가 관련 메소드 추가
 * 2022/02/22 (박인근) 프로젝트 구성원 목록 조회 관련 메소드 추가
 * 2022/02/23 (박인근) 프로젝트 구성원 추가 취소 메소드, 구성원 제외 관련 메소드 추가
 * 2022/02/24 (박인근) 프로젝트 구성원 추가 시 검색 오류 코드 수정
 * 2022/02/25 (박인근) 프로젝트 버전 히스토리, 변경 이력 관련 코드 추가
 * 2022/02/28 (박인근) 프로젝트 일정 생성 관련 코드 추가
 * 2022/03/03 (박인근) 프로젝트 구성원 추가시 탈퇴 여부 체크, 구성원 제외 시 진행중이 스프린트 여부 체크 관련 코드 추가
 * 2022/03/04 (박인근) 프로젝트 진행상태 자동 변경 코드 추가
 * 2022/03/05 (박인근) 프로젝트 생성 알림 관련 코드 추가
 * </pre>
 * @version 13
 * @author 박인근
 * see ProjectController, ProjectDTO, ProjectMembersDTO, ProjectService, ProjectMapper.java, ProjectMapper.xml, NoticeDTO, MemberDTO, RoleDTO
 * */
@Service
public class ProjectServiceImpl implements ProjectService {

	/** ProjectMapper의 메소드들을 호출하기 위해 사용하는 전역변수로 변경 불가능하도록 final로 선언 */
	private final ProjectMapper mapper;

	@Autowired
	public ProjectServiceImpl(ProjectMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 메소드 selectProjectList에 관한 문서화 주석
	 * @ param member session 정보를 담은 member의 프로젝트를 조회하기 위한 값이 담겨 있는 파라미터
	 * @ return session 에 담긴 멤버가 속한 project의 List를 반환
	 * @ exception 날짜에 따라 프로젝트의 진행도가 바뀌기 때문에 상태 변경 이력 테이블에 값을 넣을 때 처리하는 예외
	 * */
	@Override
	public List<ProjectDTO> selectProjectList(MemberDTO member) throws ProjectProgressHistoryRegistException {

		List<ProjectDTO> projectList = mapper.selectProjectList(member);

		List<ProjectMembersDTO> projectMembers = new ArrayList<>();
		
		List<ProjectMembersDTO> orderProjectMembers = new ArrayList<>();
		
		int memberCount = 0;
		
		for(int i = 0; i < projectList.size(); i++) {
		
			projectMembers = mapper.selectProjectMembers(projectList.get(i).getCode());
			orderProjectMembers = new ArrayList<>();
			memberCount = 0;
			
			for(int j = 0; j < projectMembers.size(); j++) {
				
				if("PM".equals(projectMembers.get(j).getRoleName())) {
		        
					orderProjectMembers.add(memberCount, projectMembers.get(j));
		            memberCount++;
				
				}
				
			}
			
			for(int j = 0; j < projectMembers.size(); j++) {
				
				if("부PM".equals(projectMembers.get(j).getRoleName())) {
		        
					orderProjectMembers.add(memberCount, projectMembers.get(j));
		            memberCount++;
				
				}
				
			}
			
			for(int j = 0; j < projectMembers.size(); j++) {
				
				if("일반 멤버".equals(projectMembers.get(j).getRoleName())) {
		        
					orderProjectMembers.add(memberCount, projectMembers.get(j));
		            memberCount++;
				
				}
				
			}
			
			long miliseconds = System.currentTimeMillis();
	        Date today = new Date(miliseconds);
			
	        int progressUpdateResult = 0;
	        
	        if(today.compareTo(projectList.get(i).getStartDate()) < 0) {
	        	projectList.get(i).setProgress("미진행");
	        	progressUpdateResult = mapper.updateProjectProgress(projectList.get(i));
	        } else if(today.compareTo(projectList.get(i).getStartDate()) >= 0 && today.compareTo(projectList.get(i).getEndDate()) < 0) {
	        	projectList.get(i).setProgress("진행중");
	        	progressUpdateResult = mapper.updateProjectProgress(projectList.get(i));
	        } else {
	        	projectList.get(i).setProgress("완료");
	        	progressUpdateResult = mapper.updateProjectProgress(projectList.get(i));
	        }
	        
	        System.out.println("progressUpdateResult : " + progressUpdateResult);
	        System.out.println("projectList : " + projectList.get(i));
	        
	        int statusResult = 0;
	        
	        if(progressUpdateResult > 0) {
	        	statusResult = mapper.insertProgressHistory(projectList.get(i));
	        }
	        
	        if(!(statusResult > 0)) {
	        	throw new ProjectProgressHistoryRegistException("프로젝트 상태 변경 이력 생성 실패!");
	        }
	        
			projectList.get(i).setProjectMembers(orderProjectMembers); 
			
		}
			
		return projectList;
	}
	
	/**
	 * 메소드 insertProject에 관한 문서화 주석
	 * @ param project 전달받은 ProjectDTO 값을 사용하기 위한 파라미터
	 * @ exception 프로젝트 생성 실패 예외, 프로젝트 버전 히스토리 생성 실패 예외, 상태 변경 이력 생성 실패 예외, 멤버 변경 이력 생성 실패 예외, 멤버 일정 생성 실패 예외, 멤버 알림 생성 실패 예외시 처리하는 예외
	 * */
	@Override
	public void insertProject(ProjectDTO project) throws ProjectRegistException, ProjectVersionHistoryRegistException, ProjectProgressHistoryRegistException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException, NoticeInsertException {

		int result = mapper.insertProject(project);

		ProjectMembersDTO projectMembers = new ProjectMembersDTO();
		projectMembers.setName(project.getWriter());
		projectMembers.setNo(project.getWriterMember().getNo());
		projectMembers.setRoleName("PM");
		projectMembers.setCode(project.getCode());

		project.setProgress("미진행");
		project.setVersion(1);
		
		int projectMembersRegistResult = 0;
		int projectRoleRegistResult = 0;

		NoticeDTO notice = new NoticeDTO();
		notice.setBody("\'" + project.getTitle() + "\' 프로젝트가 생성되었습니다.");
		notice.setUrl("/project/list");
		notice.setStatus("N");
		notice.setCategory(1);
		notice.setNo(project.getWriterMember().getNo());
		
		int noticeInsertResult = 0;
		
		if (result > 0) {
			projectMembersRegistResult = mapper.insertProjectWriteMember(projectMembers);
			noticeInsertResult = mapper.insertNoticeFisrtProjectRegist(notice);
			
			if(!(noticeInsertResult > 0)) {
				
				throw new NoticeInsertException("프로젝트 알림 생성 실패!");
			}
		}

		projectRoleRegistResult = mapper.insertProjectFirstMemberRole(projectMembers);

		if (!(result > 0 && projectMembersRegistResult > 0 && projectRoleRegistResult > 0)) {
			throw new ProjectRegistException("프로젝트 생성 실패!");
		} else {
			String projectTitle = project.getTitle();
			project.setTitle("\'" + project.getTitle() + "\' 프로젝트 생성 (" + project.getTitle() + ")");
			int historyResult = mapper.insertFirstVersionHistory(project);
			int statusResult = mapper.insertFirstProgressHistory(project);
			int memberHistoryResult = mapper.insertFirstMemberHistory(projectMembers);
			project.setTitle(projectTitle);
			int calendarInsertResult = mapper.insertCalendarFirstProjectSchedule(project);
			
			if(!(historyResult > 0)) {
				
				throw new ProjectVersionHistoryRegistException("프로젝트 생성 히스토리 생성 실패!");
				
			}
			
			if(!(statusResult > 0)) {
				
				throw new ProjectProgressHistoryRegistException("프로젝트 상태 변경 이력 생성 실패!");
			}
			
			if(!(memberHistoryResult > 0)) {
				
				throw new ProjectMemberHistoryRegistException("프로젝트 구성원 변경 이력 생성 실패!");
			}
			
			if(!(calendarInsertResult > 0)) {
				
				throw new CalendatRegistProjectScheduleException("캘린더 프로젝트 일정 생성 실패!");
			}
		}

	}

	/**
	 * 메소드 deleteProject에 관한 문서화 주석
	 * @ param code 삭제할 project의 식별 번호인 파라미터
	 * @ param member project를 삭제한 멤버의 정보를 담은 파라미터
	 * @ exception 프로젝트 삭제 실패 예외, 프로젝트 버전 히스토리 생성 실패 예외, 멤버 알림 생성 실패 예외시 처리하는 예외
	 * */
	@Override
	public void deleteProject(int code, MemberDTO member) throws ProjectRemoveException, ProjectVersionHistoryRegistException, NoticeInsertException {

		int result = mapper.deleteProject(code);

		ProjectDTO project = mapper.selectProjectDetail(code);
		
		List<ProjectMembersDTO> projectMembers = mapper.selectProjectMemberList(code);
		
		NoticeDTO notice = new NoticeDTO();
		notice.setBody("\'" + project.getTitle() + "\' 프로젝트가 삭제되었습니다.");
		notice.setCategory(1);
		notice.setProjectCode(project.getCode());
		notice.setUrl("/project/list");
		notice.setStatus("N");
		
		int insertNoticeProjectRegistResult = 0;
		
		for(int i = 0; i < projectMembers.size(); i++) {
			notice.setNo(projectMembers.get(i).getNo());
			insertNoticeProjectRegistResult = mapper.insertNoticeProjectRegist(notice);
		}
		
		project.setWriter(member.getName());
		project.setVersion(project.getVersion() + 1);
		project.setTitle("\'" + project.getTitle() + "\' 프로젝트 삭제 (" + project.getTitle() + ")");
		
		if (!(result > 0)) {
			throw new ProjectRemoveException("프로젝트 삭제 실패");
		} else {
			
			if(!(insertNoticeProjectRegistResult > 0)) {
							
				throw new NoticeInsertException("프로젝트 알림 생성 실패!");
			}
			
			int updateResult = mapper.updateProjectVersion(project);
			
			int updateVersionHistory = mapper.insertVersionHistory(project);
			
			if(!(updateResult > 0 && updateVersionHistory > 0)) {
				
				throw new ProjectVersionHistoryRegistException("프로젝트 버전 업데이트 및 프로젝트 삭제 히스토리 생성 실패!");
				
			}
			
		}
		

	}

	/**
	 * 메소드 selectProjectDetail에 관한 문서화 주석
	 * @ param code 상세 조회할 project의 식별 번호인 파라미터
	 * @ return 상세 조회한 project의 정보를 담은 projectDTO 반환
	 * */
	@Override
	public ProjectDTO selectProjectDetail(int code) {

		ProjectDTO projectDetail = mapper.selectProjectDetail(code);

		List<ProjectMembersDTO> projectMembers = mapper.selectProjectMembers(code);
		
		List<ProjectMembersDTO> projectPm = new ArrayList<>();
		
		for(int i = 0; i < projectMembers.size(); i++) {
			if("PM".equals(projectMembers.get(i).getRoleName())) {
				projectPm.add(projectMembers.get(i));
			}
		}
		
		projectDetail.setProjectMembers(projectPm);
		
		return projectDetail;
	}

	/**
	 * 메소드 updateProject에 관한 문서화 주석
	 * @ param project project를 변경할 값이 담겨있는 파라미터
	 * @ param member project를 수정한 멤버의 정보를 담고 있는 파라미터
	 * @ exception 프로젝트 수정 실패 예외, 프로젝트 버전 히스토리 생성 실패 예외, 멤버 알림 생성 실패 예외, 멤버 일정 변경 실패 예외시 처리하는 예외
	 * */
	@Override
	public void updateProject(ProjectDTO project, MemberDTO member) throws ProjectModifyException, ProjectVersionHistoryRegistException, NoticeInsertException, ProjectModifyCalendarException {

		ProjectDTO projectDetail = mapper.selectProjectDetail(project.getCode());

		int result = mapper.updateProject(project);

		List<ProjectMembersDTO> projectMembers = mapper.selectProjectMemberList(project.getCode());
		
		NoticeDTO notice = new NoticeDTO();
		notice.setBody("\'" + projectDetail.getTitle() + "\' 프로젝트가 " + "\'" + project.getTitle() + "\'(으)로 수정되었습니다.");
		notice.setCategory(1);
		notice.setProjectCode(project.getCode());
		notice.setUrl("/project/list");
		notice.setStatus("N");
		
		int insertNoticeProjectRegistResult = 0;
		int updateCalendarProjectResult = 0;
		
		MemberDTO calendarMember = new MemberDTO();
		ProjectDTO calendarProject = project;
		
		for(int i = 0; i < projectMembers.size(); i++) {
			notice.setNo(projectMembers.get(i).getNo());
			insertNoticeProjectRegistResult = mapper.insertNoticeProjectRegist(notice);
			calendarMember.setNo(projectMembers.get(i).getNo());
			calendarProject.setWriterMember(calendarMember);
			
			updateCalendarProjectResult = mapper.updateCalendarProject(calendarProject);
			
			if(!(updateCalendarProjectResult > 0)) {
				
				throw new ProjectModifyCalendarException("프로젝트 수정시 일정 수정 실패!");
			}
		}
		
		
		project.setWriter(member.getName());
		project.setVersion(projectDetail.getVersion() + 1);
		project.setTitle("\'" + project.getTitle() + "\' 프로젝트 수정 (" + project.getTitle() + ")");
		
		
		if (!(result > 0)) {
			throw new ProjectModifyException("프로젝트 수정 실패");
		} else {
			
			if(!(insertNoticeProjectRegistResult > 0)) {
				
				throw new NoticeInsertException("프로젝트 알림 생성 실패!");
			}
			
			int updateResult = mapper.updateProjectVersion(project);
			
			int updateVersionHistory = mapper.insertVersionHistory(project);
			
			if(!(updateResult > 0 && updateVersionHistory > 0)) {
				
				throw new ProjectVersionHistoryRegistException("프로젝트 버전 업데이트 및 프로젝트 수정 히스토리 생성 실패!");
				
			}

		}
		

	}

	/**
	 * 메소드 searchAddMemberList에 관한 문서화 주석
	 * @ param searchMember 사용자가 입력한 값에 대한 파라미터
	 * @ param projectMembersList 기존의 project 멤버 값을 담고 있는 파라미터
	 * @ param selectMembers 사용자가 이미 구성원으로 추가하려고 선택한 멤버의 정보를 담고 있는 파라미터
	 * @ return 이미 구성원이 아니며 선택되지 않은 멤버의 정보를 담은 MemberDTO의 List 반환
	 * */
	@Override
	public List<MemberDTO> searchAddMemberList(String searchMember, String[] projectMembersList,
			String[] selectMembers) {

		List<MemberDTO> searchMemberList = mapper.searchAddMemberList(searchMember);

		if (searchMemberList != null) {
			
			for(Iterator<MemberDTO> searchItem = searchMemberList.iterator(); searchItem.hasNext();) {
				
				int searchNo = searchItem.next().getNo();
				
				for(int j = 0; j < projectMembersList.length; j++) {
					
					if(searchNo == Integer.parseInt(projectMembersList[j])) {
						
						searchItem.remove();
						
					}
				}
			}

		}
		
		for(int i = 0; i < searchMemberList.size(); i++) {
			
			System.out.println(i + " : " + searchMemberList.get(i).getNo());
			
		}
		
		if (selectMembers != null && searchMemberList != null) {

			for (Iterator<MemberDTO> searchItem = searchMemberList.iterator(); searchItem.hasNext();) {

				int searchNo = searchItem.next().getNo();
				
				for (int j = 0; j < selectMembers.length; j++) {
					
					if (searchNo == Integer.parseInt(selectMembers[j])) {

						searchItem.remove();

					}

				}

			}

		}
		
		return searchMemberList;
	}

	/**
	 * 메소드 insertProjectMember에 관한 문서화 주석
	 * @ param registMember 구성원으로 등록할 멤버의 정보를 담고 있는 파라미터
	 * @ return 어떤 project에 구성원으로 추가됐는지 알려주기 위해 프로젝트의 제목을 반환
	 * @ exception 프로젝트의 구성원 추가 실패 예외, 프로젝트 구성원 변경 이력 생성 실패 예외, 프로젝트 구성원 일정 생성 실패 예외
	 * */
	@Override
	public String insertProjectMember(ProjectMembersDTO registMember) throws ProjectRegistMemberException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException {

		List<ProjectMembersDTO> projectAllMemberList = mapper.selectProjectMemberNonParticipationList(registMember.getCode());
		
		boolean checkNonParticipationList = false;
		
		for(int i = 0; i < projectAllMemberList.size(); i++) {
			
			if(projectAllMemberList.get(i).getNo() == registMember.getNo()) {
				
				checkNonParticipationList = true; 
				break;
			}
			
		}
		
		registMember.setParticipationYn("Y");
		
		if(checkNonParticipationList) {
			
			int updateParticipationResult = mapper.updateMemberParticipation(registMember);
			
			int updateRoleResult = mapper.updateMemberRole(registMember);
			
			if(!(updateParticipationResult > 0 && updateRoleResult > 0)) {
				
				throw new ProjectRegistMemberException("프로젝트 구성원 추가 실패!");
				
			} else {
				
				int memberHistoryResult = mapper.insertMemberHistory(registMember);
				
				if(!(memberHistoryResult > 0)) {
					
					throw new ProjectMemberHistoryRegistException("프로젝트 구성원 변경 이력 생성 실패!");
				}
				
			}
			
		} else {
			
			int projectMembersRegistResult = mapper.insertProjectMembers(registMember);
			int projectRoleRegistResult = 0;
			
			ProjectDTO project = mapper.selectProjectDetail(registMember.getCode());
			
			MemberDTO member = new MemberDTO();
			
			member.setNo(registMember.getNo());
			
			project.setWriterMember(member);
			
			if (projectMembersRegistResult > 0) {
				
				projectRoleRegistResult = mapper.insertProjectMemberRole(registMember);
				
			}

			if (!(projectMembersRegistResult > 0 && projectRoleRegistResult > 0)) {
				
				throw new ProjectRegistMemberException("프로젝트 구성원 추가 실패!");
				
			} else {
				
				int memberHistoryResult = mapper.insertMemberHistory(registMember);
				
				int calendarInsertResult = mapper.insertCalendarProjectSchedule(project);
				
				if(!(memberHistoryResult > 0)) {
					
					throw new ProjectMemberHistoryRegistException("프로젝트 구성원 변경 이력 생성 실패!");
				}
				
				if(!(calendarInsertResult > 0)) {
					
					throw new CalendatRegistProjectScheduleException("캘린더 프로젝트 일정 생성 실패!");
				}
				
			}
			
		}
		
		return mapper.selectProjectDetail(registMember.getCode()).getTitle();

	}

	/**
	 * 메소드 selectProjectMemberList에 관한 문서화 주석
	 * @ param code 선택한 project의 멤버 목록을 조회하기 위한 project의 식별 번호인 파라미터
	 * @ return project의 멤버들 정보를 담은 projectMembersDTO List를 반환
	 * */
	@Override
	public List<ProjectMembersDTO> selectProjectMemberList(int code) {
		
		List<ProjectMembersDTO> projectMemberList = mapper.selectProjectMemberList(code);
		
		System.out.println(projectMemberList);
		
		return projectMemberList;
	}

	/**
	 * 메소드 removeProjectMembers에 관한 문서화 주석
	 * @ param removeMember 프로젝트 구성원에서 제외할 멤버의 정보를 담고 있는 파라미터
	 * @ exception 프로젝트의 구성원 제외 실패 예외, 프로젝트 구성원 변경 이력 생성 실패 예외
	 * */
	@Override
	public void deleteProjectMembers(ProjectMembersDTO removeMember) throws ProjectMemberRemoveException, ProjectMemberHistoryRegistException {
		
		int result = mapper.deleteProjectMembers(removeMember);

		ProjectMembersDTO projectMemberDetail = new ProjectMembersDTO();

		projectMemberDetail = mapper.selectProjectMember(removeMember);
		
		projectMemberDetail.setParticipationYn("N");
		
		int memberHistoryResult = mapper.insertMemberHistory(projectMemberDetail);

		if(!(result > 0)) {
			
			throw new ProjectMemberRemoveException("구성원 제외 실패!");
		}
		
		if(!(memberHistoryResult > 0)) {
		
			throw new ProjectMemberHistoryRegistException("프로젝트 구성원 변경 이력 생성 실패!");
		}
		
	}

	/**
	 * 메소드 updateProjectMemberRole에 관한 문서화 주석
	 * @ param members project 내 역할을 변경한 멤버들의 정보를 담은 목록 파라미터
	 * @ exception 프로젝트 구성원의 역할 변경 실패 예외, 프로젝트의 PM 변경 실패 예외, 프로젝트의 구성원 변경 이력 생성 실패 예외
	 * */
	@Override
	public void updateProjectMemberRole(List<ProjectMembersDTO> members) throws ProjectMemberModifyRoleException, ProjectWriterChangeException, ProjectMemberHistoryRegistException {
		
		int result = 0;
		
		ProjectMembersDTO newMember = new ProjectMembersDTO();
		
		for(int i = 0; i < members.size(); i++) {
			
			members.get(i).setParticipationYn("Y");
			
			ProjectMembersDTO projectMemberBeforeDetail = mapper.selectProjectMember(members.get(i));
			
			if(!(members.get(i).getRoleName().equals(projectMemberBeforeDetail.getRoleName()))) {
				
				newMember = members.get(i);
				result = mapper.updateProjectMemberRole(newMember);
				
				if(!(result > 0)) {
					
					throw new ProjectMemberModifyRoleException("구성원 역할 변경 실패!");
				} else {
					
					if(!(members.get(i).getRoleName().equals(projectMemberBeforeDetail.getRoleName()))) {
					
						int memberHistoryResult = mapper.insertMemberHistory(newMember);

						if(!(memberHistoryResult > 0)) {
							
							throw new ProjectMemberHistoryRegistException("프로젝트 구성원 변경 이력 생성 실패!");
						}
					}
					
				}
				
			}
			
			
			if("PM".equals(members.get(i).getRoleName())) {
				
				int writerChangeResult = mapper.updateProjectWriter(members.get(i));
				
				if(!(writerChangeResult > 0)) {
					
					throw new ProjectWriterChangeException("프로젝트 작성자 변경 실패!");
				}
			}
			
		}
		
	}

	/**
	 * 메소드 selectSprintProceedingCount에 관한 문서화 주석
	 * @ param code project의 진행중인 스프린트를 조회하기 위한 project의 식별 변호인 파라미터
	 * @ param no project의 진행중인 스프린트를 조회하기 위한 로그인한 멤버의 식별 번호인 파라미터
	 * @ return 진행중인 sprint의 갯수를 반환
	 * */
	@Override
	public int selectSprintProceedingCount(int code, int no) {
		
		ProjectMembersDTO projectMembers = new ProjectMembersDTO();
		projectMembers.setCode(code);
		projectMembers.setNo(no);
		
		int count = mapper.selectSprintProceedingCount(projectMembers);
		
		return count;
	}

}
