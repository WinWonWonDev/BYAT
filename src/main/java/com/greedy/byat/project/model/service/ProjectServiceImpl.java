package com.greedy.byat.project.model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		for(int i = 0; i < projectList.size(); i++) {
			
			projectMembers = selectProjectMembers(projectList.get(i).getCode());
			
			projectList.get(i).setProjectMembers(projectMembers);
		}
		
		return projectList;
	}

	@Override
	public List<ProjectMembersDTO> selectProjectMembers(int code) {

		List<ProjectMembersDTO> projectMembers = mapper.selectProjectMembers(code);

		List<ProjectMembersDTO> orderProjectMembers = new ArrayList<>();

		int memberCount = 0;

		for (int i = 0; i < projectMembers.size(); i++) {
			projectMembers.get(i).setName(projectMembers.get(i).getName().substring(1, 3));

			if ("PM".equals(projectMembers.get(i).getRoleName())) {

				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}

		}

		for (int i = 0; i < projectMembers.size(); i++) {

			if ("부PM".equals(projectMembers.get(i).getRoleName())) {

				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}

		}

		for (int i = 0; i < projectMembers.size(); i++) {

			if ("일반 멤버".equals(projectMembers.get(i).getRoleName())) {

				orderProjectMembers.add(memberCount, projectMembers.get(i));
				memberCount++;
			}

		}

		return orderProjectMembers;
	}

	@Override
	public void insertProject(ProjectDTO project) throws ProjectRegistException, ProjectVersionHistoryRegistException, ProjectProgressHistoryRegistException, ProjectMemberHistoryRegistException {

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

		if (projectMembersRegistResult > 0) {
			projectRoleRegistResult = mapper.insertProjectFirstMemberRole(projectMembers);
		}

		if (!(result > 0 && projectMembersRegistResult > 0 && projectRoleRegistResult > 0)) {
			throw new ProjectRegistException("프로젝트 생성 실패!");
		} else {
			project.setTitle("\'" + project.getTitle() + "\' 프로젝트 생성 (" + project.getTitle() + ")");
			int historyResult = mapper.insertFirstVersionHistory(project);
			int statusResult = mapper.insertFirstProgressHistory(project);
			int memberHistoryResult = mapper.insertFirstMemberHistory(projectMembers);
			
			if(!(historyResult > 0)) {
				
				throw new ProjectVersionHistoryRegistException("프로젝트 생성 히스토리 생성 실패!");
				
			}
			
			if(!(statusResult > 0)) {
				
				throw new ProjectProgressHistoryRegistException("프로젝트 상태 변경 이력 생성 실패!");
			}
			
			if(!(memberHistoryResult > 0)) {
				
				throw new ProjectMemberHistoryRegistException("프로젝트 구성원 변경 이력 생성 실패!");
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
			
			for (int i = 0; i < searchMemberList.size(); i++) {
				
				for (int j = 0; j < projectMembersList.length; j++) {

					if (searchMemberList.get(i).getNo() == Integer.parseInt(projectMembersList[j])) {

						searchMemberList.remove(i);
					}

				}

			}

		}
		
		if (selectMembers != null && searchMemberList != null) {

			for (Iterator<MemberDTO> searchItem = searchMemberList.iterator(); searchItem.hasNext();) {

				int searchNo = searchItem.next().getNo();
				
				System.out.println("searchNo : " + searchNo);

				for (int j = 0; j < selectMembers.length; j++) {
					
					System.out.println("selectMembers[j] : " + selectMembers[j]);

					if (searchNo == Integer.parseInt(selectMembers[j])) {

						searchItem.remove();

					}

				}

			}

		}
		
		return searchMemberList;
	}

	@Override
	public String insertProjectMember(ProjectMembersDTO registMember) throws ProjectRegistMemberException, ProjectMemberHistoryRegistException {

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
			
			if (projectMembersRegistResult > 0) {
				projectRoleRegistResult = mapper.insertProjectMemberRole(registMember);
			}

			if (!(projectMembersRegistResult > 0 && projectRoleRegistResult > 0)) {
				
				throw new ProjectRegistMemberException("프로젝트 구성원 추가 실패!");
				
			} else {
				
				int memberHistoryResult = mapper.insertMemberHistory(registMember);
				
				if(!(memberHistoryResult > 0)) {
					
					throw new ProjectMemberHistoryRegistException("프로젝트 구성원 변경 이력 생성 실패!");
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
		
		if(!(result > 0)) {
			
			throw new ProjectMemberRemoveException("구성원 제외 실패!");
		} /*
			 * else {
			 * 
			 * int memberHistoryResult = mapper.insertMemberHistory(projectMemberDetail);
			 * 
			 * if(!(memberHistoryResult > 0)) {
			 * 
			 * throw new ProjectMemberHistoryRegistException("프로젝트 구성원 변경 이력 생성 실패!"); }
			 * 
			 * }
			 */
		
	}

	@Override
	public void updateProjectMemberRole(List<ProjectMembersDTO> members) throws ProjectMemberModifyRoleException, ProjectWriterChangeException {
		
		for(int i = 0; i < members.size(); i++) {
			
			int result = mapper.updateProjectMemberRole(members.get(i));
			
			if("PM".equals(members.get(i).getRoleName())) {
				
				int writerChangeResult = mapper.updateProjectWriter(members.get(i));
				
				if(!(writerChangeResult > 0)) {
					
					throw new ProjectWriterChangeException("프로젝트 작성자 변경 실패!");
				}
			}
			
			if(!(result > 0)) {
				
				throw new ProjectMemberModifyRoleException("구성원 역할 변경 실패!");
			}
			
		}
		
	}

	@Override
	public int selectTotalCount(Map<String, String> searchMap) {

		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}

}
