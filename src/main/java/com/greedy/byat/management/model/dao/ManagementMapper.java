package com.greedy.byat.management.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.member.model.dto.MemberDTO;

/**
* <pre>
* Class : ManagementMapper
* Comment : mapper.xml의 id들이 담긴 Class
* History
* 2021/02/17 (이소현) 처음 작성
* </pre>
* @version 1.0.0
* @author 이소현
* @see ManagementController, ManagementService, ManagementServiceImpl, ManagementDTO
* */
public interface ManagementMapper {

	List<ManagementDTO> selectManagementList();

	int insertManagement(Map<String, Object> map);

	int updateManagement(ManagementDTO management);

	int deleteManagement(int memberNo);

	List<ManagementDTO> selectManagementDeletedList();

	int restoreManagement(int no);

	int insertNoticeSettingByOneMember();


}
