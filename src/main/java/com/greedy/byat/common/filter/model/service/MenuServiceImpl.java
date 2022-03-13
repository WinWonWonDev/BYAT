package com.greedy.byat.common.filter.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greedy.byat.common.filter.model.dao.MenuMapper;

/**
 * <pre>
 * Class : MenuServiceImpl
 * Comment : MenuService를 상속받아 method들을 재정의한 Class
 * History
 * 2021/03/08 (황인수) 처음 작성함
 * 2021/03/09 (황인수) postHandle 부분 보완
 * 2021/03/10 (황인수) postHandle DB 연결 성공
 * </pre>
 * @version 3
 * @author 황인수
 * @see MenuController, MenuService, MenuMapper
 * */
@Repository
public class MenuServiceImpl implements MenuService {
	
	private final MenuMapper mapper;
	
	@Autowired
	public MenuServiceImpl(MenuMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public ArrayList<Integer> selectPermitByURL(String url) {
		
		int menuCode = mapper.selectMenuCodeByURL(url);
		
		return mapper.selectPermitInURL(menuCode);
	}

}
