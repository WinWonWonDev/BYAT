package com.greedy.byat.common.filter.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greedy.byat.common.filter.model.dao.MenuMapper;

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
