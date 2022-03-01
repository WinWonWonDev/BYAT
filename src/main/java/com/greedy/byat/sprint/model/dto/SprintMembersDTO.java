package com.greedy.byat.sprint.model.dto;

import java.util.List;

import com.greedy.byat.issue.model.dto.IssueDTO;
import com.greedy.byat.member.model.dto.MemberDTO;
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
public class SprintMembersDTO {

	private int no;
	private int projectCode;
	private int sprintCode;
	private String participationYn;
	private String id;
	private String name;
	
}
