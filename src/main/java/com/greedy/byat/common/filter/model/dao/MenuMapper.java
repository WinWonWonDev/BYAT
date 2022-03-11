package com.greedy.byat.common.filter.model.dao;

import java.util.ArrayList;

public interface MenuMapper {

	int selectMenuCodeByURL(String url);

	ArrayList<Integer> selectPermitInURL(int menuCode);
}
