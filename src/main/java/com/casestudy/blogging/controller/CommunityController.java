package com.casestudy.blogging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.blogging.bean.Community;
import com.casestudy.blogging.dto.CommunityInputDto;
import com.casestudy.blogging.service.ICommunityService;

@RestController
public class CommunityController {
	@Autowired   //Creates object to the mentioned class
	ICommunityService comServ;
	
	//Adding Community
	@PostMapping("/communities")
	ResponseEntity<Community> addCommunity(@RequestBody CommunityInputDto com)
	{
		//Calling communityService addCommunity method to add the community to database
		Community newCom = comServ.addCommunity(com);
		return new ResponseEntity<>(newCom,HttpStatus.CREATED);
	}
	
	//Updating Community
	@PutMapping("/communities")
	ResponseEntity<Community> updateCommunity(@RequestBody CommunityInputDto com)
	{
		//Calling communityService updateCommunity method to update the community to database
		Community upCom = comServ.updateCommunity(com);
		return new ResponseEntity<>(upCom,HttpStatus.OK);
	}
	
	//Deleting community By using CommunityId
	@DeleteMapping("/communities/ByCommunityId/{comId}")
	ResponseEntity<String> deleteCommunity(@PathVariable("comId") int comId)
	{
		//Calling communityService deleteCommunity method to delete the community from database
		comServ.deleteCommunity(comId);
		return new ResponseEntity<>("Community with id " + comId + " is deleted", HttpStatus.OK);
	}
	
	// Get all communities
		@GetMapping("/communities")
		ResponseEntity<List<Community>> listAllCommunities()
		{
			//Calling communityService listAllCommunities method to list all communities from database by communityDescription
			List<Community> listCom = comServ.listAllCommunities();
			return new ResponseEntity<>(listCom,HttpStatus.OK);
		}
	
	//Get All communities list by using community description
	@GetMapping("/communities/{communityDescription}")
	ResponseEntity<List<Community>> listAllCommunitiesByDescription(@PathVariable("communityDescription") String communityDescription)
	{
		//Calling communityService listAllCommunities method to list all communities from database by communityDescription
		List<Community> listCom = comServ.listAllCommunitiesByDescription(communityDescription);
		return new ResponseEntity<>(listCom,HttpStatus.OK);
	}
	
}
