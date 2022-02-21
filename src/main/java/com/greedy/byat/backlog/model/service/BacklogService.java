package com.greedy.byat.backlog.model.service;

import java.util.List;

import com.greedy.byat.backlog.model.dto.BacklogDTO;

public interface BacklogService {

	List<BacklogDTO> selectBacklogList(int projectCode);
}
