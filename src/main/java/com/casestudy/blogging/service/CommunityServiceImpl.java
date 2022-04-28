package com.casestudy.blogging.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.blogging.bean.Community;
import com.casestudy.blogging.dto.CommunityInputDto;
import com.casestudy.blogging.exception.CommunityNotFoundException;
import com.casestudy.blogging.repository.ICommunityRepository;

@Service
public class CommunityServiceImpl implements ICommunityService {

	@Autowired
	ICommunityRepository comRepo;

	@Override
	public Community addCommunity(CommunityInputDto community) {

		// Create community object
		Community newCommunity = new Community();
		
		//Set values to Community object
		newCommunity.setCommunityDescription(community.getCommunityDescription());
		newCommunity.setTotalMembers(community.getTotalMembers());
		newCommunity.setOnlineMembers(community.getOnlineMembers());
		newCommunity.setImage(community.getImage());
		newCommunity.setCreatedOn(community.getCreatedOn());
		newCommunity.setPostRulesAllowed(community.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(community.getBanningPolicy());
		newCommunity.setFlairs(community.getFlairs());
		
		// Saving Community Object to Repository
		Community com = comRepo.save(newCommunity);
		return com;
	}

	@Override
	public Community updateCommunity(CommunityInputDto community) {
		// Check whether community is available in DB or not by using Id
		Optional<Community> opt = comRepo.findById(community.getCommunityId());
		if (!opt.isPresent()) {
			throw new CommunityNotFoundException("Community not found with the given id:" + community.getCommunityId());
		}
		//Get the community from database
		Community newCommunity = opt.get();
		
		//Update with new values
		newCommunity.setCommunityDescription(community.getCommunityDescription());
		newCommunity.setTotalMembers(community.getTotalMembers());
		newCommunity.setOnlineMembers(community.getOnlineMembers());
		newCommunity.setImage(community.getImage());
		newCommunity.setCreatedOn(community.getCreatedOn());
		newCommunity.setPostRulesAllowed(community.getPostRulesAllowed());
		newCommunity.setPostRulesDisAllowed(community.getPostRulesDisAllowed());
		newCommunity.setBanningPolicy(community.getBanningPolicy());
		newCommunity.setFlairs(community.getFlairs());
		
		// Save Updated Community to repository
		Community com = comRepo.save(newCommunity);
		return com;
	}

	@Override
	public void deleteCommunity(int comId) {
		// Check whether community is available in DB or not by using Id
		Optional<Community> opt = comRepo.findById(comId);
		
		if (!opt.isPresent()) {
			throw new CommunityNotFoundException("Community not found with the given id:" + comId);
		}
		
		// Delete Community
		Community deletedCommunity = opt.get();
		comRepo.delete(deletedCommunity);
	}

	@Override
	public List<Community> listAllCommunities() {
		List<Community> comList= comRepo.findAll();
		
		if(comList.isEmpty()) {
			throw new CommunityNotFoundException("No community is found");
		}
				
		return comList;
	}

	@Override
	public List<Community> listAllCommunitiesByDescription(String communityDescription) {
		String description = '%' + communityDescription + '%';
		// Creating a list of PostOutputDto
		List<Community> allCommunities = comRepo.listAllCommunitiesByDescription(description);
				
		if(allCommunities.isEmpty()) {
			throw new CommunityNotFoundException("No community with description: " + communityDescription);
		}
		return allCommunities;
	}


	@Override
	public long count() {
		return comRepo.count();
	}

	@Override
	public Community addCommunityWithoutDto(Community community) {

		return comRepo.save(community);
	}

	@Override
	public Community updateCommunityWithoutDto(Community community) {
		Optional<Community> opt = comRepo.findById(community.getCommunityId());
		if (!opt.isPresent()) {
			throw new CommunityNotFoundException("Community not found with the given id:" + community.getCommunityId());
		}
		// Save Updated Community to repository
		Community com = comRepo.save(community);
		return com;
	}

}
