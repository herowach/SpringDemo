package com.demo.spring.transaction;

import com.demo.model.Club;

public interface ClubService {
	void addClub(Club club);
	
	void deleteClubById(int clubId);
}
