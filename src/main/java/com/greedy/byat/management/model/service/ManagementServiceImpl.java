package com.greedy.byat.management.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.byat.management.model.dao.ManagementMapper;
import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.member.model.dto.MemberDTO;

@Service
public class ManagementServiceImpl implements ManagementService {
 
	private final ManagementMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public ManagementServiceImpl(ManagementMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.mapper = mapper;
	}

	@Override
	public int selectTotalCount() {
		
		int result = mapper.selectTotalCount();
		
		return result;
	}

	@Override
	public List<ManagementDTO> selectManagementList() {
		
		List<ManagementDTO> managementList = mapper.selectManagementList();
		
		return managementList;
	}

	@Override
	public int registManagement(MemberDTO member, String managementRoleforCreate) {

		String pwd = passwordEncoder.encode(member.getId());
		int permitNo = 0;
		
		if(managementRoleforCreate.equals("PM")) {
			permitNo = 2;
		} else if(managementRoleforCreate.equals("일반 멤버")) {
			permitNo = 3;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("permitNo", permitNo);
		map.put("id", member.getId());
		map.put("name", member.getName());
		map.put("pwd", pwd);
		
		int result = mapper.insertManagement(map);
		
		return result;
	}

	@Override
	public int modifyManagement(ManagementDTO management) {

		int permitCode = 0;
		
		if("PM".equals(management.getPermitName())) {
			management.setPermitCode(2) ;
		} else if("일반 멤버".equals(management.getPermitName())) {
			management.setPermitCode(3);
		}
		
		int result = mapper.updateManagement(management);
		
		return result;
	}

	@Override
	public int removeManagement(int memberNo) {
		
		int result = mapper.deleteManagement(memberNo);
		
		return result;
	}






}


