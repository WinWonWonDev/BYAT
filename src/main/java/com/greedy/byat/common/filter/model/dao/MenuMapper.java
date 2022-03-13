package com.greedy.byat.common.filter.model.dao;

import java.util.ArrayList;

/**
 * <pre>
 * Class : MenuMapper
 * Comment : MenuMapper.xml의 id들이 담긴 Class
 * History
 * 2021/03/08 (황인수) 처음 작성함
 * 2021/03/09 (황인수) postHandle 부분 보완
 * 2021/03/10 (황인수) postHandle DB 연결 성공
 * </pre>
 * @version 3
 * @author 황인수
 * @see AuthLoginInterceptor, MenuService, MenuServiceImpl
 * */
public interface MenuMapper {

	int selectMenuCodeByURL(String url);

	ArrayList<Integer> selectPermitInURL(int menuCode);
}
