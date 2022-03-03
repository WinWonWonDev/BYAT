package com.greedy.byat.project.model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.project.CalendatRegistProjectScheduleException;
import com.greedy.byat.common.exception.project.ProjectMemberHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectMemberModifyRoleException;
import com.greedy.byat.common.exception.project.ProjectMemberRemoveException;
import com.greedy.byat.common.exception.project.ProjectModifyException;
import com.greedy.byat.common.exception.project.ProjectProgressHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistException;
import com.greedy.byat.common.exception.project.ProjectRegistMemberException;
import com.greedy.byat.common.exception.project.ProjectRemoveException;
import com.greedy.byat.common.exception.project.ProjectVersionHistoryRegistException;
import com.greedy.byat.common.exception.project.ProjectWriterChangeException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.project.model.dao.ProjectMapper;
import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.project.model.dto.ProjectMembersDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectMapper mapper;

	@Autowired
	public ProjectServiceImpl(ProjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<ProjectDTO> selectProjectList(MemberDTO member) {

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
			
			projectList.get(i).setProjectMembers(orderProjectMembers); 
			
		}
			
		return projectList;
	}

	@Override
	public void insertProject(ProjectDTO project) throws ProjectRegistException, ProjectVersionHistoryRegistException, ProjectProgressHistoryRegistException, ProjectMemberHistoryRegistException, CalendatRegistProjectScheduleException {

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

		if (result > 0) {
			projectMembersRegistResult = mapper.insertProjectWriteMember(projectMembers);
		}

		projectRoleRegistResult = mapper.insertProjectFirstMemberRole(projectMembers);

		if (!(result > 0 && projectMembersRegistResult > 0 && projectRoleRegistResult > 0)) {
			throw new ProjectRegistException("프로젝트 생성 실패!");
		} else {
			project.setTitle("\'" + project.getTitle() + "\' 프로젝트 생성 (" + project.getTitle() + ")");
			int historyResult = mapper.insertFirstVersionHistory(project);
			int statusResult = mapper.insertFirstProgressHistory(project);
			int memberHistoryResult = mapper.insertFirstMemberHistory(projectMembers);
			int calendarInsertResult = mapper.insertCalendarProjectSchedule(project);
			
			
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

	@Override
	public void deleteProject(int code, MemberDTO member) throws ProjectRemoveException, ProjectVersionHistoryRegistException {

		int result = mapper.deleteProject(code);

		ProjectDTO project = mapper.selectProjectDetail(code);
		project.setWriter(member.getName());
		project.setVersion(project.getVersion() + 1);
		project.setTitle("\'" + project.getTitle() + "\' 프로젝트 삭제 (" + project.getTitle() + ")");
		
		if (!(result > 0)) {
			throw new ProjectRemoveException("프로젝트 삭제 실패");
		} else {
			
			int updateResult = mapper.updateProjectVersion(project);
			
			int updateVersionHistory = mapper.insertVersionHistory(project);
			
			if(!(updateResult > 0 && updateVersionHistory > 0)) {
				
				throw new ProjectVersionHistoryRegistException("프로젝트 버전 업데이트 및 프로젝트 삭제 히스토리 생성 실패!");
				
			}
			
		}
		

	}

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

	@Override
	public void updateProject(ProjectDTO project, MemberDTO member) throws ProjectModifyException, ProjectVersionHistoryRegistException {

		int result = mapper.updateProject(project);
		
		ProjectDTO projectDetail = mapper.selectProjectDetail(project.getCode());
		
		project.setWriter(member.getName());
		project.setVersion(projectDetail.getVersion() + 1);
		project.setTitle("\'" + project.getTitle() + "\' 프로젝트 수정 (" + project.getTitle() + ")");
		
		
		if (!(result > 0)) {
			throw new ProjectModifyException("프로젝트 수정 실패");
		} else {
			
			int updateResult = mapper.updateProjectVersion(project);
			
			int updateVersionHistory = mapper.insertVersionHistory(project);
			
			if(!(updateResult > 0 && updateVersionHistory > 0)) {
				
				throw new ProjectVersionHistoryRegistException("프로젝트 버전 업데이트 및 프로젝트 수정 히스토리 생성 실패!");
				
			}

		}
		

	}

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
				
				System.out.println("문제있니 ? : " + project);
				
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

	@Override
	public List<ProjectMembersDTO> selectProjectMemberList(int code) {
		
		List<ProjectMembersDTO> projectMemberList = mapper.selectProjectMemberList(code);
		
		System.out.println(projectMemberList);
		
		return projectMemberList;
	}

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

	@Override
	public void updateProjectMemberRole(List<ProjectMembersDTO> members) throws ProjectMemberModifyRoleException, ProjectWriterChangeException, ProjectMemberHistoryRegistException {
		
		int result = 0;
		
		ProjectMembersDTO newMember = new ProjectMembersDTO();
		
		for(int i = 0; i < members.size(); i++) {
			
			members.get(i).setParticipationYn("Y");
			
			System.out.println("! : " + members.get(i));
			
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

	@Override
	public int selectSprintProceedingCount(int code, int no) {
		
		ProjectMembersDTO projectMembers = new ProjectMembersDTO();
		projectMembers.setCode(code);
		projectMembers.setNo(no);
		
		int count = mapper.selectSprintProceedingCount(projectMembers);
		
		return count;
	}

}
