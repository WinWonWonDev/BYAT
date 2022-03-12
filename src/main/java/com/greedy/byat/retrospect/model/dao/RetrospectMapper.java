package com.greedy.byat.retrospect.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.retrospect.model.dto.RetrospectCommentDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectDTO;

/**
* <pre>
* Class : RetrospectMapper
* Comment : mapper.xml의 id들이 담긴 Class
* History
* 2021/02/17 (박상범) 처음 작성
* </pre>
* @version 1.0.0
* @author 박상범
* @see RetrospectController, RetrospectService, RetrospectServiceImpl, RetrospectDTO, RetrospectCommentDTO
* */
public interface RetrospectMapper {

	List<RetrospectDTO> selectRetrospectList(int projectCode);

	int insertRetrospectComment(RetrospectCommentDTO retrosepctComment);

	List<RetrospectCommentDTO> selectRetrospectCommentList2(RetrospectCommentDTO retrosepctComment);

	int deleteRetrospectComment(int retrospectiveCommentNo);

	List<RetrospectCommentDTO> selectRetrospectCommentList3(int retrospectiveCommentNo);

}
