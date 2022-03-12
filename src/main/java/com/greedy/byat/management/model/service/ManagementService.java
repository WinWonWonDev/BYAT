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
 * 2021/02/17 (이소현) 처음 작성
 * </pre>
 * @version 1.0.0
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
