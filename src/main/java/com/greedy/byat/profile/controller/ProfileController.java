package com.greedy.byat.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.profile.model.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	private final ProfileService profileService;
	
	@Autowired
	public ProfileController(ProfileService profileService) {
		
		this.profileService = profileService;
	}
	
	
}
