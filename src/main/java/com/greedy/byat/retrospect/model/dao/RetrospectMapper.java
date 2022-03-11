package com.greedy.byat.retrospect.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.retrospect.model.dto.RetrospectCommentDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectDTO;

public interface RetrospectMapper {

	List<RetrospectDTO> selectRetrospectList(int projectCode);

	int insertRetrospectComment(RetrospectCommentDTO retrosepctComment);

	List<RetrospectCommentDTO> selectRetrospectCommentList2(RetrospectCommentDTO retrosepctComment);

	int deleteRetrospectComment(int retrospectiveCommentNo);

	List<RetrospectCommentDTO> selectRetrospectCommentList3(int retrospectiveCommentNo);

}
