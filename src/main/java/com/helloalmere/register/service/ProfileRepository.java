package com.helloalmere.register.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.helloalmere.register.model.Profile;

@RepositoryRestResource
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
