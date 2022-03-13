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
* 2022/02/24 (이소현) 처음 작성, 관리 목록 조회
* 2022/02/25 (이소현) 회원 계정 수정,
* 2022/02/26 (이소현) 회원 계정 수정, 삭제, 삭제된 멤버 목록 조회, 삭제된 멤버 검색
* 2022/02/27 (이소현) 회원 계정 삭제 jsp 작성, 삭제된 계정 복구
* </pre>
* @version 4
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
