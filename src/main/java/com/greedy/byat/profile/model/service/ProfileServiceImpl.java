package com.greedy.byat.profile.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.byat.profile.model.dao.ProfileMapper;
import com.greedy.byat.profile.model.dto.ProfileDTO;

@Service
public class ProfileServiceImpl implements ProfileService {
 
	private final ProfileMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public ProfileServiceImpl(ProfileMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public void modifyProfile(ProfileDTO profile) {
		// TODO Auto-generated method stub
		
	}
}


