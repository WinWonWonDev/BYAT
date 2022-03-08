package com.greedy.byat.retrospect.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.retrospect.model.dao.RetrospectMapper;
import com.greedy.byat.retrospect.model.dto.RetrospectCommentDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectDTO;

@Service
public class RetrospectServiceImpl implements RetrospectService {
 
	private final RetrospectMapper mapper;

	@Autowired
	public RetrospectServiceImpl(RetrospectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<RetrospectDTO> selectRetrospectList(int projectCode) {

		List<RetrospectDTO> retorspectList  = mapper.selectRetrospectList(projectCode);
		
		return retorspectList;
	}

	@Override
	public List<RetrospectCommentDTO> registRetrospectComment(RetrospectCommentDTO retrospectComment) {

		int result = mapper.insertRetrospectComment(retrospectComment);
		
		List<RetrospectCommentDTO> commentList = null;
		
		if(result > 0) {
			
			commentList = mapper.selectRetrospectCommentList2(retrospectComment);
		}
		
		return commentList;
	}

	







}


