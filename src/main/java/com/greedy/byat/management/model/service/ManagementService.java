package com.greedy.byat.management.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.member.model.dto.MemberDTO;

public interface ManagementService {

	List<ManagementDTO> selectManagementList();

	int registManagement(MemberDTO member, String managementRoleforCreate);

	int modifyManagement(ManagementDTO management);

	int removeManagement(int memberNo);

	List<ManagementDTO> selectManagementRemovedList();




}
