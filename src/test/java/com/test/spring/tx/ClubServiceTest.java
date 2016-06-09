package com.test.spring.tx;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.Club;
import com.demo.spring.transaction.ClubService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/tx/spring-tx-config.xml"})
public class ClubServiceTest {
	
	@Resource
	private ClubService clubService;
	
	@Test
	public void testAddClub() {
		Club club = new Club();
		club.setName("NEW CLUB6");
		club.setDesc("my desc6");
		club.setFee(new BigDecimal(99));
		clubService.addClub(club);
		System.out.println("add club success");
	}
	
	@Test
	public void testDeleteClub() {
		clubService.deleteClubById(13);
	}
	
	/*@Test
	public void testGetClubById() {
		Club club = clubDao.getClubById(3);
		assertNotNull(club);
	}
	
	@Test
	public void testAddClub() {
		Club club = new Club();
		club.setName("NEW CLUB");
		club.setDesc("my desc");
		club.setFee(new BigDecimal(55));
		clubDao.addClub(club);
	}
	
	@Test
	public void testUpdateClub() {
		Club club = new Club();
		club.setId(6);
		club.setName("updated CLUB");
		club.setDesc("updated desc");
		club.setFee(new BigDecimal(11));
		clubDao.updateClub(club);
	}
	
	@Test
	public void testDeleteClub() {
		clubDao.deleteClub(6);
	}
	
	public void testServiceTransaction(){
		
		
		
		
		System.out.println("add club success");
	}*/
	
}
