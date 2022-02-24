package com.greedy.byat.management.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.management.model.dto.ManagementDTO;

public interface ManagementService {

	int selectTotalCount();

	List<ManagementDTO> selectManagementList();

}
