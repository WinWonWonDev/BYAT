package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.meetinglog.model.dao.MeetinglogMapper;
import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;

@Service
public class MeetinglogServiceImpl implements MeetinglogService {
 
	private final MeetinglogMapper mapper;

	@Autowired
	public MeetinglogServiceImpl(MeetinglogMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<MeetinglogDTO> selectMeetinglogList(int projectCode) {

		List<MeetinglogDTO> meetinglog = mapper.selectMeetinglogList(projectCode);
		
		
		return meetinglog;
	}

	@Override
	public int registMeetinglog(MeetinglogDTO meetinglog) {

		System.out.println("registMeetinglog 히스토리 값 넣기전 : "+meetinglog);
		
		int result = mapper.registMeetinglog(meetinglog);
		
		//MeetinglogDTO meetinglogCode = mapper.selectMeetinglogCode(meetinglog);
		
		//meetinglog.setCode(meetinglogCode.getCode());
		
		
		//mapper.registMeetinglogHistory(meetinglog);
		 
		return result;
	}



	@Override
	public MeetinglogDTO selectMeetinglogDetail(int meetinglogCode) {
		
		MeetinglogDTO meetinglog = mapper.selectMeetinglogDetail(meetinglogCode);
		
		return meetinglog;
	}

	@Override
	public int modifyMeetinglog(MeetinglogDTO meetinglog) {
		
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		meetinglog.setWritingDate(sqlDate);
		meetinglog.setDeleteStatus("N");
		int result = mapper.modifyMeetinglog(meetinglog);
		
		System.out.println(result + ": modify ");
		if(result==1) {
			mapper.insertVersion(meetinglog);
			//mapper.registMeetinglogHistory(meetinglog);
		}
		return result ;
	}

	@Override
	public String selectProjectName(int projectCode) {
		
		String project = mapper.selectProjectName(projectCode);
		
		return project;
	}

}


