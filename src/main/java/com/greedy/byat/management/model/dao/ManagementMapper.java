package com.greedy.byat.management.model.dao;

import java.util.Map;

public interface ManagementMapper {

	int selectTotalCount(Map<String, String> searchMap);

}
