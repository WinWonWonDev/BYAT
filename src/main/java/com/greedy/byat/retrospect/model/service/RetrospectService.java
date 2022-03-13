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
 * 2022/03/05 (박상범) 회고록 생성 관련 메소드 추가
 * 2022/03/06 (박상범) 회고록 목록 조회 관련 메소드 추가
 * 2022/03/09 (박상범) 회고록 목록 조회, 회고록 댓글 목록 조회, 회고록 댓글 작성 관련 메소드 추가
 * 2022/03/10 (박상범) 회고록 댓글 삭제 관련 메소드 추가
 * </pre>
 * @version 4
 * @author 박상범
 * @see RetrospectController, MRetrospectServiceImpl, RetrospectMapper, RetrospectDTO, Retrospect
 * */
public interface RetrospectService {

	List<RetrospectDTO> selectRetrospectList(int projectCode);

	List<RetrospectCommentDTO> registRetrospectComment(RetrospectCommentDTO retrospectComment);

	List<RetrospectCommentDTO> removeRetrospectComment(int retrospectiveCommentNo);

}
