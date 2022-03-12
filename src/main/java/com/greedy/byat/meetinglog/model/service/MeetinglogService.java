package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;

/**
 * <pre>
 * Class : MeetinglogService
 * Comment : MeetinglogController의 요청을 처리하는 Interface
 * History
 * 2021/02/17 (박수빈) 처음 작성
 * </pre>
 * @version 1.0.0
 * @author 박수빈
 * @see MeetinglogController, MeetinglogServiceImpl, MeetinglogMapper
 * */
public interface MeetinglogService {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

	String registMeetinglog(MeetinglogDTO meetinglog);

	MeetinglogDTO selectMeetinglogDetail(int meetinglogCode);

	String modifyMeetinglog(MeetinglogDTO meetinglog);

	String selectProjectName(int projectCode);

	String removeMeetinglog(int meetingCode);

}
