package com.greedy.byat.meetinglog.model.dao;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;

public interface MeetinglogMapper {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

}
