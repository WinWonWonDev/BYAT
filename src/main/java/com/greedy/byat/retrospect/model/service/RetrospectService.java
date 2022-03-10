package com.greedy.byat.retrospect.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.retrospect.model.dto.RetrospectCommentDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectDTO;

public interface RetrospectService {

	List<RetrospectDTO> selectRetrospectList(int projectCode);

	List<RetrospectCommentDTO> registRetrospectComment(RetrospectCommentDTO retrospectComment);

	List<RetrospectCommentDTO> removeRetrospectComment(int retrospectiveCommentNo);

}
