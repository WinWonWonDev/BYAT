package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;

public interface MeetinglogService {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

}
