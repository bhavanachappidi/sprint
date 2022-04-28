package com.casestudy.blogging.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.casestudy.blogging.bean.Community;
import com.casestudy.blogging.dto.CommunityInputDto;
import com.casestudy.blogging.repository.ICommunityRepository;

@ExtendWith(SpringExtension.class)
public class CommunityServiceMockitoTest {
	@InjectMocks
	CommunityServiceImpl comServ;

	@MockBean
	ICommunityRepository comRepo;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addCommunityTest() {
		File fw = new File("abc.jpg");

		List<String> glist = new ArrayList<String>();
		glist.add("Hockey");
		glist.add("Cricket");
		glist.add("Tennis");

		List<String> galist = new ArrayList<String>();
		galist.add("Tours");
		galist.add("Furniture");
		galist.add("Houses");

		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");

		List<String> f = new ArrayList<String>();
		f.add("SportsNews");

		// Creating community
		Community newCommunity = new Community(12, "Dogs", 400, 123, fw, LocalDate.parse("2019-02-07"), glist, galist,
				bp, f);

		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);

		Community community = comServ.addCommunityWithoutDto(newCommunity);

		assertEquals(12, community.getCommunityId());
		assertEquals("Dogs", community.getCommunityDescription());
		assertEquals(400, community.getTotalMembers());
		assertEquals(fw, community.getImage());
		assertEquals(LocalDate.parse("2019-02-07"), community.getCreatedOn());
		assertEquals(glist, community.getPostRulesAllowed());
		assertEquals(galist, community.getPostRulesDisAllowed());
		assertEquals(bp, community.getBanningPolicy());
		assertEquals(f, community.getFlairs());
	}

	@Test
	void updateCommunityTest() {
		File fw = new File("abc.jpg");

		List<String> glist = new ArrayList<String>();
		glist.add("Adults");
		glist.add("Kids");
		glist.add("Teenage");

		List<String> galist = new ArrayList<String>();
		galist.add("Buildings");
		galist.add("Furniture");
		galist.add("Houses");

		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");

		List<String> f = new ArrayList<String>();
		f.add("Relationship");

		// Creating CommunityInputDto Object
		CommunityInputDto com = new CommunityInputDto(2, "Humans", 430, 230, fw, LocalDate.parse("2014-09-13"), glist,
				galist, bp, f);

		// Setting the inputDto values to community
		Community newCommunity = new Community();
		newCommunity.setCommunityId(com.getCommunityId());
		newCommunity.setCommunityDescription(com.getCommunityDescription());
		newCommunity.setTotalMembers(com.getTotalMembers());
		newCommunity.setOnlineMembers(com.getOnlineMembers());
		newCommunity.setImage(com.getImage());
		newCommunity.setCreatedOn(com.getCreatedOn());
		newCommunity.setPostRulesAllowed(com.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(com.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(com.getBanningPolicy());
		newCommunity.setFlairs(com.getFlairs());

		Mockito.when(comRepo.findById(2)).thenReturn(Optional.of(newCommunity));
		Mockito.when(comRepo.save(newCommunity)).thenReturn(newCommunity);

		// Calling update Function
		Community community = comServ.updateCommunityWithoutDto(newCommunity);

		// Comparing the values
		assertEquals(2, community.getCommunityId());
		assertEquals("Humans", community.getCommunityDescription());
		assertEquals(430, community.getTotalMembers());
		assertEquals(fw, community.getImage());
		assertEquals(LocalDate.parse("2014-09-13"), community.getCreatedOn());
		assertEquals(glist, community.getPostRulesAllowed());
		assertEquals(galist, community.getPostRulesDisAllowed());
		assertEquals(bp, community.getBanningPolicy());
		assertEquals(f, community.getFlairs());
	}

	@Test
	void listAllCommunitiesTest() {
		File fw = new File("abc.jpg");

		List<String> glist = new ArrayList<String>();
		glist.add("Adults");
		glist.add("Kids");
		glist.add("Teenage");

		List<String> galist = new ArrayList<String>();
		galist.add("Buildings");
		galist.add("Furniture");
		galist.add("Houses");

		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");

		List<String> f = new ArrayList<String>();
		f.add("Relationship");

		Community com1 = new Community(12, "Science", 430, 230, fw, LocalDate.parse("2014-09-13"), glist, galist, bp,
				f);

		Community com2 = new Community(13, "Humans", 430, 230, fw, LocalDate.parse("2014-09-13"), glist, galist, bp, f);

		Mockito.when(comRepo.save(com1)).thenReturn(com1);
		Community c1 = comServ.addCommunityWithoutDto(com1);

		Mockito.when(comRepo.save(com2)).thenReturn(com2);
		Community c2 = comServ.addCommunityWithoutDto(com2);

		List<Community> clist = new ArrayList<>();
		clist.add(c1);
		clist.add(c2);

		Mockito.when(comRepo.findAll()).thenReturn(clist);
		List<Community> comlist = comServ.listAllCommunities();
		assertEquals(2, comlist.size());
	}

	@Test
	void listAllCommunitiesByDescriptionTest() {

		File fw = new File("abc.jpg");

		List<String> glist = new ArrayList<String>();
		glist.add("Adults");
		glist.add("Kids");
		glist.add("Teenage");

		List<String> galist = new ArrayList<String>();
		galist.add("Buildings");
		galist.add("Furniture");
		galist.add("Houses");

		List<String> bp = new ArrayList<String>();
		bp.add("Cheating");
		bp.add("Drugs");
		bp.add("Misuse");

		List<String> f = new ArrayList<String>();
		f.add("Relationship");

		Community com1 = new Community(12, "Science", 430, 230, fw, LocalDate.parse("2014-09-13"), glist, galist, bp,
				f);

		Community com2 = new Community(13, "Humans", 430, 230, fw, LocalDate.parse("2014-09-13"), glist, galist, bp, f);

		Mockito.when(comRepo.save(com1)).thenReturn(com1);
		Community c1 = comServ.addCommunityWithoutDto(com1);

		Mockito.when(comRepo.save(com2)).thenReturn(com2);
		Community c2 = comServ.addCommunityWithoutDto(com2);

		List<Community> list = new ArrayList<>();

		List<Community> clist = new ArrayList<>();
		clist.add(c1);
		clist.add(c2);

		for (Community c : clist) {
			if (c.getCommunityDescription().equals("Science")) {
				list.add(c);
			}
		}

		Mockito.when(comRepo.listAllCommunitiesByDescription("%Science%")).thenReturn(list);
		List<Community> comlist = comServ.listAllCommunitiesByDescription("Science");
		int noOfCommunities = comlist.size();
		assertEquals(1, noOfCommunities);
	}

}
