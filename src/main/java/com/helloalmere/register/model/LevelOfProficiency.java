package com.helloalmere.register.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="levelofproficiency")
public class LevelOfProficiency {
	
	@Id
	@GeneratedValue
	private Long id ;
	
	private Long profileId;
	
	private String name;

}
