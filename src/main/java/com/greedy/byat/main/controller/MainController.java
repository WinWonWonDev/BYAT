package com.greedy.byat.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* <pre>
* Class : MainController
* Comment : 초기 및 메인 화면 관련 메소드들을 모아놓기 위한 Controller
* History
* 2022/02/17 이소현  처음 작성
* </pre>
* @version 1.1.0
* @author 이소현
* @see AuthenticationFilter  
*
*/
@Controller
@RequestMapping("/*")
public class MainController {
	
   /**
    * 메소드 defaultLocation에 관한 문서화 주석
    * @return url("/") 요청 시 main폴더 내의 firstPage.jsp로 return 
    */
	@RequestMapping("/")
	public String defaultLocation() {
		
		return "/main/firstPage";
	}
	
   /**
    * 메소드 main에 관한 문서화 주석
    * @return url("/main") 요청 시 'main.jsp'으로 return
    */
	@RequestMapping("main")
	public void main() {}
	
	/**
	 * 메소드 home에 관한 문서화 주석
	 * @return url("/home") 요청 시 main폴더 내의 main.jsp로 return
	 * */
	@RequestMapping("home")
	public String home() {
		
		return "/main/main";
	}
	
	
}

