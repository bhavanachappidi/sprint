package com.casestudy.blogging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.blogging.bean.Community;
import com.casestudy.blogging.dto.CommunityOutputDto;


@Repository
public interface ICommunityRepository extends JpaRepository<Community, Integer> {
	
	//JPQL Query to get all communities which are having same community description
	@Query(value="select * from community where community_description LIKE :searchString",nativeQuery=true)
	public List<Community> listAllCommunitiesByDescription(@Param("searchString") String searchString);
	
}
