package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;

public interface MeetinglogService {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

	int registMeetinglog(MeetinglogDTO meetinglog);

	MeetinglogDTO selectMeetinglogDetail(int meetinglogCode);

	int modifyMeetinglog(MeetinglogDTO meetinglog);

	String selectProjectName(int projectCode);

	

}
