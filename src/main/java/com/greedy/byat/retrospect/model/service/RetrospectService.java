package com.greedy.byat.retrospect.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.byat.retrospect.model.dto.RetrospectCommentDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectDTO;

/**
 * <pre>
 * Class : RetrospectService
 * Comment : RetrospectController의 요청을 처리하는 Interface
 * History
 * 2021/02/17 (박상범) 처음 작성
 * </pre>
 * @version 1.0.0
 * @author 박상범
 * @see RetrospectController, MRetrospectServiceImpl, RetrospectMapper, RetrospectDTO, Retrospect
 * */
public interface RetrospectService {

	List<RetrospectDTO> selectRetrospectList(int projectCode);

	List<RetrospectCommentDTO> registRetrospectComment(RetrospectCommentDTO retrospectComment);

	List<RetrospectCommentDTO> removeRetrospectComment(int retrospectiveCommentNo);

}
