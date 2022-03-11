package com.greedy.byat.meetinglog.model.dao;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;

public interface MeetinglogMapper {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

	int registMeetinglog(MeetinglogDTO meetinglog);

	MeetinglogDTO selectMeetinglogDetail(int meetinglog);

	int modifyMeetinglog(MeetinglogDTO meetinglog);

	void insertVersion(MeetinglogDTO meetinglog);

	int registMeetinglogHistory(MeetinglogDTO meetinglog);

	String selectProjectName(int projectCode);

	int removeMeetinglog(int meetingCode);

	int registMeetinglogNotice(NoticeDTO notice);

	List<Integer> selectProjectMembers(int projectCode);

	

}
