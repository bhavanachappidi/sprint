package com.casestudy.blogging.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityInputDto {
	private int communityId;
	private String communityDescription;
	private int totalMembers;
	private int onlineMembers;
	private File image;
	private LocalDate createdOn;
	private List<String> postRulesAllowed;
	private List<String> postRulesDisAllowed;
	private List<String> banningPolicy;
	private List<String> flairs;
}
