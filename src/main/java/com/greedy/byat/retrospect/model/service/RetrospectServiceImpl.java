package com.greedy.byat.retrospect.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.retrospect.model.dao.RetrospectMapper;
import com.greedy.byat.retrospect.model.dto.RetrospectCommentDTO;
import com.greedy.byat.retrospect.model.dto.RetrospectDTO;

/* 
* <pre>
* Class : RetrospectServiceImpl
* Comment : RetrospectController에 필요한 메소드들의 로직을 작성하는 Service입니다.
* History
* 2022/02/17 박상범  처음 작성
* </pre>
* @version 1.0.0
* @author 박상범
* @see RetrospectDTO, RetrospectCommentDTO, RetrospectService, RetrospectController, RetrospectMapper.java 
*
*/
@Service
public class RetrospectServiceImpl implements RetrospectService {
 
	private final RetrospectMapper mapper;

	@Autowired
	public RetrospectServiceImpl(RetrospectMapper mapper) {
		this.mapper = mapper;
	}
	
   /**
    * 메소드 selectRetrospectList에 관한 문서화 주석
    * @ param int projectCode : 해당 프로젝트 코드에 대한 회고록 목록을 조회하기 위한 파라미터입니다.
    * @ return : retrospectList값을 담아 return합니다. 
    */
	@Override
	public List<RetrospectDTO> selectRetrospectList(int projectCode) {

		List<RetrospectDTO> retrospectList  = mapper.selectRetrospectList(projectCode);
		
		return retrospectList;
	}

  /**
    * 메소드 registRetrospectComment에 관한 문서화 주석
    * @ param RetrospectCommentDTO retrospectComment : 댓글관련 정보를 RetrospectCommentDTO형식으로 변환하여 전달하기 위한 파라미터입니다.
    * @ return : 회고록에 등록된 댓글 list가 담긴 commentList를 return합니다.
    */
	@Override
	public List<RetrospectCommentDTO> registRetrospectComment(RetrospectCommentDTO retrospectComment) {

		int result = mapper.insertRetrospectComment(retrospectComment);
		
		List<RetrospectCommentDTO> commentList = null;
		
		if(result > 0) {
			
			commentList = mapper.selectRetrospectCommentList2(retrospectComment);
		}
		
		return commentList;
	}

  /**
    * 메소드 removeRetrospectComment에 관한 문서화 주석
    * @ param int retrospectiveCommentNo : 회고록의 댓글 넘버 값이 담긴 파라미터입니다.
    * @ return : 삭제되고 남은 댓글의 list가 담긴 commentList를 return합니다. 
    */
	@Override
	public List<RetrospectCommentDTO> removeRetrospectComment(int retrospectiveCommentNo) {

		int result = mapper.deleteRetrospectComment(retrospectiveCommentNo);
		
		List<RetrospectCommentDTO> commentList = null;
		
		if(result > 0) {
			
			commentList = mapper.selectRetrospectCommentList3(retrospectiveCommentNo);
		}
		
		return commentList;
	}

	







}


