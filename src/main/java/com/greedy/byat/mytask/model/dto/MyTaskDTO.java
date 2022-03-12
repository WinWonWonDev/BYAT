package com.greedy.byat.mytask.model.dto;

import java.util.List;

import com.greedy.byat.project.model.dto.ProjectDTO;
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
public class MyTaskDTO {
	private List<ProjectDTO> projectDTO; 						// 프로젝트 정보 
	private List<ToDoListDTO> todolistDTO;						// ToDoList 정보
	private List<TaskDTO> taskDTO;								// Task 정보
}
