package com.greedy.byat.sprint.model.service;

import java.util.List;

import com.greedy.byat.sprint.model.dto.SprintDTO;

public interface SprintService {

	List<SprintDTO> selectSprintList(int projectNo);

}
