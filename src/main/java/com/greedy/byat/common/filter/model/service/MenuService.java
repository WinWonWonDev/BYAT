package com.greedy.byat.common.filter.model.service;

import java.util.ArrayList;

/**
 * <pre>
 * Class : MenuService
 * Comment : AuthLoginInterceptor의 요청을 처리하는 Interface
 * History
 * 2021/03/08 (황인수) 처음 작성함
 * 2021/03/09 (황인수) postHandle 부분 보완
 * 2021/03/10 (황인수) postHandle DB 연결 성공
 * </pre>
 * @author 황인수
 * @version 3
 * @see AuthLoginInterceptor, MenuServiceImpl, MenuMapper
 * */
public interface MenuService {

	ArrayList<Integer> selectPermitByURL(String url);
}
