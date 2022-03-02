package com.greedy.byat.profile.model.dao;

import com.greedy.byat.profile.model.dto.ProfileDTO;

public interface ProfileMapper {
	
	int updateProfile(ProfileDTO profile);
	
	int updatePassword(ProfileDTO profile);
}
