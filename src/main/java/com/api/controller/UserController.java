package com.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ObjectMapper mapper;

	@PostMapping
	public ResponseEntity<?> addUserInformation(@RequestParam("image") MultipartFile file,
			@RequestParam("userData") String userData) {
		this.logger.info("Add User Request");
		logger.info("File Name : {} ", file.getOriginalFilename());
		logger.info("user : {} ",userData );
		
		User user=null;
		try {
			user = mapper.readValue(userData,User.class);
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
		}
		
		logger.info("user Data is : {} ",user );
		return ResponseEntity.ok(user);
	}
}
