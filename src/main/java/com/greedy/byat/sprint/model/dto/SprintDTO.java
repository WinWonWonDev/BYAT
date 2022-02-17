package com.greedy.byat.sprint.model.dto;

import java.util.List;

import com.greedy.byat.task.model.dto.TaskDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString 
public class SprintDTO {
	
	private int code;
	private String title;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String progress;
	private String body;
	private int version;
	private List<TaskDTO> taskList;
	
}
