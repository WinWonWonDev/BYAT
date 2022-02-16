package com.greedy.byat.profile.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.profile.model.dao.ProfileMapper;

@Service
public class ProfileServiceImpl implements ProfileService {
 
	private final ProfileMapper mapper;

	@Autowired
	public ProfileServiceImpl(ProfileMapper mapper) {
		this.mapper = mapper;
	}

	







}


