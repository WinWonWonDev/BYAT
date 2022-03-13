package com.greedy.byat.management.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.member.model.dto.MemberDTO;

/**
 * <pre>
 * Class : ManagementService
 * Comment : ManagementController의 요청을 처리하는 Interface
 * History
 * 2022/02/24 (이소현) 처음 작성, 관리 목록 조회
 * 2022/02/25 (이소현) 회원 계정 수정,
 * 2022/02/26 (이소현) 회원 계정 수정, 삭제, 삭제된 멤버 목록 조회, 삭제된 멤버 검색
 * 2022/02/27 (이소현) 회원 계정 삭제 jsp 작성, 삭제된 계정 복구
 * </pre>
 * @version 4
 * @author 이소현
 * @see ManagementController, ManagementServiceImpl, ManagementMapper, ManagementDTO
 * */
public interface ManagementService {

	List<ManagementDTO> selectManagementList();

	int registManagement(MemberDTO member, String managementRoleforCreate);

	int modifyManagement(ManagementDTO management);

	int removeManagement(int memberNo);

	List<ManagementDTO> selectManagementRemovedList();

	int restoreManagement(int no);




}
