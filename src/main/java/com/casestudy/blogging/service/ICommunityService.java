package com.casestudy.blogging.service;

import java.util.List;

import com.casestudy.blogging.bean.Community;
import com.casestudy.blogging.dto.CommunityInputDto;

public interface ICommunityService {
	public Community addCommunity(CommunityInputDto community);
	public Community updateCommunity(CommunityInputDto community);
	public void deleteCommunity(int comId);
	public List<Community> listAllCommunities();
	public List<Community> listAllCommunitiesByDescription(String searchString);
	public long count();
	Community addCommunityWithoutDto(Community community);
	Community updateCommunityWithoutDto(Community community);
	
}
