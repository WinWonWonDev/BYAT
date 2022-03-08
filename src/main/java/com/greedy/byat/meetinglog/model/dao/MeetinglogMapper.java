package com.greedy.byat.meetinglog.model.dao;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;
import com.greedy.byat.member.model.dto.MemberDTO;

public interface MeetinglogMapper {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

	int registMeetinglog(MeetinglogDTO meetinglog);

	MeetinglogDTO selectMeetinglogDetail(int meetinglog);

	int modifyMeetinglog(MeetinglogDTO meetinglog);

	void insertVersion(MeetinglogDTO meetinglog);

	void registMeetinglogHistory(MeetinglogDTO meetinglog);

	MeetinglogDTO selectMeetinglogCode(MeetinglogDTO meetinglog);

}
