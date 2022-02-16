package com.greedy.byat.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {

	@RequestMapping("/")
	public String defaultLocation() {
		
		return "/main/firstPage";
	}
	
	@RequestMapping("main")
	public void main() {}
	
}
