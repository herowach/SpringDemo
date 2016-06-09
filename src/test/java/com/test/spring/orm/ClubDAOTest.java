package com.test.spring.orm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.Club;
import com.demo.spring.orm.ClubDAO;

//source F:\\javaworkspace\\SpringDemo\\SpringDemo\\src\\main\\resources\\sql\\demo-mysql.sql
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/orm/spring-orm-config.xml"})
public class ClubDAOTest {
	
	@Resource
	private ClubDAO clubDao;
	
	@Test
	public void testGetClubList() {
		Map<String, Object> param = new HashMap<>();
		param.put("id", 3);
		param.put("name", "foot");
		List<Club> list = clubDao.getClubList(param);
		assertNotNull(list);
		assertTrue("club size is 0", list.size() > 0);
		System.out.println(list.size());
	}
	
	@Test
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
	}
	
}
