package com.helloalmere.register.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="proficiency")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "beginner", "intermediate", "advanced" })
public class Proficiency {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="beginner")
	@JsonProperty("beginner")
	private Boolean beginner;
	
	@Column(name="intermediate")
	@JsonProperty("intermediate")
	private Boolean intermediate;
	
	@Column(name="advanced")
	@JsonProperty("advanced")
	private Boolean advanced;
	
	/** The created at. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    private Date createdAt = new Date();
    
    @JsonIgnore
    @OneToOne(mappedBy="proficiency")
    private Profile profile;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("beginner")
	public Boolean getBeginner() {
		return beginner;
	}

	@JsonProperty("beginner")
	public void setBeginner(Boolean beginner) {
		this.beginner = beginner;
	}

	@JsonProperty("intermediate")
	public Boolean getIntermediate() {
		return intermediate;
	}

	@JsonProperty("intermediate")
	public void setIntermediate(Boolean intermediate) {
		this.intermediate = intermediate;
	}

	@JsonProperty("advanced")
	public Boolean getAdvanced() {
		return advanced;
	}

	@JsonProperty("advanced")
	public void setAdvanced(Boolean advanced) {
		this.advanced = advanced;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
     * On create.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();        
    }

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
    
    

}