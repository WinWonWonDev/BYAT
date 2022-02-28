package com.greedy.byat.management.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.member.model.dto.MemberDTO;

public interface ManagementMapper {

	List<ManagementDTO> selectManagementList();

	int insertManagement(Map<String, Object> map);

	int updateManagement(ManagementDTO management);

	int deleteManagement(int memberNo);

	List<ManagementDTO> selectManagementDeletedList();

	int restoreManagement(int no);


}
