package com.greedy.byat.management.model.dao;

import java.util.List;

import com.greedy.byat.management.model.dto.ManagementDTO;

public interface ManagementMapper {

	int selectTotalCount();

	List<ManagementDTO> selectManagementList();

}
