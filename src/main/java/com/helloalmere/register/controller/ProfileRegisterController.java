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
import com.helloalmere.register.service.MailService;
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
		
		if (!isAlreadyRegisteredCheck(profile)) {
				
		Profile savedProfile = repository.save(profile);
		MailService.sendMail(savedProfile);
		return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
		
		}else {
			return new ResponseEntity<>(profile,HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	private boolean isAlreadyRegisteredCheck(Profile profile) {

		List<Profile> registredProfiles = repository.findAll();

		for (Profile p : registredProfiles) {
			if (p.getFirstName().equalsIgnoreCase(profile.getFirstName())
					&& p.getEmail().equalsIgnoreCase(profile.getEmail())) {
				return true;
			}
		}

		return false;
	}

}
