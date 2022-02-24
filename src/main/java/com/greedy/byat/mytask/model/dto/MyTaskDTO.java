package com.greedy.byat.mytask.model.dto;

import java.util.List;

import com.greedy.byat.project.model.dto.ProjectDTO;
import com.greedy.byat.task.model.dto.TaskDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//(필드 넣어준다음에 주석 풀어주시기 바랍니다)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString 
public class MyTaskDTO {
	private List<ProjectDTO> projectDTO;
	private List<ToDoListDTO> todolistDTO;
	private List<TaskDTO> taskDTO;
}
