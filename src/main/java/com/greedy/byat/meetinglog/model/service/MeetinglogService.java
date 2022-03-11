package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;

public interface MeetinglogService {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

	String registMeetinglog(MeetinglogDTO meetinglog);

	MeetinglogDTO selectMeetinglogDetail(int meetinglogCode);

	String modifyMeetinglog(MeetinglogDTO meetinglog);

	String selectProjectName(int projectCode);

	String removeMeetinglog(int meetingCode);

}
