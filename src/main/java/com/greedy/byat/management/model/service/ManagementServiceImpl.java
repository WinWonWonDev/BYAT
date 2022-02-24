package com.greedy.byat.management.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.management.model.dao.ManagementMapper;
import com.greedy.byat.management.model.dto.ManagementDTO;

@Service
public class ManagementServiceImpl implements ManagementService {
 
	private final ManagementMapper mapper;

	@Autowired
	public ManagementServiceImpl(ManagementMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int selectTotalCount() {
		
		int result = mapper.selectTotalCount();
		
		System.out.println("토탈 카운트 : " + result);
		
		
		return result;
	}

	@Override
	public List<ManagementDTO> selectManagementList() {
		
		List<ManagementDTO> managementList = mapper.selectManagementList();
		
		return managementList;
	}

	







}


