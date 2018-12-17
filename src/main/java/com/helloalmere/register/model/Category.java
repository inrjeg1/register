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
@Table(name = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "single", "doubles", "mixed_doubles" })
public class Category {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="single")
	@JsonProperty("single")
	private Boolean single;
	
	@Column(name="doubles")
	@JsonProperty("doubles")
	private Boolean doubles;
	
	@Column(name="mixed_doubles")
	@JsonProperty("mixed_doubles")
	private Boolean mixedDoubles;
	
	/** The created at. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    private Date createdAt = new Date();
    
    @JsonIgnore
    @OneToOne(mappedBy="category")
    private Profile profile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("single")
	public Boolean getSingle() {
		return single;
	}

	@JsonProperty("single")
	public void setSingle(Boolean single) {
		this.single = single;
	}

	@JsonProperty("doubles")
	public Boolean getDoubles() {
		return doubles;
	}

	@JsonProperty("doubles")
	public void setDoubles(Boolean doubles) {
		this.doubles = doubles;
	}

	@JsonProperty("mixed_doubles")
	public Boolean getMixedDoubles() {
		return mixedDoubles;
	}

	@JsonProperty("mixed_doubles")
	public void setMixedDoubles(Boolean mixedDoubles) {
		this.mixedDoubles = mixedDoubles;
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