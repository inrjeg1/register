package com.helloalmere.register.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.helloalmere.register.model.Profile;
import com.helloalmere.register.service.ProfileRepository;

@RestController
@RequestMapping("/api/profiles")
public class ProfileRegisterController {

	@Autowired
	private ProfileRepository repository;

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Profile>> getAllProfiles() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createRule(@Valid @RequestBody Profile profile) {

		Profile savedProfile = repository.save(profile);
		return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);

	}

}
