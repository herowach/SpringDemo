package com.demo.spring.orm;

import java.util.List;
import java.util.Map;

import com.demo.model.Club;

public interface ClubDAO {
	void addClub(Club club);
	
	void updateClub(Club club);
	
	void deleteClub(int id);
	
	void deleteMembershipByClubId(int clubId);
	
	List<Club> getClubList(Map<String, Object> param);
	
	List<Club> getClubListByEmployeeId(int empId);

	Club getClubById(int clubId);
	
	Club getClubByName(String name);
}
