package com.casestudy.blogging.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.casestudy.blogging.bean.Community;
import com.casestudy.blogging.dto.CommunityInputDto;

@SpringBootTest
public class CommunityServiceTest {
	@Autowired
	ICommunityService comServ;
	
	@Test
	@Disabled
	void addCommunityTest()
	{
		//Create community object
		CommunityInputDto com = new CommunityInputDto();
		com.setCommunityId(1);
		com.setCommunityDescription("Humans");
		com.setTotalMembers(120);
		com.setOnlineMembers(110);
		//To set File
		File fw = new File("abc.jpg");
		com.setImage(fw);
		
		//To set created date
		com.setCreatedOn(LocalDate.parse("2019-02-07"));
		
		//To set list of post rules allowed
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		com.setPostRulesAllowed(glist);
		
		//To set list of post rules disallowed
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		com.setPostRulesDisAllowed(galist);
		
		//To set list of Banning policy
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		com.setBanningPolicy(bp);
		
		//To set list of flairs
		List<String> f = new ArrayList<String>();
		f.add("SportsNews");
		com.setFlairs(f);
		
		//Persist the community object to the DB using service implementation
		Community c = comServ.addCommunity(com);
		
		//Validate details
		assertEquals("Humans",c.getCommunityDescription());
		assertEquals(120,c.getTotalMembers());
		assertEquals(110,c.getOnlineMembers());
		assertEquals(fw,c.getImage());
		assertEquals(LocalDate.parse("2019-02-07"),c.getCreatedOn());
		assertEquals(glist,c.getPostRulesAllowed());
		assertEquals(galist,c.getPostRulesDisAllowed());
		assertEquals(bp,c.getBanningPolicy());
		assertEquals(f,c.getFlairs());
	}
	
	@Test
	@Disabled
	void updateCommunityTest()
	{
		CommunityInputDto com = new CommunityInputDto();
		com.setCommunityId(411);
		com.setCommunityDescription("World");
		com.setTotalMembers(120);
		com.setOnlineMembers(110);
		//To set File
		File fw = new File("abc.jpg");
		com.setImage(fw);
		
		//To set created date
		com.setCreatedOn(LocalDate.parse("2019-02-07"));
		
		//To set list of post rules allowed
		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");
		com.setPostRulesAllowed(glist);
		
		//To set list of post rules disallowed
		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");
		com.setPostRulesDisAllowed(galist);
		
		//To set list of Banning policy
		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");
		com.setBanningPolicy(bp);
		
		//To set list of flairs
		List<String> f = new ArrayList<String>();
		f.add("DogsNews");
		com.setFlairs(f);
		
		//Update the community
		Community c = comServ.updateCommunity(com);
		
		//Validate details
		assertEquals(411,c.getCommunityId());
		assertEquals("World",c.getCommunityDescription());
		assertEquals(120,c.getTotalMembers());
		assertEquals(110,c.getOnlineMembers());
		assertEquals(fw,c.getImage());
		assertEquals(LocalDate.parse("2019-02-07"),c.getCreatedOn());
		assertEquals(glist,c.getPostRulesAllowed());
		assertEquals(galist,c.getPostRulesDisAllowed());
		assertEquals(bp,c.getBanningPolicy());
		assertEquals(f,c.getFlairs());
	}
	
	@Test
	@Disabled
	void deleteCommunityTest()
	{	
		//Count before Delete operation
		long beforeDeletecount = comServ.count();
		
		//Delete the community
		comServ.deleteCommunity(411);
		
		//Count after delete operation
		long afterDeleteCount = comServ.count();
		
		//Validate
		assertEquals(beforeDeletecount,afterDeleteCount+1);
		
	}
	@Test
	@Disabled
	void listAllCommunitiesTest()
	{
		List<Community> comList = comServ.listAllCommunities();
		assertEquals(1,comList.size());
	}
	
	@Test
	@Disabled
	void listAllCommunitiesByDescriptionTest()
	{
		List<Community> comList = comServ.listAllCommunitiesByDescription("Science");
		int noOfCommunities = comList.size();
		assertEquals(1,noOfCommunities);
	}
}
