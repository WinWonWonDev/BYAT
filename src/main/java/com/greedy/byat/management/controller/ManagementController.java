package com.greedy.byat.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.byat.common.paging.Pagenation;
import com.greedy.byat.common.paging.SelectCriteria;
import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.management.model.service.ManagementService;

@Controller
@RequestMapping("/management")
public class ManagementController {

	private final ManagementService managementService;
	
	@Autowired
	public ManagementController(ManagementService managementService) {
		
		this.managementService = managementService;
	}
	
	@GetMapping("list")
	public ModelAndView selectManagementList(HttpServletRequest request, ModelAndView mv) {
		
//		String currentPage = request.getParameter("currentPage");
//		int pageNo = 1;
//		
//		if(currentPage != null && !"".equals(currentPage)) {
//			pageNo = Integer.parseInt(currentPage);
//		}
//
//		String searchCondition = request.getParameter("searchCondition");
//		String searchValue = request.getParameter("searchValue");
//		
//		Map<String, String> searchMap = new HashMap<>();
//		searchMap.put("searchCondition", searchCondition);
//		searchMap.put("searchValue", searchValue);
//		
//		int totalCount = managementService.selectTotalCount(searchMap);
//		
//		int limit = 9;
//		int buttonAmount = 10;
//
//		SelectCriteria selectCriteria = null;
//		
//		if(searchCondition != null && !"".equals(searchCondition)) {
//			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
//		} else {
//			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
//		}
//		
//		List<ManagementDTO> managementList = managementService.selectManagementList(selectCriteria);
//		
//		mv.addObject("managementList", managementList);
//		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("/management/management");
		
		return mv;
	}
	
}
