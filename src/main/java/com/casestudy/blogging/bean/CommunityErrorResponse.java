package com.casestudy.blogging.bean;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class CommunityErrorResponse {
	private int status;
	private String message;
	private LocalDateTime timeStamp;
}
