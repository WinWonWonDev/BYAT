package com.greedy.byat.mytask.model.dao;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.mytask.model.dto.MyTaskDTO;

public interface MyTaskMapper {

	MyTaskDTO selectMyTask(MemberDTO member);

}
